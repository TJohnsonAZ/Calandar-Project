package com.CalandarProject.v1;

public class Counter {
	
	private int runningTotal;
	private String unitsForActivity;
	private int daysBetweenCounts;
	
	public Counter( ) {
		runningTotal = 0;
		unitsForActivity = "";
		daysBetweenCounts = 1;
	}
	
	public Counter( String units, int daysBetween ) {
		runningTotal = 0;
		unitsForActivity = units;
		daysBetweenCounts = daysBetween;
	}
	

	public void setUnitsForActivity( String units ) {
		unitsForActivity = units;
	}
	
	
	public void setDaysBetweenCounts( int daysBetween) {
		daysBetweenCounts = daysBetween;
	}
	
	public void incrementTotal() {
		runningTotal++;
	}
	
	
}
