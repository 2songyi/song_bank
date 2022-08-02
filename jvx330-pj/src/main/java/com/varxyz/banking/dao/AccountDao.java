package com.varxyz.banking.dao;

import static java.sql.Types.BIGINT;
import static java.sql.Types.CHAR;
import static java.sql.Types.DOUBLE;

import java.util.List;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.varxyz.banking.domain.Account;
import com.varxyz.banking.domain.AccountListCommand;
import com.varxyz.banking.domain.CheckingAccount;
import com.varxyz.banking.domain.Customer;
import com.varxyz.banking.domain.SavingAccount;
import com.varxyz.banking.domain.TransferHistory;

@Repository
public class AccountDao implements AccountRepository {
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public AccountDao(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	// 신규 계좌 신청
	@Override
	public void addAccount(Account account) {
		String sql = "INSERT INTO Account(accountNum, accType, balance, accountPasswd, interestRate, overAmount, customerId) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?)";
		SavingAccount sa = null;
		CheckingAccount ca = null;
		Object[] args = null;
		int[] types = new int[] { CHAR, CHAR, DOUBLE, CHAR, DOUBLE, DOUBLE, BIGINT };

		if (account instanceof SavingAccount) {
			sa = (SavingAccount) account;
			
			args = new Object[] { 
					sa.getAccountNum(), String.valueOf(sa.getAccType()), 
					sa.getBalance(), sa.getAccountPasswd(), sa.getInterestRate(), 0.0, 
					sa.getCustomer().getCid() 
					};
		} else {
			ca = (CheckingAccount) account;
			args = new Object[] { 
					ca.getAccountNum(), String.valueOf(ca.getAccType()), 
					ca.getBalance(), ca.getAccountPasswd(), 0.0, ca.getOverdraftAmount(), 
					ca.getCustomer().getCid() };
		}

		jdbcTemplate.update(sql, args, types);
	}

	// 회원id로 자신의 계좌 정보를 확인
	@Override
	public List<AccountListCommand> getAccount(String userId) {
		String sql = "SELECT * FROM Account a INNER JOIN Customer c "
				+ " ON a.customerId = c.cid " + " WHERE c.userId = ?";

		return jdbcTemplate.query(sql, new AccountListRowMapper(), userId);
	}
	// 전체 계좌 조회
	@Override
	public List<AccountListCommand> findAllAccount() {
		String sql = "SELECT * FROM Account a INNER JOIN Customer c "
				+ " ON a.customerId = c.cid ";
		return jdbcTemplate.query(sql, new AccountListRowMapper());
	}
	
	
	// 회원 아이디로 cid 찾기
	@Override
	public long findCidbyUserId(String userId) {
		String sql = "SELECT cid FROM Customer WHERE userId = ?";
		
		return jdbcTemplate.queryForObject(sql, long.class, userId) ;
	}
	
	// 자신의 계좌 잔고를 확인할 수 있다.
	@Override
	public double getBalance(String accountNum) {
		String sql = "SELECT balance FROM Account WHERE accountNum = ?";
		
		Account account = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Account>(Account.class), accountNum);
		return account.getBalance();
	}
	
	// 출금
	@Override
	public void withDraw(String accountNum, double money) {
		String sql = "UPDATE Account SET balance = ? WHERE accountNum = ?";
		jdbcTemplate.update(sql, money, accountNum);
	}
	
	//입금
	@Override
	public void deposit(String accountNum, double money) {
		String sql = "UPDATE Account SET balance = ? WHERE accountNum = ?";
		jdbcTemplate.update(sql, money, accountNum);
	}
	
	// 계좌번호, 비밀번호 일치 확인
	@Override
	public Account checkPasswdForTransfer(String accountNum, String accountPasswd) {
		
		try {
			String sql = "SELECT * FROM Account WHERE accountNum = ? AND accountPasswd= ?";
			return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Account>(Account.class), accountNum, accountPasswd);	
			
		} catch (IncorrectResultSizeDataAccessException error) {
			return null;
		}
		
	}
	
	// 계좌이체 내역 등록
	@Override
	public void addtransferHistory(String outAccountNum, String inAccountNum, double money, double balance) {
		String sql = "INSERT INTO TRANSFER(outAccountNum, inAccountNum, money, balance) "
				+ "VALUES(?, ?, ?, ?)";
		jdbcTemplate.update(sql, outAccountNum, inAccountNum, money, balance);
		
	}
	
	// 거래내역 조회
	@Override
	public List<TransferHistory> findAllTransferHistory() {
		String sql = "SELECT * FROM TRANSFER";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<TransferHistory>(TransferHistory.class));
	}
	



}
