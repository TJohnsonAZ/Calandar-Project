package com.CalandarProject.v1.User;

import java.util.UUID;

public class User {
	
	private String username;
	private String userID;
	
	
	public User() {
		this.userID = UUID.randomUUID().toString();
		username = "Unnamed User " + userID;
	}
	
	/**
	 * Creates a new user. Generates random, unique ID
	 * @param username chosen username
	 */
	public User( String username ) {
		this.userID = UUID.randomUUID().toString();
		this.username = username;
	}
	
	/**
	 * Creates a new User, used for updating an existing user
	 * @param username new username for the user
	 * @param userID existing userID for the user
	 */
	public User( String username, String userID ) {
		this.username = username;
		this.userID = userID;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername( String newUsername) {
		username = newUsername;
	}
	
	public String getUserID() {
		return userID;
	}
	
	@Override
	public String toString() {
		return "DayData [Username=" + username + ", UserID=" + userID + "]";
	}

	public String[] toStringArray() {
		return new String[] {username, userID}; 
	}
	
}
