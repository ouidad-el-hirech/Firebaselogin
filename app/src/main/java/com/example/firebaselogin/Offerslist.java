package com.example.firebaselogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Offerslist extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, MyAdapter.OnOfferListener {
    RecyclerView recyclerView;
    DatabaseReference database;
    MyAdapter myAdapter;
    ArrayList<offers> list;
    private FirebaseAuth mAuth;
    private Button btnLogout;
    String _USERNAME, _NAME, _EMAIL, _PHONENO, _PASSWORD;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    androidx.appcompat.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offerslist);
        navigationView=findViewById(R.id.navigation_view);
        toolbar = findViewById(R.id.toolbar);
        //envoyer les données
        Intent intent = getIntent();
        _USERNAME = intent.getStringExtra("username");
        _NAME = intent.getStringExtra("name");
        _EMAIL = intent.getStringExtra("email");
        _PHONENO = intent.getStringExtra("phoneNo");
        _PASSWORD = intent.getStringExtra("password");
        // setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_lay);
        navigationView = findViewById(R.id.navigation_view);
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle= new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        mAuth = FirebaseAuth.getInstance();
        recyclerView = findViewById(R.id.offers);
        //    database = FirebaseDatabase.getInstance().getReference("offers");//nom de la base
        database = FirebaseDatabase.getInstance().getReference().child("offers");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        HashMap<String,String> images = new HashMap<>();
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    offers offers = dataSnapshot.getValue(offers.class);
                    list.add(offers);
                    for (Map.Entry<String, String> entry : offers.getImages().entrySet()) {
                        offers.setfinalImages(entry.getValue());
                    }
                }
                //  myAdapter.notifyDataSetChanged();
                myAdapter = new MyAdapter(Offerslist.this,list,(MyAdapter.OnOfferListener)Offerslist.this);
                recyclerView.setAdapter(myAdapter);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Offerslist.this, "ha homa"+ error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });


    }
    @Override
    public void onStart() {
        super.onStart();
       /* FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null) {
            startActivity(new Intent(Offerslist.this, LoginActivity.class));
        }*/

    }

    public void logout() {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(Offerslist.this, LoginActivity.class));
    }
    public void toupdate() {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(Offerslist.this, UserProfile.class));

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.logout_btn: logout();
            case R.id.update_profile: toupdate();

        }
        return true;
    }

    @Override
    public void onOfferClick(int position) {
        Intent intent= new Intent(this, Offerdescription.class);
        intent.putExtra("selected",list.get(position));
        startActivity(intent);
    }
}