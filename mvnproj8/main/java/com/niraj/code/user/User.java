package com.niraj.code.user;

//The user data will be sent to us by the service which is sending the 
// Bid or auction
public class User {

	private String userId;
	private String lastName;
	private String firstName;

	
	public User(String login, String lastName, String firstName) {

		this.userId = login;
		this.lastName = lastName;
		this.firstName = firstName;
	}
	/**
	 * @return the loginId
	 */
	public String getLoginId() {
		return userId;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	
	
	
}