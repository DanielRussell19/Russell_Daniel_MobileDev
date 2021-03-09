package com.example.russell_daniel_courseworkone.Controllers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import com.example.russell_daniel_courseworkone.R;

public class SplashActivity extends Activity {

    private long time = 3000l;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

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