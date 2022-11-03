package com.varxyz.banking.Service;


import java.util.List;

import javax.inject.Inject;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.varxyz.banking.dao.CustomerDao;
import com.varxyz.banking.domain.Customer;
import com.varxyz.banking.domain.CustomerCommand;

@Component
public class CustomerService {
	 private CustomerDao customerDao;
	 
	 @Inject
	 PasswordEncoder passwordEncoder;
	 
	 @Autowired 
	 public CustomerService(DataSource dataSource) { 
		 customerDao = new CustomerDao(dataSource); 
	 }
	 
	
	 // 고객 회원가입
	public void addCustomer(CustomerCommand customer) {
		// 암호화
		String encodePasswd = passwordEncoder.encode(customer.getPasswd());
		customer.setPasswd(encodePasswd);
		System.out.println("userId:" + customer.getUserId());
		System.out.println("encodePasswd:" + encodePasswd);
		// 디코딩해서 비교할때는
		// passwordEncoder.matches(암호화된거, 입력된비번) 사용하면 된다.
		customerDao.addCustomer(customer);
	}
	
	// 로그인시 유저 체크
	public boolean checkUser(String userId, String passwd) {
		
		String encodePW = checkId(userId).getPasswd();
		
		if (passwordEncoder.matches(passwd, encodePW)) {
			return true;
		} else {
			return false;
		}

	}
	
	// 아이디로 회원 찾기
	public Customer checkId(String userId) {
		return customerDao.checkId(userId);
	}
	
	public List<Customer> allCustomer() {
		return customerDao.allCustomer();
	}
	
}
