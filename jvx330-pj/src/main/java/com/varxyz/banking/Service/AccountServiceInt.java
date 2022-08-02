package com.varxyz.banking.Service;

import java.util.List;

import com.varxyz.banking.domain.Account;
import com.varxyz.banking.domain.AccountListCommand;
import com.varxyz.banking.domain.TransferHistory;

public interface AccountServiceInt {
//accountService 인터페이스
	
	// 계좌 생성
	void addAccount(Account account);
	
	// 아이디로 계좌 목록 조회
	List<AccountListCommand> getAccount(String userId);
	
	// 전체 계좌 조회
	List<AccountListCommand> findAllAccount();
		
	// 계좌 잔액 확인
	double getBalance(String accountNum);
	
	// 아이디로 customerId찾기
	long findCidbyUserId(String userId);
	
	// 계좌 이체
	void transfer(String outAccountNum, String inAccountNum, double money);
	/*
	 * 예외처리 해야하는 경우
	 * - 송금하려는 금액이 잔액보다 큰 경우
	 * - 받는사람의 계좌가 존재하지 않는 계좌일 경우
	 * - 송금하는 도중 에러가 나서 중단되는 경우 출금과 입금이 취소되어야 한다.
	 * - 보내는 사람의 계좌와 계좌비밀번호가 일치하지 않는 경우
	 * */
	
	// 출금
	void withDraw(String accountNum, double money);
	
	// 입금
	void deposit(String accountNum, double money);
	
	// 계좌이체 시 비밀번호 체크
	Account checkPasswdForTransfer(String accountNum, String accountPasswd);
	
	// 계좌이체 내역 등록
	void addtransferHistory(String outAccountNum, String inAccountNum, double money, double balance);
	
	// 계좌이체 거래내역 조회
	List<TransferHistory> findAllTransferHistory();
	
	// 이자 입금
	void saveInterest(double interestRate, String accountNum);
	
	// 말일 이자입금을 위한 말일 구하기
	int getLastDay(int year, int month);
	
	// 계좌 랜덤 생성기
	String generateAccountNum();
	
	
	
}
