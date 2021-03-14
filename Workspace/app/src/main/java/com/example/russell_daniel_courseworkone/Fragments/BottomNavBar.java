package com.example.russell_daniel_courseworkone.Fragments;

import android.content.ClipData;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.russell_daniel_courseworkone.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

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
                    case R.id.pgListing: System.out.println("Hey"); break;
                    case R.id.pgMap: System.out.println("Hey"); break;
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
