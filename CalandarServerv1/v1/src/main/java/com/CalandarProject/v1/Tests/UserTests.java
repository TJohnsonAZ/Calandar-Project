package com.CalandarProject.v1.Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.CalandarProject.v1.User.User;

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
		User u = new User("username");
		String tempName = "username";
		assertEquals(tempName, u.getUsername());
		System.out.print("Username set and get: ");
		
		if(tempName != u.getUsername()) {
			fail("Fail");
		}
		else {
			System.out.println("		Pass");
		}
	}
	
	@Test
	void userGetUserId() {
		User u = new User();
		String tempId = null;
		assertNotEquals(tempId, u.getUserID());
		System.out.print("UserId get: ");
		
		if(tempId == u.getUserID()) {
			fail("Fail");
		}
		else {
			System.out.println("			Pass");
		}
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
		System.out.println("Placeholder for User Database Add");
	}
	
	@Test
	void userDatabaseUpdate() {
		System.out.println("Placeholder for User Database Update");
	}
	
	@Test
	void userDatabaseGet() {
		System.out.println("Placeholder for User Database Get");
	}
}
