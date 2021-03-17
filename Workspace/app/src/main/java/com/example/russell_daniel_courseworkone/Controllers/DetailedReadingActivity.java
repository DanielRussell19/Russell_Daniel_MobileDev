package com.example.russell_daniel_courseworkone.Controllers;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.example.russell_daniel_courseworkone.Fragments.BottomNavBar;
import com.example.russell_daniel_courseworkone.Fragments.ReadingDisplay;
import com.example.russell_daniel_courseworkone.Fragments.ReadingListing;
import com.example.russell_daniel_courseworkone.Fragments.TopActionBar;
import com.example.russell_daniel_courseworkone.Models.Reading;
import com.example.russell_daniel_courseworkone.R;

public class DetailedReadingActivity extends AppCompatActivity {
    private TopActionBar FragNavBar;
    private BottomNavBar FragBotNavBar;
    private ReadingDisplay FragReadDisplay;
    private Reading reading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailedreading);

        FragNavBar = new TopActionBar();
        FragBotNavBar = new BottomNavBar();

        reading = (Reading) getIntent().getSerializableExtra("Reading");

        FragReadDisplay = new ReadingDisplay(reading);

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
