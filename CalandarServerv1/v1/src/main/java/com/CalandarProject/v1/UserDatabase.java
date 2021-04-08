package com.CalandarProject.v1;

import java.util.ArrayList;
import java.util.List;

public class UserDatabase {


	private static List<User> userData = new ArrayList<User>();
		
		public static User addUser( User user ) {
			userData.add(user);
			return user;
		}
		
		public static User deleteUser( String id ) {
			User user = getUser( id );
			userData.remove( user );
			return user;
		}
		
		public static User updateUser( String id, User user ) {
			User userToUpdate = getUser( id );
			if( userToUpdate != null ) {
				userToUpdate.setUsername(user.getUsername());
			}
			return userToUpdate;
		}
		
		public static User getUser(String id) {
			for( int index = 0; index < userData.size(); index++ ) {
				if( userData.get(index).getUserID().equals(id) ) {
					return userData.get(index);
				}
			}
			return null;
		}
		
		public static List<User> getAllUsers() {
			return userData;
		}
		
}
