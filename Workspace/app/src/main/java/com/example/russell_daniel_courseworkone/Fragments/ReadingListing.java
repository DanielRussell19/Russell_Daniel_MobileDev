package com.example.russell_daniel_courseworkone.Fragments;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.russell_daniel_courseworkone.Controllers.DetailedReadingActivity;
import com.example.russell_daniel_courseworkone.Models.CustomAdapter;
import com.example.russell_daniel_courseworkone.Models.Reading;
import com.example.russell_daniel_courseworkone.Models.XmlParser;
import com.example.russell_daniel_courseworkone.R;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.MarkerOptions;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

//Daniel Russell S1707149
//Class used to execute the Threaded Task as a new thread
public class ReadingListing extends Fragment implements AdapterView.OnItemClickListener, View.OnClickListener {

    private ListView readingList;
    private Button btnDateFilter;
    private Button btnDirectionFilter;

    private RadioGroup rgSorts;
    private RadioButton rbNone;
    private RadioButton rbMag;
    private RadioButton rbDepthA;
    private RadioButton rbDepthD;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    )
    {
        View v = inflater.inflate(R.layout.fragment_readinglisting, container, false);

        readingList = (ListView) v.findViewById(R.id.list);
        btnDateFilter = (Button) v.findViewById(R.id.btnDateFilter);
        btnDirectionFilter = (Button) v.findViewById(R.id.btnDirectionFilter);

        rgSorts = (RadioGroup) v.findViewById(R.id.rgSorts);
        rbNone = (RadioButton) v.findViewById(R.id.rbNone);
        rbMag = (RadioButton) v.findViewById(R.id.rbMag);
        rbDepthA = (RadioButton) v.findViewById(R.id.rbDepthA);
        rbDepthD = (RadioButton) v.findViewById(R.id.rbDepthD);

        readingList.setOnItemClickListener(this);

        rbNone.setOnClickListener(this);
        rbMag.setOnClickListener(this);
        rbDepthA.setOnClickListener(this);
        rbDepthD.setOnClickListener(this);
        btnDirectionFilter.setOnClickListener(this);
        btnDateFilter.setOnClickListener(this);

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

        CustomAdapter AA = new CustomAdapter(v.getContext(), android.R.layout.simple_list_item_1, result);
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

    public void sortDate(){

    }

    @Override
    public void onClick(View v) {

        switch ( v.getId() ){
            case R.id.rbNone: System.out.println("Noe"); break;
            case R.id.btnDateFilter: System.out.println("Date"); break;
            case R.id.btnDirectionFilter: System.out.println("Erection"); break;
            case R.id.rbMag: System.out.println("Mag"); break;
            case R.id.rbDepthA: System.out.println("DepthA"); break;
            case R.id.rbDepthD: System.out.println("DepthB"); break;
        }

    }
}
