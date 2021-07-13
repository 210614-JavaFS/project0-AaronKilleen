package com.revature.project0.models;



public class User {


	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private long phone;
	private int ssn;
	private String streetAddress;
	private String city;
	private String state;
	private int zip;
	private int accountID;
	private boolean regApproved; //registration approved
	private boolean jointApproved; //joint account approved by other account owner
	private boolean isEmployee;
	private boolean isAdmin;
	
	
	public User(String userName,  String password, String firstName, String lastName, String email, 
			long phone, int ssn, String streetAddress, String city, String state, int zip, int accountID, 
			boolean regApproved, boolean jointApproved, boolean isEmployee, boolean isAdmin) {
		super();
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.ssn = ssn;
		this.streetAddress = streetAddress;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.accountID = accountID;
		this.regApproved = regApproved;
		this.jointApproved = jointApproved;
		this.isEmployee = isEmployee;
		this.isAdmin = isAdmin;

	}

	public User() {
		super();
	}
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}
	
	public int getSSN(){
		return ssn;
	}
	
	public void setSSN(int ssn){
		this.ssn = ssn;
		
	}
	
	public String getStreetAddress(){
		return streetAddress;
	}
	
	public void setStreetAddress(String streetAddress){
		this.streetAddress = streetAddress;
	}
	
	public String getCity(){
		return city;
	}
	
	public void setCity(String city){
		this.city = city;
	}
	
	public String getState(){
		return state;
	}
	
	public void setState(String state){
		this.state = state;
	}
	
	public int getZip(){
		return zip;
	}
	
	public void setZip(int zip){
		this.zip = zip;
	}

	public int getAccountID() {
		return accountID;
	}

	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}
	
	public boolean getRegApproved() {
		return regApproved;
	}

	public void setRegApproved(boolean regApproved) {
		this.regApproved = regApproved;
	}
	
	public boolean getJointApproved() {
		return jointApproved;
	}

	public void setJointApproved(boolean jointApproved) {
		this.jointApproved = jointApproved;
	}
	
	public boolean getIsEmployee() {
		return isEmployee;
	}

	public void setIsEmployee(boolean isEmployee) {
		this.isEmployee = isEmployee;
	}
	
	public boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	
}
