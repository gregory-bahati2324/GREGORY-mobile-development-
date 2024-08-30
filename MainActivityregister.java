package com.example.registerloginapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class MainActivityregister extends AppCompatActivity {

    EditText userregister, emailregister, phoneregister, password1, confirmpassword;


    Button backbtn, registerbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register2);

        userregister = findViewById(R.id.userregister);
        emailregister = findViewById(R.id.emailregister);
        phoneregister = findViewById(R.id.phoneregister);
        password1 = findViewById(R.id.password1register);
        confirmpassword = findViewById(R.id.passwordconfirmregister);
        registerbtn = findViewById(R.id.registerbtn);

        backbtn = findViewById(R.id.backbtn);

        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = userregister.getText().toString();
                String email = emailregister.getText().toString();
                String phonenumber = phoneregister.getText().toString();
                String password = password1.getText().toString();
                String password2 = confirmpassword.getText().toString();

                if (username.isEmpty() || email.isEmpty() || phonenumber.isEmpty() || password.isEmpty() || password2.isEmpty()){
                    Toast.makeText(MainActivityregister.this, "Tafadhali jaza taarifa zote kuendelea", Toast.LENGTH_SHORT).show();
                }else {
                    if(password.equals(password2)){
                        checkIfUsernameExists(username, email, phonenumber, password);

                    }else {
                        Toast.makeText(MainActivityregister.this, "Password do not match", Toast.LENGTH_SHORT).show();
                    }

                }




            }
        });

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityregister.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private void checkIfUsernameExists(String username, String email, String phonenumber, String password) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("New profile").child(username);

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    // Username already exists
                    Toast.makeText(MainActivityregister.this, "Username is already taken, please choose another one", Toast.LENGTH_SHORT).show();
                } else {
                    // Username is available, proceed with registration
                    data();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivityregister.this, "Database error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }



    public void data() {
        String username = userregister.getText().toString();
        String email = emailregister.getText().toString();
        String phonenumber = phoneregister.getText().toString();
        String password = password1.getText().toString();
        String password2 = confirmpassword.getText().toString();

        if (!username.isEmpty() || !email.isEmpty() || !phonenumber.isEmpty() || password.isEmpty()) {
            // Using a Map to store the username directly
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("username", username);
            dataMap.put("email", email);
            dataMap.put("phone", phonenumber);
            dataMap.put("password", password);

            FirebaseDatabase.getInstance().getReference("New profile").child(username)
                    .setValue(dataMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(MainActivityregister.this, "Data sent successfully", Toast.LENGTH_SHORT).show();
                                userregister.setText("");
                                emailregister.setText("");
                                phoneregister.setText("");
                                password1.setText("");
                                confirmpassword.setText("");
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(MainActivityregister.this, "Failed to save data: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            Toast.makeText(MainActivityregister.this, "Fill all spaces", Toast.LENGTH_SHORT).show();
        }
    }
}
