package com.CalandarProject.v1;

public class Month{
		private final String[] MONTHS = { "JANUARY", "FEBRUARY", "MARCH", "APRIL",
				"MAY", "JUNE", "JULY", "AUGUST", "SEPTEMBER", "OCTOBER", "NOVEMBER", "DECEMBER" };	
		private final String[] DAYS = {"MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY", "SUNDAY"};
		private String month;
		private String firstDayOfMonth;
		private int numDays;
		private int year;
		
		private int monthNum;
		private int firstDayNum;
	
		public Month( String monthName, int yearInt ) {
			month = monthName;
			year = yearInt;
			numDays = daysInMonth( month, year );
			monthNum = findMonthNum( month );
		}
		
		private int findMonthNum( String month ) {
			int index;
			for( index = 0; index < MONTHS.length; index++ ) {
				if ( month.equals( MONTHS[ index ] )){
					return index;
				}
			}
			//error statement
			return -99999999;
		}
	
		private int daysInMonth( String month, int year ) {
			switch( month ) {

			case "FEBRUARY":
				if ( year % 4 == 0 && year % 400 != 0) {
					return 29;
				}
				return 28;
			
			case "APRIL":
				return 30;
			
			case "JUNE":
				return 30;

			case "SEPTEMBER":
				return 30;

			case "NOVEMBER":
				return 30;
			
			default:
				return 31;
			}
		}
	
		public int getNumDays() {
			return numDays;
		}
		
		public int getYear() {
			return year;
		}
		
		public String getMonthName() {
			return month;
		}
		
		public void setFirstDayOfMonth( int dayNum ) {
			firstDayNum = dayNum % 7;
			firstDayOfMonth = DAYS[ firstDayNum ];
		}
		
		public String getFirstDayOfMonth() {
			return firstDayOfMonth;
		}
		
		public int getFirstDayNum() {
			return firstDayNum;
		}
		
		public int getMonthNum() {
			return monthNum;
		}
	}

