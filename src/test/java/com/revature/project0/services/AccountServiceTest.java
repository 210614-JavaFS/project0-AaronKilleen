package com.revature.project0.services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class AccountServiceTest {

	public static int accountID1 = 999999999;
	public static int accountID2 = 888888888;
	public static double ammount;
	private static AccountService accountService= new AccountService();



	@Test
	public void editAccountBalanceTest(int accountID, double ammount){
		System.out.println("Testing edit balance");
		boolean testReturn = accountService.editAccountBalance(accountID1, ammount);
		assertEquals(testReturn , true);
	}
	@Test
	public void transferTest(int senderID, int receiverID, double amount){
		System.out.println("Testing transfer");
		boolean testReturn = accountService.transfer(accountID1, receiverID, amount);
		assertEquals(testReturn , true);
	}
	@Test
	public void deleteAccountTest(){
		System.out.println("Testing delete Account");
		boolean testReturn = accountService.deleteAccount(accountID1);	
	assertEquals(testReturn , true);
	}
}
