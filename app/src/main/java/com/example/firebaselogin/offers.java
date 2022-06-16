package com.example.firebaselogin;

import android.icu.text.Edits;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class offers implements Parcelable{
       String image;
       String price;
       String title;
       String city;
       String date;
       String location;
       String description;
       String lat;
       String lng;
    HashMap<String,String> images;
    ArrayList<String> finalImages = new ArrayList<>();
public offers(){

}


    protected offers(Parcel in) {
        image = in.readString();
        price = in.readString();
        title = in.readString();
        city = in.readString();
        date = in.readString();
        location = in.readString();
        description = in.readString();
        lat = in.readString();
        lng = in.readString();
        finalImages = in.createStringArrayList();
    }

    public static final Creator<offers> CREATOR = new Creator<offers>() {
        @Override
        public offers createFromParcel(Parcel in) {
            return new offers(in);
        }

        @Override
        public offers[] newArray(int size) {
            return new offers[size];
        }
    };

    public String getPrice() {
        return price;
    }
    public String getTitle() {
        return title;
    }
    public String getCity() {
        return city;
    }
    public String getImage() {
        return image;
    }
    public String getDate() {
        return date;
    }
    public String getDescription() {
        return description;
    }
    public String getLocation() {return location;}
    public String getLat() {
        return lat;
    }
    public String getLng() {return lng;}
    public ArrayList getfinalImages() {return finalImages;}
    public void setfinalImages(String s) {finalImages.add(s);}
    public HashMap<String,String> getImages() {return images;}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(image);
        dest.writeString(price);
        dest.writeString(title);
        dest.writeString(city);
        dest.writeString(date);
        dest.writeString(location);
        dest.writeString(description);
        dest.writeString(lat);
        dest.writeString(lng);
        dest.writeStringList(finalImages);
    }
    //  public void setImages(HashMap<String,String> im) { images = im;}



}
