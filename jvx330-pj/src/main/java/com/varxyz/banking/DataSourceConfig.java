package com.varxyz.banking;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import com.varxyz.banking.Service.AccountService;
import com.varxyz.banking.Service.CustomerService;
import com.varxyz.banking.dao.AccountDao;
import com.varxyz.banking.dao.CustomerDao;

@Configuration
//@ComponentScan(basePackages="com.varxyz.banking")
public class DataSourceConfig {
	
	@Bean(destroyMethod = "close")
	public DataSource dataSource() {
		DataSource ds = new DataSource();
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/jvx330?serverTimezone=Asia/Seoul");
		ds.setUsername("jvx330");
		ds.setPassword("jvx330");
		ds.setInitialSize(2); 	// 커넥션 풀 초기화시 생성할 초기 커넥션 개수(default = 10)
		ds.setMaxActive(10);  	// 풀에서 가져올 수 있는 최대 커넥션 개수(default = 100)
		ds.setMaxIdle(10); 		// 풀에 유지할 수 있는 최대 커넥션 수(default = maxActive)
		return ds;
	}
	
	@Bean
	public JdbcTemplate jdbcTemplate() { //스프링에서 제공하는 jdbc 템플릿
		return new JdbcTemplate(dataSource()); // 계속 호출해도 싱글톤이라 동일한 템플릿 사용하게됨
	}
	
	
	@Bean
	public AccountDao accountDao() {
		return new AccountDao(dataSource());
	}
	
	@Bean
	public CustomerDao customerDao() {
		return new CustomerDao(dataSource());
	}
	
	@Bean
	public AccountService accountService() {
		return new AccountService(dataSource());
	}
	
	@Bean
	public CustomerService customerService() {
		return new CustomerService(dataSource());
	}
	
	
}
