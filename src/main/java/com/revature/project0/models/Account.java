package com.revature.project0.models;

public class Account {



		private int accountID;
		private double balance;

		
		
		public Account(int accountID, double balance) {
			super();
			this.accountID = accountID;
			this.balance = balance;

		}

		public Account() {
			super();
		}
		
		public int getAccountID(){
			return accountID;
		}
		
		public void setAccountID(int accountID){
			this.accountID = accountID;
		}
		public double getBalance() {
			return balance;
		}
		
		public void setBalance(double balance){
			this.balance = balance;
		}
		
}
