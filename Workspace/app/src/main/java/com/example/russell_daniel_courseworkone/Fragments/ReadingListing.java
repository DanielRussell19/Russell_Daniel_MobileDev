package com.example.russell_daniel_courseworkone.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.russell_daniel_courseworkone.Controllers.DetailedReadingActivity;
import com.example.russell_daniel_courseworkone.Models.CustomAdapter;
import com.example.russell_daniel_courseworkone.Models.Reading;
import com.example.russell_daniel_courseworkone.Models.XmlParser;
import com.example.russell_daniel_courseworkone.R;
import java.util.List;

//Daniel Russell S1707149
//Class used to execute the Threaded Task as a new thread
public class ReadingListing extends Fragment implements AdapterView.OnItemClickListener, View.OnClickListener {

    private List<Reading> result = null;

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
        XmlParser xp = new XmlParser();
        result = xp.getXML();

        CustomAdapter AA = new CustomAdapter(v.getContext(), android.R.layout.simple_list_item_1, result);
        readingList.setAdapter( AA );
    }

    public void getReading(int pos, View v){
        Reading t = result.get(pos);
        Intent intent = new Intent(v.getContext(), DetailedReadingActivity.class);
        intent.putExtra("Reading", t);
        startActivity(intent);
        this.getActivity().finish();
    }

    public void sortMagnitude(View v){
        for(int i = 1; i < result.size(); i++){

            Reading target = result.get(i);
            int in = i - 1;

            while( in >= 0 && Double.parseDouble(result.get(in).getMagnitude()) < Double.parseDouble(target.getMagnitude()) ){
                result.set(in + 1, result.get(in));
                in = in - 1;
            }

            result.set(in + 1, target);
        }

        CustomAdapter AA = new CustomAdapter(v.getContext(), android.R.layout.simple_list_item_1, result);
        readingList.setAdapter( AA );
    }

    public void sortDeepest(View v){
        for(int i = 1; i < result.size(); i++){

            Reading target = result.get(i);
            int in = i - 1;

            while( in >= 0 && Integer.parseInt(result.get(in).getDepth()) < Integer.parseInt(target.getDepth()) ){
                result.set(in + 1, result.get(in));
                in = in - 1;
            }

            result.set(in + 1, target);
        }

        CustomAdapter AA = new CustomAdapter(v.getContext(), android.R.layout.simple_list_item_1, result);
        readingList.setAdapter( AA );
    }

    public void sortShallowest(View v){
        for(int i = 1; i < result.size(); i++){

            Reading target = result.get(i);
            int in = i - 1;

            while( in >= 0 && Integer.parseInt(result.get(in).getDepth()) > Integer.parseInt(target.getDepth()) ){
                result.set(in + 1, result.get(in));
                in = in - 1;
            }

            result.set(in + 1, target);
        }

        CustomAdapter AA = new CustomAdapter(v.getContext(), android.R.layout.simple_list_item_1, result);
        readingList.setAdapter( AA );
    }

    public void sortDirection(View v){
        for(int i = 1; i < result.size(); i++){

            Reading target = result.get(i);
            int in = i - 1;

            while( in >= 0 && Double.parseDouble(result.get(in).getMagnitude()) < Double.parseDouble(target.getMagnitude()) ){
                result.set(in + 1, result.get(in));
                in = in - 1;
            }

            result.set(in + 1, target);
        }

        CustomAdapter AA = new CustomAdapter(v.getContext(), android.R.layout.simple_list_item_1, result);
        readingList.setAdapter( AA );
    }

    public void sortDate(View v){
        for(int i = 1; i < result.size(); i++){

            Reading target = result.get(i);
            int in = i - 1;

            while( in >= 0 && Double.parseDouble(result.get(in).getMagnitude()) < Double.parseDouble(target.getMagnitude()) ){
                result.set(in + 1, result.get(in));
                in = in - 1;
            }

            result.set(in + 1, target);
        }

        CustomAdapter AA = new CustomAdapter(v.getContext(), android.R.layout.simple_list_item_1, result);
        readingList.setAdapter( AA );
    }

    @Override
    public void onClick(View v) {

        switch ( v.getId() ){
            case R.id.rbNone: getReadings(v); break;
            case R.id.btnDateFilter: sortDate(v); break;
            case R.id.btnDirectionFilter: sortDirection(v); break;
            case R.id.rbMag: sortMagnitude(v); break;
            case R.id.rbDepthA: sortShallowest(v); break;
            case R.id.rbDepthD: sortDeepest(v); break;
        }

    }

    public void dateDialog(){

    }

    public void dateDialogListener(){

    }
}
