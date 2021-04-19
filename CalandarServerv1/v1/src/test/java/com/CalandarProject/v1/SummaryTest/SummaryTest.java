package com.CalandarProject.v1.SummaryTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.CalandarProject.v1.DayData.DayData;
import com.CalandarProject.v1.DayData.DayDatabase;
import com.CalandarProject.v1.Summary.Summary;

public class SummaryTest {

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        System.out.println("Begin Summary Tests\n");
    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
        System.out.println("\nEnd Summary Tests");
    }

    @Test
    public void summaryGetCompleteCounter() {
        DayData day = new DayData("6527", "7", "3", "1", "2", "3");
        DayDatabase database = new DayDatabase();
        database.addDayData(day);
        Summary summary = new Summary(7, 7, "6527");
        int[] output = summary.getCompleteCounter();
        assertEquals(1, output[0]);
    }

    @Test
    public void summaryGetIncompleteCounter() {
        DayData day = new DayData("6527", "7", "3", "1", "2", "3");
        DayDatabase database = new DayDatabase();
        database.addDayData(day);
        Summary summary = new Summary(7, 7, "6527");
        int[] output = summary.getIncompleteCounter();
        assertEquals(1, output[2]);
    }

    @Test
    public void summaryGetClearCounter() {
        DayData day = new DayData("6527", "7", "3", "1", "2", "3");
        DayDatabase database = new DayDatabase();
        database.addDayData(day);
        Summary summary = new Summary(7, 7, "6527");
        int[] output = summary.getClearCounter();
        assertEquals(1, output[1]);
    }

    @Test
    public void summaryGetHighestStreak() {
        DayData day = new DayData("6527", "8", "3", "1", "1", "0");
        DayDatabase database = new DayDatabase();
        database.addDayData(day);
        Summary summary = new Summary(8, 8, "6527");
        int[] output = new int[4];
        output = summary.getHighestStreak();
        assertEquals(0, output[2]);
    }

    @Test
    public void summaryGetNumActivities() {
        DayData day = new DayData("6527", "8", "3", "1", "1", "0");
        DayDatabase database = new DayDatabase();
        database.addDayData(day);
        Summary summary = new Summary(8, 8, "6527");
        int output = summary.getNumActivities();
        assertEquals(3, output);
    }

    @Test
    public void summaryGetHighestOverallStreak() {
        DayData day = new DayData("6527", "7", "3", "1", "2", "3");
        DayDatabase database = new DayDatabase();
        database.addDayData(day);
        day = new DayData("6527", "8", "3", "1", "1", "0");
        database.addDayData(day);
        Summary summary = new Summary(7, 8, "6527");
        int output = summary.getHighestOverallStreak();
        assertEquals(2, output);
    }
}