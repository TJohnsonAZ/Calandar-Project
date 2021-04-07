package com.example.calandarapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Calandar calandar = new Calandar(2021);

    ArrayList<TextView> dayId =  new ArrayList<TextView>();

    TextView monthName;

    Button prevMonth;

    Button nextMonth;

    int startingDay;

    Notes savingNotes = new Notes();

    TextView dayClickedOn;

    PopupWindow theWindowForPopping;

    Boolean previousDayInfoSet = true;

    static String urlForHttpReq = "http://142.11.236.52:8080/dayData";

    JSONObject jsonObjectFromGet;

    JSONArray jsonArrayFromGet;

    httpRequest httpRequestMaker;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        httpRequestMaker = new httpRequest( this );


        Log.d("THREADTESTBeforeRun","successVal: " + httpRequestMaker.requestSuccess);

        tryGetRequestThreadAndLoadCal();

        Log.d("THREADTEST","Finished value: " + httpRequestMaker.requestSuccess);
        //prevents dark mode from doing anything
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

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

        //gets the first day of the month as an int, sunday is 0 ... saterday is 6
        //here starting day is set to 5, means friday is the first day of january.
        startingDay = calandar.getStartingDay(calandar.getCurrentYear(), calandar.getStartingMonth());

        //Log.d("FIRST DAY: ", "is: " + startingDay);

        //index 0 represents day 1 in the dayId list,
        for(index = 0; index < startingDay ; index++)
        {
            dayId.get(index).setText(" ");
        }

        int janAsInt = 0;

        int numberOfDaysInMonth = calandar.getMonths().get(janAsInt).getNumOfDays();

        int dayNum = 1;

        //the starting day provided by java calandar align with the days in dayId
        //iterates through the day list, to set the text of each day with the correct number
        //index starts at starting day because thats when the first day of the month is and thats where we will start.
        //dayNum starts at 1 and increases until it reaches numberOfDaysInMonth so the calandar is labeled with numbers
        //for every day
        for(index = startingDay; dayNum <= numberOfDaysInMonth; index++)
        {
            dayId.get(index).setText(dayNum + "");
            dayNum++;
        }


        Log.d("CALANDDEBUG", "Days in month: " + numberOfDaysInMonth + " starting day: " + startingDay);

        //starts at index = numberOfDaysInMonth + startingDay because it represents the last day of the month
        //in relation to the index in the dayId list. Because our first day of the month is friday which equals 5,
        //we add the length of the month, 31, to get 36. We start at index 36 which in the dayId array represents day 35.
        //day 31 is index 34.
        // so if we initialized index = numberOfDaysInMonth, we would be starting at day 30, but our starting day is offset by 5
        //because that was the first day of the month so we add 5 to the numberOfDaysInMonth.
        for(index = numberOfDaysInMonth + startingDay ; index < 42; index++)
        {
            dayId.get(index).setText(R.string.blank_day);
        }

        int dayNumber;


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
        tryGetRequestThreadAndLoadCal();

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
        tryGetRequestThreadAndLoadCal();

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

    private void tryGetRequestThreadAndLoadCal()
    {
        httpRequestMaker.start();

        try {
            loadCalWithDays();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void getJSONArrayResponse()
    {
        try {
            loadCalWithDays();
        } catch (JSONException e) {
            e.printStackTrace();
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
            dayId.get(index).setTag("day");
            dayId.get(index).setOnClickListener(this);
        }

    }

    private void setDayInformationPopUp()
    {
        View customView = getLayoutInflater().inflate(R.layout.realpopupwindow, null);
        theWindowForPopping = new PopupWindow(customView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        theWindowForPopping.showAtLocation((LinearLayout)findViewById(R.id.mainactivityid), Gravity.CENTER, 0, 0);
    }

    @Override
    public void onClick(View v) {
        TextView aView = (TextView)v;
        String aViewTag = aView.getTag().toString();
        String subStringDay = aViewTag.substring(0,3);

        Log.d("SUBSTRINGTEST", "Sub String: " + subStringDay.toString() + " View Tag as String: " + aViewTag);

        //if the user has not choosen completed or not completed then dont let them open a new pop up
        if( subStringDay.equals("day") && previousDayInfoSet)
        {
            previousDayInfoSet = false;
            setDayInformationPopUp();
            dayClickedOn = aView;
        }
    }

    public void nonCompletedDayButton(View view) throws JSONException {

        String urlForPutRequest;

        String dayNumberAsString = dayClickedOn.getText().toString();

        dayClickedOn.setBackgroundColor( getResources().getColor(R.color.Crimson) );

        theWindowForPopping.dismiss();

        previousDayInfoSet = true;

        JSONObject updateDayJSONObject = new JSONObject().put("dayOfYear", dayNumberAsString);
        updateDayJSONObject.put("activity1DayStatus","2");
        updateDayJSONObject.put("activity2DayStatus","0");
        updateDayJSONObject.put("activity3DayStatus","0");
        updateDayJSONObject.put("activity2DayStatus","0");

        urlForPutRequest = String.format("http://142.11.236.52:8080/dayData?dayNum=%s", dayNumberAsString );

        httpRequestMaker.tryHTTPPutRequest(urlForPutRequest, updateDayJSONObject);
    }

    public void completedDayButton(View view) throws JSONException {

        String urlForPutRequest;

        String dayNumberAsString = dayClickedOn.getText().toString();

        dayClickedOn.setBackgroundColor( getResources().getColor(R.color.LimeGreen) );

        theWindowForPopping.dismiss();

        previousDayInfoSet = true;

        JSONObject updateDayJSONObject = new JSONObject().put("dayOfYear", dayNumberAsString);
        updateDayJSONObject.put("activity1DayStatus","3");
        updateDayJSONObject.put("activity2DayStatus","0");
        updateDayJSONObject.put("activity3DayStatus","0");
        updateDayJSONObject.put("activity2DayStatus","0");

        urlForPutRequest = String.format("http://142.11.236.52:8080/dayData?dayNum=%s", dayNumberAsString );

        httpRequestMaker.tryHTTPPutRequest(urlForPutRequest, updateDayJSONObject);
    }

    public void clearDayButton(View view) throws JSONException {

        String urlForPutRequest;

        String dayNumberAsString = dayClickedOn.getText().toString();

        dayClickedOn.setBackgroundColor( getResources().getColor(R.color.White) );

        theWindowForPopping.dismiss();

        previousDayInfoSet = true;

        JSONObject updateDayJSONObject = new JSONObject().put("dayOfYear", dayNumberAsString);
        updateDayJSONObject.put("activity1DayStatus","1");
        updateDayJSONObject.put("activity2DayStatus","0");
        updateDayJSONObject.put("activity3DayStatus","0");
        updateDayJSONObject.put("activity2DayStatus","0");

        urlForPutRequest = String.format("http://142.11.236.52:8080/dayData?dayNum=%s", dayNumberAsString );

        httpRequestMaker.tryHTTPPutRequest(urlForPutRequest, updateDayJSONObject);
    }




    public void checkIfLoadingDone(View view)
    {
        //making a get request updates a global variable with an array of 365 days, is then used by loadCalWithDays.
        httpRequestMaker.tryHTTPGetRequestForJSONArray(urlForHttpReq);

        Toast toast = Toast.makeText(this, "ArrayLength: " + jsonArrayFromGet.length(), Toast.LENGTH_SHORT);

        toast.show();
    }

    //only works for January
    public void loadCalWithDays() throws JSONException
    {
        jsonArrayFromGet = httpRequestMaker.jsonArrayFromRequest;
        //when starting day is used as an index in dayId array it is correct

        //firstDayOfAMonth is the index where we will start iterating through the dayId list, 31 times
        int firstDayOfAMonth = calandar.getStartingDay(calandar.getCurrentYear(), calandar.getStartingMonth());

        //0 represents january, only temporary
        int numberOfDaysInMonth = calandar.getMonths().get(0).getNumOfDays();

        //index var to be used to access days in the dayId list
        int aDayInDayIdList;

        //index 0 represents day 1 in the year, we start here for january, will change in the future
        int JSONArrayIndex = 0;

        //start at first day of month, aligns with first day index in dayId list.
        //aDayInDayIdList < numberOfDaysInMonth + firstDayOfAMonth -- iterate from first day of month in dayId list to last day of month in id list
        for(aDayInDayIdList = firstDayOfAMonth ; aDayInDayIdList < numberOfDaysInMonth + firstDayOfAMonth; aDayInDayIdList++)
        {
            JSONObject aDayObj = jsonArrayFromGet.getJSONObject(JSONArrayIndex);

            //this stuff dont work no more after adam changed some stuff
            if(aDayObj.get("activity1DayStatus").equals("3"))
            {
                dayId.get(aDayInDayIdList).setBackgroundColor(getResources().getColor(R.color.LimeGreen));
            }
            else if(aDayObj.get("activity1DayStatus").equals("2"))
            {
                dayId.get(aDayInDayIdList).setBackgroundColor(getResources().getColor(R.color.Crimson));
            }
            else
            {
                Log.d("Else","else statement hit");
                dayId.get(aDayInDayIdList).setBackgroundColor(getResources().getColor(R.color.White));
            }

            JSONArrayIndex++;
        }
    }

    public void loadCalWithDays(View view) throws JSONException
    {
        jsonArrayFromGet = httpRequestMaker.jsonArrayFromRequest;
        //when starting day is used as an index in dayId array it is correct

        Log.d("THREADTESTLoadCal","successVal: " + httpRequestMaker.requestSuccess);

        //firstDayOfAMonth is the index where we will start iterating through the dayId list, 31 times
        int firstDayOfAMonth = calandar.getStartingDay(calandar.getCurrentYear(), calandar.getStartingMonth());

        //0 represents january, only temporary
        int numberOfDaysInMonth = calandar.getMonths().get(0).getNumOfDays();

        //index var to be used to access days in the dayId list
        int aDayInDayIdList;

        //index 0 represents day 1 in the year, we start here for january, will change in the future
        int JSONArrayIndex = 0;

        //start at first day of month, aligns with first day index in dayId list.
        //aDayInDayIdList < numberOfDaysInMonth + firstDayOfAMonth -- iterate from first day of month in dayId list to last day of month in id list
        for(aDayInDayIdList = firstDayOfAMonth ; aDayInDayIdList < numberOfDaysInMonth + firstDayOfAMonth; aDayInDayIdList++)
        {
            JSONObject aDayObj = jsonArrayFromGet.getJSONObject(JSONArrayIndex);

            //this stuff dont work no more after adam changed some stuff
            if(aDayObj.get("activity1DayStatus").equals("3"))
            {
                dayId.get(aDayInDayIdList).setBackgroundColor(getResources().getColor(R.color.LimeGreen));
            }
            else if(aDayObj.get("activity1DayStatus").equals("2"))
            {
                dayId.get(aDayInDayIdList).setBackgroundColor(getResources().getColor(R.color.Crimson));
            }
            else
            {
                Log.d("Else","else statement hit");
                dayId.get(aDayInDayIdList).setBackgroundColor(getResources().getColor(R.color.White));
            }

            JSONArrayIndex++;
        }
    }
}
