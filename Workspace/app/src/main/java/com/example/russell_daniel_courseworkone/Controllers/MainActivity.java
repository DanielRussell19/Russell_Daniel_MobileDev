package com.example.russell_daniel_courseworkone.Controllers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import com.example.russell_daniel_courseworkone.Fragments.NavActionBar;
import com.example.russell_daniel_courseworkone.Fragments.ReadingListing;
import com.example.russell_daniel_courseworkone.R;

//Daniel Russell S1707149
//Controller used to handle MainActivity
public class MainActivity extends AppCompatActivity {

    private NavActionBar FragNavBar;
    private ReadingListing FragReadingListing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragReadingListing = new ReadingListing();
        FragNavBar = new NavActionBar();

        FragmentManager manageListing = getSupportFragmentManager();
        FragmentTransaction transactionA = manageListing.beginTransaction();
        transactionA.replace(R.id.fragReadListing, FragReadingListing);
        transactionA.commit();

        FragmentManager manageNavBar = getSupportFragmentManager();
        FragmentTransaction transactionB = manageNavBar.beginTransaction();
        transactionB.replace(R.id.fragNavBar, FragNavBar);
        transactionB.commit();
    }
}