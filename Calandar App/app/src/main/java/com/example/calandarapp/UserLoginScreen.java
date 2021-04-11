package com.example.calandarapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class UserLoginScreen extends AppCompatActivity {

    int userNumber;

    public static final String userNumberMsg = "User Number";

    protected void onCreate(Bundle savedInstanceState) {

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.userloginscreen);
    }

    public void userButtonListener(View view)
    {
        Button userButton = (Button)view;
        String buttonClickedOnName = userButton.getText().toString();

        if( buttonClickedOnName.compareTo("User 1") == 0 )
        {
            userNumber = 1;
        }
        else if( buttonClickedOnName.compareTo("User 2") == 0 )
        {
            userNumber = 2;
        }
        else if( buttonClickedOnName.compareTo("User 3") == 0 )
        {
            userNumber = 3;
        }
        else
        {
            userNumber = 4;
        }

        Intent intent = new Intent(UserLoginScreen.this, MainMenu.class);

        String userNumberAsString = "" + userNumber;

        intent.putExtra(userNumberMsg , userNumberAsString);

        startActivity(intent);
    }
}
