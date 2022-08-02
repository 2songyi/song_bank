package com.varxyz.banking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	// 회원가입 시 아이디 중복체크
	@GetMapping("/banking/check_id")
	public String checkId(@RequestParam String userId, Model model) {
		// 회원가입창에 아이디 입력버튼 -> input+button형태로 수정
		// 아이디 중복확인

		Customer checkResult = service.checkId(userId);
		
		// checkResult가 null이 아니면 중복된 아이디, null이면 회원가입 진행
		if (checkResult != null) {
			model.addAttribute("msg", "중복된 아이디입니다.");
			return "customer/add_customer";
		}
		
		model.addAttribute("msg", "사용가능한 아이디입니다.");
		model.addAttribute("userId", userId);
		return "customer/add_customer";
	}
	
	// 관리자용 ) 전체 고객 리스트(계좌번호까지) -> 조인필요한가
	@GetMapping("/banking/all_customer")
	public String allCustomer(Model model) {

		List<Customer> customerList = service.allCustomer();
		model.addAttribute("customerList", customerList);
		
		return "customer/all_customer";
	}
	
	
	
	
	
	

}
