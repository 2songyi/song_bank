package com.varxyz.banking.Service;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.varxyz.banking.dao.AccountDao;
import com.varxyz.banking.domain.Account;
import com.varxyz.banking.domain.AccountListCommand;
import com.varxyz.banking.domain.CheckingAccount;
import com.varxyz.banking.domain.SavingAccount;
import com.varxyz.banking.domain.TransferHistory;

@Component
public class AccountService {
	private AccountDao accountDao;
	
	@Inject
	PasswordEncoder passwordEncoder;
	
	@Autowired
	public AccountService(DataSource dataSource) {
		accountDao = new AccountDao(dataSource);
	}
	
	// 계좌 생성
	public String addAccount(Account account) {
		
		String accountNum = generateAccountNum();
		
		if (account.getAccType() == 'S') {
			// 예금계좌
			SavingAccount saccount = new SavingAccount();
			
			saccount.setInterestRate(0.2);
			saccount.setAccountNum(accountNum);
			saccount.setBalance(account.getBalance());
			String encodePasswd = passwordEncoder.encode(account.getAccountPasswd());
			saccount.setAccountPasswd(encodePasswd);
			saccount.setCustomer(account.getCustomer());
			
			accountDao.addAccount(saccount);
			
		} else if (account.getAccType() == 'C') {
			// 입출금 계좌
			CheckingAccount caccount = new CheckingAccount();
			
			caccount.setOverdraftAmount(100000);
			caccount.setAccountNum(accountNum);
			caccount.setBalance(account.getBalance());
			String encodePasswd = passwordEncoder.encode(account.getAccountPasswd());
			caccount.setAccountPasswd(encodePasswd);
			caccount.setCustomer(account.getCustomer());
			
			accountDao.addAccount(caccount);
		}
		
		return accountNum;
	}
	
	// 아이디로 계좌목록 조회
	public List<AccountListCommand> getAccount(String userId) {
		return accountDao.getAccount(userId);
	}
	
	// 전체 계좌 조회
	public List<AccountListCommand> findAllAccount() {
		return accountDao.findAllAccount();
	}
	
	// 계좌 잔액 확인
	public int getBalance(String accountNum) {
		return accountDao.getBalance(accountNum);
	}
	
	// userId로 cid찾기
	public long findCidbyUserId(String userId) {
		return accountDao.findCidbyUserId(userId);
	}
	
	// 계좌 이체
	@Transactional  // 출금, 입금 중 하나만 실패여도 rollback, 둘 다 성공해야 commit  분리할 수 없는 하나의 단위로 본다.
	public void transfer(String outAccountNum, String inAccountNum, int money) throws Exception{
		int balance1 = getBalance(outAccountNum) - money; //출금계좌용
		int balance2 = getBalance(inAccountNum) + money; //입금계좌용
		
		withDraw(outAccountNum, balance1);
		deposit(inAccountNum, balance2);
		
		// outAccountNum계좌에서 money만큼 출금 withdraw
		// inAccountNum 계좌에 money만큼 입금 deposit
		// 잔액이 -가 되면 예외처리
		// 계좌이체 시 거래내역 추가하기
		addtransferHistory(outAccountNum, inAccountNum, money, balance1);
	}
	
	// 출금
	public void withDraw(String accountNum, int money) throws Exception {
		// 만약 잔액-출금액 < 0이 된다면 error발생
		int balance = getBalance(accountNum) - money;
		
		if (balance > 0) {
			accountDao.withDraw(accountNum, money);
		} else {
			throw new Exception("잔액이 부족합니다.");
		}
	}
	
	// 입금
	public void deposit(String accountNum, double money) {
		accountDao.deposit(accountNum, money);
	}
	
	// 계좌이체 시 비밀번호 확인
	public boolean checkPasswdForTransfer(String accountNum, String accountPasswd) {
		
		String encodePasswd = accountDao.checkAccountPasswd(accountNum);
		System.out.println("계좌이체확인용비번" + encodePasswd);
		
		if (passwordEncoder.matches(accountPasswd, encodePasswd)) {
			return true;
		} else {
			return false;
		}
	}
	
	// 계좌이체 내역 등록
	public void addtransferHistory(String outAccountNum, String inAccountNum, double money, double balance) {
		
		accountDao.addtransferHistory(outAccountNum, inAccountNum, money, balance);
	}
	
	// 계좌이체 거래내역 조회
	public List<TransferHistory> findAllTransferHistory() {
		return accountDao.findAllTransferHistory();
	}
	
	// 계좌별 거래내역 조회
	public List<TransferHistory> findTransferHistoryByAccountNum() {
		return null;
	}
	
	// 이자 입금
	// saveInterest(이자율, 계좌번호) -> 지급여부 확인
	public void saveInterest(double interestRate, String accountNum) {
		// 매월 말일 계산 -> 현재날짜가 말일이 아니면 error
		LocalDate today = LocalDate.now();
		int year = Integer.parseInt(String.valueOf(today.getYear()));
		int month = Integer.parseInt(String.format("%02d", today.getMonthValue()));
		
		String lastDay = String.valueOf(getLastDay(year, month));
		
		// 말일이 맞다면 해당계좌에 이자 입금
		double saveInterest = getBalance(accountNum) * interestRate;
		deposit(accountNum, saveInterest);
		
	}
	
	// 말일 구하기
	public int getLastDay(int year, int month) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month-1, 1);
		int lastDay = cal.getActualMaximum(Calendar.DATE);
		return lastDay;
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
		
		/* 
			if (존재하지 않는 계좌번호) {
				계좌번호변수에 등록
				return accountNum;
			} else if (중복 계좌번호) {
				return generateAccount(); 
				//재귀함수
			}
		*/
		

		return accountNum;
	}
	
	
}
