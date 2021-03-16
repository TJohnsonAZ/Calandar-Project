package com.example.calandarapp;

import java.util.ArrayList;
import java.util.Calendar;

public class Month
{

    private Calendar cal = Calendar.getInstance();

    private int startingDay;

    private String monthName;

    private int year;

    private int numOfDays;

    private int monthNum;

    public ArrayList<Day> days = new ArrayList<Day>();

    public Month (int monthNum, String name, int year)
    {
        this.year = year;
        this.monthNum = monthNum;
        this.monthName = name;
        cal.set(this.year, this.monthNum, 1);
        numOfDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        int index;
        startingDay = cal.DAY_OF_WEEK;
        int dayOfWeek = startingDay;
        Day newDay;
        for (index = 0; index < numOfDays; index++)
        {

            newDay = new Day(index, dayOfWeek);
            days.add(newDay);
            if (dayOfWeek >= 7)
            {
                dayOfWeek = 1;
            }
            else
            {
                dayOfWeek += 1;
            }
        }


    }

    public int getStartingDay()
    {
        return startingDay;
    }

    public int getNumOfDays()
    {
        return numOfDays;
    }


}
