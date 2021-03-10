package com.example.russell_daniel_courseworkone.Controllers;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.russell_daniel_courseworkone.Models.Reading;
import com.example.russell_daniel_courseworkone.Models.ThreadedTask;
import com.example.russell_daniel_courseworkone.R;
import java.util.List;

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

class XmlParser
{
    private List<Reading> result;
    private Object lock = new Object();

    public List<Reading> getXML() //XmlParser Start
    {
        try
        {
            synchronized (lock){
                ThreadedTask tsk = new ThreadedTask();
                tsk.setLock(lock);

                tsk.start(); //Starts the method responsible for getting the XML and parsing on a separate thread

                if(tsk.isAlive()){
                    lock.wait();
                }

                result = tsk.getResult();
            }

            return result;
        }
        catch (Exception e)
        {
            Log.println(Log.ERROR, "GetXMLInit", e.toString());
        }

        return null;
    }
}