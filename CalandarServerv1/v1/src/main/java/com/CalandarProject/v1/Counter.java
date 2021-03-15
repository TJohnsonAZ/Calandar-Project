package com.CalandarProject.v1;

public class Counter {
	
	private int runningTotal;
	private String unitsForActivity;
	private int daysBetweenCounts;
	
	public Counter( ) {
		this.runningTotal = 0;
		this.unitsForActivity = "";
		this.daysBetweenCounts = 1;
	}
	
	public Counter( String units, int daysBetween ) {
		this.runningTotal = 0;
		this.unitsForActivity = units;
		this.daysBetweenCounts = daysBetween;
	}
	

	public void setUnitsForActivity( String units ) {
		this.unitsForActivity = units;
	}
	
	
	public void setDaysBetweenCounts( int daysBetween) {
		this.daysBetweenCounts = daysBetween;
	}
	
	public void incrementTotal() {
		runningTotal++;
	}
	
	
}
