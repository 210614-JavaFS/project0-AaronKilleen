package com.revature.project0.controllers;

import java.util.InputMismatchException;

//import java.sql.Connection;
//import java.sql.SQLException;
//import java.sql.Statement;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;

import com.revature.project0.models.Account;
import com.revature.project0.models.User;
import com.revature.project0.services.AccountService;
import com.revature.project0.services.UserService;
//import com.revature.project0.utils.ConnectionUtil;


public class UserController {
	
	private static UserService userService = new UserService();
	private static AccountService accountService = new AccountService();
	private static Scanner scan = new Scanner(System.in);
	
	
	public boolean userLogin()
	{
		boolean goBack = false;
		boolean isDone = false;
		while(!goBack)
		{
		System.out.println("Enter your user name or type 0 to return to the previous menu:");
		String response = scan.nextLine();
		User user = userService.getUser(response);
		if(response.equals("0") )
		{
			goBack = true;
		}
		else if(user.getRegApproved() == false)
		{
			System.out.println("This account has not been approved by an employee yet and or it is a joint account whose co owner needs to approve it.");
		}
		else
		{

		if(user.getUserName() != null){
			boolean correctPassword = false;
			
			while(!correctPassword)
			{
			System.out.println("Enter Password:");
			response = scan.nextLine();
			if(response.equals(user.getPassword()))
			{
			System.out.println("Welcome " + user.getFirstName() + ".");
			correctPassword = true;
			int userType = 0;
			if(user.getIsEmployee() == true)
			{
				userType = 1;
			}
			if(user.getIsAdmin() == true)
			{
				userType = 2;
			}
			
			while(!isDone)
			{
				switch (userType)
				{

				case 1:
					isDone = employeeMenu();
				break;
				case 2:
					isDone = adminMenu();
				break;
				default:
					isDone = userMenu(user);
				}
			}
			goBack = true;
			}
			else{
				System.out.println("Incorrect Password.");
				}
			}
			
			
		}else {
			System.out.println("That is not a valid user name, try again.");
		}
		}
		}
		return true;
	}

	
	public boolean userMenu(User user)
	{

		boolean willLogout = false;
		boolean isDone = false;
		boolean gotLine = false;
		boolean willPrint = true;
		String response = "initial";
		while(!willLogout)
		{
		if(willPrint == true)
		{
		System.out.println("Please select an option: \n"
				+ "0) Logout. \n"
				+ "1) View User Information. \n"
				+ "2) Change information. \n"
				+ "3) View Balance. \n"
				+ "4) Make a Deposit. \n"
				+ "5) Make a Withdrawal.\n"
				+ "6) Make a Transfer. \n"
				+ "7) Approve Joint Account Request.\n");
		willPrint = false;
		}
		try
		{
		response = scan.nextLine();
		if(response.length() != 0)
		{
		gotLine = true;
		}
		}
		catch(NoSuchElementException e)
		{
			gotLine = false;
		}
		switch(response) {
			case "0":
				willLogout = true;
				break;
			case "1":
				while(!isDone){
				isDone = viewUserInformation(user.getUserName());
				}
				isDone = false;
				willPrint = true;
				break;
			case "2":
				while(!isDone){
				isDone = updateUser(user.getUserName());
				}
				isDone = false;
				willPrint = true;
				break;
			case "3":	
				while(!isDone){
				isDone = viewBalance(user.getAccountID());
				}
				isDone = false;
				willPrint = true;
				break;
			case "4":
				while(!isDone){
				isDone = makeDeposit(user.getAccountID());
				}
				isDone = false;
				willPrint = true;
				break;
			case "5":
				while(!isDone){
				isDone = makeWithdrawal(user.getAccountID());
				}
				isDone = false;
				willPrint = true;
				break;
			case "6":
				while(!isDone){
				isDone = makeTransfer(user.getAccountID());
				}
				isDone = false;
				willPrint = true;
				break;
			case "7":
				while(!isDone) {
				isDone = approveJointAccount(user.getAccountID());
				}
				isDone = false;
				willPrint = true;
				break;
	
			default:
				if((gotLine == true)&&(response != ""))
				{
				System.out.println("Invalid input, try again.");
				}
		}
		}
		return true;
	}
	
	public boolean employeeMenu()
	{

		boolean willLogout = false;
		boolean isDone = false;
		boolean gotLine = false;
		boolean willPrint = true;
		String response = "initial";
		while(!willLogout)
		{
		if(willPrint == true)
		{
		System.out.println("Please select an option: \n"
				+ "0) Logout. \n"
				+ "1) View User Information. \n"
				+ "2) Approve Accounts.");
		willPrint = false;
		}
		try
		{
		response = scan.nextLine();
		if(response.length() != 0)
		{
		gotLine = true;
		}
		}
		catch(NoSuchElementException e)
		{
			gotLine = false;
		}
		switch(response) {
			case "0":
				willLogout = true;
				break;
			case "1":
				while(!isDone){
					isDone = accessUserInformation();
				}
				isDone = false;
				willPrint = true;
				break;
			case "2":
				while(!isDone){
					isDone = approveAccounts();
				}
				isDone = false;
				willPrint = true;
				break;
			default:
				if((gotLine == true)&&(response != ""))
				{
				System.out.println("Invalid input, try again.");
				}
		}
		}
		return true;

	}
	
	public boolean adminMenu()
	{
		boolean willLogout = false;
		boolean isDone = false;
		boolean gotLine = false;
		boolean willPrint = true;
		String response = "initial";
		while(!willLogout)
		{
		if(willPrint == true)
		{
		System.out.println("Please select an option: \n"
				+ "0) Logout. \n"
				+ "1) View User Information. \n"
				+ "2) Approve Accounts. \n"
				+ "3) Make Deposit.\n"
				+ "4) Make Withdrawal.\n"
				+ "5) Make Transfer.\n"
				+ "6) Cancel Account."
				);
				
		willPrint = false;
		}
		try
		{
		response = scan.nextLine();
		if(response.length() != 0)
		{
		gotLine = true;
		}
		}
		catch(NoSuchElementException e)
		{
			gotLine = false;
		}
		switch(response) {
			case "0":
				willLogout = true;
				break;
			case "1":
				while(!isDone){
					isDone = accessUserInformation();
				}
				isDone = false;
				willPrint = true;
				break;
			case "2":
				while(!isDone){
					isDone = approveAccounts();
				}
				isDone = false;
				willPrint = true;
				break;
			case "3":
				while(!isDone){
					isDone = adminDeposit();
				}
				isDone = false;
				willPrint = true;
				break;
			case "4":
				while(!isDone){
					isDone = adminWithdrawal();
				}
				isDone = false;
				willPrint = true;
				break;
				
			case "5":
				while(!isDone){
					isDone = adminTransfer();
				}
				isDone = false;
				willPrint = true;
				break;
			case "6":
				while(!isDone){
					isDone = cancelAccount();
				}
				isDone = false;
				willPrint = true;
				break;
			default:
				if((gotLine == true)&&(response != ""))
				{
				System.out.println("Invalid input, try again.");
				}
		}
		}

		return true;
	}
	
	

	
	public boolean accessUserInformation()
	{
		
		User user = new User();
		boolean validResponse = false;
		boolean hasCancelled = false;
		boolean gotLine = false;
		String response = "initial";
		boolean nameTaken = false;
		List<User> nameChecker = userService.getAllUsers();
		Account account = new Account();
		for(int i = 0; i < nameChecker.size(); ++i)
		{
				user = nameChecker.get(i);
				if((!user.getIsEmployee())&&(!user.getIsAdmin())){
				account = accountService.getAccount(user.getAccountID());
				System.out.println("User Name: " + user.getUserName() 
				 + " First Name: " + user.getFirstName()
				 + " Last Name: " + user.getLastName()
				 + " Account ID: " + user.getAccountID()
				 + " Registration Approved: " + user.getRegApproved()
				 + " Account Balance: " + account.getBalance());
				}
			
		}
		
		System.out.println("enter user name to view information or 0 to cancel:");
		while((!validResponse)&&(!hasCancelled))
		{

			try
			{
			response = scan.nextLine();
			if(response.length() != 0)
			{
			gotLine = true;
			}
			}
			catch(NoSuchElementException e)
			{
				gotLine = false;
			}
			
	
		for(int i = 0; i < nameChecker.size(); ++i)
		{
			if(response.equals(nameChecker.get(i).getUserName()))
			{
				nameTaken = true;
			}
			
		}
		
		
		if(!response.equals("0"))
		{
	
		if((response.length() > 30)|| (response.length() < 2)){
			if(gotLine) {
			System.out.println("That is not a valid user name, try again.");
			}
		}
		else if(nameTaken == true)
		{
			
			user = new User();
			user = userService.getUser(response);
			account = accountService.getAccount(user.getAccountID());
			System.out.println("User Name: " + user.getUserName());
			System.out.println("First Name: " + user.getFirstName());
			System.out.println("Last Name: " + user.getLastName());
			System.out.println("Email Address: " + user.getEmail());
			System.out.println("Phone Number: " + user.getPhone());
			System.out.println("Social Security Number: " + user.getSSN());
			System.out.println("Street Address: " + user.getStreetAddress());
			System.out.println("City: " + user.getCity());
			System.out.println("State: " + user.getState());
			System.out.println("Zip Code: " + user.getZip());
			System.out.println("Account ID: " + user.getAccountID());
			System.out.println("Registration Approved: " + user.getRegApproved());
			System.out.println("Account Balance: " + account.getBalance());
			return true;
			
		}
		else 
		{
			System.out.println("User name does not exist.");
			validResponse = true;
		}
		} else{
			hasCancelled = true;
		}
		

		}
		
		
		return true;
	}
	
	
	public boolean approveAccounts()
	{

		User user = new User();
		boolean validResponse = false;
		boolean hasCancelled = false;
		boolean gotLine = false;
		String response = "initial";
		boolean nameTaken = false;
		List<User> nameChecker = userService.getAllUsers();
		for(int i = 0; i < nameChecker.size(); ++i)
		{
				user = nameChecker.get(i);
				if((!user.getIsEmployee())&&(!user.getIsAdmin())&&(!user.getRegApproved())){
				System.out.println("User Name: " + user.getUserName() 
				 + " Account ID: " + user.getAccountID()
				 + " Registration Approved: " + user.getRegApproved());
				}
			
		}
		
		System.out.println("enter user name to approve or 0 to cancel:");
		while((!validResponse)&&(!hasCancelled))
		{

			try
			{
			response = scan.nextLine();
			if(response.length() != 0)
			{
			gotLine = true;
			}
			}
			catch(NoSuchElementException e)
			{
				gotLine = false;
			}
			
	
		for(int i = 0; i < nameChecker.size(); ++i)
		{
			if(response.equals(nameChecker.get(i).getUserName()))
			{
				nameTaken = true;
			}
			
		}
		
		
		if(!response.equals("0"))
		{
	
		if((response.length() > 30)|| (response.length() < 2)){
			if(gotLine) {
			System.out.println("That is not a valid user name, try again.");
			}
		}
		else if(nameTaken == true)
		{
			
			user = new User();
			user = userService.getUser(response);
			
			user.setRegApproved(true);
			userService.editUserInformation("reg_approved", "true", user.getUserName());
			System.out.println("Account approved for: " + user.getUserName());
			return true;
			
		}
		else 
		{
			System.out.println("User name does not exist.");
			validResponse = true;
		}
		} else{
			hasCancelled = true;
		}
		

		}
		
		
		return true;
	}
	
	public boolean viewUserInformation(String userName)
	{
		User user = new User();
		user = userService.getUser(userName);
		System.out.println("User Name: " + user.getUserName());
		System.out.println("Password: " + user.getPassword());
		System.out.println("First Name: " + user.getFirstName());
		System.out.println("Last Name: " + user.getLastName());
		System.out.println("Email Address: " + user.getEmail());
		System.out.println("Phone Number: " + user.getPhone());
		System.out.println("Social Security Number: " + user.getSSN());
		System.out.println("Street Address: " + user.getStreetAddress());
		System.out.println("City: " + user.getCity());
		System.out.println("State: " + user.getState());
		System.out.println("Zip Code: " + user.getZip());
		System.out.println("Account ID: " + user.getAccountID());
		System.out.println("Registration Approved: " + user.getRegApproved() + "\n");
		return true;
	}
	
	public boolean viewBalance(int accountID)
	{
		Account account = new Account();
		account = accountService.getAccount(accountID);
		System.out.println("Account Balance for account: " + accountID);
		System.out.println("$" + account.getBalance());	
		return true;
	}
	
	public boolean approveJointAccount(int accountID)
	{
		User user = new User();
		boolean validResponse = false;
		boolean hasCancelled = false;
		boolean gotLine = false;
		String response = "initial";
		boolean nameTaken = false;
		List<User> nameChecker = userService.getAllUsers();
		for(int i = 0; i < nameChecker.size(); ++i)
		{
				user = nameChecker.get(i);
				if((!user.getIsEmployee())&&(!user.getIsAdmin())&&(!user.getJointApproved())&&(user.getAccountID() == accountID)){
				System.out.println("User Name: " + user.getUserName() 
				 + " Account ID: " + user.getAccountID()
				 + " Joint Account Approved: " + user.getJointApproved());
				}
			
		}
		
		System.out.println("enter user name to approve or 0 to cancel:");
		while((!validResponse)&&(!hasCancelled))
		{

			try
			{
			response = scan.nextLine();
			if(response.length() != 0)
			{
			gotLine = true;
			}
			}
			catch(NoSuchElementException e)
			{
				gotLine = false;
			}
			
	
		for(int i = 0; i < nameChecker.size(); ++i)
		{
			if((response.equals(nameChecker.get(i).getUserName()))&&(!user.getIsEmployee())&&(!user.getIsAdmin())&&(!user.getJointApproved())&&(user.getAccountID() == accountID))
			{
				nameTaken = true;
			}
			
		}
		
		
		if(!response.equals("0"))
		{
	
		if((response.length() > 30)|| (response.length() < 2)){
			if(gotLine) {
			System.out.println("That is not a valid user name, try again.");
			}
		}
		else if(nameTaken == true)
		{
			
			user = new User();
			user = userService.getUser(response);
			
			user.setJointApproved(true);
			userService.editUserInformation("joint_approved", "true", user.getUserName());
			System.out.println("Account approved for: " + user.getUserName());
			return true;
			
		}
		else 
		{
			System.out.println("User name does not exist.");
			validResponse = true;
		}
		} else{
			hasCancelled = true;
		}
		

		}
		
		
		return true;
	}
	

	public boolean makeDeposit(int accountID)
	{
		
		
		
	Account account = new Account();
	account = accountService.getAccount(accountID);
	double balance = account.getBalance();
	System.out.println("Current Account Balance for account: " + accountID);
	System.out.println("$" + balance + "\n" + "How much would you like to deposit?");
	
	boolean validResponse = false;
	boolean gotLine = false;
	boolean hasCancelled = false;
	double numericResponse = 0;
	String response = "initial";
	while((!validResponse)&&(!hasCancelled))
	{
	try
	{
	response = scan.nextLine();
	numericResponse = Double.parseDouble(response);
	gotLine = true;
	}catch(NumberFormatException e)
	{
	System.out.println("input not a number, try again.");
	numericResponse = 1;		
	}
	catch(NoSuchElementException e)
	{
		gotLine = false;
	}

	if(numericResponse != 0)
	{
		int length = String.valueOf(numericResponse).length();
	if((length > 15)||(numericResponse < 0)){
	if(gotLine) {
		System.out.println("That is not a valid amount to deposit, try again.");
	}
	}
	else 
	{
		balance = balance + numericResponse;
		accountService.editAccountBalance(accountID, balance);
		System.out.println("Deposit complete your new balance is: " + balance);
		validResponse = true;
	}
	} else
		hasCancelled = true;
	}
			

	return true;
	}
	
	public boolean makeWithdrawal(int accountID)
	{
	Account account = new Account();
	account = accountService.getAccount(accountID);
	double balance = account.getBalance();
	System.out.println("Current Account Balance for account: " + accountID);
	System.out.println("$" + balance + "\n" + "How much would you like to withdraw?");
	
	boolean validResponse = false;
	boolean gotLine = false;
	boolean hasCancelled = false;
	double numericResponse = 0;
	String response = "initial";
	while((!validResponse)&&(!hasCancelled))
	{
	try
	{
	response = scan.nextLine();
	numericResponse = Double.parseDouble(response);
	gotLine = true;
	}catch(NumberFormatException e)
	{
	System.out.println("input not a number, try again.");
	numericResponse = 1;		
	}
	catch(NoSuchElementException e)
	{
		gotLine = false;
	}

	if(numericResponse != 0)
	{
		int length = String.valueOf(numericResponse).length();
	if((length > 15)||(numericResponse < 0)){
	if(gotLine) {
		System.out.println("That is not a valid amount to withdraw, try again.");
	}
	}
	else 
	{
		balance = balance - numericResponse;
		accountService.editAccountBalance(accountID, balance);
		System.out.println("Withdrawal complete your new balance is: " + balance);
		validResponse = true;
	}
	} else
		hasCancelled = true;
	}
			

	return true;
	}
	
	public boolean makeTransfer(int accountID)
	{
	Account account = new Account();
	account = accountService.getAccount(accountID);
	double balance = account.getBalance();
	System.out.println("Current Account Balance for account: " + accountID);
	System.out.println("$" + balance + "\n" + "How much would you like to transfer?");
	
	boolean validResponse = false;
	boolean gotLine = false;
	boolean hasCancelled = false;
	double valueResponse = 0;
	int numericResponse = 0;
	String response = "initial";
	while((!validResponse)&&(!hasCancelled))
	{
	try
	{
	response = scan.nextLine();
	valueResponse = Double.parseDouble(response);
	gotLine = true;
	}catch(NumberFormatException e)
	{
	System.out.println("input not a number, try again.");
	valueResponse = -1;		
	}
	catch(NoSuchElementException e)
	{
		gotLine = false;
	}

	if(valueResponse != 0)
	{
		int length = String.valueOf(valueResponse).length();
	if((length > 15)||(valueResponse < 0)||(valueResponse > balance)){
	if(gotLine) {
		System.out.println("That is not a valid amount to transfer, try again.");
	}
	}
	else 
	{

		validResponse = true;
	}
	} else
		hasCancelled = true;
	}

	// get Account ID
	validResponse = false;
	gotLine = false;
	System.out.println("enter Account ID to transfer to number or 0 to cancel");
	while((!validResponse)&&(!hasCancelled))
	{
	try
	{
	response = scan.nextLine();
	numericResponse = Integer.parseInt(response);
	gotLine = true;
	}catch(NumberFormatException e)
	{
	System.out.println("input not a number, try again.");
	numericResponse = 1;		
	}
	catch(NoSuchElementException e)
	{
		gotLine = false;
	}

	if(numericResponse != 0)
	{
	int length = String.valueOf(numericResponse).length();
	if((length != 9)){
		if(gotLine) {
		System.out.println("That is not a valid account ID, try again.");
		}
	}
	else
	{
		boolean IDValid = false;
		List<Account> IDChecker = accountService.getAllAccounts();
		for(int i = 0; i < IDChecker.size(); ++i)
		{
			if(numericResponse == IDChecker.get(i).getAccountID())
			{
				IDValid = true;
			}
			
		}
		
		if(IDValid == true)
		{
		accountService.transfer(accountID, numericResponse, valueResponse);
		double remaining = balance - valueResponse;
		System.out.println("Transfer complete! Your current balance is: $" + remaining );
		validResponse = true;
		}else
		{
			System.out.println("ID does not exist.");
		}
		
		
	}
	} else
		hasCancelled = true;
	}
	
	

	return true;
	}
	
	
	public boolean adminDeposit()
	{
		

	
		System.out.println("How much would you like to deposit?");

		boolean validResponse = false;
		boolean gotLine = false;
		boolean hasCancelled = false;
		double valueResponse = 0;
		int numericResponse = 0;
		String response = "initial";
		while((!validResponse)&&(!hasCancelled))
		{
		try
		{
		response = scan.nextLine();
		valueResponse = Double.parseDouble(response);
		gotLine = true;
		}catch(NumberFormatException e)
		{
		System.out.println("input not a number, try again.");
		numericResponse = -1;		
		}
		catch(NoSuchElementException e)
		{
			gotLine = false;
		}

		if(valueResponse != 0)
		{
			int length = String.valueOf(valueResponse).length();
		if((length > 15)||(valueResponse < 0)){
		if(gotLine) {
			System.out.println("That is not a valid amount to deposit, try again.");
		}
		}
		else 
		{

			validResponse = true;
		}
		} else
			hasCancelled = true;
		}

		// get Account ID
		validResponse = false;
		gotLine = false;
		System.out.println("enter Account ID for deposit or 0 to cancel");
		while((!validResponse)&&(!hasCancelled))
		{
		try
		{
		response = scan.nextLine();
		numericResponse = Integer.parseInt(response);
		gotLine = true;
		}catch(NumberFormatException e)
		{
		System.out.println("input not a number, try again.");
		numericResponse = 1;		
		}
		catch(NoSuchElementException e)
		{
			gotLine = false;
		}

		if(numericResponse != 0)
		{
		int length = String.valueOf(numericResponse).length();
		if((length != 9)){
			if(gotLine) {
			System.out.println("That is not a valid account ID, try again.");
			}
		}
		else
		{
			boolean IDValid = false;
			List<Account> IDChecker = accountService.getAllAccounts();
			for(int i = 0; i < IDChecker.size(); ++i)
			{
				if(numericResponse == IDChecker.get(i).getAccountID())
				{
					IDValid = true;
				}
				
			}
			
			if(IDValid == true)
			{
				
			double balance = accountService.getAccount(numericResponse).getBalance() + valueResponse;
			accountService.editAccountBalance(numericResponse, balance);
			System.out.println("Deposit complete the balance of " + numericResponse + " is: $" + balance);
			validResponse = true;
			}else
			{
				System.out.println("ID does not exist.");
			}
			
			
		}
		} else
			hasCancelled = true;
		}
		
		



		return true;
				
		
		
	}
	
	public boolean adminWithdrawal()
	{

		
		System.out.println("How much would you like to withdraw?");

		boolean validResponse = false;
		boolean gotLine = false;
		boolean hasCancelled = false;
		double valueResponse = 0;
		int numericResponse = 0;
		String response = "initial";
		while((!validResponse)&&(!hasCancelled))
		{
		try
		{
		response = scan.nextLine();
		valueResponse = Double.parseDouble(response);
		gotLine = true;
		}catch(NumberFormatException e)
		{
		System.out.println("input not a number, try again.");
		numericResponse = 1;		
		}
		catch(NoSuchElementException e)
		{
			gotLine = false;
		}

		if(valueResponse != 0)
		{
			int length = String.valueOf(valueResponse).length();
		if((length > 15)||(valueResponse < 0)){
		if(gotLine) {
			System.out.println("That is not a valid amount to withdraw, try again.");
		}
		}
		else 
		{

			validResponse = true;
		}
		} else
			hasCancelled = true;
		}

		// get Account ID
		validResponse = false;
		gotLine = false;
		System.out.println("enter Account ID to withdraw from to number or 0 to cancel");
		while((!validResponse)&&(!hasCancelled))
		{
		try
		{
		numericResponse = scan.nextInt();
		gotLine = true;
		}catch(InputMismatchException e)
		{
			System.out.println("input not a number, try again.");
		}
		catch(NoSuchElementException e)
		{
			gotLine = false;
		}

		if(numericResponse != 0)
		{
		int length = String.valueOf(numericResponse).length();
		if((length != 9)){
			if(gotLine) {
			System.out.println("That is not a valid account ID, try again.");
			}
		}
		else
		{
			boolean IDValid = false;
			List<Account> IDChecker = accountService.getAllAccounts();
			for(int i = 0; i < IDChecker.size(); ++i)
			{
				if(numericResponse == IDChecker.get(i).getAccountID())
				{
					IDValid = true;
				}
				
			}
			
			if(IDValid == true)
			{
				
			double balance = accountService.getAccount(numericResponse).getBalance() - valueResponse;
			if(balance >= 0)
			{
			accountService.editAccountBalance(numericResponse, balance);
			System.out.println("Withdrawal complete the balance of " + numericResponse + " is: $" + balance);
			validResponse = true;
			} else
			{
				System.out.println("insufficient funds.");
			}
			
			
			}else
			{
				System.out.println("ID does not exist.");
			}
			
			
		}
		} else
			hasCancelled = true;
		}
		
		



		return true;
				
		
		
		
	}
	
	public boolean adminTransfer()
	{
		
		boolean validResponse = false;
		boolean gotLine = false;
		boolean hasCancelled = false;
		double valueResponse = 0;
		int numericResponse = 0;
		String response = "initial";
		
		// get Account ID
		validResponse = false;
		gotLine = false;
		System.out.println("enter Account ID to transfer to from or 0 to cancel");
		while((!validResponse)&&(!hasCancelled))
		{
		try
		{
		response = scan.nextLine();
		numericResponse = Integer.parseInt(response);
		gotLine = true;
		}catch(NumberFormatException e)
		{
		System.out.println("input not a number, try again.");
		numericResponse = 1;		
		}
		catch(NoSuchElementException e)
		{
			gotLine = false;
		}

		if(numericResponse != 0)
		{
		int length = String.valueOf(numericResponse).length();
		if((length != 9)){
			if(gotLine) {
			System.out.println("That is not a valid account ID, try again.");
			}
		}
		else
		{
			boolean IDValid = false;
			List<Account> IDChecker = accountService.getAllAccounts();
			for(int i = 0; i < IDChecker.size(); ++i)
			{
				if(numericResponse == IDChecker.get(i).getAccountID())
				{
					IDValid = true;
				}
				
			}
			
			if(IDValid == true)
			{
			validResponse = true;
			}else
			{
				System.out.println("ID does not exist.");
			}
			
			
		}
		} else
			hasCancelled = true;
		}
		
		
		
		
		Account account = new Account();
		int accountID = numericResponse;
		account = accountService.getAccount(accountID);
		double balance = account.getBalance();
		System.out.println("Current Account Balance for account: " + accountID);
		System.out.println("$" + balance + "\n" + "How much would you like to transfer?");
		
	
		
		validResponse = false;
		gotLine = false;
		while((!validResponse)&&(!hasCancelled))
		{
		try
		{
		response = scan.nextLine();
		valueResponse = Double.parseDouble(response);
		gotLine = true;
		}catch(NumberFormatException e)
		{
		System.out.println("input not a number, try again.");
		valueResponse = -1;		
		}
		catch(NoSuchElementException e)
		{
			gotLine = false;
		}

		if(valueResponse != 0)
		{
			int length = String.valueOf(valueResponse).length();
		if((length > 15)||(valueResponse < 0)||(valueResponse > balance)){
		if(gotLine) {
			System.out.println("That is not a valid amount to transfer, try again.");
		}
		}
		else 
		{

			validResponse = true;
		}
		} else
			hasCancelled = true;
		}

		// get Account ID
		validResponse = false;
		gotLine = false;
		System.out.println("enter Account ID number to transfer to or 0 to cancel");
		while((!validResponse)&&(!hasCancelled))
		{
		try
		{
		response = scan.nextLine();
		numericResponse = Integer.parseInt(response);
		gotLine = true;
		}catch(NumberFormatException e)
		{
		System.out.println("input not a number, try again.");
		numericResponse = 1;
				
		}
		catch(NoSuchElementException e)
		{
			gotLine = false;
		}

		if(numericResponse != 0)
		{
		int length = String.valueOf(numericResponse).length();
		if((length != 9)){
			if(gotLine) {
			System.out.println("That is not a valid account ID, try again.");
			}
		}
		else
		{
			boolean IDValid = false;
			List<Account> IDChecker = accountService.getAllAccounts();
			for(int i = 0; i < IDChecker.size(); ++i)
			{
				if(numericResponse == IDChecker.get(i).getAccountID())
				{
					IDValid = true;
				}
				
			}
			
			if(IDValid == true)
			{
			accountService.transfer(accountID, numericResponse, valueResponse);
			double remaining = balance - valueResponse;
			System.out.println("Transfer complete! Remaining balance in sending account is: $" + remaining );
			validResponse = true;
			}else
			{
				System.out.println("ID does not exist.");
			}
			
			
		}
		} else
			hasCancelled = true;
		}
		
		

		return true;
	}
	
	public boolean cancelAccount() 
	{
		// get Account ID
				boolean validResponse = false;
				boolean gotLine = false;
				boolean hasCancelled = false;
				int numericResponse = 1;
				String response = "initial";
				System.out.println("enter Account ID number to delete to or 0 to cancel");
				while((!validResponse)&&(!hasCancelled))
				{
				try
				{
				response = scan.nextLine();
				numericResponse = Integer.parseInt(response);
				gotLine = true;
				}catch(NumberFormatException e)
				{
				System.out.println("input not a number, try again.");
				numericResponse = 1;
						
					}
				catch(NoSuchElementException e)
				{
					gotLine = false;
				}

				if(numericResponse != 0)
				{
				int length = String.valueOf(numericResponse).length();
				if((length != 9)){
					if(gotLine) {
					System.out.println("That is not a valid account ID, try again.");
					}
				}
				else
				{
					boolean IDValid = false;
					List<Account> IDChecker = accountService.getAllAccounts();
					for(int i = 0; i < IDChecker.size(); ++i)
					{
						if(numericResponse == IDChecker.get(i).getAccountID())
						{
							IDValid = true;
						}
						
					}
					
					if(IDValid == true)
					{
					 userService.deleteUser(numericResponse);
					 accountService.deleteAccount(numericResponse);
					 System.out.println("Account: "+ numericResponse +" and it's associated Users deleted.");
					validResponse = true;
					}else
					{
						System.out.println("ID does not exist.");
					}
					
					
				}
				} else
					hasCancelled = true;
				}
				
				

		return true;
	}
	
	public boolean createUser()
	{
		

		User user = new User();
		boolean validResponse = false;
		boolean hasCancelled = false;
		boolean gotLine = false;
		String response = "initial";
		int numericResponse = 0;
		long longNumericResponse = 0;
		//get username
		System.out.println("enter user name or 0 to cancel:");
		while((!validResponse)&&(!hasCancelled))
		{

			try
			{
			response = scan.nextLine();
			if(response.length() != 0)
			{
			gotLine = true;
			}
			}
			catch(NoSuchElementException e)
			{
				gotLine = false;
			}
			
		boolean nameTaken = false;
		List<User> nameChecker = userService.getAllUsers();
		for(int i = 0; i < nameChecker.size(); ++i)
		{
			if(response.equals(nameChecker.get(i).getUserName()))
			{
				nameTaken = true;
			}
			
		}
		
		
		if(!response.equals("0"))
		{
	
		if((response.length() > 30)|| (response.length() < 2)){
			if(gotLine) {
			System.out.println("That is not a valid user name, try again.");
			}
		}
		else if(nameTaken == true)
		{

			System.out.println("User name already in use.");
		}
		else 
		{
			System.out.println(response + " is available, using " + response);
			user.setUserName(response);
			validResponse = true;
		}
		} else{
			hasCancelled = true;
		}
		

		}
		// get password
		validResponse = false;
		gotLine = false;
		if(hasCancelled == false)
		{
		System.out.println("enter password or 0 to cancel:");
		}
		while((!validResponse)&&(!hasCancelled))
		{
			try
			{
			response = scan.nextLine();
			if(response.length() != 0)
			{
			gotLine = true;
			}
			}
			catch(NoSuchElementException e)
			{
				gotLine = false;
			}
		if(!response.equals("0"))
		{
		if((response.length() > 30)|| (response.length() < 2)){
			if(gotLine) {
			System.out.println("That is not a valid password, try again.");
			}
		}
		else 
		{
			user.setPassword(response);
			validResponse = true;
		}
		} else
			hasCancelled = true;
		}
		
		
		//get first name
		validResponse = false;
		gotLine = false;
		if(hasCancelled == false)
		{
		System.out.println("enter first name or 0 to cancel:");
		}
		while((!validResponse)&&(!hasCancelled))
		{
			try
			{
			response = scan.nextLine();
			if(response.length() != 0)
			{
			gotLine = true;
			}
			}
			catch(NoSuchElementException e)
			{
				gotLine = false;
			}
		if(!response.equals("0"))
		{
		if((response.length() > 30)|| (response.length() < 2)){
			if(gotLine) {
			System.out.println("That is not a valid first name, try again.");
			}
		}
		else 
		{
			user.setFirstName(response);
			validResponse = true;
		}
		} else
			hasCancelled = true;
		}
		
		
		//get last name
		validResponse = false;
		gotLine = false;
		if(hasCancelled == false)
		{
		System.out.println("enter last name or 0 to cancel:");
		}
		while((!validResponse)&&(!hasCancelled))
		{

			try
			{
			response = scan.nextLine();
			if(response.length() != 0)
			{
			gotLine = true;
			}
			}
			catch(NoSuchElementException e)
			{
				gotLine = false;
			}
		if(!response.equals("0"))
		{
		if((response.length() > 30)|| (response.length() < 2)){
			if(gotLine) {
			System.out.println("That is not a valid last name, try again.");
			}
		}
		else 
		{
			user.setLastName(response);
			validResponse = true;
		}
		} else
			hasCancelled = true;
		}
		
		// get email
		validResponse = false;
		gotLine = false;
		if(hasCancelled == false)
		{
		System.out.println("enter email or 0 to cancel:");
		}
		while((!validResponse)&&(!hasCancelled))
		{

			try
			{
			response = scan.nextLine();
			if(response.length() != 0)
			{
			gotLine = true;
			}
			}
			catch(NoSuchElementException e)
			{
				gotLine = false;
			}
		if(!response.equals("0"))
		{
		if((response.length() > 30)|| (response.length() < 2)){
			if(gotLine) {
			System.out.println("That is not a valid email, try again.");
			}
		}
		else 
		{
			user.setEmail(response);
			validResponse = true;
		}
		} else
			hasCancelled = true;
		}
		
		
		// get phone number
		validResponse = false;
		gotLine = false;
		if(hasCancelled == false)
		{
		System.out.println("enter phone number or 0 to cancel");
		}
		while((!validResponse)&&(!hasCancelled))
		{

		try
		{
		response = scan.nextLine();
		longNumericResponse = Long.parseLong(response);
		gotLine = true;
		}catch(NumberFormatException e)
		{
			System.out.println("input not a number, try again.");
			longNumericResponse = 1;
		}
		catch(NoSuchElementException e)
		{
			gotLine = false;
		}

		if(longNumericResponse != 0)
		{
		int length = String.valueOf(longNumericResponse).length();
		if((length != 10)){
			if(gotLine) {
			System.out.println("That is not a valid phone number, try again.");
			}
		}
		else 
		{
			user.setPhone(longNumericResponse);
			validResponse = true;
		}
		} else
			hasCancelled = true;
		}
		
		// get ssn
		validResponse = false;
		gotLine = false;
		if(hasCancelled == false)
		{
		System.out.println("enter social security number or 0 to cancel");
		}
		while((!validResponse)&&(!hasCancelled))
		{
		try
		{
		response = scan.nextLine();
		numericResponse = Integer.parseInt(response);
		gotLine = true;
		}catch(NumberFormatException e)
		{
		System.out.println("input not a number, try again.");
		numericResponse = 1;
			
		}
		catch(NoSuchElementException e)
		{
			gotLine = false;
		}

		if(numericResponse != 0)
		{
		int length = String.valueOf(numericResponse).length();
		if((length != 9)){
			if(gotLine) {
			System.out.println("That is not a valid social security number, try again.");
			}
		}
		else 
		{
			user.setSSN(numericResponse);
			validResponse = true;
		}
		} else
			hasCancelled = true;
		}
		

		
		//get streetAddress
		validResponse = false;
		gotLine = false;
		if(hasCancelled == false)
		{
		System.out.println("enter street address or 0 to cancel:");
		}
		while((!validResponse)&&(!hasCancelled))
		{


			try
			{
			response = scan.nextLine();
			if(response.length() != 0)
			{
			gotLine = true;
			}
			}
			catch(NoSuchElementException e)
			{
				gotLine = false;
			}
		if(!response.equals("0"))
		{
		if((response.length() > 30)|| (response.length() < 2)){
			if(gotLine) {
			System.out.println("That is not a valid street address, try again.");
			}
		}
		else 
		{
			user.setStreetAddress(response);
			validResponse = true;
		}
		} else
			hasCancelled = true;
		}
						
		//get city
		validResponse = false;
		gotLine = false;
		if(hasCancelled == false)
		{
		System.out.println("enter city or 0 to cancel:");
		}
		while((!validResponse)&&(!hasCancelled))
		{
			try
			{
			response = scan.nextLine();
			if(response.length() != 0)
			{
			gotLine = true;
			}
			}
			catch(NoSuchElementException e)
			{
				gotLine = false;
			}
		if(!response.equals("0"))
		{
		if((response.length() > 30)|| (response.length() < 2)){
			if(gotLine) {
			System.out.println("That is not a valid city, try again.");
			}
		}
		else 
		{
			user.setCity(response);
			validResponse = true;
		}
		} else
			hasCancelled = true;
		}
				
		//get state
		validResponse = false;
		gotLine = false;
		if(hasCancelled == false)
		{
		System.out.println("enter state (2 letter abbreviation) or 0 to cancel:");
		}
		while((!validResponse)&&(!hasCancelled))
		{
			try
			{
			response = scan.nextLine();
			if(response.length() != 0)
			{
			gotLine = true;
			}
			}
			catch(NoSuchElementException e)
			{
				gotLine = false;
			}
		if(!response.equals("0"))
		{
		if(response.length() != 2){
			if(gotLine) {
			System.out.println("That is not a valid state, try again.");
			}
		}
		else 
		{
			user.setState(response);
			validResponse = true;
		}
		} else
			hasCancelled = true;
		}		
		//get zip

		validResponse = false;
		gotLine = false;
		if(hasCancelled == false)
		{
		System.out.println("enter zip code or 0 to cancel");
		}
		while((!validResponse)&&(!hasCancelled))
		{

			try
			{
			response = scan.nextLine();
			numericResponse = Integer.parseInt(response);
			gotLine = true;
			}catch(NumberFormatException e)
			{
			System.out.println("input not a number, try again.");
			numericResponse = 1;
				
			}
		catch(NoSuchElementException e)
		{
			gotLine = false;
		}

		if(numericResponse != 0)
		{
		int length = String.valueOf(numericResponse).length();
		if((length != 5)){
			if(gotLine) {
			System.out.println("That is not a valid zip code, try again.");
			}
		}
		else 
		{
			user.setZip(numericResponse);
			validResponse = true;
		}
		} else
			hasCancelled = true;
		}
		
		//get AccountID and jointApproved 
		validResponse = false;
		gotLine = false;
		if(hasCancelled == false)
		{
		System.out.println("would you like to:\n 0) cancel\n 1) create a new account\n 2) request to form a joint account with an existing user");
		}
		while((!validResponse)&&(!hasCancelled))
		{
		try
		{
		response = scan.nextLine();
		if(response.length() != 0)
		{
		gotLine = true;
		}
		}
		catch(NoSuchElementException e)
		{
			gotLine = false;
		}
		if(!response.equals("0"))
		{
			
			
			
		if((response.equals("1"))){

				List<User> IDChecker = userService.getAllUsers();
				int accountID = 0;
				boolean nameTaken = true;
				while(nameTaken == true)
				{

				Random IDGenerator = new Random();
				accountID = IDGenerator.nextInt(899999999) + 100000000;
				nameTaken = false;
				for(int i = 0; i < IDChecker.size(); ++i)
				{

					if(accountID == IDChecker.get(i).getAccountID())
					{
						nameTaken = true;
					}
					
				}
				}
				user.setAccountID(accountID);
				user.setJointApproved(true);
				Account account = new Account();
				account.setAccountID(accountID);
				account.setBalance(0.00);
				accountService.addAccount(account);
			validResponse = true;
		}
		else if((response.equals("2"))){
			validResponse = false;
			gotLine = false;
			System.out.println("Please enter your account ID");
			while((!validResponse)&&(!hasCancelled))
			{
			try
			{
			response = scan.nextLine();
			numericResponse = Integer.parseInt(response);
			gotLine = true;
			}catch(NumberFormatException e)
			{
			System.out.println("input not a number, try again.");
			numericResponse = 1;	
			}
			catch(NoSuchElementException e)
			{
				gotLine = false;
			}

			if(numericResponse != 0)
			{
			int length = String.valueOf(numericResponse).length();
			if((length != 9)){
				if(gotLine) {
				System.out.println("That is not an accountID, try again.");
				}
			}
			else 
			{
				user.setAccountID(numericResponse);
				user.setJointApproved(false);
				validResponse = true;
			}
			} else
				hasCancelled = true;
			}
			
			

		}
		else 
		{
			if(gotLine&&(!response.equals(""))) {
			System.out.println("invalid input, try again.");
			}
			validResponse = false;
		}
		} else
			hasCancelled = true;
		}
		if(hasCancelled == false)
		{
			user.setRegApproved(false);

			user.setIsEmployee(false);
			user.setIsAdmin(false);
		if(userService.addUser(user))
		{
			System.out.println("user created!");
		}
		} else
		{
			System.out.println("Account creation canceled, returning to previous menu.");
		}
		return true;
	}
	
	public boolean updateUser(String userName)
	{

		
		boolean hasCancelled = false;
		boolean validResponse = false;
		boolean gotLine = false;
		String columnName = "initial";
		String[] columnNames = {"passwords", "first_name", "last_name", "email", "phone", "ssn", "street_address", "city", "state", "zip"};
		System.out.println("Select which thing to change:\n 0) cancel\n 1) password\n 2) first name\n 3) last name\n 4) email\n 5) phone\n 6) social security number\n" +
				"7) street_address\n 8) city\n 9) state\n 10) zip");
		while((!validResponse)&&(!hasCancelled))
		{

			columnName = scan.nextLine();	
			if(columnName.length() != 0)
			{
			gotLine = true;
			}
			if(columnName.equals("0"))
			{
				hasCancelled = true;
			} else
			{
			for(int i = 1; i < 11; ++i)
			{
				if(columnName.equals(String.valueOf(i)))
				{
					validResponse = true;
					columnName = columnNames[i-1];
				}
				
			}
			}
			
			if((!validResponse)&&(!hasCancelled)&&(gotLine))
			{
				System.out.println("invalid input, try again.");
			}
			
			
		}
		
		// do a switch case with the column name.
		String response = "initial";
		int numericResponse = 0;
		long longNumericResponse = 0;
		switch(columnName)
		{
		case "passwords":
		// get password
		validResponse = false;
		gotLine = false;
		System.out.println("enter password or 0 to cancel:");
		while((!validResponse)&&(!hasCancelled))
		{
			try
			{
			response = scan.nextLine();
			if(response.length() != 0)
			{
			gotLine = true;
			}
			}
			catch(NoSuchElementException e)
			{
				gotLine = false;
			}
		if(!response.equals("0"))
		{
		if((response.length() > 30)|| (response.length() < 2)){
			if(gotLine) {
			System.out.println("That is not a valid password, try again.");
			}
		}
		else 
		{
			validResponse = true;
			response = "'" + response + "'";
			 userService.editUserInformation(columnName, response, userName);

		}
		} else
			hasCancelled = true;
		}
		break;
		
		case "first_name":

		//get first name
		validResponse = false;
		gotLine = false;
		System.out.println("enter first name or 0 to cancel:");
		while((!validResponse)&&(!hasCancelled))
		{
			try
			{
			response = scan.nextLine();
			if(response.length() != 0)
			{
			gotLine = true;
			}
			}
			catch(NoSuchElementException e)
			{
				gotLine = false;
			}
		if(!response.equals("0"))
		{
		if((response.length() > 30)|| (response.length() < 2)){
			if(gotLine) {
			System.out.println("That is not a valid first name, try again.");
			}
		}
		else 
		{
			validResponse = true;
			response = "'" + response + "'";
			 userService.editUserInformation(columnName, response, userName);
		}
		} else
			hasCancelled = true;
		}
		break;
		case "last_name":

		
		//get last name
		validResponse = false;
		gotLine = false;
		System.out.println("enter last name or 0 to cancel:");
		while((!validResponse)&&(!hasCancelled))
		{

			try
			{
			response = scan.nextLine();
			if(response.length() != 0)
			{
			gotLine = true;
			}
			}
			catch(NoSuchElementException e)
			{
				gotLine = false;
			}
		if(!response.equals("0"))
		{
		if((response.length() > 30)|| (response.length() < 2)){
			if(gotLine) {
			System.out.println("That is not a valid last name, try again.");
			}
		}
		else 
		{
			validResponse = true;
			response = "'" + response + "'";
			 userService.editUserInformation(columnName, response, userName);
		}
		} else
			hasCancelled = true;
		}
		break;
		case "email":

		// get email
		validResponse = false;
		gotLine = false;
		System.out.println("enter email or 0 to cancel:");
		while((!validResponse)&&(!hasCancelled))
		{

			try
			{
			response = scan.nextLine();
			if(response.length() != 0)
			{
			gotLine = true;
			}
			}
			catch(NoSuchElementException e)
			{
				gotLine = false;
			}
		if(!response.equals("0"))
		{
		if((response.length() > 30)|| (response.length() < 2)){
			if(gotLine) {
			System.out.println("That is not a valid email, try again.");
			}
		}
		else 
		{
			validResponse = true;
			response = "'" + response + "'";
			 userService.editUserInformation(columnName, response, userName);
		}
		} else
			hasCancelled = true;
		}
		
		break;
		case "phone":

		// get phone number
		validResponse = false;
		gotLine = false;
		System.out.println("enter phone number or 0 to cancel");
		while((!validResponse)&&(!hasCancelled))
		{

		try
		{
		response = scan.nextLine();
		longNumericResponse = Long.parseLong(response);
		gotLine = true;
		}catch(NumberFormatException e)
		{
		System.out.println("input not a number, try again.");
		longNumericResponse = 1;
				
		}
		catch(NoSuchElementException e)
		{
			gotLine = false;
		}

		if(longNumericResponse != 0)
		{
		int length = String.valueOf(longNumericResponse).length();
		if((length != 10)){
			if(gotLine) {
			System.out.println("That is not a valid phone number, try again.");
			}
		}
		else 
		{
			validResponse = true;
			 userService.editUserInformation(columnName, String.valueOf(longNumericResponse), userName);
			
		}
		} else
			hasCancelled = true;
		}
		break;
		case "ssn":

		// get ssn
		validResponse = false;
		gotLine = false;
		System.out.println("enter social security number or 0 to cancel");
		while((!validResponse)&&(!hasCancelled))
		{
		try
		{
		response = scan.nextLine();
		numericResponse = Integer.parseInt(response);
		gotLine = true;
		}catch(NumberFormatException e)
		{
		System.out.println("input not a number, try again.");
		numericResponse = 1;	
		}
		catch(NoSuchElementException e)
		{
			gotLine = false;
		}

		if(numericResponse != 0)
		{
		int length = String.valueOf(numericResponse).length();
		if((length != 9)){
			if(gotLine) {
			System.out.println("That is not a valid social security number, try again.");
			}
		}
		else 
		{
			validResponse = true;
			 userService.editUserInformation(columnName, String.valueOf(numericResponse), userName);
			
		}
		} else
			hasCancelled = true;
		}
		
		break;
		case "street_address":

		
		//get streetAddress
		validResponse = false;
		gotLine = false;
		System.out.println("enter street address or 0 to cancel:");
		while((!validResponse)&&(!hasCancelled))
		{


			try
			{
			response = scan.nextLine();
			if(response.length() != 0)
			{
			gotLine = true;
			}
			}
			catch(NoSuchElementException e)
			{
				gotLine = false;
			}
		if(!response.equals("0"))
		{
		if((response.length() > 30)|| (response.length() < 2)){
			if(gotLine) {
			System.out.println("That is not a valid street address, try again.");
			}
		}
		else 
		{
			validResponse = true;
			response = "'" + response + "'";
			 userService.editUserInformation(columnName, response, userName);
		}
		} else
			hasCancelled = true;
		}
		break;
		case "city":

		//get city
		validResponse = false;
		gotLine = false;
		System.out.println("enter city or 0 to cancel:");
		while((!validResponse)&&(!hasCancelled))
		{
			try
			{
			response = scan.nextLine();
			if(response.length() != 0)
			{
			gotLine = true;
			}
			}
			catch(NoSuchElementException e)
			{
				gotLine = false;
			}
		if(!response.equals("0"))
		{
		if((response.length() > 30)|| (response.length() < 2)){
			if(gotLine) {
			System.out.println("That is not a valid city, try again.");
			}
		}
		else 
		{
			validResponse = true;
			response = "'" + response + "'";
			 userService.editUserInformation(columnName, response, userName);
		}
		} else
			hasCancelled = true;
		}
		break;
		case "state":

		//get state
		validResponse = false;
		gotLine = false;
		System.out.println("enter state (2 letter abbreviation) or 0 to cancel:");
		while((!validResponse)&&(!hasCancelled))
		{
			try
			{
			response = scan.nextLine();
			if(response.length() != 0)
			{
			gotLine = true;
			}
			}
			catch(NoSuchElementException e)
			{
				gotLine = false;
			}
		if(!response.equals("0"))
		{
		if(response.length() != 2){
			if(gotLine) {
			System.out.println("That is not a valid state, try again.");
			}
		}
		else 
		{
			validResponse = true;
			response = "'" + response + "'";
			 userService.editUserInformation(columnName, response, userName);
		}
		} else
			hasCancelled = true;
		}		
		//get zip
		break;
		case "zip":
		validResponse = false;
		gotLine = false;
		System.out.println("enter zip code or 0 to cancel");
		while((!validResponse)&&(!hasCancelled))
		{

		try
		{
		response = scan.nextLine();
		numericResponse = Integer.parseInt(response);
		gotLine = true;
		}catch(NumberFormatException e)
		{
		System.out.println("input not a number, try again.");
		numericResponse = 1;
				
		}
		catch(NoSuchElementException e)
		{
			gotLine = false;
		}

		if(numericResponse != 0)
		{
		int length = String.valueOf(numericResponse).length();
		if((length != 5)){
			if(gotLine) {
			System.out.println("That is not a valid zip code, try again.");
			}
		}
		else 
		{

			validResponse = true;
			 userService.editUserInformation(columnName, String.valueOf(numericResponse), userName);
			
			
		}
		} else
			hasCancelled = true;
		}

		break;
		default:
		
			System.out.println("Invalid Column Name Returning.");
			
		}
		return true;
	}
}

