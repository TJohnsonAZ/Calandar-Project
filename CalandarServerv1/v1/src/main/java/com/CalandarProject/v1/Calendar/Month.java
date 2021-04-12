package com.CalandarProject.v1;

public class Month{
		private final String[] MONTHS = { "JANUARY", "FEBRUARY", "MARCH", "APRIL",
				"MAY", "JUNE", "JULY", "AUGUST", "SEPTEMBER", "OCTOBER", "NOVEMBER", "DECEMBER" };	
		private final String[] DAYS = {"MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY", "SUNDAY"};
		private String month;
		private String firstDayOfMonth;
		private int numDays;
		private int year;
		private int absDayNum;
		
		private int monthNum;
		private int firstDayNum;
	
		/**
		 * Month constructor
		 * @param monthName Name of the month
		 * @param yearInt Year of the month you want to find
		 */
		public Month( String monthName, int yearInt ) {
			month = monthName;
			year = yearInt;
			numDays = daysInMonth( month, year );
			monthNum = findMonthNum( month );
		}
		
		/**
		 * Finds the number of a given month to make calculations easier
		 * @param month Month you want to find the number of 
		 * @return the number of the month, where 0 is January and 11 is December
		 */
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
	
		/**
		 * Finds the days in a given month, primarily for Feburuary
		 * @param month month that you want to find the number of days for
		 * @param year the year the month has
		 * @return the amount of days in the month
		 */
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
		
		/**
		 * Sets the first day of the month. To be used with the MonthNode class
		 * @param dayNum the day of the week as an integer, 0 is Monday, and 6 is Sunday
		 */
		public void setFirstDayOfMonth( int dayNum ) {
			firstDayNum = dayNum % 7;
			firstDayOfMonth = DAYS[ firstDayNum ];
		}
		
		public void setAbsDayNum( int absDayNum ) {
			this.absDayNum = absDayNum;
		}
		
		public String getFirstDayOfMonth() {
			return firstDayOfMonth;
		}
		
		public int getFirstDayNum() {
			return firstDayNum;
		}
		
		public int getAbsDayNum() {
			return absDayNum;
		}
		
		public int getMonthNum() {
			return monthNum;
		}
	}

