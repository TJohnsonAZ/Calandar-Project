package com.example.calandarapp;

import android.content.res.Resources;

public class Activity {

    String activityName;

    int completionColor;

    int nonCompletionColor;

    int activityNumber;

    Boolean activityExists = false;

    public Activity(String activityName, int activityNumber)
    {
        this.activityName = activityName;

        this.activityNumber = activityNumber;

    }


    public boolean exists( )
    {
        return activityExists;
    }

    public void setExistence(boolean exists)
    {
        activityExists = exists;
    }

    public void setColors( int completeColor, int nonCompleteColor )
    {
        completionColor = completeColor;

        nonCompletionColor = nonCompleteColor;
    }

}
