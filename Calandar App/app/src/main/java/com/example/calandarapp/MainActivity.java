package com.example.calandarapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

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

        Intent intent = getIntent();
        String calandarNameFromUser = intent.getStringExtra(MainMenu.CALNAME_MESSAGE);
        Log.d("Cal name", " " + calandarNameFromUser);

        TextView calandarNameTitle = (TextView)findViewById(R.id.calendarName);
        calandarNameTitle.setText("Activity: " + calandarNameFromUser);

        addIdsToDayId();
        setListenersForDays();

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

    /*
    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.name, popup.getMenu());
        popup.show();
    }
    */


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

    private void setListenersForDays()
    {
        int index;
        for(index = 0; index < 42; index++)
        {
            dayId.get(index).setOnClickListener(this);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.day1:
                Toast.makeText(this, "Button 1 clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.day2:
                Toast.makeText(this, "Button 2 clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.day3:
                Toast.makeText(this, "Button 3 clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.day4:
                Toast.makeText(this, "Button 4 clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.day5:
                Toast.makeText(this, "Button 5 clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.day6:
                Toast.makeText(this, "Button 6 clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.day7:
                Toast.makeText(this, "Button 7 clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.day8:
                Toast.makeText(this, "Button 8 clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.day9:
                Toast.makeText(this, "Button 9 clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.day10:
                ;
                Toast.makeText(this, "Button 10 clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.day11:
                Toast.makeText(this, "Button 11 clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.day12:
                Toast.makeText(this, "Button 12 clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.day13:
                Toast.makeText(this, "Button 13 clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.day14:
                Toast.makeText(this, "Button 14 clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.day15:
                Toast.makeText(this, "Button 15 clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.day16:
                Toast.makeText(this, "Button 16 clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.day17:
                Toast.makeText(this, "Button 17 clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.day18:
                Toast.makeText(this, "Button 18 clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.day19:
                Toast.makeText(this, "Button 19 clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.day20:
                Toast.makeText(this, "Button 20 clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.day21:
                Toast.makeText(this, "Button 21 clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.day22:
                Toast.makeText(this, "Button 22 clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.day23:
                Toast.makeText(this, "Button 23 clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.day24:
                Toast.makeText(this, "Button 24 clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.day25:
                Toast.makeText(this, "Button 25 clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.day26:
                Toast.makeText(this, "Button 26 clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.day27:
                Toast.makeText(this, "Button 27 clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.day28:
                Toast.makeText(this, "Button 28 clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.day29:
                Toast.makeText(this, "Button 29 clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.day30:
                Toast.makeText(this, "Button 30 clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.day31:
                Toast.makeText(this, "Button 31 clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.day32:
                Toast.makeText(this, "Button 32 clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.day33:
                Toast.makeText(this, "Button 33 clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.day34:
                Toast.makeText(this, "Button 34 clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.day35:
                Toast.makeText(this, "Button 35 clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.day36:
                Toast.makeText(this, "Button 36 clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.day37:
                Toast.makeText(this, "Button 37 clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.day38:
                Toast.makeText(this, "Button 38 clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.day39:
                Toast.makeText(this, "Button 39 clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.day40:
                Toast.makeText(this, "Button 40 clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.day41:
                Toast.makeText(this, "Button 41 clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.day42:
                Toast.makeText(this, "Button 42 clicked", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}