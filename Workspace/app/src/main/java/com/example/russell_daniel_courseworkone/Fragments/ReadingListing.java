package com.example.russell_daniel_courseworkone.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.russell_daniel_courseworkone.Controllers.DetailedReadingActivity;
import com.example.russell_daniel_courseworkone.Models.Reading;
import com.example.russell_daniel_courseworkone.Models.XmlParser;
import com.example.russell_daniel_courseworkone.R;
import java.util.ArrayList;
import java.util.List;

//Daniel Russell S1707149
//Class used to execute the Threaded Task as a new thread
public class ReadingListing extends Fragment implements AdapterView.OnItemClickListener {

    private ListView readingList;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    )
    {
        View v = inflater.inflate(R.layout.fragment_readinglisting, container, false);

        readingList = (ListView) v.findViewById(R.id.list);
        readingList.setOnItemClickListener(this);

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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        getReading(position, view);
    }

    public void getReadings(View v){
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

    public void getReading(int pos, View v){
        List<Reading> result;
        XmlParser xp = new XmlParser();
        result = xp.getXML();

        Reading t = result.get(pos);
        Intent intent = new Intent(v.getContext(), DetailedReadingActivity.class);
        intent.putExtra("Reading", t);
        startActivity(intent);
        this.getActivity().finish();
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
