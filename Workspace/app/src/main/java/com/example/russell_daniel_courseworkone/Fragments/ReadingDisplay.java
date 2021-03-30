package com.example.russell_daniel_courseworkone.Fragments;

import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.russell_daniel_courseworkone.Models.Reading;
import com.example.russell_daniel_courseworkone.R;

//Daniel Russell S1707149
//Class used to define fragment reading display
public class ReadingDisplay extends Fragment {

    //variables
    private Reading reading;
    private TextView txtTitle;
    private TextView txtDescription;
    private TextView txtPubDate;
    private TextView txtMag;
    private TextView txtDepth;
    private TextView txtLat;
    private TextView txtLon;
    private TextView txtCategory;
    private TextView txtUrl;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    )
    {
        View v = inflater.inflate(R.layout.fragment_readingdisplay, container, false);
        reading = (Reading) getActivity().getIntent().getSerializableExtra("Reading");

        txtTitle = (TextView) v.findViewById(R.id.txtTitle);
        txtDescription = (TextView) v.findViewById(R.id.txtDescription);
        txtPubDate = (TextView) v.findViewById(R.id.txtPubDate);
        txtMag = (TextView) v.findViewById(R.id.txtMag);
        txtDepth = (TextView) v.findViewById(R.id.txtDepth);
        txtLat = (TextView) v.findViewById(R.id.txtLat);
        txtLon = (TextView) v.findViewById(R.id.txtLon);
        txtUrl = (TextView) v.findViewById(R.id.txtUrl);
        txtCategory = (TextView) v.findViewById(R.id.txtCategory);

        //if reading fetch fails, toast is activated like map activity, etc
        try {
            txtTitle.setText(reading.getTitle());
            txtDescription.setText(reading.getDescription());
            txtPubDate.setText(reading.getPubdate());
            txtMag.setText("Magnitude: " + reading.getMagnitude());
            txtDepth.setText("Depth: " + reading.getDepth());
            txtLat.setText("Lat: " + reading.getLat());
            txtLon.setText("Long: " + reading.getLon());
            txtCategory.setText("Category: " + reading.getCategory());

            //txtUrl.setText(Html.fromHtml("<a href=" + reading.getLink() + ">Source</a> "));
            //txtUrl.setMovementMethod(LinkMovementMethod.getInstance());

            txtUrl.setText("Source: " + reading.getLink());
        }
        catch(Exception e){
            Toast.makeText(this.getActivity(),"Failed to fetch", Toast.LENGTH_SHORT).show();
        }

        return v;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
    }

}
