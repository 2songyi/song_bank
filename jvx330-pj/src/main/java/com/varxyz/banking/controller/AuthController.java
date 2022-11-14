package com.varxyz.banking.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.varxyz.banking.DataSourceConfig;
import com.varxyz.banking.Service.AccountService;
import com.varxyz.banking.Service.CustomerService;
import com.varxyz.banking.domain.AccountListCommand;
import com.varxyz.banking.domain.Customer;

@Controller("banking.loginController")
public class AuthController {
		
	@Autowired
	private AccountService aservice;
	
	@Autowired
	private CustomerService cservice;
	
		@GetMapping("banking/login")
		public String startLogin() {
			return "auth/login";
		}
		
		// 로그인 페이지
		@PostMapping("banking/login")
		public String login(@RequestParam String userId, @RequestParam String passwd, Model model, HttpSession session) {
			
			List<AccountListCommand> accountList = aservice.getAccount(userId);
			session.setAttribute("accountList", accountList);
			
			//로그인 아이디 세션에 올리기
			session.setAttribute("userId", userId);
			
			// 암호화된걸로 확인
			if (cservice.checkUser(userId, passwd)) {
				// 일치하다면
				System.out.println("로그인확인");
				model.addAttribute("userId", userId);
				
				return "redirect:/banking/main";
			} else {
				model.addAttribute("msg", "아이디 혹은 비밀번호가 일치하지 않습니다.");
				model.addAttribute("retrun_mapping", "auth/login");
				return "msg_alert";
			}
			
		}
		

		@GetMapping("banking/main")
		public String gotoMain(HttpSession session, Model model) {
			String userId = (String)session.getAttribute("userId");
			model.addAttribute("userId", userId);
			
			// 세션정보가 없으면 login페이지로 이동
			if (session.getAttribute("userId") == null) {
				return "redirect:/banking/login";
			}
			
			// 메인페이지 들어오면 계좌리스트 카드로 띄우기
			List<AccountListCommand> accountList = aservice.getAccount(userId);
			model.addAttribute("accountList", accountList);
			System.out.println("main accountList : " + accountList);
			return "auth/main";
		}
		
		// 로그아웃
		@GetMapping("banking/logout")
		public String logout(HttpSession session) {
			// 로그아웃시 세션 제거
			session.invalidate();
			return "auth/login";
		}
}
