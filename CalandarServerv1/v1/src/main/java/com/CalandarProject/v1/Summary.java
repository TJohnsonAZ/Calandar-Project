package com.CalandarProject.v1;

public class Summary {

    DayData[] summaryPeriod;

    public Summary(int startDate, int endDate) {
       summaryPeriod = new DayData[endDate - startDate + 1];
       int index;
       int dayIterator = startDate;

       // populate array with information about days in the given period
       for (index = 0; index < summaryPeriod.length; index++) {
          summaryPeriod[index] = DayDatabase.getDayData(String.valueOf(dayIterator));
          dayIterator++;
       }
    }

    public DayData[] getsummaryPeriod() {
       return summaryPeriod;
    }
}
