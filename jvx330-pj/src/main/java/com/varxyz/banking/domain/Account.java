package com.varxyz.banking.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Account {
	private int aid;
	private Customer customer;
	private String accountNum;
	private char accType;
	private double balance;
	private String accountPasswd; //4자
	private Date regDate;
	
	public Account() {
		
	}


}
