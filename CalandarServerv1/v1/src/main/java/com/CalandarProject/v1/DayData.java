package com.CalandarProject.v1;

public class DayData {

	private String dayOfYear;
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
		this.dayOfYear = dayOfMonth;
		this.defaultColor = defaultColor;
		this.incompleteColor = incompleteColor;
		this.completeColor = completeColor;
		this.setColor = setColor;
	}
	
	public DayData(String dayNum) {
		this();
		dayOfYear = dayNum;
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

	public String getDayOfYear() {
		return dayOfYear;
	}
	
	@Override
	public String toString() {
		return "DayData [dayOfYear=" + dayOfYear + ", defaultColor=" + defaultColor + ", incompleteColor="
				+ incompleteColor + ", completeColor=" + completeColor + ", setColor=" + setColor + "]";
	}

	public String[] toStringArray() {
		return new String[] {String.valueOf(dayOfYear), defaultColor, incompleteColor, completeColor, setColor}; 
	}
	
}
