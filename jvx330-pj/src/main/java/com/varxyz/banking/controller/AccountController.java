package com.varxyz.banking.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.varxyz.banking.Service.AccountService;
import com.varxyz.banking.domain.Account;
import com.varxyz.banking.domain.AccountListCommand;
import com.varxyz.banking.domain.CheckingAccount;
import com.varxyz.banking.domain.Customer;
import com.varxyz.banking.domain.SavingAccount;
import com.varxyz.banking.domain.TransferHistory;

@Controller("banking.accountController")
public class AccountController {

	@Autowired
	private AccountService service;
	
	// 계좌 생성
	@GetMapping("banking/add_account")
	public String addAccount(HttpSession session, Model model) {
		// 고객코드(cid)는 고객이 입력하지 않고 자동 입력되어야 한다. -> userId로 cid 찾기
		String userId = (String)session.getAttribute("userId");
		model.addAttribute(userId);
		
		
		long cid = service.findCidbyUserId(userId);
		model.addAttribute("cid", cid);
		System.out.println(cid);
		System.out.println("계좌 개설 페이지");
		return "account/add_account";
	}

	@PostMapping("banking/add_account")
	public String addAccountForm(@RequestParam long cid, @RequestParam String accType, 
			@RequestParam double balance, @RequestParam String accountPasswd,
			Model model, HttpSession session) {
		String userId = (String)session.getAttribute("userId");
		model.addAttribute(userId);
		
		String accountNum;
		
		// service로 내용 옮기기(계좌 생성기)
		if (accType.equals("S")) {
			accType = "예금 계좌";
			accountNum = generateAccountNum(); // 계좌번호생성기로 랜덤계좌번호 생성

			SavingAccount account = new SavingAccount();
			account.setCustomer(new Customer(cid));
			account.setAccountNum(accountNum);
			account.setAccType('S');
			account.setBalance(balance);
			account.setAccountPasswd(accountPasswd);
			account.setInterestRate(0.2);

			service.addAccount(account);
		} else {
			accType = "입출금 계좌";
			accountNum = generateAccountNum();

			CheckingAccount account = new CheckingAccount();
			account.setCustomer(new Customer(cid));
			account.setAccountNum(accountNum);
			account.setAccType('C');
			account.setBalance(balance);
			account.setAccountPasswd(accountPasswd);
			account.setOverdraftAmount(100000);

			service.addAccount(account);
		}

		model.addAttribute("userId", cid);
		model.addAttribute("accType", accType);
		model.addAttribute("accountNum", accountNum);
		model.addAttribute("balance", balance);
		model.addAttribute("accountPasswd", accountPasswd);

		return "account/add_account_success";
	}

	// 계좌 목록 확인
	@GetMapping("banking/find_account")
	public String findAccount(Model model, HttpSession session) {
		
		String userId = (String)session.getAttribute("userId");
		model.addAttribute(userId);
		
		//유저 아이디로 account정보 받기
		List<AccountListCommand> accountList = service.getAccount(userId);
		
		System.out.println("accountList" + accountList);
		
		model.addAttribute("accountList", accountList);
		
		return "account/find_account";
	}


	// 계좌번호로 잔액조회
	@GetMapping("banking/get_balance")
	public String getBalance(HttpSession session, Model model) {
		// 잔액조회 페이지에 들어가면 session에 저장해둔userId로 계좌번호를 미리 받아야한다.
		String userId = (String)session.getAttribute("userId");
		model.addAttribute("userId", userId);
		
		// userId로 계좌조회 한 거에서 split[0]해서 계좌번호만 뽑고 select박스로 만들기 -> 세션으로 받기 (바로 적용 안돼서 세션안씀)
//		List<AccountListCommand> accountList = (List<AccountListCommand>) session.getAttribute("accountList");
//		model.addAttribute("accountList", accountList);
		// session으로 하면 안될듯
		

		List<AccountListCommand> accountList = service.getAccount(userId);
		model.addAttribute("accountList", accountList);
		
		return "account/get_balance";
	}
	
	@PostMapping("banking/get_balance")
	public String getBalanceResult(@RequestParam String accountNum, Model model, HttpSession session) {
		String userId = (String)session.getAttribute("userId");
		model.addAttribute(userId);
		
		System.out.println(accountNum);
		
		double balance = service.getBalance(accountNum);
		
		model.addAttribute("accountNum", accountNum + " 계좌의 현재 잔액은");
		model.addAttribute("balance", balance + "원 입니다.");
		System.out.println(balance);
		return "account/get_balance";
	}

	
	// 계좌이체
	@GetMapping("banking/transfer")
	public String transfer(HttpSession session, Model model) {
		String userId = (String)session.getAttribute("userId");
		
		model.addAttribute("userId", userId);
		
		// session에 저장해둔 userId로 계좌번호 찾아서 select박스로 만들기
		List<AccountListCommand> accountList = service.getAccount(userId);
		model.addAttribute("accountList", accountList);
		
		// 전체 계좌목록 찾아서 list로 받는사람 select박스로 만들기
		List<AccountListCommand> allAccountList = service.findAllAccount();
		model.addAttribute("allAccountList", allAccountList);
		
		return "account/transfer";
	}
	
	@PostMapping("banking/transfer")
	public String transferDo(@RequestParam String outAccountNum, @RequestParam String inAccountNum, 
			@RequestParam double money, @RequestParam String accountPasswd, Model model, HttpSession session) {
		
		
		// outAccountNum, accountPasswd로 계좌 소유주 조회 -> 맞으면 진행
		Account checkPasswd = service.checkPasswdForTransfer(outAccountNum, accountPasswd);
		
		if (checkPasswd == null) {
			model.addAttribute("error_msg", "비밀번호가 일치하지 않습니다.");
			return "account/transfer";
		}
		
		service.transfer(outAccountNum, inAccountNum, money);
		double balance = service.getBalance(outAccountNum); 
		
		model.addAttribute("outAccountNum", outAccountNum);
		model.addAttribute("inAccountNum", inAccountNum);
		model.addAttribute("money", money);
		model.addAttribute("balance", balance); // 이체 결과 잔액
		model.addAttribute("userId", session.getAttribute("userId"));
		return "account/transfer_success";
	}
	
	// 거래내역 조회하기
	@PostMapping("banking/find_transfer_history")
	public String transcationDesc(@RequestParam String accountNum, Model model, HttpSession session) {
		String userId = (String)session.getAttribute("userId");
		model.addAttribute("userId", userId);
		
		//accountNum으로 거래내역 조회해서 거래내역 리스트 받아오고 테이블출력
		//@RequestParam String accountNum -> 받아와서 계좌별 리스트 뽑기기능 추가
		// 현재는 전체 내역 조회만 구현했음
		List<TransferHistory> transferList = service.findAllTransferHistory();
		model.addAttribute("transferList", transferList);
		return "account/find_transfer_history";
	}
	
	// 관리자용 ) 전체 계좌정보 가져오기
	@GetMapping("banking/all_account")
	public String allAccount(HttpSession session, Model model) {
		String userId = (String)session.getAttribute("userId");
		model.addAttribute("userId", userId);
		
		
		return null;
	}
	


	// 계좌랜덤생성기
	public String generateAccountNum() {
		String accountNum = "";

		for (int i = 0; i < 3; i++) {
			accountNum += (int) (Math.random() * 10);
		}
		accountNum += "-";
		for (int i = 0; i < 2; i++) {
			accountNum += (int) (Math.random() * 10);
		}
		accountNum += "-";
		for (int i = 0; i < 4; i++) {
			accountNum += (int) (Math.random() * 10);
		}

		return accountNum;
	}
	
	
	
	


}
