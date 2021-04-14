package com.CalandarProject.v1.Tests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.UUID;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.CsvSource;

import com.CalandarProject.v1.dayData.DayData;

class DayDataTests {
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.out.println("Begin dayData Tests\n");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		System.out.println("\nEnd dayData Tests");
	}
	
	@Test
	void dayDataSetAndGetUserId() {
		DayData dd = new DayData();
		String tempName = UUID.randomUUID().toString();
		dd.setUser(tempName);
		assertEquals(tempName, dd.getUser());
		System.out.print("dayData set and get userId: ");
		
		if(tempName != dd.getUser()) {
			fail("Fail");
		}
		else {
			System.out.println("		Pass");
		}
	}
	
	@Test
	void dayDataSetAndGetActivity1() {
		DayData dd = new DayData();
		String tempValue = "10";
		dd.setActivity1DayStatus(tempValue);
		assertEquals(tempValue, dd.getActivity1DayStatus());
		System.out.print("dayData set and get activity1 status: ");
		
		if(tempValue != dd.getActivity1DayStatus()) {
			fail("Fail");	
		}
		else {
			System.out.println("	Pass");
		}
	}
	
	@Test
	void dayDataSetAndGetActivity2() {
		DayData dd = new DayData();
		String tempValue = "20";
		dd.setActivity2DayStatus(tempValue);
		assertEquals(tempValue, dd.getActivity2DayStatus());
		System.out.print("dayData set and get activity2 status: ");
		
		if(tempValue != dd.getActivity2DayStatus()) {
			fail("Fail");
		}
		else {
			System.out.println("	Pass");
		}
	}
	
	@Test
	void dayDataSetAndGetActivity3() {
		DayData dd = new DayData();
		String tempValue = "30";
		dd.setActivity3DayStatus(tempValue);
		assertEquals(tempValue, dd.getActivity3DayStatus());
		System.out.print("dayData set and get activity3 status: ");
		
		if(tempValue != dd.getActivity3DayStatus()) {
			fail("Fail");
		}
		else {
			System.out.println("	Pass");
		}
	}

	@Test
	void dayDataSetAndGetActivity4() {
		DayData dd = new DayData();
		String tempValue = "40";
		dd.setActivity4DayStatus(tempValue);
		assertEquals(tempValue, dd.getActivity4DayStatus());
		System.out.print("dayData set and get activity4 status: ");
		
		if(tempValue != dd.getActivity4DayStatus()) {
			fail("Fail");
		}
		else {
			System.out.println("	Pass");
		}
	}
	
	@Test
	void dayDataGetDayOfYear() {
		
		String day = String.valueOf((int) (Math.random() * 100));
		
		DayData dd = new DayData("userID", day);
		String tempDay = day;
		assertEquals(tempDay, dd.getDayOfYear());
		System.out.print("dayData get dayOfYear: ");
		
		if(tempDay != dd.getDayOfYear()) {
			fail("Fail");
		}
		else {
			System.out.println("			Pass");
		}
	}
	
	@Test
	@CsvSource({
		"1", "101", "1", "1", "1", "1",
		"1", "102", "2", "2", "1", "1",
		"1", "103", "3", "2", "2", "1"
	})
//	void dayDataCSVRead(String userID, String day, String activity1, String activity2, 
//			String activity3, String activity4) {
		
	void dayDataCSVRead() {
		System.out.println("Placeholder for CSV Read");
		
//		String[] items = new String[] {userID, day, activity1, activity2, activity3, activity4};
//		String[] ddItems = new String[6];
//		DayData dd = new DayData(userID, day, activity1, activity2, activity3, activity4);
//		ddItems[0] = dd.getUser();
//		ddItems[1] = dd.getDayOfYear();
//		ddItems[2] = dd.getActivity1DayStatus();
//		ddItems[3] = dd.getActivity2DayStatus();
//		ddItems[4] = dd.getActivity3DayStatus();
//		ddItems[5] = dd.getActivity4DayStatus();
//		assertArrayEquals(items, ddItems);
//		System.out.print("dayData CSV read: ");
//		
//		if(ddItems.equals(items)) {
//			System.out.println("Pass");
//		}
//		else {
//			fail("Fail");
//		}
	}
	
	@Test
	void dayDataCSVWrite() {
		System.out.println("Placeholder for CSV Write");
	}
	
	@Test
	void dayDataDatabaseAdd() {
		System.out.println("Placeholder for Database Add");
	}
	
	@Test
	void dayDataDatabaseUpdate() {
		System.out.println("Placeholder for Database Update");
	}
	
	@Test
	void dayDataDatabaseGet() {
		System.out.println("Placeholder for Database Get");
	}
	
	@Test
	void dayDataDatabaseUserGet() {
		System.out.println("Placeholder for Database Get by User");
	}
}
