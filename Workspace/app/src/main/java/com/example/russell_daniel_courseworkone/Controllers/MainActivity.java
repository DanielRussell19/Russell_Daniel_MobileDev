package com.example.russell_daniel_courseworkone.Controllers;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.russell_daniel_courseworkone.Models.Reading;
import com.example.russell_daniel_courseworkone.Models.ThreadedTask;
import com.example.russell_daniel_courseworkone.Models.XmlParser;
import com.example.russell_daniel_courseworkone.R;
import java.util.List;

//Daniel Russell S1707149
//Controller used to handle MainActivity
public class MainActivity extends AppCompatActivity {

    private LinearLayout test;
    private List<Reading> result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        test = (LinearLayout) findViewById(R.id.test);

        XmlParser xp = new XmlParser();
        result = xp.getXML();

        for(Reading x: result){
            TextView t = new TextView(this);
            t.setText(x.getPubdate());
            test.addView(t);
        }
    }
}