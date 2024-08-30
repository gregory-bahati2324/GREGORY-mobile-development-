package com.example.registerloginapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class nunuaMainActivity extends AppCompatActivity {


    private static final int REQUEST_PERMISSIONS = 3;


    DrawerLayout drawerLayout;
    ImageButton buttondrawertoggle;
    NavigationView navigationView;

    Button mavazi, samani, shule, ofisi, maji, vipodozi, nyumba, ndani, madawa, shamba;



    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.nunua_main_layout);

        mavazi = findViewById(R.id.mavazi);
        samani = findViewById(R.id.samani);
        shule = findViewById(R.id.shule);
        ofisi = findViewById(R.id.ofisi);
        maji = findViewById(R.id.maji);
        vipodozi = findViewById(R.id.vipodozi);
        nyumba = findViewById(R.id.nyumba);
        ndani = findViewById(R.id.ndani);
        madawa = findViewById(R.id.madawa);
        shamba = findViewById(R.id.shamba);



        drawerLayout = findViewById(R.id.drawerLayout);
        buttondrawertoggle = findViewById(R.id.buttondrawertoggle);
        navigationView = findViewById(R.id.navigationView);

        username = getIntent().getStringExtra("username");

        String text6 = "Help";
        String text7 = "Feedback";
        String text8 = "FAQ";

        mavazi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(nunuaMainActivity.this, mavaziNunuaActivity.class);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        samani.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(nunuaMainActivity.this, samaniNunuaActivity.class);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        shule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(nunuaMainActivity.this, shuleNunuaActivity.class);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        ofisi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(nunuaMainActivity.this, ofisiNunuaActivity.class);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        maji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(nunuaMainActivity.this, majiNunuaActivity.class);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        vipodozi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(nunuaMainActivity.this, vipodoziNunuaActivity.class);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        nyumba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(nunuaMainActivity.this, nyumbaNunuaActivity.class);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        ndani.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(nunuaMainActivity.this, ndaniNunuaActivity.class);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        madawa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(nunuaMainActivity.this, madawaNunuaActivity.class);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        shamba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(nunuaMainActivity.this, shambaNunuaActivity.class);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

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
                    Intent intent = new Intent(nunuaMainActivity.this, home_page_activity_main.class);
                    intent.putExtra("username", username);
                    startActivity(intent);
                    drawerLayout.close();
                } else if (itenId == R.id.Chart) {
                    username = getIntent().getStringExtra("username");
                    Intent intent = new Intent(nunuaMainActivity.this, edite_activity.class);
                    startActivity(intent);
                    drawerLayout.close();
                }else if (itenId == R.id.Uza) {
                    username = getIntent().getStringExtra("username");
                    Intent intent = new Intent(nunuaMainActivity.this,uzaMainActivity.class);
                    startActivity(intent);
                    intent.putExtra("username", username); // Pass the username to the UploadActivity
                    startActivity(intent);
                    drawerLayout.close();
                }else if (itenId == R.id.Nunua) {
                    username = getIntent().getStringExtra("username");
                    Intent intent = new Intent(nunuaMainActivity.this,nunuaMainActivity.class);
                    startActivity(intent);
                    intent.putExtra("username", username); // Pass the username to the UploadActivity
                    startActivity(intent);
                    drawerLayout.close();
                }else if (itenId == R.id.Help) {
                    username = getIntent().getStringExtra("username");
                    Intent intent = new Intent(nunuaMainActivity.this,MainActivityfaq.class);
                    startActivity(intent);
                    intent.putExtra("username", username); // Pass the username to the UploadActivity
                    startActivity(intent);
                    drawerLayout.close();
                }else if (itenId == R.id.Feedback) {
                    Toast.makeText(nunuaMainActivity.this,text7, Toast.LENGTH_SHORT).show();
                }else if (itenId == R.id.FAQ) {
                    Toast.makeText(nunuaMainActivity.this, text8, Toast.LENGTH_SHORT).show();
                }

                return false;
            }
        });




    }




}