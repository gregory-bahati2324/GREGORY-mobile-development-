package com.example.registerloginapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    EditText usernameLogin, passwordLogin;
    Button loginBtn;
    TextView registerBtn;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        usernameLogin = findViewById(R.id.userinput);
        passwordLogin = findViewById(R.id.passwordinput);
        loginBtn = findViewById(R.id.Loginbtn);
        registerBtn = findViewById(R.id.registerbtntext);

        // Initialize Firebase Database reference
        databaseReference = FirebaseDatabase.getInstance().getReference("New profile");

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameLogin.getText().toString();
                String password = passwordLogin.getText().toString();

                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter both username and password", Toast.LENGTH_SHORT).show();
                } else {
                    // Perform manual authentication using username and password
                    authenticateUser(username, password);
                }
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivityregister.class);
                startActivity(intent);
            }
        });
    }

    private void authenticateUser(String username, String password) {
        // Use the username as the key to look up the user's data in the database
        DatabaseReference userRef = databaseReference.child(username);

        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    // Get the stored password
                    String dbPassword = snapshot.child("password").getValue(String.class);

                    // Check if the provided password matches the stored password
                    if (dbPassword != null && dbPassword.equals(password)) {
                        Toast.makeText(MainActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                        // Navigate to the next activity
                        Intent intent = new Intent(MainActivity.this, home_page_activity_main.class);
                        intent.putExtra("username", username); // Pass the username to the next activity
                        startActivity(intent);
                        finish(); // Close the login activity
                    } else {
                        Toast.makeText(MainActivity.this, "Incorrect password", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "User does not exist", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this, "Failed to login: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
