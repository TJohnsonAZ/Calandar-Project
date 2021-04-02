package com.CalandarProject.v1;

public class Summary {

    DayData[] summaryPeriod;
    int completeCounter, incompleteCounter, clearCounter, highestStreak;

    public Summary(int startDate, int endDate) {
       summaryPeriod = new DayData[endDate - startDate + 1];
       int index;
       int dayIterator = startDate;
       completeCounter = 0;
       incompleteCounter = 0;
       clearCounter = 0;
       highestStreak = 0;
       int currentStreak = 0;

       // populate array with information about days in the given period and get summary information
       for (index = 0; index < summaryPeriod.length; index++) {
           summaryPeriod[index] = DayDatabase.getDayData(String.valueOf(dayIterator));

          // check for day complete
          if (summaryPeriod[index].getActivity1DayStatus() == "3") {
              completeCounter++;
              currentStreak++;
          }

          // otherwise, assume day not complete
          else {
              // check for current streak greater than current highest
              if (currentStreak > highestStreak) {
                  highestStreak = currentStreak;
              }

              // reset streak
              currentStreak = 0;

              // check for day not complete
              if (summaryPeriod[index].getActivity1DayStatus() == "2") {
                  incompleteCounter++;
              }

              // otherwise, assume day not marked
              else {
                  clearCounter++;
              }
          }

          dayIterator++;
       }
    }

    public DayData[] getsummaryPeriod() {
        return summaryPeriod;
    }

    public int getCompleteCounter() {
        return completeCounter;
    }

    public int getIncompelteCounter() {
        return incompleteCounter;
    }

    public int getClearCounter() {
        return clearCounter;
    }

    public int getHighestStreak() {
        return highestStreak;
    }
}