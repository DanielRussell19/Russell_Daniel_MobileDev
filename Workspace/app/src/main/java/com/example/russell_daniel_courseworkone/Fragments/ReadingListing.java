package com.example.russell_daniel_courseworkone.Fragments;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
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
import android.widget.Toast;

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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

//Daniel Russell S1707149
//Class used to execute the Threaded Task as a new thread

//possibly unnessesary amount of implements but wouldn't work otherwise
public class ReadingListing extends Fragment implements AdapterView.OnItemClickListener, View.OnClickListener, AdapterView.OnItemSelectedListener, DatePickerDialog.OnDateSetListener { //Daniel Russell S1707149

    //variables
    private Handler h = new Handler();
    private Runnable recursiveFetch;

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
        ArrayAdapter<String> aaD = new ArrayAdapter<String>(v.getContext(), android.R.layout.simple_spinner_item, directions); //Daniel Russell S1707149
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

    //on stopping this fragment the recurrive task is killed
    @Override
    public void onStop() {
        super.onStop();
        h.removeCallbacksAndMessages(null);
        System.out.println("Recursive Fetch Killed.");
    }

    //on start getreadings in executed
    //the recurrsive task of getreadingupdates is executed every 10 seconds
    @Override
    public void onStart() {
        super.onStart();
        getReadings(getView());

        recursiveFetch = new Runnable() {
            @Override
            public void run() {
                getReadingUpdates();
                System.out.println("Result Renewed");
                h.postDelayed(this, 10000);
            }
        };

        h.post(recursiveFetch);
    }

    //if a list item is selected such as on the listview for which this is assigned to the corrosponding position is used to //Daniel Russell S1707149
    //get that specific reading to view in detail
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        getReading(position, view);
    }

    //if list item selected method is executed, used to sort by direction using either dateresult(if not null) or result //Daniel Russell S1707149
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

                    while( in >= 0 && Double.parseDouble(usedList.get(in).getLat()) < Double.parseDouble(target.getLat()) ){ //Daniel Russell S1707149
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

                    while( in >= 0 && Double.parseDouble(usedList.get(in).getLon()) < Double.parseDouble(target.getLon()) ){ //Daniel Russell S1707149
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

                    while( in >= 0 && Double.parseDouble(usedList.get(in).getLon()) > Double.parseDouble(target.getLon()) ){ //Daniel Russell S1707149
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

    //overriden methods onnothingselected to ensure nothing happens
    @Override
    public void onNothingSelected(AdapterView<?> parent) { }

    //onclicklistener for button clicks, mainly for radio group and date buttons
    @Override
    public void onClick(View v) {

        switch ( v.getId() ){
            case R.id.rbNone: clearDate(); btnDirectionFilter.setSelection(0); rgSorts.clearCheck(); getReadings(v); break;
            case R.id.rbMag: sortMagnitude(v); break;
            case R.id.rbDepthA: sortShallowest(v); break;
            case R.id.rbDepthD: sortDeepest(v); break;//S1707149
            case R.id.btnDateFilterStart: dateShowDialog(); break;
            case R.id.btnDateFilterEnd: dateShowDialog(); break;
            case R.id.btnDateFilterClear: clearDate(); btnDirectionFilter.setSelection(0); rgSorts.clearCheck(); getReadings(v); break; //Daniel Russell S1707149
        }

    }

    //executed when one date is selected, triggers search by date
    public void setStartDate(String date){
        this.startDate = date;
        searchByDate(date);
        btnDateFilterEnd.setEnabled(true);
    }

    //executed when two dates are selected, triggers search by between two dates//S1707149
    public void setEndDate(String endDate){
        this.endDate = endDate;
        searchBetweenDates(startDate, endDate);
    }

    //clears variables used for date selection and resets user interface
    public void clearDate(){
        btnDateFilterEnd.setText("Date End?");
        btnDateFilterStart.setText("Date Start?");

        btnDateFilterEnd.setEnabled(false);
        btnDateFilterStart.setEnabled(true);

        this.startDate = null;
        this.endDate = null;

        resultDate = null;
    }

    //displays the custom date picker dialog on screen, use of depracated methods required to work from a fragment //Daniel Russell S1707149
    public void dateShowDialog(){
        DialogFragment datePicker = new CustomDatePicker();
        datePicker.setTargetFragment(this, 0);
        datePicker.show(getFragmentManager(), "Date Dialog" /*S1707149*/);
    }

    //executing when a date is selected from datepicker dialog
    //checks if start date is filled first before allowing for the end date to be entered
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

    //used on start up and reset to get the latest readings and resets the listview accordingly
    public void getReadings(View v){
        XmlParser xp = new XmlParser();
        result = xp.getXML();

        try{
            Reading test = result.get(0);
        }
        catch (Exception e){
            Toast.makeText(v.getContext(),"Failed to fetch", Toast.LENGTH_SHORT).show();
        }

        CustomAdapter AA = new CustomAdapter(v.getContext(), android.R.layout.simple_list_item_1, result);
        AA.notifyDataSetChanged();

        readingList.setAdapter( AA );
    }

    //used for recurrrive fetch to passively get reading updates
    public void getReadingUpdates(){
        XmlParser xp = new XmlParser();
        List<Reading> newList = xp.getXML();

        if(!result.containsAll(newList)){
            result = new ArrayList<Reading>();
            result.addAll(newList);
        }
    }

    //if result date is not null result date is used, if it is result is used
    //gets the specified reading according by position from listview
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

    //if result date is not null result date is used, if it is result is used
    //sort reading enties by magnitude in decending order
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

            while( in >= 0 && Double.parseDouble(usedList.get(in).getMagnitude()) < Double.parseDouble(target.getMagnitude()) ){ //Daniel Russell S1707149
                usedList.set(in + 1, usedList.get(in));
                in = in - 1;
            }

            usedList.set(in + 1, target);
        }

        AA.notifyDataSetChanged();

        readingList.setAdapter( AA );
    }

    //if result date is not null result date is used, if it is result is used
    //sorts reading enties according by depth in decending order
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

            while( in >= 0 && Integer.parseInt(usedList.get(in).getDepth()) < Integer.parseInt(target.getDepth()) ){ //Daniel Russell S1707149
                usedList.set(in + 1, usedList.get(in));
                in = in - 1;
            }

            usedList.set(in + 1, target);
        }

        AA.notifyDataSetChanged();

        readingList.setAdapter( AA );
    }

    //if result date is not null result date is used, if it is result is used
    //sorts reading enties according by depth in accending order
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
        //Daniel Russell S1707149
        readingList.setAdapter( AA );
    }

    //fills the resultdate list with result list entries
    //resultdate is then filtered for entries that match the defined date
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
        //Monday, March 22, 2021//Daniel Russell S1707149
    }

    //fills the resultdate list with result list entries
    //resultdate is then filtered for dates that are outside the defined confines for removal
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

            CustomAdapter AA = new CustomAdapter(getView().getContext(), android.R.layout.simple_list_item_1, resultDate);//Daniel Russell S1707149
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

    //method used to parse the resulting date from the date picker dialog into something similar with Reading date
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
