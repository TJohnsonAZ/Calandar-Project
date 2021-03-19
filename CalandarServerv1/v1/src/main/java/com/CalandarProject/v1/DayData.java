package com.CalandarProject.v1;

public class DayData {

	private String dayOfMonth;
	private String defaultColor;
	private String incompleteColor;
	private String completeColor;
	private String setColor;
		
	public DayData() {
		defaultColor = "white";
		incompleteColor = "red";
		completeColor = "green";
		setColor = defaultColor;
	}
	
	public DayData(String dayOfMonth, String defaultColor, String incompleteColor, String completeColor, String setColor) {
		this.dayOfMonth = dayOfMonth;
		this.defaultColor = defaultColor;
		this.incompleteColor = incompleteColor;
		this.completeColor = completeColor;
		this.setColor = setColor;
	}
	
	public DayData(String dayNum) {
		this();
		dayOfMonth = dayNum;
	}
	
	public String getDefaultColor() {
		return defaultColor;
	}
	public void setDefaultColor(String defaultColor) {
		this.defaultColor = defaultColor;
	}
	public String getIncompleteColor() {
		return incompleteColor;
	}
	public void setIncompleteColor(String incompleteColor) {
		this.incompleteColor = incompleteColor;
	}
	public String getCompleteColor() {
		return completeColor;
	}
	public void setCompleteColor(String completeColor) {
		this.completeColor = completeColor;
	}

	public String getSetColor() {
		return setColor;
	}

	public void setSetColor(String setColor) {
		this.setColor = setColor;
	}

	public String getDayOfMonth() {
		return dayOfMonth;
	}
	
	@Override
	public String toString() {
		return "DayData [dayOfMonth=" + dayOfMonth + ", defaultColor=" + defaultColor + ", incompleteColor="
				+ incompleteColor + ", completeColor=" + completeColor + ", setColor=" + setColor + "]";
	}

	public String[] toStringArray() {
		return new String[] {String.valueOf(dayOfMonth), defaultColor, incompleteColor, completeColor, setColor}; 
	}
	
}
