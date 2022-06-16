package com.example.firebaselogin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<offers> list;
private OnOfferListener monOfferListener;
    public MyAdapter(Context context, ArrayList<offers> list, OnOfferListener onOfferListener) {
        this.context = context;
        this.list = list;
        this.monOfferListener= onOfferListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new MyViewHolder(v, monOfferListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
    offers offers = list.get(position);
    holder.price.setText(offers.getPrice());
    holder.title.setText(offers.getTitle());
    holder.city.setText(offers.getCity());
    Glide.with(context).load(offers.getImage()).into(holder.imageView);
    holder.date.setText(offers.getDate());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
   TextView price;
   TextView title;
   TextView city;
   TextView date;

   ImageView imageView;
   OnOfferListener onOfferListener;
        public MyViewHolder(@NonNull View itemView, OnOfferListener onOfferListener) {
            super(itemView);
            price = itemView.findViewById(R.id.price);
            title = itemView.findViewById(R.id.title);
            city = itemView.findViewById(R.id.city);
            date = itemView.findViewById(R.id.date);
            imageView = itemView.findViewById(R.id.image);
            this.onOfferListener= onOfferListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onOfferListener.onOfferClick(getAdapterPosition());
        }
    }
    public interface OnOfferListener{
        void onOfferClick(int position);
    }
}
