package com.revature.project0.services;
import java.util.List;
import com.revature.project0.models.Account;
import com.revature.project0.repos.AccountDAO;
import com.revature.project0.repos.AccountDAOImpl;


public class AccountService {


		private static AccountDAO accountDao = new AccountDAOImpl();
		
		public List<Account> getAllAccounts() {
			return accountDao.findAll();
		}
		
		public Account getAccount(int accountID) {
			return accountDao.findAccount(accountID);
		}
		
		public boolean addAccount(Account account) {
			return accountDao.addAccount(account);
		}
		public boolean editAccountBalance(int accountID, double ammount){
			return accountDao.editAccountBalance(accountID, ammount);
		}
		public boolean transfer(int senderID, int receiverID, double amount){
			return accountDao.transfer(senderID, receiverID, amount);
		}
		public boolean deleteAccount(int accountID){
		return accountDao.deleteAccount(accountID);	
		}
}
