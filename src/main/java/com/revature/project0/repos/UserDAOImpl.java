package com.revature.project0.repos;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.project0.models.User;
import com.revature.project0.utils.ConnectionUtil;

public class UserDAOImpl implements UserDAO {
	private static Logger log = LoggerFactory.getLogger(UserDAOImpl.class);

	@Override
	public List<User> findAll() {
		List<User> list = new ArrayList<>();
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM users;";
			log.debug(sql);
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			
			//ResultSets have a cursor similarly to Scanners or other I/O classes. 
			while(result.next()) {
				User user = new User();
				user.setUserName(result.getString("user_name"));
				user.setPassword(result.getString("passwords"));
				user.setFirstName(result.getString("first_name"));
				user.setLastName(result.getString("last_name"));
				user.setEmail(result.getString("email"));
				user.setPhone(result.getLong("phone"));
				user.setSSN(result.getInt("ssn"));
				user.setStreetAddress(result.getString("street_address"));
				user.setCity(result.getString("city"));
				user.setState(result.getString("state"));
				user.setZip(result.getInt("zip"));
				user.setAccountID(result.getInt("account_id"));
				user.setRegApproved(result.getBoolean("reg_approved"));
				user.setJointApproved(result.getBoolean("joint_approved"));
				user.setIsEmployee(result.getBoolean("is_employee"));
				user.setIsAdmin(result.getBoolean("is_admin"));
				list.add(user);
			}
			

		ConnectionUtil.closeConnection(conn);
		return list;
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public User findUser(String userName) {
		User user = new User();
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM users WHERE user_name = '" + userName + "';";
			log.debug(sql);
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
				

			
			//ResultSets have a cursor similarly to Scanners or other I/O classes. 
				while(result.next()) {
				user.setUserName(result.getString("user_name"));
				user.setPassword(result.getString("passwords"));
				user.setFirstName(result.getString("first_name"));
				user.setLastName(result.getString("last_name"));
				user.setEmail(result.getString("email"));
				user.setPhone(result.getLong("phone"));
				user.setSSN(result.getInt("ssn"));
				user.setStreetAddress(result.getString("street_address"));
				user.setCity(result.getString("city"));
				user.setState(result.getString("state"));
				user.setZip(result.getInt("zip"));
				user.setAccountID(result.getInt("account_id"));
				user.setRegApproved(result.getBoolean("reg_approved"));
				user.setJointApproved(result.getBoolean("joint_approved"));
				user.setIsEmployee(result.getBoolean("is_employee"));
				user.setIsAdmin(result.getBoolean("is_admin"));
				}
				ConnectionUtil.closeConnection(conn);
				return user;
		}catch (SQLException e) {
			e.printStackTrace();
		}
			return user;
	
	}

 public boolean editUserInformation(String columnName, String value, String userName) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "UPDATE users SET " + columnName + " = " + value + " WHERE user_name = '" + userName + "';";
			log.debug(sql);
			Statement statement = conn.createStatement();
			statement.execute(sql);
			
			ConnectionUtil.closeConnection(conn);			
			return true;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	

	@Override
	public boolean addUser(User user) {

		try(Connection conn = ConnectionUtil.getConnection()){
			Statement statement = conn.createStatement();
			String sql = "INSERT INTO users (user_name, passwords, first_name, last_name, email, phone, ssn, street_address, city, "
			+ "state, zip, account_id, reg_approved, joint_approved, is_employee, is_admin) "
			+ "VALUES ('" + user.getUserName() + "', '" + user.getPassword() + "', '" + user.getFirstName() + "', '" + user.getLastName() + "', '" 
			+ user.getEmail() + "', " + user.getPhone() + ", " + user.getSSN() + ", '" + user.getStreetAddress() + "', '"+ user.getCity() + "', '"
			+ user.getState() + "', " + user.getZip() + ", " + user.getAccountID() + ", " + user.getRegApproved() + ", " + user.getJointApproved() + ", "  
			+ user.getIsEmployee() + ", " + user.getIsAdmin() + ");";
			
			log.debug(sql);
			statement.execute(sql);
			ConnectionUtil.closeConnection(conn);
			return true;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return false;
	}
	
	public boolean deleteUser(int accountID)
	{
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "DELETE FROM users WHERE account_id = " + accountID + ";";
			log.debug(sql);
			Statement statement = conn.createStatement();
			statement.execute(sql);
			
			ConnectionUtil.closeConnection(conn);			
			return true;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
