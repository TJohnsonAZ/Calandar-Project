package com.CalandarProject.v1;

public class Activity {
	
	private String activityName;
	private int completeColor = 0x000000;
	private int notCompleteColor = 0x000000;
	private static int activityID = 1;
	private Counter counter;
	
	
	public Activity( String name, int color, int endColor, Counter newCounter ) {
		activityName = name;
		notCompleteColor = color;
		completeColor = endColor;
		counter = newCounter;
		activityID++;
		
	}
	
	public String getActivityName() {
		return activityName;
	}
	
	public int getCompleteColor() {
		return completeColor;
	}
	
	public int getNotCompleteColor() {
		return notCompleteColor;
	}
	
	public int getActivityID() {
		return activityID;
	}
	
	public void saveActivity() {
		
	}
	
	static void returnAllActivities() {
		
	}
	
	static Activity getActivityWithID( int id ) {
		return new Activity();
	}
}
