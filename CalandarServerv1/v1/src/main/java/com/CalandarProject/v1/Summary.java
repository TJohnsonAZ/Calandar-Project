package com.CalandarProject.v1;

public class Summary {

    DayData[] week;

    public Summary(int startDate) {
       week = new DayData[7];
       int index;
       int dayIterator = startDate;

       //create week array starting at given date
       for (index = 0; index < week.length; index++) {
          week[index] = DayDatabase.getDayData(String.valueOf(dayIterator));
          dayIterator++;
       }
    }

    public DayData[] getWeekArray() {
       return week;
    }
}
