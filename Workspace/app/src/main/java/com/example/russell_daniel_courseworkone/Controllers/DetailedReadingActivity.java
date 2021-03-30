package com.example.russell_daniel_courseworkone.Controllers;

import android.content.res.Configuration;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.example.russell_daniel_courseworkone.Fragments.BottomNavBar;
import com.example.russell_daniel_courseworkone.Fragments.ReadingDisplay;
import com.example.russell_daniel_courseworkone.Fragments.TopActionBar;
import com.example.russell_daniel_courseworkone.Models.Reading;
import com.example.russell_daniel_courseworkone.R;

//Daniel Russell S1707149
//Controller used to handle the activity Detailed Reading
public class DetailedReadingActivity extends AppCompatActivity {

    //Variables
    private TopActionBar FragNavBar; //defined fragnavbar using the fragment class topactionbar
    private BottomNavBar FragBotNavBar; //defines fragbotnavbar using the bottom fragment class bottomnavbar
    private ReadingDisplay FragReadDisplay; // defines fragreaddisplay using the reading display fragment class
    private Reading reading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailedreading);

        //Definitions of Fragments used for Detailed Reading
        FragReadDisplay = new ReadingDisplay();
        FragNavBar = new TopActionBar();
        FragBotNavBar = new BottomNavBar();

        //If the app is in landscape the top action bar is removed to save space for the remaining views
        //if the app is in portrait the top action is preserved as there is save for the top action bar to display neatly
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {

            //Commits fragments to display their corresponding views into the layout of detailed reading
            FragmentManager manageReadingDisplay = getSupportFragmentManager();
            FragmentTransaction transactionB = manageReadingDisplay.beginTransaction();
            transactionB.replace(R.id.fragReadDisplay, FragReadDisplay);
            transactionB.commit();

            FragmentManager manageBotNavBar = getSupportFragmentManager();
            FragmentTransaction transactionC = manageBotNavBar.beginTransaction();
            transactionC.replace(R.id.fragBotNav, FragBotNavBar);
            transactionC.commit();
        }
        else
        {
            //Commits fragments to display their corresponding views into the layout of detailed reading
            FragmentManager manageNavBar = getSupportFragmentManager();
            FragmentTransaction transactionA = manageNavBar.beginTransaction();
            transactionA.replace(R.id.fragNavBar, FragNavBar);
            transactionA.commit();

            FragmentManager manageReadingDisplay = getSupportFragmentManager();
            FragmentTransaction transactionB = manageReadingDisplay.beginTransaction();
            transactionB.replace(R.id.fragReadDisplay, FragReadDisplay);
            transactionB.commit();

            FragmentManager manageBotNavBar = getSupportFragmentManager();
            FragmentTransaction transactionC = manageBotNavBar.beginTransaction();
            transactionC.replace(R.id.fragBotNav, FragBotNavBar);
            transactionC.commit();
        }
    }
}
