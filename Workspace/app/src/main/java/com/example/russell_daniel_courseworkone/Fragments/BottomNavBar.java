package com.example.russell_daniel_courseworkone.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.russell_daniel_courseworkone.Controllers.MainActivity;
import com.example.russell_daniel_courseworkone.Controllers.MapActivity;
import com.example.russell_daniel_courseworkone.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

//Daniel Russell S1707149
//Class used to execute the Threaded Task as a new thread
public class BottomNavBar extends Fragment implements View.OnClickListener {

    private BottomNavigationView NavBar;
    private MenuItem pgListing;
    private MenuItem pgMap;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    )
    {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_bottom_navigation, container, false);
        NavBar = (BottomNavigationView) v.findViewById(R.id.bottom_navigation);

        NavBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.pgListing:
                        Intent Intent1 = new Intent(v.getContext(), MainActivity.class);
                        startActivity(Intent1);
                        break;
                    case R.id.pgMap:
                        Intent Intent2 = new Intent(v.getContext(), MapActivity.class);
                        startActivity(Intent2);
                        break;
                }
                return true;
            }
        });

        return v;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onClick(View v) {

    }
}
