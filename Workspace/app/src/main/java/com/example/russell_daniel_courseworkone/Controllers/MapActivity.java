package com.example.russell_daniel_courseworkone.Controllers;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.russell_daniel_courseworkone.Fragments.BottomNavBar;
import com.example.russell_daniel_courseworkone.Fragments.TopActionBar;
import com.example.russell_daniel_courseworkone.Models.Reading;
import com.example.russell_daniel_courseworkone.R;
import java.util.List;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

//Daniel Russell S1707149
//Controller used to handle MainActivity
public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    List<Reading> readings;
    private GoogleMap mMap;
    private BottomNavBar FragBotNavBar;
    private TopActionBar FragNavBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        FragBotNavBar = new BottomNavBar();
        FragNavBar = new TopActionBar();

        FragmentManager manageNavBar = getSupportFragmentManager();
        FragmentTransaction transactionB = manageNavBar.beginTransaction();
        transactionB.replace(R.id.fragNavBar, FragNavBar);
        transactionB.commit();

        FragmentManager manageBotNavBar = getSupportFragmentManager();
        FragmentTransaction transactionC = manageBotNavBar.beginTransaction();
        transactionC.replace(R.id.fragBotNav, FragBotNavBar);
        transactionC.commit();

        SupportMapFragment mapFragment = SupportMapFragment.newInstance();
        getSupportFragmentManager().beginTransaction().add(R.id.map_con, mapFragment).commit();
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions()
                .position(sydney)
                .title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
