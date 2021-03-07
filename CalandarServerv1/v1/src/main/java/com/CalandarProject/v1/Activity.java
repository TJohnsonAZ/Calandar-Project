package com.CalandarProject.v1;

public class Activity {
	
	private String activityName;
	private int completeColor = 0x000000;
	private int notCompleteColor = 0x000000;
	private static int activityID = 1;
	
	public Activity(String name, int color) {
		activityName = name;
		notCompleteColor = color;
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
}
