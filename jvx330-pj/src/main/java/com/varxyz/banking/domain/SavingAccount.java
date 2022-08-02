package com.varxyz.banking.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SavingAccount extends Account {
	private double interestRate;
	
	public SavingAccount() {
		
	}
	public void withdraw(double amount) {
		
	}
}
