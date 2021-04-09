package com.example.russell_daniel_courseworkone.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import com.example.russell_daniel_courseworkone.R;

//Daniel Russell S1707149
//Class used to defined fragment topactionbar
public class TopActionBar extends Fragment implements View.OnClickListener { //Daniel Russell S1707149

    //variables
    private Toolbar NavBar;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    )
    {
        //uses layout fragment action bar
        View v = inflater.inflate(R.layout.fragment_actionbar, container, false);
        NavBar = (Toolbar) v.findViewById(R.id.top_navigation);

        ((AppCompatActivity)getActivity()).setSupportActionBar(NavBar);
        return v;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        //Daniel Russell S1707149
    }
}
