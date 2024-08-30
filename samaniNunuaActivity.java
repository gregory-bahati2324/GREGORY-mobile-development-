package com.example.registerloginapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class samaniNunuaActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference database;
    ImageAdapter imageAdapter;


    DrawerLayout drawerLayout;
    ImageButton buttondrawertoggle;
    NavigationView navigationView;

    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nunua_layout);

        drawerLayout = findViewById(R.id.drawerLayout);
        buttondrawertoggle = findViewById(R.id.buttondrawertoggle);
        navigationView = findViewById(R.id.navigationView);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<user> options = new  FirebaseRecyclerOptions.Builder<user>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("samani"), user.class)
                .build();
        imageAdapter = new ImageAdapter(options);
        recyclerView.setAdapter(imageAdapter);




        username = getIntent().getStringExtra("username");

        String text6 = "Help";
        String text7 = "Feedback";
        String text8 = "FAQ";

        buttondrawertoggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.open();
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int itenId = item.getItemId();

                if(itenId == R.id.Home){
                    Intent intent = new Intent(samaniNunuaActivity.this, home_page_activity_main.class);
                    startActivity(intent);
                    intent.putExtra("username", username);
                    drawerLayout.close();
                } else if (itenId == R.id.Chart) {
                    Intent intent = new Intent(samaniNunuaActivity.this, edite_activity.class);
                    startActivity(intent);
                    drawerLayout.close();
                }else if (itenId == R.id.Uza) {
                    Intent intent = new Intent(samaniNunuaActivity.this,uzaMainActivity.class);
                    startActivity(intent);
                    intent.putExtra("username", username); // Pass the username to the UploadActivity
                    startActivity(intent);
                    drawerLayout.close();
                }else if (itenId == R.id.Nunua) {
                    Intent intent = new Intent(samaniNunuaActivity.this,nunuaMainActivity.class);
                    startActivity(intent);
                    intent.putExtra("username", username); // Pass the username to the UploadActivity
                    startActivity(intent);
                    drawerLayout.close();
                }else if (itenId == R.id.Help) {
                    Intent intent = new Intent(samaniNunuaActivity.this,MainActivityfaq.class);
                    startActivity(intent);
                    intent.putExtra("username", username); // Pass the username to the UploadActivity
                    startActivity(intent);
                    drawerLayout.close();
                }else if (itenId == R.id.Feedback) {
                    Toast.makeText(samaniNunuaActivity.this,text7, Toast.LENGTH_SHORT).show();
                }else if (itenId == R.id.FAQ) {
                    Toast.makeText(samaniNunuaActivity.this, text8, Toast.LENGTH_SHORT).show();
                }

                return false;
            }
        });


    }

    @Override
    protected void onStart() {
        imageAdapter.startListening();
        super.onStart();
    }

    @Override
    protected void onStop() {
        imageAdapter.startListening();
        super.onStop();
    }
}