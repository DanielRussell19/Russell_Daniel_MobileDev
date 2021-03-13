package com.example.russell_daniel_courseworkone.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.russell_daniel_courseworkone.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BottomNavBar extends Fragment implements View.OnClickListener {

    private BottomNavigationView NavBar;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    )
    {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_bottom_navigation, container, false);
        NavBar = (BottomNavigationView) v.findViewById(R.id.bottom_navigation);

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
