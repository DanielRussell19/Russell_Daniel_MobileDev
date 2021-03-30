package com.example.russell_daniel_courseworkone.Controllers;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.russell_daniel_courseworkone.R;

//Daniel Russell S1707149
//Controller used to handle SplashScreen activity
public class SplashActivity extends AppCompatActivity {

    //variables
    private long time = 3000l;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //displays a slash screen for 3 seconds before switching to main activity
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                Intent mainSwitch = new Intent(SplashActivity.this, MainActivity.class);
                SplashActivity.this.startActivity(mainSwitch);
                SplashActivity.this.finish();
            }
        },time);
    }
}