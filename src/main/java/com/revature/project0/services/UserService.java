package com.revature.project0.services;


import java.util.List;

import com.revature.project0.models.User;
import com.revature.project0.repos.UserDAO;
import com.revature.project0.repos.UserDAOImpl;

public class UserService {
	
	private static UserDAO userDao = new UserDAOImpl();
	
	public List<User> getAllUsers() {
		return userDao.findAll();
	}
	
	public User getUser(String userName) {
		return userDao.findUser(userName);
	}
	
	public boolean addUser(User user) {
		return userDao.addUser(user);
	}
	 public boolean editUserInformation(String columnName, String value, String userName)
	{
		userDao.editUserInformation(columnName,value, userName);
		return true;
	}
	 public boolean deleteUser(int accountID)
	 {
		 return userDao.deleteUser(accountID);
	 }
	 
}