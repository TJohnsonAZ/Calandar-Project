package com.CalandarProject.v1;

public class Activity {
	
	private String activityName;
	private int completeColor;
	private int notCompleteColor;
	private static int activityID = 1;
	private Counter counter;
	
	
	public Activity( String name, int color, int endColor, Counter newCounter ) {
		activityName = name;
		notCompleteColor = color;
		completeColor = endColor;
		counter = newCounter;
		activityID++;
		
	}
	
	public Activity( ) {
		activityName = "New Activity" + activityID;
		notCompleteColor = 0xFFFFFF;
		completeColor = 0x000000;
		counter = new Counter();
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
