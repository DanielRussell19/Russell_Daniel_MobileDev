package com.example.russell_daniel_courseworkone.Controllers;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.russell_daniel_courseworkone.Models.Reading;
import com.example.russell_daniel_courseworkone.R;
import java.util.List;

//Daniel Russell S1707149
//Controller used to handle MainActivity
public class MapActivity extends AppCompatActivity {

    List<Reading> readings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);

}
}
