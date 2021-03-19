package com.example.russell_daniel_courseworkone.Controllers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.russell_daniel_courseworkone.Fragments.BottomNavBar;
import com.example.russell_daniel_courseworkone.Fragments.TopActionBar;
import com.example.russell_daniel_courseworkone.Fragments.ReadingListing;
import com.example.russell_daniel_courseworkone.R;

//Daniel Russell S1707149
//Controller used to handle MainActivity
public class MainActivity extends AppCompatActivity {

    private TopActionBar FragNavBar;
    private BottomNavBar FragBotNavBar;
    private ReadingListing FragReadingListing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragReadingListing = new ReadingListing();
        FragNavBar = new TopActionBar();
        FragBotNavBar = new BottomNavBar();

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