package com.CalandarProject.v1;

import java.util.UUID;

public class Activity {
	
	private String activityID;
	private String activityName;
	private String activityColor;
	private String activityDescription;
	private String activityComplete;
	private Counter counter;
	
	public Activity() {
		this.activityID = UUID.randomUUID().toString();
	}

	public Activity(String activityName, String activityColor, String activityDescription, String activityComplete) {
		this(UUID.randomUUID().toString(), activityName, activityColor, activityDescription, activityComplete);
	}
	
	public Activity(String activityID, String activityName, String activityColor, String activityDescription,  String activityComplete) {
		this.activityID = activityID;
		this.activityName = activityName;
		this.activityDescription = activityDescription;
		this.activityColor = activityColor;
		this.activityComplete = activityComplete;
	}
	
	public String getActivityName() {
		return activityName;
	}
	
	public String getActivityColor() {
		return activityColor;
	}
	
	public String getActivityID() {
		return activityID;
	}
	
	public String getActivityDescription() {
		return activityDescription;
	}
	
	public String getActivityComplete() {
		return activityComplete;
	}
	
	public void setActivityColor(String color) {
		this.activityColor = color;
	}
	
	public void setActivityName(String name) {
		this.activityName = name;
	}
	
	public void setActivityDescription(String description) {
		this.activityDescription = description;
	}

	public void setActivityComplete(String status) {
		this.activityComplete = status;
	}
	
	@Override
	public String toString() {
		return "Activity [activityID=" + activityID + ", activityName=" + activityName + ", activityColor="
				+ activityColor + ", activityDescription=" + activityDescription + ", activityComplete="
				+ activityComplete + "]";
	}

	public String[] toStringArray() {
		return new String[] {String.valueOf(activityID), activityName, activityColor, activityDescription, activityComplete};
	}
	
	
}
