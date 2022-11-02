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
		
		// 패스워드 디코딩해서 비교하기
		//pw = 현재입력된 비밀번호체크
		//encodepw = 입력된 유저의 비밀번호
		// if (passwordEncoder.matches(encodepw, pw))
		// 비밀번호 일치, 입력된 유저의 비밀번호를 인코딩된걸로 
		
		// 일치하면 true를 반환, 아니면 false를 반환한다.
		// authcontroller에도 true, false로 받아서 구분해야한다.

		String encodePW = getEncodePW(userId);
		
		if (passwordEncoder.matches(encodePW, passwd)) {
			return true;
		} else {
			return false;
		}
		
	}
	
	// 암호화된 비밀번호 찾기
	public String getEncodePW(String userId) {
		return customerDao.getEncodePW(userId);
	}
	
	// 아이디로 회원 찾기 (아이디 중복체크)
	public Customer checkId(String userId) {
		return customerDao.checkId(userId);
	}
	
	public List<Customer> allCustomer() {
		return customerDao.allCustomer();
	}
	
}
