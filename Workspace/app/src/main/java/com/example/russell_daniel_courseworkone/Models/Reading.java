package com.example.russell_daniel_courseworkone.Models;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

//Daniel Russell S1707149
//Class used to define Reading objects
//Two functions toward the bottom extract additional variables for all reading objects
public class Reading implements Serializable {
    private String title, description, link, pubdate, category,lat,lon, depth, magnitude;
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDepth() { return depth; }

    public void setDepth(String depth) {this.depth = depth;}

    public String getMagnitude() { return magnitude; }

    public void setMagnitude(String magnitude) {this.magnitude = magnitude;}

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    //extracts depth from description
    public void extractDepth(){
        String t = getDescription().substring(getDescription().indexOf("Depth:") + 7, getDescription().indexOf("km") - 1);
        setDepth(t);
    }

    //extracts magnitude from description
    public void extractMagnitude(){
        String t = getDescription().substring(getDescription().indexOf("Magnitude:") + 10, getDescription().length());
        setMagnitude(t);
    }
}