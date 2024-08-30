package com.example.registerloginapp;



import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class downloadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.download_main);

        ImageView menu= findViewById(R.id.menu);
        menu.setOnClickListener(v -> {
            Intent intent = new Intent(downloadActivity.this,           MainActivityfaq.class);
            startActivity(intent);
        });
    }
}