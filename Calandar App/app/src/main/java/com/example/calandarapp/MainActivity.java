package com.example.calandarapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Calandar calandar = new Calandar(2021);

    ArrayList<TextView> dayId =  new ArrayList<TextView>();

    TextView monthName;

    Button prevMonth;

    Button nextMonth;

    int startingDay;

    Notes savingNotes = new Notes();

    TextView dayClickedOn;

    PopupWindow setDayInformationWindow;

    PopupWindow chooseActivityWindow;

    Boolean previousDayInfoSet = true;

    static String urlForHttpReq = "http://142.11.236.52:8080/dayData";

    JSONObject jsonObjectFromGet;

    JSONArray jsonArrayFromGet;

    JSONObject jsonObjectPost;

    httpRequest httpRequestMaker;

    String UUIDForUser1 = "04dd4132-6341-4394-a9bb-1dbfe4d24906";

    Activity activity1;

    Activity activity2 = new Activity("", 2);

    Activity activity3 = new Activity("", 3);

    Activity activity4 = new Activity("", 4);

    Activity currentActivity;

    final Context context = this;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //the integer param represents the function to run in the thread, this object not used for that.
        //this object only used for making put requests, just havent changed the name yet.
        httpRequestMaker = new httpRequest( this, 0 );

        //tryPostRequestThread();

        //prevents dark mode from doing anything
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();

        String calandarNameFromUser = intent.getStringExtra(MainMenu.CALNAME_MESSAGE);

        //create a new activity for the user
        activity1 = new Activity(calandarNameFromUser, 1);

        activity1.setExistence( true );

        activity1.setColors( getResources().getColor(R.color.LimeGreen), getResources().getColor(R.color.Crimson));

        currentActivity = activity1;

        tryGetRequestThreadAndLoadCal();

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

    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.actions, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.addEvent:
                LayoutInflater popup = LayoutInflater.from(context);
                View promptsView = popup.inflate(R.layout.prompts, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                alertDialogBuilder.setView(promptsView);

                final EditText newUserActivity = (EditText) promptsView.findViewById(R.id.new_activity_input);

                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id)
                            {
                                createNewActivity( newUserActivity.getText().toString() );
                            }

                        })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });

                AlertDialog newActivityAlertDialog = alertDialogBuilder.create();

                newActivityAlertDialog.show();

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void createNewActivity(String newActName)
    {
        if(!activity2.exists())
        {
            activity2.activityName = newActName;

            activity2.setExistence( true );

            activity2.setColors(getResources().getColor(R.color.Goldenrod),getResources().getColor(R.color.DarkOrange));
        }
        else if(!activity3.exists())
        {
            activity3.activityName = newActName;

            activity3.setExistence( true );

            activity3.setColors(getResources().getColor(R.color.CadetBlue),getResources().getColor(R.color.IndianRed));
        }
        else if(!activity4.exists())
        {
            activity4.activityName = newActName;

            activity4.setExistence( true );

            activity4.setColors(getResources().getColor(R.color.DarkSeaGreen),getResources().getColor(R.color.HotPink));
        }
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

    public void tryGetRequestThreadAndLoadCal()
    {
        //1 means run the get request
        httpRequest httpReqThread = new httpRequest(this, 1);

        httpReqThread.start();

    }

    private void tryPostRequestThread()
    {
        //2 means run a post request.
        httpRequest httpReqThread = new httpRequest(this, 2);

        httpReqThread.start();

    }

    public void getJSONArrayResponse( JSONArray jsonArray)
    {
        try {
            jsonArrayFromGet = jsonArray;
            loadCalWithDays();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void getJSONObjectPostResponse(JSONObject jsonObjectPost)
    {
        this.jsonObjectPost = jsonObjectPost;
        ((TextView)findViewById(R.id.USERIDDISPLAY)).setText(jsonObjectPost.toString());
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
        setDayInformationWindow = new PopupWindow(customView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        setDayInformationWindow.showAtLocation((LinearLayout)findViewById(R.id.mainactivityid), Gravity.CENTER, 0, 0);
    }

    private void setActivityInformationPopUp()
    {
        View customView = getLayoutInflater().inflate(R.layout.activityselectionpopup, null);
        chooseActivityWindow = new PopupWindow(customView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        chooseActivityWindow.showAtLocation((LinearLayout)findViewById(R.id.mainactivityid), Gravity.CENTER, 0, 0);
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

            setActivityInformationPopUp();

            dayClickedOn = aView;
        }
    }

    public void activitySelectionButtons(View view) throws JSONException {

        Button activityButtonClickedOn = (Button)view;

        String getActNameAsString = activityButtonClickedOn.getText().toString();

        Log.d("ACTNAMEASSTRING","num: " + getActNameAsString);

        String getActNumberAsString = getActNameAsString.substring( 9,10 );

        Log.d("ACTNUM","num: " + getActNumberAsString);

        int actNum = Integer.parseInt(getActNumberAsString);

        Log.d("ACTNUMMM","num: " + "" + actNum);

        Activity anActivity = getActivity( actNum );

        if( anActivity.exists() )
        {
            currentActivity = anActivity;

            loadCalWithDays();

            TextView calandarNameTitle = (TextView)findViewById(R.id.calendarName);

            calandarNameTitle.setText("Activity: " + anActivity.activityName);

            chooseActivityWindow.dismiss();

            setDayInformationPopUp();
        }
        else
        {
            Log.d("ACTNUMMM","false");
        }

    }

    public void nonCompletedDayButton( View view ) throws JSONException {
        
        String dayNumberAsString = dayClickedOn.getText().toString();

        int dayNumberAsInt = Integer.parseInt(dayNumberAsString);

        int monthNameAsNumber = calandar.monthNameToNum.get( calandar.getCurrentMonthName() );

        Calendar randCalendar = Calendar.getInstance();

        randCalendar.set(2021, monthNameAsNumber, dayNumberAsInt);

        int dayOfYear = randCalendar.get(Calendar.DAY_OF_YEAR);

        dayNumberAsString = "" + dayOfYear;

        String urlForPutRequest;

        //dayClickedOn.setBackgroundColor( currentActivity.nonCompletionColor );

        setDayInformationWindow.dismiss();

        previousDayInfoSet = true;

        JSONObject updateDayJSONObject = new JSONObject().put("user", UUIDForUser1);

        setValuesForPutJSONObject( updateDayJSONObject, 2 );

        //Log.d("CODEEEEE","" + updateDayJSONObject.toString() );

        /*
        updateDayJSONObject.put("activity1DayStatus","2");
        updateDayJSONObject.put("activity2DayStatus","0");
        updateDayJSONObject.put("activity3DayStatus","0");
        updateDayJSONObject.put("activity4DayStatus","0");
        */


        urlForPutRequest = String.format("http://142.11.236.52:8080/dayData?dayNum=%s", dayNumberAsString );

        httpRequestMaker.tryHTTPPutRequest(urlForPutRequest, updateDayJSONObject);

        Log.d("THREADTEST", "COOKIE");

        //tryGetRequestThreadAndLoadCal();

        //dayId.get(dayNumberAsInt).setBackgroundColor( currentActivity.nonCompletionColor );
    }

    public void completedDayButton(View view) throws JSONException {

        String dayNumberAsString = dayClickedOn.getText().toString();

        int dayNumberAsInt = Integer.parseInt(dayNumberAsString);

        int monthNameAsNumber = calandar.monthNameToNum.get( calandar.getCurrentMonthName() );

        Calendar randCalendar = Calendar.getInstance();

        randCalendar.set(2021, monthNameAsNumber, dayNumberAsInt);

        int dayOfYear = randCalendar.get(Calendar.DAY_OF_YEAR);

        dayNumberAsString = "" + dayOfYear;

        String urlForPutRequest;



        //dayClickedOn.setBackgroundColor( currentActivity.completionColor );

        setDayInformationWindow.dismiss();

        previousDayInfoSet = true;

        JSONObject updateDayJSONObject = new JSONObject().put("user", UUIDForUser1);

        setValuesForPutJSONObject( updateDayJSONObject, 3 );

        /*
        updateDayJSONObject.put("activity1DayStatus","3");
        updateDayJSONObject.put("activity2DayStatus","0");
        updateDayJSONObject.put("activity3DayStatus","0");
        updateDayJSONObject.put("activity4DayStatus","0");

         */

        urlForPutRequest = String.format("http://142.11.236.52:8080/dayData?dayNum=%s", dayNumberAsString );

        httpRequestMaker.tryHTTPPutRequest(urlForPutRequest, updateDayJSONObject);

        Log.d("THREADTEST", "COOKIE");

        //tryGetRequestThreadAndLoadCal();

        //dayId.get(dayNumberAsInt).setBackgroundColor( currentActivity.completionColor );
    }

    public void clearDayButton(View view) throws JSONException {

        String dayNumberAsString = dayClickedOn.getText().toString();

        int dayNumberAsInt = Integer.parseInt(dayNumberAsString);

        int monthNameAsNumber = calandar.monthNameToNum.get( calandar.getCurrentMonthName() );

        Calendar randCalendar = Calendar.getInstance();

        randCalendar.set(2021, monthNameAsNumber, dayNumberAsInt);

        int dayOfYear = randCalendar.get(Calendar.DAY_OF_YEAR);

        dayNumberAsString = "" + dayOfYear;

        String urlForPutRequest;


        //dayClickedOn.setBackgroundColor( getResources().getColor(R.color.White) );

        setDayInformationWindow.dismiss();

        previousDayInfoSet = true;

        JSONObject updateDayJSONObject = new JSONObject().put("user", UUIDForUser1);
        setValuesForPutJSONObject( updateDayJSONObject, 1 );

        /*
        updateDayJSONObject.put("activity1DayStatus","1");
        updateDayJSONObject.put("activity2DayStatus","0");
        updateDayJSONObject.put("activity3DayStatus","0");
        updateDayJSONObject.put("activity4DayStatus","0");

         */

        urlForPutRequest = String.format("http://142.11.236.52:8080/dayData?dayNum=%s", dayNumberAsString );

        httpRequestMaker.tryHTTPPutRequest(urlForPutRequest, updateDayJSONObject);

        Log.d("THREADTEST", "COOKIE");

        //tryGetRequestThreadAndLoadCal();



       // dayId.get(dayNumberAsInt).setBackgroundColor( getResources().getColor(R.color.White) );
    }




    public void clearCal(View view)
    {
        clearCalandar();
    }

    //only works for January
    public void loadCalWithDays() throws JSONException
    {
        //clear the calendar or else days beyond the current months capacity dont change color
        clearCalandar();

        int monthNameAsNumber = calandar.monthNameToNum.get( calandar.getCurrentMonthName() );
        //jsonArrayFromGet = httpRequestMaker.jsonArrayFromRequest;
        //when starting day is used as an index in dayId array it is correct

        //firstDayOfAMonth is the index where we will start iterating through the dayId list, 31 times
        int firstDayOfAMonth = calandar.getStartingDay(calandar.getCurrentYear(), calandar.getCurrentMonthName());

        Calendar randCalendar = Calendar.getInstance();

        Log.d("DAYOFYEAR", "Month: " + monthNameAsNumber);
        Log.d("DAYOFYEAR", "firstDayOfMonth: " + firstDayOfAMonth);

        randCalendar.set(2021, monthNameAsNumber, 1);
        int dayOfYear = randCalendar.get(Calendar.DAY_OF_YEAR);


        Log.d("DAYOFYEAR", "dayofYear: " + dayOfYear);

        //0 represents january, only temporary
        int numberOfDaysInMonth = calandar.getMonths().get(monthNameAsNumber).getNumOfDays();

        //index var to be used to access days in the dayId list
        int aDayInDayIdList;

        //index 0 represents day 1 in the year, we start here for january, will change in the future
        //-1 because json array starts at index 0 and the first day of the year starts at 1
        int JSONArrayIndex = dayOfYear - 1;

        String activityStatus = "activity" + currentActivity.activityNumber + "DayStatus";

        //start at first day of month, aligns with first day index in dayId list.
        //aDayInDayIdList < numberOfDaysInMonth + firstDayOfAMonth -- iterate from first day of month in dayId list to last day of month in id list
        for(aDayInDayIdList = firstDayOfAMonth ; aDayInDayIdList < numberOfDaysInMonth + firstDayOfAMonth; aDayInDayIdList++)
        {
            JSONObject aDayObj = jsonArrayFromGet.getJSONObject(JSONArrayIndex);

            //this stuff dont work no more after adam changed some stuff
            if(aDayObj.get(activityStatus).equals("3"))
            {
                dayId.get(aDayInDayIdList).setBackgroundColor( currentActivity.completionColor );
            }
            else if(aDayObj.get(activityStatus).equals("2"))
            {
                dayId.get(aDayInDayIdList).setBackgroundColor(currentActivity.nonCompletionColor);
            }
            else
            {
                Log.d("Else","else statement hit");
                dayId.get(aDayInDayIdList).setBackgroundColor(getResources().getColor(R.color.White));
            }

            JSONArrayIndex++;
        }
    }

    public void clearCalandar()
    {
        int aDayIndex;

        for( aDayIndex = 0; aDayIndex <  dayId.size() ; aDayIndex++ )
        {
            dayId.get(aDayIndex).setBackgroundColor(getResources().getColor(R.color.White));
        }
    }

    public Activity getActivity( int actNum )
    {
        if(actNum == 1)
        {
            return activity1;
        }
        else if(actNum == 2)
        {
            Log.d("ACTNUMMM","activity 2");
            return activity2;
        }
        else if(actNum == 3)
        {
            return activity3;
        }
        else
        {
            return activity4;
        }
    }

    //mark code indicates completion, incompletion or clearing of the day
    public void setValuesForPutJSONObject(JSONObject jsonObject, int markCode) throws JSONException {

        int activityToMark = currentActivity.activityNumber;

        if( activityToMark == 1 )
        {
            jsonObject.put("activity1DayStatus", markCode + "");
            jsonObject.put("activity2DayStatus","-1");
            jsonObject.put("activity3DayStatus","-1");
            jsonObject.put("activity4DayStatus","-1");
        }
        else if( activityToMark == 2 )
        {
            jsonObject.put("activity1DayStatus", "-1");
            jsonObject.put("activity2DayStatus",markCode + "");
            jsonObject.put("activity3DayStatus","-1");
            jsonObject.put("activity4DayStatus","-1");
        }
        else if( activityToMark == 3 )
        {
            jsonObject.put("activity1DayStatus", "-1");
            jsonObject.put("activity2DayStatus","-1");
            jsonObject.put("activity3DayStatus",markCode + "");
            jsonObject.put("activity4DayStatus","-1");
        }
        else
        {
            jsonObject.put("activity1DayStatus", "-1");
            jsonObject.put("activity2DayStatus","-1");
            jsonObject.put("activity3DayStatus","-1");
            jsonObject.put("activity4DayStatus",markCode + "");
        }




    }


}
