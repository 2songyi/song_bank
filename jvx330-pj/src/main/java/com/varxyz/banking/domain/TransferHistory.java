package com.varxyz.banking.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TransferHistory {
	private String outAccountNum;
	private String inAccountNum;
	private double money;
	private double balance;
	private Date regDate;
	
	@Override
	public String toString() {
		return outAccountNum + "," + inAccountNum + "," + money + "," + balance + "," + regDate;
	}
	
	public TransferHistory() {
		
	}
	
	
}
