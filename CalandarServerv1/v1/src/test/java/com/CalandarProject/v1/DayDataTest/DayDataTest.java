package com.CalandarProject.v1.DayDataTest;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.CalandarProject.v1.DayData.DayData;
import com.CalandarProject.v1.DayData.DayDatabase;
import com.CalandarProject.v1.User.User;
import com.CalandarProject.v1.User.UserDatabase;

class DayDataTest {
	
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
	void dayDataCSVWrite() {
		System.out.println("Placeholder for CSV Write");
	}
	
	@Test
	void dayDataDatabaseAdd() {
		DayDatabase.resetDays();
		DayData testDay = new DayData( "test", "444", "1", "2", "3", "4" );
		DayDatabase.addDayData(testDay);
		DayData addedDay = DayDatabase.addDayData( testDay );
		assert( testDay.getUser().equals( addedDay.getUser() ) &&
				testDay.getDayOfYear().equals( addedDay.getDayOfYear()) &&
				testDay.getActivity1DayStatus().equals(addedDay.getActivity1DayStatus() ) &&
				testDay.getActivity2DayStatus().equals(addedDay.getActivity2DayStatus()) &&
				testDay.getActivity3DayStatus().equals(addedDay.getActivity3DayStatus()) &&
				testDay.getActivity4DayStatus().equals(addedDay.getActivity4DayStatus())
				);
		System.out.println( "Day added successfully" );
	}
	
	@Test
	void dayDataDatabaseUpdate() {
		DayDatabase.resetDays();
		DayData testDay = new DayData();
		DayDatabase.addDayData( testDay );
		DayData passDay = new DayData();
		passDay.setActivity1DayStatus("2");
		DayDatabase.updateDayData(testDay.getDayOfYear(), passDay );
		assert( DayDatabase.getDayData( testDay.getDayOfYear(), testDay.getUser() )
				.getActivity1DayStatus()
				.equals( "2" ));
		System.out.println( "Day successfully updated" );
	}
	
	@Test
	void dayDataDatabaseGet() {
		DayDatabase.resetDays();
		DayData testData = DayDatabase.addDayData( new DayData() );
		assert( testData == DayDatabase.getDayData("1", ""));
		System.out.println( "Day gotten successfully" );
	}
	
	@Test
	void dayDataDatabaseUserGet() {
		UserDatabase.resetUsers();
		DayDatabase.resetDays();
		int index;
		String[] userIDs = new String[ 100 ];
		for( index = 0; index < 100; index++ ) {
			userIDs[ index ] = createUser( index + "" ).getUserID();
		}
		List<DayData> testList;
		List<DayData> output = DayDatabase.getAllDayData();
		for( index = 0; index < 100; index++ ) {
			System.out.println( userIDs[ index] );
			testList = DayDatabase.getDayDataByUser( userIDs[ index ] );
			assertEquals(365, testList.size() );
			assertEquals( userIDs[ index ], testList.get( index ).getUser());
		}
	}
	
	
	public User createUser( String username) {
		User user = new User(username);
		int daysInYear = 365;
		for(int i = 1; i <= daysInYear; i++) {
			DayDatabase.addDayData(new DayData(user.getUserID(), String.valueOf(i)));
		}
		return 	UserDatabase.addUser(user);
	}

}
