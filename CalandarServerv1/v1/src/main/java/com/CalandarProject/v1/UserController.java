package com.CalandarProject.v1;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	
	// Create
	@PostMapping("/user")
	public User createUser(@RequestParam(value = "username") String username) {
		User user = new User(username);
		int daysInYear = 365;
		for(int i = 1; i <= daysInYear; i++) {
			DayDatabase.addDayData(new DayData(user.getUserID(), String.valueOf(i)));
		}
		return 	UserDatabase.addUser(user);
	}
	
	// retrieve
	@GetMapping("/user")
	public Object retrieveUser(@RequestParam(value = "userID", required = false) String userID) {
		if(userID != null) {
		 	return UserDatabase.getUser( userID );	
		}
		return UserDatabase.getAllUsers();
	}
	
	// update
	@PutMapping("/user")
	public User updateUser( @RequestParam(value = "userID", required = true ) String userID,
									@RequestBody User user) {
		return UserDatabase.updateUser(userID, user);
	}
	
	// delete
	@DeleteMapping("/user")
	public User removeActivity( @RequestParam(value = "userID", required = true) String userID) {
		return UserDatabase.deleteUser(userID);
	}
}