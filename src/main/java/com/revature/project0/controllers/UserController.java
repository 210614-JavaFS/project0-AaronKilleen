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
	
	
	public void userLogin()
	{
		boolean goBack = false;
		while(!goBack)
		{
		System.out.println("Enter your user name or type \"back\" to return to the previous menu:");
		String response = scan.nextLine();
		if(response.equals("back") )
		{
			goBack = true;
		}
		else
		{
		User user = userService.getUser(response);
		if(user.getFirstName() != null){
			boolean correctPassword = false;
			
			while(!correctPassword)
			{
			System.out.println("Enter Password:");
			response = scan.nextLine();
			if(response.equals(user.getPassword()))
			{
			System.out.println("Welcome " + user.getFirstName() + ".");
			correctPassword = true;
			userMenu(user);
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
		return;
	}

	
	public void userMenu(User user)
	{

		boolean goBack = false;
		while(!goBack)
		{
		System.out.println("Please select an option: \n"
				+ "1) View Balance. \n"
				+ "2) View User Information. \n"
				+ "3) Change information. \n"
				+ "4) Make a Deposit."
				+ "5) Make a Withdrawal."
				+ "6) Make a Transfer."
				+ "7) Approve Join Request."
				+ "8) return to previous menu.");
		
		String response = scan.nextLine();
		
		switch(response) {
			case "1":
				System.out.println("Not Implemented:\n");
				break;
			case "2":
				System.out.println("Not Implemented:\n");
				break;
			case "3":
				System.out.println("Not Implemented:\n");				
				break;
			case "4":
				goBack = true;
				break;
			default:
				System.out.println("Invalid input, try again.");
		}
		}
	}
	
	
	public void createUser()
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
			user.setPassword(response);
			validResponse = true;
		}
		} else
			hasCancelled = true;
		}
		
		
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
			user.setFirstName(response);
			validResponse = true;
		}
		} else
			hasCancelled = true;
		}
		
		
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
			user.setLastName(response);
			validResponse = true;
		}
		} else
			hasCancelled = true;
		}
		
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
			user.setEmail(response);
			validResponse = true;
		}
		} else
			hasCancelled = true;
		}
		
		
		// get phone number
		validResponse = false;
		gotLine = false;
		System.out.println("enter phone number or 0 to cancel");
		while((!validResponse)&&(!hasCancelled))
		{

		try
		{
		longNumericResponse = scan.nextLong();
		gotLine = true;
		}catch(InputMismatchException e)
		{
			System.out.println("input not an int, try again.");
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
		System.out.println("enter social security number or 0 to cancel");
		while((!validResponse)&&(!hasCancelled))
		{
		try
		{
		numericResponse = scan.nextInt();
		gotLine = true;
		}catch(InputMismatchException e)
		{
			System.out.println("input not an int, try again.");
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
			user.setStreetAddress(response);
			validResponse = true;
		}
		} else
			hasCancelled = true;
		}
						
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
			user.setCity(response);
			validResponse = true;
		}
		} else
			hasCancelled = true;
		}
				
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
			user.setState(response);
			validResponse = true;
		}
		} else
			hasCancelled = true;
		}		
		//get zip

		validResponse = false;
		gotLine = false;
		System.out.println("enter zip code or 0 to cancel");
		while((!validResponse)&&(!hasCancelled))
		{

		try
		{
		numericResponse = scan.nextInt();
		gotLine = true;
		}catch(InputMismatchException e)
		{
			System.out.println("input not an int, try again.");
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
		System.out.println("would you like to:\n 0) cancel\n 1) create a new account\n 2) request to form a joint account with an existing user");
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
				Account account = new Account();
				account.setAccountID(accountID);
				account.setBalance(0.00);
				accountService.addAccount(account);
			validResponse = true;
		}
		else if((response.equals("2"))){
			validResponse = true;
			System.out.println("Please ask an admin to assist you in setting up the joint account.");
			user.setAccountID(0);
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
			user.setJointApproved(true);
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
	}
	
	public void updateUser()
	{
		return;
		/*
		boolean hasCancelled = false;
		boolean validResponse = false;
		
		String[] columnNames = {"passwords", "first_name", "last_name", "email", "phone", "ssn", "street_address", "city", "state", "zip"};
			
		while((!validResponse)&&(!hasCancelled))
		{
			System.out.println("Enter a number:\n 0) cancel\n 1) password\n 2) first name\n 3) last name\n 4) email\n 5) phone\n 6) social security number\n" +
					"7) street_address\n 8) city\n 9) state\n 10) zip");
			String columnName = scan.nextLine();	
			if(columnName.equals("0"))
			{
				hasCancelled = true;
			} else
			{
			for(int i = 0; i < 10; ++i)
			{
				if(columnName.equals(String.valueOf(i)))
				{
					validResponse = true;
					columnName = columnNames[i];
				}
				
			}
			}
			
			if((!validResponse)&&(!hasCancelled))
			{
				System.out.println("invalid input, try again.");
			}
			
			
		}
		
		// do a switch case with the column name.
		

		while((!validResponse)&&(!hasCancelled))
		{
			System.out.println("enter user name or 0 to cancel:");
			response = scan.nextLine();
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
			System.out.println("That is not a valid user name, try again.");
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
		while((!validResponse)&&(!hasCancelled))
		{
		System.out.println("enter password or 0 to cancel:");
		response = scan.nextLine();
		if(!response.equals("0"))
		{
		if((response.length() > 30)|| (response.length() < 2)){
			System.out.println("That is not a valid password, try again.");
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
		while((!validResponse)&&(!hasCancelled))
		{
		System.out.println("enter first name or 0 to cancel:");
		response = scan.nextLine();
		if(!response.equals("0"))
		{
		if((response.length() > 30)|| (response.length() < 2)){
			System.out.println("That is not a valid first name, try again.");
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
		while((!validResponse)&&(!hasCancelled))
		{
		System.out.println("enter last name or 0 to cancel:");
		response = scan.nextLine();
		if(!response.equals("0"))
		{
		if((response.length() > 30)|| (response.length() < 2)){
			System.out.println("That is not a valid last name, try again.");
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
		while((!validResponse)&&(!hasCancelled))
		{
		System.out.println("enter email or cancel:");
		response = scan.nextLine();
		if(!response.equals("0"))
		{
		if((response.length() > 30)|| (response.length() < 2)){
			System.out.println("That is not a valid email, try again.");
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
		while((!validResponse)&&(!hasCancelled))
		{
		System.out.println("enter phone number or 0 to cancel");
		try
		{
		longNumericResponse = scan.nextLong();
		}catch(InputMismatchException e)
		{
			System.out.println("input not an int, try again.");
		}

		if(longNumericResponse != 0)
		{
		int length = String.valueOf(longNumericResponse).length();
		if((length != 10)){
			System.out.println("That is not a valid phone number, try again.");
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
		while((!validResponse)&&(!hasCancelled))
		{
		System.out.println("enter social security number or 0 to cancel");
		try
		{
		numericResponse = scan.nextInt();
		}catch(InputMismatchException e)
		{
			System.out.println("input not an int, try again.");
		}

		if(numericResponse != 0)
		{
		int length = String.valueOf(numericResponse).length();
		if((length != 9)){
			System.out.println("That is not a valid social security number, try again.");
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
		while((!validResponse)&&(!hasCancelled))
		{
			System.out.println("enter street address or 0 to cancel:");
			response = scan.nextLine();
		if(!response.equals("0"))
		{
		if((response.length() > 30)|| (response.length() < 2)){
			System.out.println("That is not a valid street address, try again.");
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
		while((!validResponse)&&(!hasCancelled))
		{
			System.out.println("enter city or cancel:");
			response = scan.nextLine();
		if(!response.equals("0"))
		{
		if((response.length() > 30)|| (response.length() < 2)){
			System.out.println("That is not a valid city, try again.");
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
		while((!validResponse)&&(!hasCancelled))
		{
			System.out.println("enter state (2 letter abbreviation) or 0 to cancel:");
			response = scan.nextLine();
		if(!response.equals("0"))
		{
		if(response.length() != 2){
			System.out.println("That is not a valid state, try again.");
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
		while((!validResponse)&&(!hasCancelled))
		{
		System.out.println("enter zip code or 0 to cancel");
		try
		{
		numericResponse = scan.nextInt();
		}catch(InputMismatchException e)
		{
			System.out.println("input not an int, try again.");
		}

		if(numericResponse != 0)
		{
		int length = String.valueOf(numericResponse).length();
		if((length != 5)){
			System.out.println("That is not a valid zip code, try again.");
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



		
		
		
		
		/*
		public boolean editUserInformation(String columnName, String type, String text, long number, boolean bool)
		{
			return userDao.editUserInformation(columnName, type, text, number, bool);
		}
		*/
	}
}
