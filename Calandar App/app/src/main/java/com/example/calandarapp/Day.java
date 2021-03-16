package com.example.calandarapp;

public class Day {

    private Notes noteForDay = new Notes();

    private boolean completation;

    private boolean beenSet = false;

    private int dayNum;

    private int dayOfWeek;

    public Day(int dayNum, int dayOfWeek)
    {
        this.dayNum = dayNum;
        this.dayOfWeek = dayOfWeek;

    }

    /*
    Use this first to see if user has set completetion for the day before return back anything
     */
    private Boolean hasBeenSet()
    {
        return beenSet;
    }

    /*
    Use after making a request to post information on the server to have on front end
     */
    private void setCompletion(boolean completed)
    {
        completation = completed;
    }

    /*
    Local side of the completion check,
    If completed: color background green
    If not completed: color background red
     */
    private Boolean isCompleted()
    {
        return completation;
    }



    

}
