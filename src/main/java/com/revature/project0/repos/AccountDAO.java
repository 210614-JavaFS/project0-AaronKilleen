package com.revature.project0.repos;

import java.util.List;

import com.revature.project0.models.Account;

public interface AccountDAO {
	
	public List<Account> findAll();
	public Account findAccount(int accountID);
	public boolean addAccount(Account account);
	public boolean editAccountBalance(int accountID, double amount);
	public boolean transfer(int senderID, int receiverID, double ammunt);
	public boolean deleteAccount(int accountID);
}
