package com.revature.project0.services;



import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class UserServiceTest{
	private static UserService userService = new UserService();

	String columnName = "street_address";
	String value = "111 example blvd.";
	String userName = "bill";
	int accountID = 777777777;

	@Test
	 public void editUserInformationTest(){
		System.out.println("Testing edit info");
		boolean testReturn = userService.editUserInformation(columnName,value, userName);
		assertEquals(testReturn , true);
	}
	 @Test
	 public void deleteUserTest(){
		 System.out.println("Testing delete user");
		 boolean testReturn = userService.deleteUser(accountID);
		 assertEquals(testReturn , true);
	 }
	 
}