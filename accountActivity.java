package com.example.registerloginapp;



import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class accountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_account);
        ImageView icon= findViewById(R.id.icon);
        icon.setOnClickListener(v -> {
            Intent intent = new Intent(accountActivity.this,           MainActivityfaq.class);
            startActivity(intent);
        });
    }
}
