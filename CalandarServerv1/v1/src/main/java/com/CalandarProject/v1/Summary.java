package com.CalandarProject.v1;

public class Summary {

    DayData[] summaryPeriod;

    public Summary(int startDate, int endDate) {
       summaryPeriod = new DayData[endDate - startDate + 1];
       int index;
       int dayIterator = startDate;

       //create week array starting at given date
       for (index = 0; index < summaryPeriod.length; index++) {
          summaryPeriod[index] = DayDatabase.getDayData(String.valueOf(dayIterator));
          dayIterator++;
       }
    }

    public DayData[] getWeekArray() {
       return summaryPeriod;
    }
}
