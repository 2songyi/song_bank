package com.varxyz.banking.Service;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.varxyz.banking.dao.AccountDao;
import com.varxyz.banking.domain.Account;
import com.varxyz.banking.domain.AccountListCommand;
import com.varxyz.banking.domain.TransferHistory;

@Component
public class AccountService implements AccountServiceInt {
	private AccountDao accountDao;
	
	@Autowired
	public AccountService(DataSource dataSource) {
		accountDao = new AccountDao(dataSource);
	}
	
	// 계좌 생성
	@Override
	public void addAccount(Account account) {
		// controller에 있는 내용 service로 옮기기, 랜덤계좌 생성기도 service로 옮기기
		accountDao.addAccount(account);
	}
	
	// 아이디로 계좌목록 조회
	@Override
	public List<AccountListCommand> getAccount(String userId) {
		return accountDao.getAccount(userId);
	}
	// 전체 계좌 조회
	@Override
	public List<AccountListCommand> findAllAccount() {
		return accountDao.findAllAccount();
	}
	
	// 계좌 잔액 확인
	@Override
	public double getBalance(String accountNum) {
		return accountDao.getBalance(accountNum);
	}
	
	// userId로 cid찾기
	@Override
	public long findCidbyUserId(String userId) {
		return accountDao.findCidbyUserId(userId);
	}
	
	// 계좌 이체
	@Transactional  // 출금, 입금 중 하나만 실패여도 rollback, 둘 다 성공해야 commit  분리할 수 없는 하나의 단위로 본다.
	@Override
	public void transfer(String outAccountNum, String inAccountNum, double money) {
		double balance1 = getBalance(outAccountNum) - money; //출금계좌용
		double balance2 = getBalance(inAccountNum) + money; //입금계좌용
		
		withDraw(outAccountNum, balance1);
		deposit(inAccountNum, balance2);
		
		// outAccountNum계좌에서 money만큼 출금 withdraw
		// inAccountNum 계좌에 money만큼 입금 deposit
		
		// 계좌이체 시 거래내역 추가하기
		addtransferHistory(outAccountNum, inAccountNum, money, balance1);
	}
	
	// 출금
	@Override
	public void withDraw(String accountNum, double money) {
		accountDao.withDraw(accountNum, money);
	}
	
	// 입금
	@Override
	public void deposit(String accountNum, double money) {
		accountDao.deposit(accountNum, money);
	}
	
	// 계좌이체 시 비밀번호 확인
	@Override
	public Account checkPasswdForTransfer(String accountNum, String accountPasswd) {
		// accountNum, passwd는 다른테이블에 있으니 조인이 필요할 듯
		// -> 계좌등록할 때 accountPasswd를 추가하는걸로 수정해서 조인 불필요
		return accountDao.checkPasswdForTransfer(accountNum, accountPasswd);
	}
	
	// 계좌이체 내역 등록
	@Override
	public void addtransferHistory(String outAccountNum, String inAccountNum, double money, double balance) {
		
		accountDao.addtransferHistory(outAccountNum, inAccountNum, money, balance);
	}
	
	// 계좌이제 거래내역 조회
	@Override
	public List<TransferHistory> findAllTransferHistory() {
		return accountDao.findAllTransferHistory();
	}
	
	// 이자 입금
	// saveInterest(이자율, 계좌번호) -> 지급여부 확인
	@Override
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
	@Override
	public int getLastDay(int year, int month) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month-1, 1);
		int lastDay = cal.getActualMaximum(Calendar.DATE);
		return lastDay;
	}

	@Override
	public String generateAccountNum() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
