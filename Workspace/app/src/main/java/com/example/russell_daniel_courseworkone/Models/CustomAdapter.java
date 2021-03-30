package com.example.russell_daniel_courseworkone.Models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.example.russell_daniel_courseworkone.R;
import java.util.ArrayList;
import java.util.List;

//Daniel Russell S1707149
//Class used to define a custom adapter for listing of readings, custom details are best examplified in get view
public class CustomAdapter extends ArrayAdapter {

    ArrayList<Reading> readings;

    public CustomAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);

        //sets the readings by casting the assumed objects as readings, this adapter is solely used in reading listing
        readings = (ArrayList<Reading>) objects;
    }

    //getview is used as the method to systematically process the readings for display in list view
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //uses androids default list item to inflate list
        v = inflater.inflate(android.R.layout.simple_list_item_1, null);
        Double tempMag;
        TextView tv;

        //retrieves reading based on postion passed via the overridden method
        Reading x = readings.get(position);

        //the default list item of android only uses a textview called text1
        tv = (TextView) v.findViewById(android.R.id.text1);

        tempMag = Double.parseDouble(x.getMagnitude());

        //textview is modified according to x reading
        //map activity bias of magnitude apply for low, medium, high readings
        if(tempMag <= 1.0){
            tv.setBackgroundColor(v.getResources().getColor(R.color.green));
            tv.setText(x.getTitle());
        }
        else if(tempMag > 2.0){
            tv.setBackgroundColor(v.getResources().getColor(R.color.red));
            tv.setText(x.getTitle());
        }
        else if(tempMag > 1.0){
            tv.setBackgroundColor(v.getResources().getColor(R.color.yellow));
            tv.setText(x.getTitle());
        }

        return v;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }
}
