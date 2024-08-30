package com.example.registerloginapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class edite_activity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ImageButton buttondrawertoggle;
    NavigationView navigationView;

    String username;


    EditText useredit, passwordedit, new_user, new_password, confirmnew_password, new_email, new_phone;

    Button editbtn, backeditbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_main_page);

        String text7 = "Feedback";
        String text8 = "FAQ";

        drawerLayout = findViewById(R.id.drawerLayout);
        buttondrawertoggle = findViewById(R.id.buttondrawertoggle);
        navigationView = findViewById(R.id.navigationView);

        username = getIntent().getStringExtra("username");

        useredit = findViewById(R.id.useredit);
        passwordedit = findViewById(R.id.password1edit);
        new_user = findViewById(R.id.New_user);
        new_password = findViewById(R.id.newpassword);
        confirmnew_password = findViewById(R.id.passwordconfirnew);
        new_email = findViewById(R.id.emailredit);
        new_phone = findViewById(R.id.phoneredit);

        editbtn = findViewById(R.id.editbtn);

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
                    Intent intent = new Intent(edite_activity.this, home_page_activity_main.class);
                    intent.putExtra("username", username);
                    startActivity(intent);
                    drawerLayout.close();
                } else if (itenId == R.id.Chart) {
                    username = getIntent().getStringExtra("username");
                    Intent intent = new Intent(edite_activity.this, edite_activity.class);
                    startActivity(intent);
                    drawerLayout.close();
                }else if (itenId == R.id.Uza) {
                    username = getIntent().getStringExtra("username");
                    Intent intent = new Intent(edite_activity.this,uzaMainActivity.class);
                    startActivity(intent);
                    intent.putExtra("username", username); // Pass the username to the UploadActivity
                    startActivity(intent);
                    drawerLayout.close();
                }else if (itenId == R.id.Nunua) {
                    username = getIntent().getStringExtra("username");
                    Intent intent = new Intent(edite_activity.this,nunuaMainActivity.class);
                    startActivity(intent);
                    intent.putExtra("username", username); // Pass the username to the UploadActivity
                    startActivity(intent);
                    drawerLayout.close();
                }else if (itenId == R.id.Help) {
                    username = getIntent().getStringExtra("username");
                    Intent intent = new Intent(edite_activity.this,MainActivityfaq.class);
                    startActivity(intent);
                    intent.putExtra("username", username); // Pass the username to the UploadActivity
                    startActivity(intent);
                    drawerLayout.close();
                }else if (itenId == R.id.Feedback) {
                    Toast.makeText(edite_activity.this,text7, Toast.LENGTH_SHORT).show();
                }else if (itenId == R.id.FAQ) {
                    Toast.makeText(edite_activity.this, text8, Toast.LENGTH_SHORT).show();
                }

                return false;
            }
        });


        editbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String previoususername = useredit.getText().toString();
                String previouspassword = passwordedit.getText().toString();
                String newusername = new_user.getText().toString();
                String email = new_email.getText().toString();
                String phonenumber = new_phone.getText().toString();
                String password = new_password.getText().toString();
                String password2 = confirmnew_password.getText().toString();

                if (previoususername.isEmpty() ||previouspassword.isEmpty() ||newusername.isEmpty() || email.isEmpty() || phonenumber.isEmpty() || password.isEmpty() || password2.isEmpty()){
                    Toast.makeText(edite_activity.this, "Tafadhali jaza taarifa zote kuendelea", Toast.LENGTH_SHORT).show();
                }else {
                    if(password.equals(password2)){
                        login(previoususername, previouspassword);

                    }else {
                        Toast.makeText(edite_activity.this, "New Password do not match", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });

    }
    public void login(String previoususername, String previouspassword) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("New profile").child(previoususername);

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String previoususername = useredit.getText().toString();
                String previouspassword = passwordedit.getText().toString();
                String newusername = new_user.getText().toString();
                String email = new_email.getText().toString();
                String phonenumber = new_phone.getText().toString();
                String password = new_password.getText().toString();
                String password2 = confirmnew_password.getText().toString();

                if (snapshot.exists()) {
                    String dbPassword = snapshot.child("password").getValue(String.class);

                    if (dbPassword != null && dbPassword.equals(previouspassword)) {

                        checkIfUsernameExists(newusername, password);
                    } else {
                        Toast.makeText(edite_activity.this, "Incorrect password", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(edite_activity.this, "User does not exist", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(edite_activity.this, "Failed to login: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void checkIfUsernameExists(String username, String password) {
        String previoususername = useredit.getText().toString();
        String previouspassword = passwordedit.getText().toString();
        String newusername = new_user.getText().toString();
        String email = new_email.getText().toString();
        String phonenumber = new_phone.getText().toString();
        String newpassword = new_password.getText().toString();
        String password2 = confirmnew_password.getText().toString();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("New profile").child(username);

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    // Username already exists
                    Toast.makeText(edite_activity.this, "Username is already taken, please choose another one", Toast.LENGTH_SHORT).show();
                } else {
                    // Username is available, proceed with registration
                    updataProfile(previoususername, newusername, password, email, phonenumber);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(edite_activity.this, "Database error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void updataProfile(String previoususername, String newusername, String newpassword, String newemail, String newphone){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("New profile");
        //copy data from the previous username
        reference.child(previoususername).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    //copy data to the new username
                    reference.child(newusername).setValue(snapshot.getValue()).addOnCompleteListener(task ->{
                        if(task.isSuccessful()){
                            //update profile
                            Map<String, Object> userUpdates = new HashMap<>();
                            userUpdates.put("username", newusername);
                            userUpdates.put("email", newemail);
                            userUpdates.put("phone", newphone);
                            userUpdates.put("password", newpassword);
                            reference.child(newusername).updateChildren(userUpdates);

                            //Now delete the old username
                            reference.child(previoususername).removeValue().addOnCompleteListener(task1 -> {
                                if(task1.isSuccessful()){
                                    Toast.makeText(edite_activity.this, "Profile updated successful", Toast.LENGTH_SHORT).show();
                                    clearFields();
                                }else {
                                    Toast.makeText(edite_activity.this, "Failed to delete old profile", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }else {
                            Toast.makeText(edite_activity.this, "Failed to update profile", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(e -> {
                        Toast.makeText(edite_activity.this, "Error: "+ e.getMessage(), Toast.LENGTH_SHORT).show();
                    });
                }else {
                    Toast.makeText(edite_activity.this, "User data not found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void clearFields(){
        useredit.setText("");
        passwordedit.setText("");
        new_user.setText("");
        new_password.setText("");
        confirmnew_password.setText("");
        new_email.setText("");
        new_phone.setText("");
    }


}
