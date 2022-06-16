package com.example.firebaselogin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.denzcoskun.imageslider.ImageSlider;

import me.relex.circleindicator.CircleIndicator;

public class Offerdescription extends AppCompatActivity implements View.OnClickListener {
TextView price;
TextView description;
TextView title;
TextView adresse;
    TextView map;
ViewPager viewPager;
CircleIndicator circleIndicator;
photoAdapter photoadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offerdescription);
        price= findViewById(R.id.price);
        title= findViewById(R.id.title);
        adresse= findViewById(R.id.adresse);
        map= findViewById(R.id.map);
        description= findViewById(R.id.description);
       viewPager= findViewById(R.id.viewpager);
       circleIndicator=findViewById(R.id.circle_indecator);
        if(getIntent().hasExtra("selected")){
            offers offer = getIntent().getParcelableExtra("selected");
            price.setText(offer.getPrice());
            description.setText(offer.getDescription());
            title.setText(offer.getTitle());
            adresse.setText(offer.getLocation());
            photoadapter = new photoAdapter(this,offer.getfinalImages());
            viewPager.setAdapter(photoadapter);
            circleIndicator.setViewPager(viewPager);
            photoadapter.registerDataSetObserver(circleIndicator.getDataSetObserver());
            //Toast.makeText(this, "ha homa"+ offer.getfinalImages().getClass(), Toast.LENGTH_LONG).show();
            //for(int i = 0 ; i < offer.getfinalImages().size(); i++)
               // Glide.with(Offerdescription.this).load(offer.getfinalImages().get(i)).into(image_gallery);





           map.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent= new Intent(Offerdescription.this,MapsActivity.class);
                   intent.putExtra("lat",offer.getLat());
                   intent.putExtra("lng",offer.getLng());
                   intent.putExtra("title",offer.getTitle());
                   startActivity(intent);

                }
            });
        }

    }

    @Override
    public void onClick(View v) {

    }
}