package com.varxyz.banking.Service;

import java.util.List;

import com.varxyz.banking.domain.Customer;
import com.varxyz.banking.domain.CustomerCommand;

public interface CustomerServiceInt {
	
	// 회원 가입
	void addCustomer(CustomerCommand customer);
	
	// 로그인시 유저 체크
	Customer checkUser(String userId, String passwd);
	
	// 아이디 중복체크
	Customer checkId(String userId);
	
	// 회원 목록
	List<Customer> allCustomer();
	
}
