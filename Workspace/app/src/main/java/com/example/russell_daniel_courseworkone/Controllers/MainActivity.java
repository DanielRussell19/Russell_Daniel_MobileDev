package com.example.russell_daniel_courseworkone.Controllers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.DatePickerDialog;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.DatePicker;

import com.example.russell_daniel_courseworkone.Fragments.BottomNavBar;
import com.example.russell_daniel_courseworkone.Fragments.TopActionBar;
import com.example.russell_daniel_courseworkone.Fragments.ReadingListing;
import com.example.russell_daniel_courseworkone.R;

import java.text.DateFormat;
import java.util.Calendar;

//Daniel Russell S1707149
//Controller used to handle main activity
public class MainActivity extends AppCompatActivity {

    //variables
    private TopActionBar FragNavBar; //defines fragnavbar using fragment class topactionbar
    private BottomNavBar FragBotNavBar; //defines fragbotnavbar using fragment class bottomnavbar
    private ReadingListing FragReadingListing; //defines fragreadinglisting as fragment class readinglisting

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //instanciation of fragments
        FragReadingListing = new ReadingListing();
        FragNavBar = new TopActionBar();
        FragBotNavBar = new BottomNavBar();

        //If the app is in landscape the top action bar is removed to save space for the remaining views
        //if the app is in portrait the top action is preserved as there is save for the top action bar to display neatly
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            FragmentManager manageListing = getSupportFragmentManager();
            FragmentTransaction transactionA = manageListing.beginTransaction();
            transactionA.replace(R.id.fragReadListing, FragReadingListing);
            transactionA.commit();

            FragmentManager manageBotNavBar = getSupportFragmentManager();
            FragmentTransaction transactionC = manageBotNavBar.beginTransaction();
            transactionC.replace(R.id.fragBotNav, FragBotNavBar);
            transactionC.commit();
        } else {
            FragmentManager manageListing = getSupportFragmentManager();
            FragmentTransaction transactionA = manageListing.beginTransaction();
            transactionA.replace(R.id.fragReadListing, FragReadingListing);
            transactionA.commit();

            FragmentManager manageNavBar = getSupportFragmentManager();
            FragmentTransaction transactionB = manageNavBar.beginTransaction();
            transactionB.replace(R.id.fragNavBar, FragNavBar);
            transactionB.commit();

            FragmentManager manageBotNavBar = getSupportFragmentManager();
            FragmentTransaction transactionC = manageBotNavBar.beginTransaction();
            transactionC.replace(R.id.fragBotNav, FragBotNavBar);
            transactionC.commit();
        }
    }
}