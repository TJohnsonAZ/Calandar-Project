package com.example.calandarapp;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Calandar extends AppCompatActivity {

    /**
     * Abstract java calendar class
     */
    private static final Calendar calendar = Calendar.getInstance();

    /**
     * Map to go from month num 0-11 to month name
     */
    public Map<Integer, String> monthNumToName
            = new HashMap<Integer, String>();

    /**
     * Map to go from month name to number 0-11
     */
    public Map<String, Integer> monthNameToNum
            = new HashMap<String, Integer>();

    /**
     * variable to tell what current year we are in
     */
    private final int year;

    /**
     * variable to tell what currentMonth we are in, automatically set to January
     */
    private int currentMonth = 0;

    /**
     * Create an Array list of months
     */
    private final ArrayList<Month> months = new ArrayList<Month>();

    /**
     * Max number of months in a year
     */
    private final int MAX_NUM_OF_MONTHS = 12;

    /**
     * Constructor method for calandar class
     * Sets the current year, sets the date to january 1
     * makes all the hashMaps, and populates the array list of months
     *
     * @param startingYear - the starting year the calendar will start on
     */
    public Calandar(int startingYear)
    {
        this.year = startingYear;
        calendar.set(startingYear, currentMonth, 1);
        makeHashMaps();
        int index;
        for (index = 0; index < MAX_NUM_OF_MONTHS; index++)
        {
            Month addMonth = new Month(index, monthNumToName.get(index), startingYear);
            months.add(addMonth);
        }

    }

    /**
     * gets the starting dat of each month
     * @param year - year that we are looking for
     * @param monthName - month that we want the 1st day of week for
     * @return - day of week that the month starts
     */
    public int getStartingDay(int year, String monthName)
    {
        calendar.set(year, monthNameToNum.get(monthName), 1);
        return calendar.get(Calendar.DAY_OF_WEEK) - 1;
    }

    /**
     * changes the current month of the calendar to the next month
     *
     * Note: if calendar is on December,11, then it will loop back January,0,
     */
    public void nextMonth()
    {
        if (currentMonth >= 11)
        {
            currentMonth = 0;
        }
        else
        {
            currentMonth += 1;
        }
    }

    /**
     * Change the current month to the last month
     *
     * Note: if calender is on January,0, then it will go to December,11,
     */
    public void preMonth() {
        if (currentMonth <= 0)
        {
            currentMonth = 11;
        }
        else
        {
            currentMonth -= 1;
        }
    }

    /**
     * Creates all the hashMaps for going back and forth from month number and month name
     */
    private void makeHashMaps()
    {
        monthNumToName.put(0, "January");
        monthNumToName.put(1, "February");
        monthNumToName.put(2, "March");
        monthNumToName.put(3, "April");
        monthNumToName.put(4, "May");
        monthNumToName.put(5, "June");
        monthNumToName.put(6, "July");
        monthNumToName.put(7, "August");
        monthNumToName.put(8, "September");
        monthNumToName.put(9, "October");
        monthNumToName.put(10, "November");
        monthNumToName.put(11, "December");

        monthNameToNum.put("January", 0);
        monthNameToNum.put("February", 1);
        monthNameToNum.put("March", 2);
        monthNameToNum.put("April", 3);
        monthNameToNum.put("May", 4);
        monthNameToNum.put("June", 5);
        monthNameToNum.put("July", 6);
        monthNameToNum.put("August", 7);
        monthNameToNum.put("September", 8);
        monthNameToNum.put("October", 9);
        monthNameToNum.put("November", 10);
        monthNameToNum.put("December", 11);

    }

    /**
     * Getter for the ArrayList of months
     *
     * @return - ArrayList of Months
     */
    public ArrayList<Month> getMonths()
    {
        return months;
    }

    /**
     * gets the currents months name that the calendar is on
     *
     * @return - Current Months name
     */
    public String getCurrentMonthName()
    {
        return monthNumToName.get(currentMonth);
    }

    /**
     * Gets the current year as an integer
     *
     * @return - Current Year
     */
    public int getCurrentYear()
    {
        return year;
    }

    public String getStartingMonth() {
        return monthNumToName.get(currentMonth);
    }

}
