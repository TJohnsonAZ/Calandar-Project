package com.example.calandarapp;

import android.util.Log;
import android.widget.TextView;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.CoordinatesProvider;
import androidx.test.espresso.action.GeneralClickAction;
import androidx.test.espresso.action.Press;
import androidx.test.espresso.action.Tap;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.rule.GrantPermissionRule;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class TestUserNumber
{
    @Rule
    public ActivityTestRule<UserLoginScreen> rule = new ActivityTestRule<>(UserLoginScreen.class, false, true);



    @Test
    public void testUserNumber()
    {
        Log.d("RunningTest!!!","asd");

        ViewInteraction userOneButton = onView(ViewMatchers.withId(R.id.user1));

        userOneButton.perform(ViewActions.click());

        ViewInteraction  firstCalendarNameTextView = onView(ViewMatchers.withId(R.id.nameCalandarTextField));


        firstCalendarNameTextView.perform(ViewActions.typeText("jogging"));


        firstCalendarNameTextView.perform(ViewActions.closeSoftKeyboard());


        ViewInteraction  createMyFirstCalendarButton = onView(ViewMatchers.withId(R.id.CreateFirstCalButton));


        createMyFirstCalendarButton.perform(ViewActions.click());



        //DataInteraction confirmCalNameButton = Espresso.onData(ViewMatchers.withId(R.id.confirmNamePopupBttn));

        //confirmCalNameButton.perform(ViewActions.click());

        clickXY(10, 500 );

        /*
        ViewInteraction  confirmCalNameButton = Espresso.onView(ViewMatchers.withId(R.id.confirmNamePopupBttn));

        confirmCalNameButton.perform(ViewActions.click());
        */
        /*
        ViewInteraction activityNameAndUserTextView = Espresso.onView(ViewMatchers.withId(R.id.calendarName));

        activityNameAndUserTextView.check(ViewAssertions.matches(ViewMatchers.withText("Activity: jogging User: 1")));

         */
    }

    public static ViewAction clickXY(final int x, final int y){
        return new GeneralClickAction(
                Tap.SINGLE,
                new CoordinatesProvider() {
                    @Override
                    public float[] calculateCoordinates(View view) {

                        final int[] screenPos = new int[2];
                        view.getLocationOnScreen(screenPos);

                        final float screenX = screenPos[0] + x;
                        final float screenY = screenPos[1] + y;
                        float[] coordinates = {screenX, screenY};

                        return coordinates;
                    }
                },
                Press.FINGER);
    }


}
