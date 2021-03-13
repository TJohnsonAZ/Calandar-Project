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
		
		
		
	}
	
	private final String[] MONTH_LIST = {"JANUARY", "FEBUARY", "MARCH", "APRIL", "MAY", "JUNE",
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
		if( headNode == null ) {
			newMonth = new MonthNode( MONTH_LIST[0], 2020 );
			headNode = newMonth;
		}
		if( tailNode.monthData.getMonthName() == MONTH_LIST[11] ) {
			newMonth = new MonthNode( MONTH_LIST[0], tailNode.monthData.getYear() + 1 );
			tailNode.nextMonth = newMonth;
		}
		else {
			String monthName = tailNode.monthData.getMonthName();
			int monthNum = findMonthNum( monthName );
			
			newMonth = new MonthNode( MONTH_LIST[ monthNum + 1 ], tailNode.monthData.getYear() );
			tailNode.nextMonth = newMonth;
		}
		tailNode = newMonth;
	}
	
	private int findMonthNum( String month ) {
		int index;
		for( index = 0; index < MONTH_LIST.length; index++ ) {
			if ( month.equals( MONTH_LIST[ index ] )){
				return index;
			}
		}
		//error statement
		return -99999999;
	}

	//TODO
	public Month findMonth( String monthName, int year ) {
		return headNode.monthData;
	}
}
