package com.varxyz.banking.dao;

import java.util.List;

import com.varxyz.banking.domain.Account;
import com.varxyz.banking.domain.AccountListCommand;
import com.varxyz.banking.domain.TransferHistory;

public interface AccountRepository {
	// accountDao 인터페이스
	
	// 신규 계좌 등록
	void addAccount(Account account);
	
	// 회원 아이디로 자신의 계좌 찾기
	List<AccountListCommand> getAccount(String userId);
	
	// 회원 아이디로 customerId 찾기
	long findCidbyUserId(String userId);
	
	// 계좌 잔고 찾기
	double getBalance(String accountNum);
	
	// 출금
	void withDraw(String accountNum, double money);
	
	// 입금
	void deposit(String accountNum, double money);
	
	// 계좌번호, 비밀번호 일치하는지 확인
	Account checkPasswdForTransfer(String accountNum, String accountPasswd);
	
	// 계좌이체 내역 등록
	void addtransferHistory(String outAccountNum, String inAccountNum, double money, double balance);
	
	// 거래내역 조회
	List<TransferHistory> findAllTransferHistory();

	// 전체 계좌 찾기
	List<AccountListCommand> findAllAccount();
	
	
}
