package com.varxyz.banking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.varxyz.banking.Service.CustomerService;
import com.varxyz.banking.domain.Customer;
import com.varxyz.banking.domain.CustomerCommand;

@Controller("banking.customerController")
public class CustomerController {
	
	@Autowired
	private CustomerService service;
	
	// 회원 가입 페이지로 이동
	@GetMapping("/banking/add_customer")
	public String addCustomer1() {
		return "customer/add_customer";
	}
	
	@PostMapping("/banking/add_customer")
	public String addCustomerForm(CustomerCommand customer, Model model) {
		// 받은정보 customer테이블에 insert한 후 가입 정보 띄우기

		System.out.println(customer);
		
		service.addCustomer(customer);
		
		model.addAttribute("userId", customer.getUserId());
		return "customer/add_customer_result";
	}
	
	// 아이디 중복체크
	@PostMapping("/banking/memberIdCheck")
	@ResponseBody
	public String memberIdCheck(String memberId) throws Exception {
	
		Customer checkResult = service.checkId(memberId);
		
		if (checkResult != null) {
			return "fail";
		} else {
			return "success";
		}
	}
	
	// 관리자용 ) 전체 고객 리스트(계좌번호까지) -> 조인필요한가
	@GetMapping("/banking/all_customer")
	public String allCustomer(Model model) {

		List<Customer> customerList = service.allCustomer();
		model.addAttribute("customerList", customerList);
		
		return "customer/all_customer";
	}
	

}
