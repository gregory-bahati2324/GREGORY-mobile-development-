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

import com.google.android.material.navigation.NavigationView;

public class frequently_questions_activity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ImageButton buttondrawertoggle;
    NavigationView navigationView;

    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.faq_main_layout);

        drawerLayout = findViewById(R.id.drawerLayout);
        buttondrawertoggle = findViewById(R.id.buttondrawertoggle);
        navigationView = findViewById(R.id.navigationView);

        username = getIntent().getStringExtra("username");

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
                    username = getIntent().getStringExtra("username");
                    Intent intent = new Intent(frequently_questions_activity.this, home_page_activity_main.class);
                    intent.putExtra("username", username);
                    startActivity(intent);
                    drawerLayout.close();
                } else if (itenId == R.id.Chart) {
                    username = getIntent().getStringExtra("username");
                    Intent intent = new Intent(frequently_questions_activity.this, edite_activity.class);
                    startActivity(intent);
                    drawerLayout.close();
                }else if (itenId == R.id.Uza) {
                    username = getIntent().getStringExtra("username");
                    Intent intent = new Intent(frequently_questions_activity.this,uzaMainActivity.class);
                    startActivity(intent);
                    intent.putExtra("username", username); // Pass the username to the UploadActivity
                    startActivity(intent);
                    drawerLayout.close();
                }else if (itenId == R.id.Nunua) {
                    username = getIntent().getStringExtra("username");
                    Intent intent = new Intent(frequently_questions_activity.this,nunuaMainActivity.class);
                    startActivity(intent);
                    intent.putExtra("username", username); // Pass the username to the UploadActivity
                    startActivity(intent);
                    drawerLayout.close();
                }else if (itenId == R.id.Help) {
                    username = getIntent().getStringExtra("username");
                    Intent intent = new Intent(frequently_questions_activity.this,MainActivityfaq.class);
                    startActivity(intent);
                    intent.putExtra("username", username); // Pass the username to the UploadActivity
                    startActivity(intent);
                    drawerLayout.close();
                }else if (itenId == R.id.Feedback) {
                    username = getIntent().getStringExtra("username");
                    Intent intent = new Intent(frequently_questions_activity.this,feedbackActivity.class);
                    startActivity(intent);
                    intent.putExtra("username", username); // Pass the username to the UploadActivity
                    startActivity(intent);
                    drawerLayout.close();
                }else if (itenId == R.id.FAQ) {
                    username = getIntent().getStringExtra("username");
                    Intent intent = new Intent(frequently_questions_activity.this,frequently_questions_activity.class);
                    startActivity(intent);
                    intent.putExtra("username", username); // Pass the username to the UploadActivity
                    startActivity(intent);
                    drawerLayout.close();
                }

                return false;
            }
        });

    }
}
