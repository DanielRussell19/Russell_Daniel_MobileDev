package com.example.russell_daniel_courseworkone.Fragments;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import com.example.russell_daniel_courseworkone.Controllers.DetailedReadingActivity;
import com.example.russell_daniel_courseworkone.Models.CustomAdapter;
import com.example.russell_daniel_courseworkone.Models.CustomDatePicker;
import com.example.russell_daniel_courseworkone.Models.Reading;
import com.example.russell_daniel_courseworkone.Models.XmlParser;
import com.example.russell_daniel_courseworkone.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

//Daniel Russell S1707149
//Class used to execute the Threaded Task as a new thread
public class ReadingListing extends Fragment implements AdapterView.OnItemClickListener, View.OnClickListener, AdapterView.OnItemSelectedListener, DatePickerDialog.OnDateSetListener {

    private List<Reading> result = null;
    private List<Reading> resultDate = null;
    private String startDate, endDate;

    private ListView readingList;
    private Spinner btnDirectionFilter;

    private Button btnDateFilterStart, btnDateFilterEnd, btnDateFilterClear;

    private RadioGroup rgSorts;
    private Button rbNone;
    private RadioButton rbMag, rbDepthA, rbDepthD;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    )
    {
        View v = inflater.inflate(R.layout.fragment_readinglisting, container, false);

        readingList = (ListView) v.findViewById(R.id.list);

        btnDateFilterStart = (Button) v.findViewById(R.id.btnDateFilterStart);
        btnDateFilterEnd = (Button) v.findViewById(R.id.btnDateFilterEnd);
        btnDateFilterClear = (Button) v.findViewById(R.id.btnDateFilterClear);
        btnDateFilterEnd.setEnabled(false);

        btnDirectionFilter = (Spinner) v.findViewById(R.id.btnDirectionFilter);

        rgSorts = (RadioGroup) v.findViewById(R.id.rgSorts);
        rbNone = (Button) v.findViewById(R.id.rbNone);
        rbMag = (RadioButton) v.findViewById(R.id.rbMag);
        rbDepthA = (RadioButton) v.findViewById(R.id.rbDepthA);
        rbDepthD = (RadioButton) v.findViewById(R.id.rbDepthD);

        readingList.setOnItemClickListener(this);

        btnDirectionFilter.setOnItemSelectedListener(this);
        String directions[] = {"None","North", "West", "East", "South"};
        ArrayAdapter<String> aaD = new ArrayAdapter<String>(v.getContext(), android.R.layout.simple_spinner_item, directions);
        btnDirectionFilter.setAdapter(aaD);

        rbNone.setOnClickListener(this);
        rbMag.setOnClickListener(this);
        rbDepthA.setOnClickListener(this);
        rbDepthD.setOnClickListener(this);

        btnDateFilterStart.setOnClickListener(this);
        btnDateFilterEnd.setOnClickListener(this);
        btnDateFilterClear.setOnClickListener(this);

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

    @Override
    public void onItemSelected(AdapterView<?> p, View v, int pos, long id) {

        List<Reading> usedList;

        CustomAdapter AA;
        if(resultDate == null){
            usedList = result;
           AA = new CustomAdapter(v.getContext(), android.R.layout.simple_list_item_1, usedList);

        }
        else{
            usedList = resultDate;
            AA = new CustomAdapter(v.getContext(), android.R.layout.simple_list_item_1, usedList);
        }


        switch ( p.getItemAtPosition(pos).toString() ){
            case "None":
                break;

            case "North":
                for(int i = 1; i < usedList.size(); i++){

                    Reading target = usedList.get(i);
                    int in = i - 1;

                    while( in >= 0 && Double.parseDouble(usedList.get(in).getLat()) < Double.parseDouble(target.getLat()) ){
                        usedList.set(in + 1, usedList.get(in));
                        in = in - 1;
                    }

                    usedList.set(in + 1, target);
                }
                rgSorts.clearCheck();

                readingList.setAdapter( AA );
                AA.notifyDataSetChanged();
                break;

            case "East":
                for(int i = 1; i < usedList.size(); i++){

                    Reading target = usedList.get(i);
                    int in = i - 1;

                    while( in >= 0 && Double.parseDouble(usedList.get(in).getLon()) < Double.parseDouble(target.getLon()) ){
                        usedList.set(in + 1, usedList.get(in));
                        in = in - 1;
                    }

                    usedList.set(in + 1, target);
                }
                rgSorts.clearCheck();

                readingList.setAdapter( AA );
                AA.notifyDataSetChanged();
                break;

            case "South":
                for(int i = 1; i < usedList.size(); i++){

                    Reading target = usedList.get(i);
                    int in = i - 1;

                    while( in >= 0 && Double.parseDouble(usedList.get(in).getLat()) > Double.parseDouble(target.getLat()) ){
                        usedList.set(in + 1, usedList.get(in));
                        in = in - 1;
                    }

                    usedList.set(in + 1, target);
                }
                rgSorts.clearCheck();

                readingList.setAdapter( AA );
                AA.notifyDataSetChanged();
                break;

            case "West":
                for(int i = 1; i < usedList.size(); i++){

                    Reading target = usedList.get(i);
                    int in = i - 1;

                    while( in >= 0 && Double.parseDouble(usedList.get(in).getLon()) > Double.parseDouble(target.getLon()) ){
                        usedList.set(in + 1, usedList.get(in));
                        in = in - 1;
                    }

                    usedList.set(in + 1, target);
                }
                rgSorts.clearCheck();

                readingList.setAdapter( AA );
                AA.notifyDataSetChanged();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) { }

    @Override
    public void onClick(View v) {

        switch ( v.getId() ){
            case R.id.rbNone: clearDate(); btnDirectionFilter.setSelection(0); rgSorts.clearCheck(); getReadings(v); break;
            case R.id.rbMag: sortMagnitude(v); break;
            case R.id.rbDepthA: sortShallowest(v); break;
            case R.id.rbDepthD: sortDeepest(v); break;
            case R.id.btnDateFilterStart: dateShowDialog(); break;
            case R.id.btnDateFilterEnd: dateShowDialog(); break;
            case R.id.btnDateFilterClear: clearDate(); btnDirectionFilter.setSelection(0); rgSorts.clearCheck(); getReadings(v); break;
        }

    }

    public void setStartDate(String date){
        this.startDate = date;
        searchByDate(date);
        btnDateFilterEnd.setEnabled(true);
    }

    public void setEndDate(String endDate){
        this.endDate = endDate;
        searchBetweenDates(startDate, endDate);
    }

    public void clearDate(){
        btnDateFilterEnd.setText("Date End?");
        btnDateFilterStart.setText("Date Start?");

        btnDateFilterEnd.setEnabled(false);
        btnDateFilterStart.setEnabled(true);

        this.startDate = null;
        this.endDate = null;

        resultDate = null;
    }

    public void dateShowDialog(){
        DialogFragment datePicker = new CustomDatePicker();
        datePicker.setTargetFragment(this, 0);
        datePicker.show(getFragmentManager(), "Date Dialog");
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        String selectedDate = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());

        if(btnDateFilterEnd.isEnabled() && startDate != null){
            setEndDate(selectedDate);
            btnDateFilterEnd.setEnabled(false);
        }
        else{
            setStartDate(selectedDate);
            btnDateFilterStart.setEnabled(false);
        }
    }

    public void getReadings(View v){
        XmlParser xp = new XmlParser();
        result = xp.getXML();

        CustomAdapter AA = new CustomAdapter(v.getContext(), android.R.layout.simple_list_item_1, result);
        AA.notifyDataSetChanged();

        readingList.setAdapter( AA );
    }

    public void getReading(int pos, View v){
        List<Reading> usedList;

        if(resultDate == null){
            usedList = result;
        }
        else{
            usedList = resultDate;
        }

        Reading t = usedList.get(pos);
        Intent intent = new Intent(v.getContext(), DetailedReadingActivity.class);
        intent.putExtra("Reading", t);
        this.getActivity().finish();
        startActivity(intent);
    }

    public void sortMagnitude(View v){

        List<Reading> usedList;

        CustomAdapter AA;
        if(resultDate == null){
            usedList = result;
            AA = new CustomAdapter(v.getContext(), android.R.layout.simple_list_item_1, usedList);

        }
        else{
            usedList = resultDate;
            AA = new CustomAdapter(v.getContext(), android.R.layout.simple_list_item_1, usedList);
        }

        for(int i = 1; i < usedList.size(); i++){

            Reading target = usedList.get(i);
            int in = i - 1;

            while( in >= 0 && Double.parseDouble(usedList.get(in).getMagnitude()) < Double.parseDouble(target.getMagnitude()) ){
                usedList.set(in + 1, usedList.get(in));
                in = in - 1;
            }

            usedList.set(in + 1, target);
        }

        AA.notifyDataSetChanged();

        readingList.setAdapter( AA );
    }

    public void sortDeepest(View v){

        List<Reading> usedList;

        CustomAdapter AA;
        if(resultDate == null){
            usedList = result;
            AA = new CustomAdapter(v.getContext(), android.R.layout.simple_list_item_1, usedList);

        }
        else{
            usedList = resultDate;
            AA = new CustomAdapter(v.getContext(), android.R.layout.simple_list_item_1, usedList);
        }

        for(int i = 1; i < usedList.size(); i++){

            Reading target = usedList.get(i);
            int in = i - 1;

            while( in >= 0 && Integer.parseInt(usedList.get(in).getDepth()) < Integer.parseInt(target.getDepth()) ){
                usedList.set(in + 1, usedList.get(in));
                in = in - 1;
            }

            usedList.set(in + 1, target);
        }

        AA.notifyDataSetChanged();

        readingList.setAdapter( AA );
    }

    public void sortShallowest(View v){

        List<Reading> usedList;

        CustomAdapter AA;
        if(resultDate == null){
            usedList = result;
            AA = new CustomAdapter(v.getContext(), android.R.layout.simple_list_item_1, usedList);

        }
        else{
            usedList = resultDate;
            AA = new CustomAdapter(v.getContext(), android.R.layout.simple_list_item_1, usedList);
        }

        for(int i = 1; i < usedList.size(); i++){

            Reading target = usedList.get(i);
            int in = i - 1;

            while( in >= 0 && Integer.parseInt(usedList.get(in).getDepth()) > Integer.parseInt(target.getDepth()) ){
                usedList.set(in + 1, usedList.get(in));
                in = in - 1;
            }

            usedList.set(in + 1, target);
        }

        AA.notifyDataSetChanged();

        readingList.setAdapter( AA );
    }

    public void searchByDate(String date){
        try{
            List<Reading> listremove = new ArrayList<Reading>();
            resultDate = new ArrayList<Reading>();
            resultDate.addAll(result);

            String in = parseDate(date);

            DateFormat format = new SimpleDateFormat("E, dd MMM yyyy");
            Date queryTarget = format.parse(in);

            for(Reading x : resultDate){
                Date xDate = format.parse(x.getPubdate());
                if(!queryTarget.equals(xDate)){
                    listremove.add(x);
                }
            }

            resultDate.removeAll(listremove);

            CustomAdapter AA = new CustomAdapter(getView().getContext(), android.R.layout.simple_list_item_1, resultDate);
            AA.notifyDataSetChanged();

            readingList.setAdapter( AA );
            btnDateFilterStart.setText(in.substring(0, 16));
        }
        catch(Exception e){
            System.out.println(e);
        }

        //tue, 16 mar 2021, time
        //Monday, March 22, 2021
    }

    public void searchBetweenDates(String dateS, String dateE){
        try{
            List<Reading> listremove = new ArrayList<Reading>();
            resultDate = new ArrayList<Reading>();
            resultDate.addAll(result);

            String in1 = parseDate(dateS);
            String in2 = parseDate(dateE);

            DateFormat format = new SimpleDateFormat("E, dd MMM yyyy");
            Date target1 = format.parse(in1);
            Date target2 = format.parse(in2);

            for(Reading x : resultDate){
                Date xDate = format.parse(x.getPubdate());
                if((xDate.equals(target1) || xDate.equals(target2))){

                }
                else if(!(xDate.after(target1) && xDate.before(target2))){
                    listremove.add(x);
                }
            }

            resultDate.removeAll(listremove);

            CustomAdapter AA = new CustomAdapter(getView().getContext(), android.R.layout.simple_list_item_1, resultDate);
            AA.notifyDataSetChanged();

            readingList.setAdapter( AA );
            btnDateFilterEnd.setText(in2.substring(0, 16));
        }
        catch(Exception e){
            System.out.println(e);
        }

        //tue, 16 mar 2021, time
        //Monday, March 22, 2021
    }

    public String parseDate(String in) {
        try{
            DateFormat originalFormat = new SimpleDateFormat("E, MMMM dd, yyyy", Locale.ENGLISH);
            DateFormat targetFormat = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss");

            Date date = originalFormat.parse(in);
            String out = targetFormat.format(date);

            return out;
        }
        catch(ParseException e){
            System.out.println(e);
        }
        return null;
    }
}
