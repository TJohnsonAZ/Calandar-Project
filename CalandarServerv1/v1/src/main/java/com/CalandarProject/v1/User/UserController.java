package com.CalandarProject.v1.User;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.CalandarProject.v1.dayData.DayData;
import com.CalandarProject.v1.dayData.DayDatabase;

@RestController
public class UserController {
	
	// Create user object
	@PostMapping("/user")
	public User createUser(@RequestParam(value = "username") String username) {
		User user = new User(username);
		int daysInYear = 365;
		for(int i = 1; i <= daysInYear; i++) {
			DayDatabase.addDayData(new DayData(user.getUserID(), String.valueOf(i)));
		}
		return 	UserDatabase.addUser(user);
	}
	
	// retrieves user object from the database
	@GetMapping("/user")
	public Object retrieveUser(@RequestParam(value = "userID", required = false) String userID) {
		if(userID != null) {
		 	return UserDatabase.getUser( userID );	
		}
		return UserDatabase.getAllUsers();
	}
	
	// updates user with a new username
	@PutMapping("/user")
	public User updateUser( @RequestParam(value = "userID", required = true ) String userID,
									@RequestBody User user) {
		return UserDatabase.updateUser(userID, user);
	}
}
