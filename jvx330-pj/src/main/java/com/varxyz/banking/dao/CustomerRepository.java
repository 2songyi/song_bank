package com.varxyz.banking.dao;

import java.util.List;

import com.varxyz.banking.domain.Customer;
import com.varxyz.banking.domain.CustomerCommand;

public interface CustomerRepository {
// customerDao 인터페이스
	
	// 전체 고객 조회
	List<Customer> allCustomer();
	
	// 회원가입
	void addCustomer(CustomerCommand customer);
	
	// 로그인시 아이디, 비밀번호 확인
	Customer checkUser(String userId, String passwd);
	
	// 아이디 중복체크
	Customer checkId(String userId);
}
