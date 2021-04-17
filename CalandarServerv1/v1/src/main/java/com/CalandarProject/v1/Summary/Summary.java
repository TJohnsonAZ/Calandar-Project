package com.CalandarProject.v1.Summary;

import com.CalandarProject.v1.DayData.DayData;
import com.CalandarProject.v1.DayData.DayDatabase;

public class Summary {

    DayData[] summaryPeriod;
    int[] completeCounter, incompleteCounter, clearCounter, highestStreak;
    int currentStreak1, currentStreak2, currentStreak3, currentStreak4, numActivities, highestOverallStreak;

    /*
     * Initialization constructor that gets all information for a specified user within a specified date range
     *
     * @param integer value of first day in date range
     *
     * @param integer value of last day in date range
     *
     * @param ID of user to get information on
     */
    public Summary(int startDate, int endDate, String user) {
       // initialize variables
       summaryPeriod = new DayData[endDate - startDate + 1];
       completeCounter = new int[4];
       incompleteCounter = new int[4];
       clearCounter = new int[4];
       highestStreak = new int[4];
       int index;
       numActivities = 0;
       int dayIterator = startDate;
       highestOverallStreak = 0;
       currentStreak1 = 0;
       currentStreak2 = 0;
       currentStreak3 = 0;
       currentStreak4 = 0;
       Boolean loopEnd = false;

       // populate array with information about days in the given period and get summary information
       for (index = 0; index < summaryPeriod.length; index++) {
           summaryPeriod[index] = DayDatabase.getDayData(String.valueOf(dayIterator), user);

           analyzeDay(summaryPeriod[index]);

           dayIterator++;
       }

       // get number of activities and overall highest streak
       index = 0;
       while (!loopEnd) {
           if (highestStreak[index] != -1) {
               numActivities++;

               if (highestStreak[index] > highestOverallStreak) {
                   highestOverallStreak = highestStreak[index];
               }
           }

           else {
               loopEnd = true;
           }

           index++;
       }
    }

    /*
     * Takes the information of a single day and updates summary data accordingly
     *
     * @param DayData object to be analyzed
     */
    private void analyzeDay(DayData day) {

         // check for first activity complete
         if (day.getActivity1DayStatus().equals("3")) {
             completeCounter[0]++;
             currentStreak1++;
             if (currentStreak1 > highestStreak[0]) {
                 highestStreak[0] = currentStreak1;
             }
         }

         // otherwise, check for incomplete
         else if (day.getActivity1DayStatus().equals("2")) {
             incompleteCounter[0]++;
             currentStreak1 = 0;
         }

         // otherwise, check for not marked
         else if (day.getActivity1DayStatus().equals("1")) {
             clearCounter[0]++;
             currentStreak1 = 0;
         }

         // otherwise, assume activity does not exist
         else {
             completeCounter[0] = -1;
             incompleteCounter[0] = -1;
             clearCounter[0] = -1;
             highestStreak[0] = -1;
         }

         /*
          * repeat process for remaining activities
          */

         // activity 2
         if (day.getActivity2DayStatus().equals("3")) {
             completeCounter[1]++;
             currentStreak2++;
             if (currentStreak2 > highestStreak[1]) {
                 highestStreak[1] = currentStreak2;
             }
         }

         else if (day.getActivity2DayStatus().equals("2")) {
             incompleteCounter[1]++;
             currentStreak2 = 0;
         }

         else if (day.getActivity2DayStatus().equals("1")) {
             clearCounter[1]++;
             currentStreak2 = 0;
         }

         else {
             completeCounter[1] = -1;
             incompleteCounter[1] = -1;
             clearCounter[1] = -1;
             highestStreak[1] = -1;
         }

         // activity 3
         if (day.getActivity3DayStatus().equals("3")) {
             completeCounter[2]++;
             currentStreak3++;
             if (currentStreak3 > highestStreak[2]) {
                 highestStreak[2] = currentStreak3;
             }
         }

         else if (day.getActivity3DayStatus().equals("2")) {
             incompleteCounter[2]++;
             currentStreak3 = 0;
         }

         else if (day.getActivity3DayStatus().equals("1")) {
             clearCounter[2]++;
             currentStreak3 = 0;
         }

         else {
             completeCounter[2] = -1;
             incompleteCounter[2] = -1;
             clearCounter[2] = -1;
             highestStreak[2] = -1;
         }

         // activity 4
         if (day.getActivity4DayStatus().equals("3")) {
             completeCounter[3]++;
             currentStreak4++;
             if (currentStreak4 > highestStreak[3]) {
                 highestStreak[3] = currentStreak4;
             }
         }

         else if (day.getActivity4DayStatus().equals("2")) {
             incompleteCounter[3]++;
             currentStreak4 = 0;
         }

         else if (day.getActivity4DayStatus().equals("1")) {
             clearCounter[3]++;
             currentStreak4 = 0;
         }

         else {
             completeCounter[3] = -1;
             incompleteCounter[3] = -1;
             clearCounter[3] = -1;
             highestStreak[3] = -1;
         }
    }

    /*
     * getter methods that return needed summary information
     */

    public int[] getCompleteCounter() {
        return completeCounter;
    }

    public int[] getIncompleteCounter() {
        return incompleteCounter;
    }

    public int[] getClearCounter() {
        return clearCounter;
    }

    public int[] getHighestStreak() {
        return highestStreak;
    }

    public int getNumActivities() {
        return numActivities;
    }

    public int getHighestOverallStreak() {
        return highestOverallStreak;
    }
}
