package com.example.russell_daniel_courseworkone.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;
import com.example.russell_daniel_courseworkone.Models.Reading;
import com.example.russell_daniel_courseworkone.Models.XmlParser;
import com.example.russell_daniel_courseworkone.R;

import java.util.ArrayList;
import java.util.List;

//Daniel Russell S1707149
//Class used to execute the Threaded Task as a new thread
public class ReadingListing extends Fragment implements View.OnClickListener {

    private ListView readingList;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    )
    {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_readinglisting, container, false);

        return v;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onStart() {
        super.onStart();
        getReadings(getView());
    }

    @Override
    public void onClick(View v) {

    }

    public void getReadings(View v){
        readingList = (ListView) v.findViewById(R.id.list);

        List<Reading> result;
        XmlParser xp = new XmlParser();
        result = xp.getXML();

        ArrayList<String> readingTitles = new ArrayList<String>();
        for(Reading x : result){
            readingTitles.add(x.getTitle());
        }

        ArrayAdapter<String> AA = new ArrayAdapter<String>(v.getContext(), android.R.layout.simple_list_item_1, readingTitles);

        readingList.setAdapter( AA );
    }

    public void getReading(){

    }

    public void sortMagnitude(){

    }

    public void sortDeepest(){

    }

    public void sortShallowest(){

    }

    public void sortDirection(){

    }

}
