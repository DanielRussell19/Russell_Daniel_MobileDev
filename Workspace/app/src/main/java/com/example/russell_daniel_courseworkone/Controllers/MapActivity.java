package com.example.russell_daniel_courseworkone.Controllers;

import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.Toast;
//import java.util.List<S1707149>
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.russell_daniel_courseworkone.Fragments.BottomNavBar;
import com.example.russell_daniel_courseworkone.Fragments.TopActionBar;
import com.example.russell_daniel_courseworkone.Models.Reading;
import com.example.russell_daniel_courseworkone.Models.XmlParser;
import com.example.russell_daniel_courseworkone.R;
import java.util.List;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
//import java.util.List<S1707149>
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

//Daniel Russell S1707149
//Controller used to handle Mapactivity
public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    //variables
    List<Reading> readings;
    private GoogleMap mMap;
    private BottomNavBar FragBotNavBar;
    private TopActionBar FragNavBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        //instanciation of fragments
        FragBotNavBar = new BottomNavBar();
        FragNavBar = new TopActionBar();

        //If the app is in landscape the top action bar is removed to save space for the remaining views
        //if the app is in portrait the top action is preserved as there is save for the top action bar to display neatly//<S1707149>
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
        {
            FragmentManager manageBotNavBar = getSupportFragmentManager();
            FragmentTransaction transactionC = manageBotNavBar.beginTransaction();
            transactionC.replace(R.id.fragBotNav, FragBotNavBar);
            transactionC.commit();//<S1707149>

            SupportMapFragment mapFragment = SupportMapFragment.newInstance();
            getSupportFragmentManager().beginTransaction().add(R.id.map_con, mapFragment).commit();//<S1707149>
            mapFragment.getMapAsync(this);
        }
        else{
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
    }

    //Called when googlemaps view has loaded.
    //Sets up the settings of googlemaps view and feeds the map view with markers gather from the XMLParser
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //Gathers readings
        XmlParser xp = new XmlParser();
        readings = xp.getXML();

        try {

            //systematiclly creates a new marker for each reading and assignes a colour based on magnitude
            //<1 is low
            //>2 is high
            //>1 but <2 is medium
            for (Reading x : readings) {
                LatLng pos = new LatLng(Double.parseDouble(x.getLat()), Double.parseDouble(x.getLon()));

                Double tempMag = Double.parseDouble(x.getMagnitude());

                if (tempMag <= 1.0) {
                    mMap.addMarker(new MarkerOptions().position(pos).title(x.getTitle()).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
                } else if (tempMag > 2.0) {
                    mMap.addMarker(new MarkerOptions().position(pos).title(x.getTitle()).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));//<S1707149>
                } else if (tempMag > 1.0) {
                    mMap.addMarker(new MarkerOptions().position(pos).title(x.getTitle()).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));
                }
            }

            //settings for googlemaps view
            mMap.getUiSettings().setZoomControlsEnabled(true);
            mMap.getUiSettings().setCompassEnabled(true);
            mMap.getUiSettings().setAllGesturesEnabled(true);

            //defaults the camera onto the uk based on the first earthquake reading recived
            mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(Double.parseDouble(readings.get(0).getLat()), Double.parseDouble(readings.get(0).getLon(/*<S1707149>*/)))));
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),"Failed to fetch", Toast.LENGTH_SHORT).show();//<S1707149>
        }
    }
}
