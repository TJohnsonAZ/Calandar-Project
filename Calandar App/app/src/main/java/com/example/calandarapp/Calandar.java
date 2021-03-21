package com.example.calandarapp;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Calandar extends AppCompatActivity {

    private static final Calendar calendar = Calendar.getInstance();


    public Map<Integer, String> monthNumToName
            = new HashMap<Integer, String>();

    public Map<String, Integer> monthNameToNum
            = new HashMap<String, Integer>();

    private int year;

    private int currentMonth = 0;

    private ArrayList<Month> months = new ArrayList<Month>();

    private final int MAX_NUM_OF_MONTHS = 12;


    public Calandar(int startingYear) {
        this.year = startingYear;
        calendar.set(startingYear, currentMonth, 1);
        makeHashMaps();
        int index;
        for (index = 0; index < MAX_NUM_OF_MONTHS; index++) {
            Month addMonth = new Month(index, monthNumToName.get(index), startingYear);
            months.add(addMonth);
        }

    }

    public int getStartingDay(int year, String monthName) {
        calendar.set(year, monthNameToNum.get(monthName), 1);
        return calendar.DAY_OF_MONTH;
    }

    public void nextMonth() {
        if (currentMonth >= 11) {
            currentMonth = 0;
        } else {
            currentMonth += 1;
        }
    }

    public void preMonth() {
        if (currentMonth <= 0) {
            currentMonth = 11;
        } else {
            currentMonth -= 1;
        }
    }

    public String getStartingMonth() {
        return monthNumToName.get(currentMonth);
    }

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

    public ArrayList<Month> getMonths()
    {
        return months;
    }

    public String getCurrentMonthName()
    {
        return monthNumToName.get(currentMonth);
    }

    public int getCurrentYear()
    {
        return year;
    }

}
