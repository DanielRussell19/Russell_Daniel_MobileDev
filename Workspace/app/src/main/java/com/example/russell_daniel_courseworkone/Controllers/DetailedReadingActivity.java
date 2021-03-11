package com.example.russell_daniel_courseworkone.Controllers;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.russell_daniel_courseworkone.Models.Reading;
import com.example.russell_daniel_courseworkone.R;

//Daniel Russell S1707149
//Controller used to handle MainActivity
public class DetailedReadingActivity extends AppCompatActivity {

    private Reading item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_reading);
    }
}
