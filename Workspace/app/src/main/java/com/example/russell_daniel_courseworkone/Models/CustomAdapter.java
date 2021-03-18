package com.example.russell_daniel_courseworkone.Models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.russell_daniel_courseworkone.R;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CustomAdapter extends ArrayAdapter {

    ArrayList<Reading> readings;

    public CustomAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);

        readings = (ArrayList<Reading>) objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v; //= super.getView(position, convertView, parent);
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        v = inflater.inflate(android.R.layout.simple_list_item_1, null);
        Double tempMag;
        TextView tv;
        Reading x = readings.get(position);

        tv = (TextView) v.findViewById(android.R.id.text1);
        tempMag = Double.parseDouble(x.getMagnitude());

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
