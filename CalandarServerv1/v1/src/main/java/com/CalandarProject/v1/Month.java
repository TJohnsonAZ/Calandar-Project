package com.CalandarProject.v1;

public class Month{
		private final String[] MONTHS = { "JANUARY", "FEBRUARY", "MARCH", "APRIL",
				"MAY", "JUNE", "JULY", "AUGUST", "SEPTEMBER", "OCTOBER", "NOVEMBER", "DECEMBER" };	
		
		private String month;
		private String firstDayOfMonth;
		private int numDays;
		private int year;
	
		public Month( String monthName, int yearInt ) {
			month = monthName;
			year = yearInt;
			numDays = daysInMonth( month, year );
		}
	
		private int daysInMonth( String month, int year ) {
			switch( month ) {

			case "FEBUARY":
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
	
		public int getYear() {
			return year;
		}
		
		public String getMonthName() {
			return month;
		}
		
		public void setFirstDayOfMonth( String day ) {
			firstDayOfMonth = day;
		}
	}

