package com.example.calandarapp;

import android.os.Bundle;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Calandar calandar = new Calandar(2021);

    ArrayList<TextView> dayId =  new ArrayList<TextView>();

    TextView monthName;

    Button prevMonth;

    Button nextMonth;

    int startingDay;

    Notes savingNotes = new Notes();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addIdsToDayId();
        monthName = findViewById(R.id.monthYear);
        monthName.setText(calandar.getStartingMonth() + " " + calandar.getCurrentYear());
        int index;
        startingDay = calandar.getStartingDay(calandar.getCurrentYear(), calandar.getStartingMonth());

        for(index = 0; index < startingDay ; index++)
        {
            dayId.get(index).setText(" ");
        }
        int numberOfDaysInMonth = calandar.getMonths().get(0).getNumOfDays();
        int dayNum = 1;
        for(index = startingDay; dayNum <= numberOfDaysInMonth; index++)
        {
            dayId.get(index).setText(dayNum + "");
            dayNum++;
        }

        for(index = numberOfDaysInMonth + startingDay ; index < 42; index++)
        {
            dayId.get(index).setText(R.string.blank_day);
        }

        prevMonth = findViewById(R.id.prevMonth);
        nextMonth = findViewById(R.id.nextMonth);

        prevMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preMonth(v);
            }
        });

        nextMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextMonth(v);
            }
        });
    }


    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.name, popup.getMenu());
        popup.show();
    }

    public void nextMonth(View view)
    {
        calandar.nextMonth();
        addIdsToDayId();
        monthName = findViewById(R.id.monthYear);
        monthName.setText(calandar.getCurrentMonthName() + " " + calandar.getCurrentYear());
        int index;
        startingDay = calandar.getStartingDay(calandar.getCurrentYear(), calandar.getCurrentMonthName());
        for(index = 0; index < startingDay ; index++)
        {
            dayId.get(index).setText(" ");
        }
        int numberOfDaysInMonth = calandar.getMonths().get(calandar.monthNameToNum.get(calandar.getCurrentMonthName())).getNumOfDays();
        int dayNum = 1;
        for(index = startingDay; dayNum <= numberOfDaysInMonth; index++)
        {
            dayId.get(index).setText(dayNum + "");
            dayNum++;
        }

        for(index = numberOfDaysInMonth + startingDay ; index < 42; index++)
        {
            dayId.get(index).setText(R.string.blank_day);
        }

    }

    public void preMonth(View view)
    {
        calandar.preMonth();
        addIdsToDayId();
        monthName = findViewById(R.id.monthYear);
        monthName.setText(calandar.getCurrentMonthName() + " " + calandar.getCurrentYear());
        int index;
        startingDay = calandar.getStartingDay(calandar.getCurrentYear(), calandar.getCurrentMonthName());
        for(index = 0; index < startingDay ; index++)
        {
            dayId.get(index).setText(" ");
        }
        int numberOfDaysInMonth = calandar.getMonths().get(calandar.monthNameToNum.get(calandar.getCurrentMonthName())).getNumOfDays();
        int dayNum = 1;
        for(index = startingDay; dayNum <= numberOfDaysInMonth; index++)
        {
            dayId.get(index).setText(dayNum + "");
            dayNum++;
        }

        for(index = numberOfDaysInMonth + startingDay ; index < 42; index++)
        {
            dayId.get(index).setText(R.string.blank_day);
        }

    }

    private void addIdsToDayId()
    {
        dayId.add(findViewById(R.id.day1));
        dayId.add(findViewById(R.id.day2));
        dayId.add(findViewById(R.id.day3));
        dayId.add(findViewById(R.id.day4));
        dayId.add(findViewById(R.id.day5));
        dayId.add(findViewById(R.id.day6));
        dayId.add(findViewById(R.id.day7));
        dayId.add(findViewById(R.id.day8));
        dayId.add(findViewById(R.id.day9));
        dayId.add(findViewById(R.id.day10));
        dayId.add(findViewById(R.id.day11));
        dayId.add(findViewById(R.id.day12));
        dayId.add(findViewById(R.id.day13));
        dayId.add(findViewById(R.id.day14));
        dayId.add(findViewById(R.id.day15));
        dayId.add(findViewById(R.id.day16));
        dayId.add(findViewById(R.id.day17));
        dayId.add(findViewById(R.id.day18));
        dayId.add(findViewById(R.id.day19));
        dayId.add(findViewById(R.id.day20));
        dayId.add(findViewById(R.id.day21));
        dayId.add(findViewById(R.id.day22));
        dayId.add(findViewById(R.id.day23));
        dayId.add(findViewById(R.id.day24));
        dayId.add(findViewById(R.id.day25));
        dayId.add(findViewById(R.id.day26));
        dayId.add(findViewById(R.id.day27));
        dayId.add(findViewById(R.id.day28));
        dayId.add(findViewById(R.id.day29));
        dayId.add(findViewById(R.id.day30));
        dayId.add(findViewById(R.id.day31));
        dayId.add(findViewById(R.id.day32));
        dayId.add(findViewById(R.id.day33));
        dayId.add(findViewById(R.id.day34));
        dayId.add(findViewById(R.id.day35));
        dayId.add(findViewById(R.id.day36));
        dayId.add(findViewById(R.id.day37));
        dayId.add(findViewById(R.id.day38));
        dayId.add(findViewById(R.id.day39));
        dayId.add(findViewById(R.id.day40));
        dayId.add(findViewById(R.id.day41));
        dayId.add(findViewById(R.id.day42));

    }

}