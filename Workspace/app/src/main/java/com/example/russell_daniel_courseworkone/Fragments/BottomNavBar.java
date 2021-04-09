package com.example.russell_daniel_courseworkone.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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
//Class used to define fragment bottom nav bar
public class BottomNavBar extends Fragment implements View.OnClickListener {

    //variables
    private BottomNavigationView NavBar;
    private View pgListing;
    private View pgMap;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    )
    {
        // Inflate the layout for this fragment//<S1707149>
        View v = inflater.inflate(R.layout.fragment_bottom_navigation, container, false);
        NavBar = (BottomNavigationView) v.findViewById(R.id.bottom_navigation);

        pgListing = (View) v.findViewById(R.id.pgListing);
        pgMap = (View) v.findViewById(R.id.pgMap);

        //Attempt at fixing the icon hightlights for bottom nav bar, wrong icon is hightlighted regardless likely due to modular approach via fragments
        switch( getActivity().getLocalClassName() ){
            case "Controllers.MainActivity": pgListing.setEnabled(false); pgMap.setEnabled(true); break;
            case "Controllers.MapActivity": pgListing.setEnabled(true); pgMap.setEnabled(false); break;
            default: pgListing.setEnabled(true); pgMap.setEnabled(true); break;
        }

        //if map icon is selected by user, new mapactivity is created
        //if listing icon is selected by user, new listing activity is created
        NavBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() { //<S1707149>
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.pgListing:
                        Intent Intent1 = new Intent(v.getContext(), MainActivity.class);
                        getActivity().finish();
                        startActivity(Intent1);
                        break;
                    case R.id.pgMap:
                        Intent Intent2 = new Intent(v.getContext(), MapActivity.class);
                        getActivity().finish();//<S1707149>
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
        super.onViewCreated(view,/*<S1707149>*/ savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        //<S1707149>
    }
}
