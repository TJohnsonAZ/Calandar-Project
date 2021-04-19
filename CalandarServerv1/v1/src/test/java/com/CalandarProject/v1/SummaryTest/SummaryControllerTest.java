package com.CalandarProject.v1.SummaryTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.CoreMatchers.*;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import com.CalandarProject.v1.DayData.DayData;
import com.CalandarProject.v1.DayData.DayDatabase;
import com.CalandarProject.v1.Summary.SummaryController;

@WebMvcTest(SummaryController.class)
public class SummaryControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@BeforeEach
	void setUp() throws Exception {
		DayDatabase.addDayData(new DayData("1","1","2","2","2","1"));
		DayDatabase.addDayData(new DayData("1","2","2","2","2","1"));
		DayDatabase.addDayData(new DayData("1","3","3","2","2","1"));
		DayDatabase.addDayData(new DayData("1","4","2","1","1","1"));
		DayDatabase.addDayData(new DayData("1","5","2","2","2","1"));
	}
	
	@AfterEach
	void tearDown() throws Exception {
		DayDatabase.resetDays();
	}
	
	
//	Test fails - No value at JSON path "$.startDate"
//	
//	Body = {"completeCounter":[1,0,0,0],"incompleteCounter":[4,4,4,0],
//	"clearCounter":[0,1,1,5],"highestStreak":[1,0,0,0],
//	"numActivities":4,"highestOverallStreak":1}
//	
	@Test
	void shouldBeAbleToGetSummary() throws Exception {
		this.mockMvc.perform(get("/summary?startDate=1&endDate=5&user=1"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.startDate", is(String.valueOf(1))))
		.andExpect(jsonPath("$.endDate", is(String.valueOf(5))))
		.andExpect(jsonPath("$.user", is("1")));
	}
	
}
