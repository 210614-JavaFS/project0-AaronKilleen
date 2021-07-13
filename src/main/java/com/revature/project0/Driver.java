package com.revature.project0;

import java.util.Scanner;

import com.revature.project0.controllers.UserController;

public class Driver {
	
	private static UserController userController = new UserController();
	private static Scanner scan = new Scanner(System.in);
	

	public static void main(String[] args) {
		System.out.println("Welcome to the Banking Application.");


		boolean willExit = false;
		while (!willExit){
			System.out.println("What would you like to do? \n"
					+ "1) Login \n"
					+ "2) CreateUser \n"
					+ "3) exit the application");
			String response = scan.nextLine();
			if(response.equals("1")) {
				userController.userLogin();
			}else if(response.equals("2")) {
				userController.createUser();
			}else if(response.equals("3")) {
				willExit = true;
			}else {
				System.out.println("That is not a valid input. Try again.");
			}
		}
	}
}