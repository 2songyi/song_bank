package com.varxyz.banking.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
public class Customer {

	private long cid;
	private String userId;
	private String passwd;
	private String name;
	private String ssn;
	private String phone;
	private Date regDate;
	
	public Customer() {
		
	}
	
	public Customer(long cid) {
		this.cid = cid;
	}
	
	public Customer(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return cid + "," + userId + "," + passwd + "," + name + "," + ssn
				+ "," + phone + "," + regDate;
	}
	
	
}
