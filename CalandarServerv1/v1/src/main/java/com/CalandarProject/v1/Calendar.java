package com.CalandarProject.v1;

public class Calendar {
	
	public class MonthNode{
		
		public Month monthData;
		public MonthNode nextMonth;
		public MonthNode previousMonth;
		
		public MonthNode() {
			monthData = null;
			nextMonth = null;
			previousMonth = null;
		}
		
		public MonthNode( String monthName, int year ) {
			monthData = new Month( monthName, year );
			nextMonth = null;
			previousMonth = null;
		}
		
		public MonthNode( Month month ) {
			monthData = month;
			nextMonth = null;
			previousMonth = null;
		}
		
		public void findFirstDay() {
			int previousFirstDay;
			int previousNumDays;
			if( previousMonth != null ) {
				previousFirstDay = previousMonth.monthData.getFirstDayNum();
				previousNumDays = previousMonth.monthData.getNumDays();
				monthData.setFirstDayOfMonth( (previousFirstDay + previousNumDays) % 7 );
			}
				
		}
		
	}
	
	private final String[] MONTH_LIST = {"JANUARY", "FEBRUARY", "MARCH", "APRIL", "MAY", "JUNE",
							"JULY", "AUGUST", "SEPTEMBER", "OCTOBER", "NOVEMBER", "DECEMBER"};
	private MonthNode headNode;
	private MonthNode tailNode;
	
	public Calendar( ) {
		headNode = null;
		tailNode = null;
	}
	
	//appends to the list of months
	public void addMonth( ) {
		MonthNode newMonth;
		int monthNum;
		if( headNode == null ) {
			newMonth = new MonthNode( MONTH_LIST[0], 2020 );
			headNode = newMonth;
			newMonth.monthData.setFirstDayOfMonth( 2 );
		}
		else {
			if( tailNode.monthData.getMonthName().equals(MONTH_LIST[11]) ) {
				newMonth = new MonthNode( MONTH_LIST[0], tailNode.monthData.getYear() + 1 );
				tailNode.nextMonth = newMonth;
			}
			else {
				monthNum = tailNode.monthData.getMonthNum();			
				newMonth = new MonthNode( MONTH_LIST[ monthNum + 1 ], tailNode.monthData.getYear() );
				tailNode.nextMonth = newMonth;
			}
			newMonth.previousMonth = tailNode;
			newMonth.findFirstDay();
		}
		tailNode = newMonth;
	}

	public Month findMonth( String monthName, int year ) {
		if( headNode == null ) {
			addMonth();
		}
		MonthNode currentNode = headNode;
		Month tmpMonth = new Month( monthName, year );
		while( currentNode.monthData.getMonthNum() != tmpMonth.getMonthNum() || currentNode.monthData.getYear() != year) {
			if( currentNode.nextMonth == null ) {
				addMonth();
			}
			currentNode = currentNode.nextMonth;
		}
		return currentNode.monthData;
	}
}
