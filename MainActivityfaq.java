package com.example.registerloginapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class MainActivityfaq extends AppCompatActivity {
    DrawerLayout drawerLayout;
    ImageButton buttondrawertoggle;
    NavigationView navigationView;

    String username;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.help_main_page);

        String text7 = "Feedback";
        String text8 = "FAQ";

        drawerLayout = findViewById(R.id.drawerLayout);
        buttondrawertoggle = findViewById(R.id.buttondrawertoggle);
        navigationView = findViewById(R.id.navigationView);

        username = getIntent().getStringExtra("username");


        ImageView download= findViewById(R.id.download);
        ImageView account= findViewById(R.id.account);
        ImageView browser= findViewById(R.id.browser);
        ImageView post= findViewById(R.id.post);
        ImageView update= findViewById(R.id.update);
        ImageView chat= findViewById(R.id.chat);
        ImageView change= findViewById(R.id.change);

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
                    username = getIntent().getStringExtra("username");
                    Intent intent = new Intent(MainActivityfaq.this, home_page_activity_main.class);
                    intent.putExtra("username", username);
                    startActivity(intent);
                    drawerLayout.close();
                } else if (itenId == R.id.Chart) {
                    username = getIntent().getStringExtra("username");
                    Intent intent = new Intent(MainActivityfaq.this, edite_activity.class);
                    startActivity(intent);
                    drawerLayout.close();
                }else if (itenId == R.id.Uza) {
                    username = getIntent().getStringExtra("username");
                    Intent intent = new Intent(MainActivityfaq.this,uzaMainActivity.class);
                    startActivity(intent);
                    intent.putExtra("username", username); // Pass the username to the UploadActivity
                    startActivity(intent);
                    drawerLayout.close();
                }else if (itenId == R.id.Nunua) {
                    username = getIntent().getStringExtra("username");
                    Intent intent = new Intent(MainActivityfaq.this,nunuaMainActivity.class);
                    startActivity(intent);
                    intent.putExtra("username", username); // Pass the username to the UploadActivity
                    startActivity(intent);
                    drawerLayout.close();
                }else if (itenId == R.id.Help) {
                    username = getIntent().getStringExtra("username");
                    Intent intent = new Intent(MainActivityfaq.this,MainActivityfaq.class);
                    startActivity(intent);
                    intent.putExtra("username", username); // Pass the username to the UploadActivity
                    startActivity(intent);
                    drawerLayout.close();
                }else if (itenId == R.id.Feedback) {
                    Toast.makeText(MainActivityfaq.this,text7, Toast.LENGTH_SHORT).show();
                }else if (itenId == R.id.FAQ) {
                    Toast.makeText(MainActivityfaq.this, text8, Toast.LENGTH_SHORT).show();
                }

                return false;
            }
        });


        download.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivityfaq.this, downloadActivity.class);
            startActivity(intent);
        });
        account.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivityfaq.this, accountActivity.class);
            startActivity(intent);
        });
        browser.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivityfaq.this, browserActivity.class);
            startActivity(intent);
        });
        post.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivityfaq.this, postActivity.class);
            startActivity(intent);
        });
        update.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivityfaq.this, updateActivity
                    .class);
            startActivity(intent);
        });
        chat.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivityfaq.this, chatActivity
                    .class);
            startActivity(intent);
        });
        change.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivityfaq.this, changeActivity
                    .class);
            startActivity(intent);
        });
    }
}