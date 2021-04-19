package com.CalandarProject.v1.UserTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.CalandarProject.v1.User.User;
import com.CalandarProject.v1.User.UserDatabase;

import java.util.ArrayList;
import java.util.List;

class UserTests {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.out.println("Begin User Tests\n");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		System.out.println("\nEnd User Tests");
	}

	@Test
	void userSetAndGetUsername() {
		User u = new User("");
		assertEquals( "", u.getUsername() );
		String tempName = "username";
		u.setUsername( tempName );
		assertEquals(tempName, u.getUsername());
		System.out.println("Username set and get: " + u.getUsername() );				
	}
	
	@Test
	void checkUserIdAssignment() {
		User u = new User();
		String tempId = null;
		assertNotEquals(tempId, u.getUserID());
		System.out.println("UserId get: " + u.getUserID() );
	}
	
	@Test
	void checkEquals() {
		User user1 = new User();
		User user2 = user1; 
		assert( user1.equals( user2 ));
		System.out.println( "User 1 equals User 2" );
	}
	
	@Test
	void userCsvWrite() {
		System.out.println("Placeholder for CSV Write");
	}

	@Test
	void userCsvRead() {
		System.out.println("Placeholder for CSV Read");
	}
	
	@Test
	void userDatabaseAdd() {
		UserDatabase.resetUsers();
		User testUser = new User( "testUser" );
		UserDatabase.addUser( testUser );
		assert( testUser.equals( UserDatabase.getUser( testUser.getUserID()) ));
		System.out.println( "User added successfully" );
	}
	
	@Test
	void userDatabaseGetUser() {
		UserDatabase.resetUsers();
		User testUser = UserDatabase.addUser( new User("Test"));
		assert( testUser.equals(UserDatabase.getUser(testUser.getUserID())));
		System.out.println( "User added successfully" );
	}
	
	@Test
	void userDatabaseUpdate() {
		UserDatabase.resetUsers();
		User testUser = new User("FailedTest");
		User passedTest = new User( "PassedTest" );
		System.out.println( "\ntestUser:" + testUser.toString() );
		System.out.println( "passedTest" + passedTest.toString() );
		UserDatabase.addUser( testUser );
		System.out.println( "Updating testUser..." );
		UserDatabase.updateUser(testUser.getUserID(), passedTest );
		assert( !UserDatabase.getUser( testUser.getUserID() ).getUsername().equals( "FailedTest" ) );
		System.out.println( "testUser:" + testUser.toString() );
		System.out.println( "passedTest" + passedTest.toString() );
		System.out.println( "User successfully updated\n" );
	}
	
	@Test
	void userDatabaseGet() {
		UserDatabase.resetUsers();
		ArrayList<User> testUserList = new ArrayList<User>();
		int index;
		for( index = 1; index <= 100; index++ ) {
			User user = new User( index + "" );
			testUserList.add( user );
			UserDatabase.addUser( user );
		}
		List<User> database = UserDatabase.getAllUsers();
		for ( index = 0; index < 100; index++ ) {
		assert( testUserList.get( index ).equals( database.get( index )) );
		}
		System.out.println( "Added users are all the same");
	}
}
