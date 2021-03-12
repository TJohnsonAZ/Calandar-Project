package com.CalandarProject.v1;

public class Day {

	private Month month;
	private Note note;
	private int year;
	private int dayInMonth;
	
	public Day( Month monthName, int dayNum ) {
		month = monthName;
		year = monthName.getYear();
		dayInMonth = dayNum;
	}
	
	public void writeDayToFile() {
		
	}
	
}
