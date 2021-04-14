package com.CalandarProject.v1.Tests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class SummaryTests {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.out.println("Begin Summary Tests\n");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		System.out.println("\nEnd Summary Tests");
	}

	@Test
	void summaryGetCompleteCounter() {
		System.out.println("Complete Counter Placeholder");
	}
	
	@Test
	void summaryGetIncompleteCounter() {
		System.out.println("Incomplete Counter Placeholder");
	}
	
	@Test
	void summaryGetClearCounter() {
		System.out.println("Clear Counter Placeholder");
	}
	
	@Test
	void summaryGetHighestStreak() {
		System.out.println("Num Activities Placeholder");
	}
	
	@Test
	void summaryGetNumbActivities() {
		System.out.println("Highest Overall Streak Placeholder");
	}
	
	@Test
	void summaryAnalyzeData() { 
		System.out.println("Analyze Data Placeholder");
	}

}
