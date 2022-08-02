package com.varxyz.banking.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.varxyz.banking.domain.AccountListCommand;

public class AccountListRowMapper implements RowMapper<AccountListCommand> {

	@Override
	public AccountListCommand mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		AccountListCommand account = new AccountListCommand();
		account.setAccountNum(rs.getString("accountNum"));
		account.setAccType(rs.getString("accType").charAt(0));
		account.setBalance(rs.getDouble("balance"));
		account.setInterestRate(rs.getDouble("interestRate"));
		account.setOverAmount(rs.getDouble("overAmount"));
		account.setName(rs.getString("name"));
		
		return account;
	}
	
	
}
