package com.revature.project0;

import java.util.NoSuchElementException;
import java.util.Scanner;

import com.revature.project0.controllers.UserController;

public class Driver {
	
	private static UserController userController = new UserController();
	private static Scanner scan = new Scanner(System.in);
	

	public static void main(String[] args) {
		System.out.println("Welcome to the Banking Application.");

		boolean willLogout = false;
		boolean isDone = false;
		boolean gotLine = false;
		boolean willPrint = true;
		String response = "initial";
		while(!willLogout)
		{
		if(willPrint == true)
		{
			System.out.println("What would you like to do? \n"
					+ "0) Quit \n"
					+ "1) Login \n"
					+ "2) CreateUser");
				
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
		while(!isDone)
		{
		isDone = userController.userLogin();
		}
		isDone = false;
		willPrint = true;
		break;
		case "2":
		while(!isDone)
		{
		isDone = userController.createUser();
		}
		isDone = false;
		willPrint = true;
		break;
		default:
			if(gotLine == true)
			{
			System.out.println("That is not a valid input. Try again.");	
			}
		}

		}
	}
}
