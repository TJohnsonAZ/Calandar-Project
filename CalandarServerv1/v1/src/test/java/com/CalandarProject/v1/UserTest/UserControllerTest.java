package com.CalandarProject.v1.UserTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.CoreMatchers.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.CalandarProject.v1.User.User;
import com.CalandarProject.v1.User.UserController;
import com.CalandarProject.v1.User.UserDatabase;

@WebMvcTest(UserController.class)
public class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@BeforeEach
	void setUp() throws Exception {
		UserDatabase.addUser(new User("name"));
	}
	
	@AfterEach
	void tearDown() throws Exception {
		UserDatabase.resetUsers();
	}

	@Test
	void shouldBeAbleToRetrieveAllUsers() throws Exception {
		this.mockMvc.perform(get("/user"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$[0].username", is("name")));
	}
	
	@Test
	void shouldBeAbleToCreateUser() throws Exception {
		this.mockMvc.perform(post("/user?username=name")
		.contentType(MediaType.APPLICATION_JSON)
		.content("{\"username\":\"name\"}"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.username", is("name")));
	}
	
}
