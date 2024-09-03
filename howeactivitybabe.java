package com.example.registerloginapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class howeactivitybabe extends AppCompatActivity {
    Button editbtn, sendbtn, nunua;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);

        editbtn = findViewById(R.id.editbtn);
        sendbtn = findViewById(R.id.sendbtn);
        nunua = findViewById(R.id.downloadbtn);

        username = getIntent().getStringExtra("username");

        nunua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(howeactivitybabe.this, DisplayImagesActivity.class);
                startActivity(intent);
                intent.putExtra("username", username); // Pass the username to the UploadActivity
                startActivity(intent);
            }
        });

        editbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(howeactivitybabe.this, edite_activity.class);
                startActivity(intent);

            }
        });

        sendbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(howeactivitybabe.this, send_fileActivity.class);
                startActivity(intent);
                intent.putExtra("username", username); // Pass the username to the UploadActivity
                startActivity(intent);
            }
        });

    }
}
