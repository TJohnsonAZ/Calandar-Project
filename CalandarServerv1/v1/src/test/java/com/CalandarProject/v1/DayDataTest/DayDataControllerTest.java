package com.CalandarProject.v1.DayDataTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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

import com.CalandarProject.v1.DayData.DayData;
import com.CalandarProject.v1.DayData.DayDataController;
import com.CalandarProject.v1.DayData.DayDatabase;

@WebMvcTest(DayDataController.class)
public class DayDataControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@BeforeEach
	void setUp() throws Exception {
		DayDatabase.addDayData(new DayData("user", "10"));
	}
	
	@AfterEach
	void tearDown() throws Exception {
		DayDatabase.resetDays();
	}
	
	@Test
	void shouldBeAbleToRetrieveData() throws Exception {
		this.mockMvc.perform(get("/dayData"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$[0].dayOfYear", is("10")))
		.andExpect(jsonPath("$[0].user", is("user")));
	}
	
	@Test
	void shouldBeAbleToUpdateData() throws Exception {
		this.mockMvc.perform(put("/dayData?dayNum=10")
		.contentType(MediaType.APPLICATION_JSON)
		.content("{\"user\":\"user\",\"dayOfYear\":\"10\","
				+ "\"activity1DayStatus\":\"2\",\"activity2DayStatus\":\"2\","
				+ "\"activity3DayStatus\":\"1\",\"activity4DayStatus\":\"1\"}"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.user", is("user")))
		.andExpect(jsonPath("$.dayOfYear", is("10")))
		.andExpect(jsonPath("$.activity1DayStatus", is("2")))
		.andExpect(jsonPath("$.activity2DayStatus", is("2")))
		.andExpect(jsonPath("$.activity3DayStatus", is("1")))
		.andExpect(jsonPath("$.activity4DayStatus", is("1")));
	}
	
	
	
	
	
}
