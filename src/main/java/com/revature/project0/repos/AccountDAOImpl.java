package com.revature.project0.repos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.project0.models.Account;
import com.revature.project0.utils.ConnectionUtil;

public class AccountDAOImpl implements AccountDAO{
	
	public List<Account> findAll()
	{
		List<Account> list = new ArrayList<>();
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM accounts;";
			
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			
			//ResultSets have a cursor similarly to Scanners or other I/O classes. 
			while(result.next()) {
				Account account = new Account();

				account.setAccountID(result.getInt("account_id"));
				account.setBalance(result.getDouble("balance"));
				list.add(account);
			}
			
			ConnectionUtil.closeConnection(conn);
		return list;
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	public Account findAccount(int accountID)
	{
		Account account = new Account();
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM accounts WHERE account_name = '" + accountID + "';";
			
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
				

			
			//ResultSets have a cursor similarly to Scanners or other I/O classes. 
				while(result.next()) {
					account.setAccountID(result.getInt("account_id"));
					account.setBalance(result.getDouble("balance"));
				}
				ConnectionUtil.closeConnection(conn);
				return account;
		}catch (SQLException e) {
			e.printStackTrace();
		}
			return account;
	}
	
	public boolean addAccount(Account account)
	{
		try(Connection conn = ConnectionUtil.getConnection()){
			Statement statement = conn.createStatement();
			String sql = "INSERT INTO accounts (account_id, balance)\n"
					+ "VALUES (" + account.getAccountID() + ", " + account.getBalance() +  ");";
			
			System.out.println(sql);
			statement.execute(sql);
			ConnectionUtil.closeConnection(conn);
			return true;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return false;
	}
	
	
	
	public boolean editAccountBalance(int accountID, double ammount) {
		return true;
	}
}
