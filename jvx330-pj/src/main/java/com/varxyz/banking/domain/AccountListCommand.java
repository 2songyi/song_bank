package com.varxyz.banking.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AccountListCommand {
	private String accountNum;
	private char accType;
	private double balance;
	private double interestRate;
	private double overAmount;
	private String name;
	
	public AccountListCommand() {
		
	}
	
	@Override
	public String toString() {
		return accountNum + "," + accType + "," + balance
				+ "," + interestRate + "," + overAmount + "," + name;
	}
	
	
}
