package com.revature.project0.repos;

import java.util.List;

import com.revature.project0.models.User;

public interface UserDAO {
	
	public List<User> findAll();
	public User findUser(String userName);
	public boolean addUser(User user);
	public boolean editUserInformation(String columnName, String type, String text, long number, boolean bool);
}
