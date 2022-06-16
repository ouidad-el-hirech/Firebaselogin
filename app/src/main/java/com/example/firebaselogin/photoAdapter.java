package com.example.firebaselogin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class photoAdapter extends PagerAdapter {
    private Context mcontext;
    private ArrayList<String> photoList;

    public photoAdapter(Context mcontext, ArrayList<String> photoList) {
        this.mcontext = mcontext;
        this.photoList = photoList;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.gallery_item, container,false);
        ImageView imgPhoto= view.findViewById(R.id.image_gallery);
        String photo = photoList.get(position);
        if(photo != null){
            Glide.with(mcontext).load(photo).into(imgPhoto);
        }
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        if(photoList != null){
            return photoList.size();
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}
