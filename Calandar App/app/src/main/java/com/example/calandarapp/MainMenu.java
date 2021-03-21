package com.example.calandarapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import android.view.ViewGroup.LayoutParams;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class MainMenu extends AppCompatActivity {

    String firstCalandarName;
    Boolean calandarNameSet = false;
    Boolean confirmName = false;

    Button nameCalandarBttn;
    Boolean clickedConfirm = false;
    Boolean confirmedChoice = false;
    TextView welcomeViewTextBox;

    public static final String CALNAME_MESSAGE = "CalandarName";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menue);

        nameCalandarBttn = (Button)findViewById(R.id.CreateFirstCalButton);
        welcomeViewTextBox = (TextView)findViewById(R.id.WelcomeTextView);

    }

    public void createCalandarGivenName(View view) {
        TextView firstCalNameField = (TextView)findViewById(R.id.nameCalandarTextField);

        //returns "" if user has not entered anything into the text field
        //non global calandar name to make sure is not empty
        String firstCalNameText = firstCalNameField.getText().toString();

        if( firstCalNameText.equals("") )
        {
            firstCalNameField.setHint("Name cannot be empty! Try again.");
        }
        else
        {
            confirmCalName();
        }



    }

    public void confirmCalName()
    {
            //Creating the instance of PopupMenu
            PopupMenu popup = new PopupMenu(MainMenu.this, nameCalandarBttn);
            //Inflating the Popup using xml file
            popup.getMenuInflater().inflate(R.menu.confirm_first_cal_name_popup_menu, popup.getMenu());

            //registering popup with OnMenuItemClickListener
            popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                public boolean onMenuItemClick(MenuItem item) {
                    if(item.getTitle().toString().equals("Confirm"))
                    {
                        //initialized as false, only need to set to true
                        clickedConfirm = true;

                        confirmedChoice = true;

                        setFirstCalandarName(false);

                        Intent intent = new Intent(MainMenu.this, MainActivity.class);

                        String message = firstCalandarName;

                        intent.putExtra(CALNAME_MESSAGE, firstCalandarName);

                        startActivity(intent);
                    }
                    else
                    {
                        setFirstCalandarName(true);
                    }

                    return true;
                }
            });

            popup.show();//showing popup menu
    }

    public void setFirstCalandarName(Boolean clearText) {
        TextView firstCalNameField = (TextView)findViewById(R.id.nameCalandarTextField);

        //returns "" if user has not entered anything into the text field
        //non global calandar name to make sure is not empty
        String firstCalNameText = firstCalNameField.getText().toString();

        //set global calandar name after confirmation
        if(!clearText)
        {
            firstCalandarName = firstCalNameText;

        }
        else
        {
            firstCalandarName = "";
        }
        calandarNameSet = true;
        Log.d("Confirmed", "Name confirmed!!!!");
    }

    public void toastAFuckYou(View view) {
        Toast toast = Toast.makeText(this, "Hello Toast", Toast.LENGTH_SHORT);
        toast.show();
    }
}
