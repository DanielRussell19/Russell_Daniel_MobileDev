package com.example.russell_daniel_courseworkone.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.russell_daniel_courseworkone.Models.Reading;
import com.example.russell_daniel_courseworkone.Models.XmlParser;
import com.example.russell_daniel_courseworkone.R;
import java.util.List;

public class ReadingListing extends Fragment implements View.OnClickListener {

    private TextView Title;
    private ListView ReadListing;
    private ScrollView ReadScroll;
    private LinearLayout test;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    )
    {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_readinglisting, container, false);

        test = (LinearLayout) v.findViewById(R.id.test);

        List<Reading> result;
        XmlParser xp = new XmlParser();
        result = xp.getXML();

        for(Reading x: result){
            TextView t = new TextView(v.getContext());
            t.setText(x.getPubdate());
            test.addView(t);
        }

        return v;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onClick(View v) {

    }

}
