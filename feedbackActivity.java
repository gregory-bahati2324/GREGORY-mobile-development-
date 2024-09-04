package com.example.registerloginapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class feedbackActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ImageButton buttondrawertoggle;
    NavigationView navigationView;

    Button submitfeedback;
    EditText feedback;

    RecyclerView recyclerView;
    DatabaseReference database;
    ArrayList<userfeedback> list;

    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.feedback_main_layout);

        recyclerView = findViewById(R.id.recycler_view);
        database = FirebaseDatabase.getInstance().getReference("feedback");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        FeedbackAdapter feedbackAdapter = new FeedbackAdapter(this, list);
        recyclerView.setAdapter(feedbackAdapter);

        drawerLayout = findViewById(R.id.drawerLayout);
        buttondrawertoggle = findViewById(R.id.buttondrawertoggle);
        navigationView = findViewById(R.id.navigationView);

        submitfeedback = findViewById(R.id.submitfeedack);
        feedback = findViewById(R.id.feedback);

        username = getIntent().getStringExtra("username");

        buttondrawertoggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.open();
            }
        });

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                    userfeedback userfeedback = dataSnapshot.getValue(com.example.registerloginapp.userfeedback.class);
                    list.add(userfeedback);


                }
                feedbackAdapter.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int itenId = item.getItemId();

                if(itenId == R.id.Home){
                    username = getIntent().getStringExtra("username");
                    Intent intent = new Intent(feedbackActivity.this, home_page_activity_main.class);
                    intent.putExtra("username", username);
                    startActivity(intent);
                    drawerLayout.close();
                } else if (itenId == R.id.Chart) {
                    username = getIntent().getStringExtra("username");
                    Intent intent = new Intent(feedbackActivity.this, edite_activity.class);
                    startActivity(intent);
                    drawerLayout.close();
                }else if (itenId == R.id.Uza) {
                    username = getIntent().getStringExtra("username");
                    Intent intent = new Intent(feedbackActivity.this,uzaMainActivity.class);
                    startActivity(intent);
                    intent.putExtra("username", username); // Pass the username to the UploadActivity
                    startActivity(intent);
                    drawerLayout.close();
                }else if (itenId == R.id.Nunua) {
                    username = getIntent().getStringExtra("username");
                    Intent intent = new Intent(feedbackActivity.this,nunuaMainActivity.class);
                    startActivity(intent);
                    intent.putExtra("username", username); // Pass the username to the UploadActivity
                    startActivity(intent);
                    drawerLayout.close();
                }else if (itenId == R.id.Help) {
                    username = getIntent().getStringExtra("username");
                    Intent intent = new Intent(feedbackActivity.this,MainActivityfaq.class);
                    startActivity(intent);
                    intent.putExtra("username", username); // Pass the username to the UploadActivity
                    startActivity(intent);
                    drawerLayout.close();
                }else if (itenId == R.id.Feedback) {
                    username = getIntent().getStringExtra("username");
                    Intent intent = new Intent(feedbackActivity.this,feedbackActivity.class);
                    startActivity(intent);
                    intent.putExtra("username", username); // Pass the username to the UploadActivity
                    startActivity(intent);
                    drawerLayout.close();
                }else if (itenId == R.id.FAQ) {
                    username = getIntent().getStringExtra("username");
                    Intent intent = new Intent(feedbackActivity.this,frequently_questions_activity.class);
                    startActivity(intent);
                    intent.putExtra("username", username); // Pass the username to the UploadActivity
                    startActivity(intent);
                    drawerLayout.close();
                }

                return false;
            }
        });

        submitfeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String sendfeedback = feedback.getText().toString();


                if (sendfeedback.isEmpty()){
                    Toast.makeText(feedbackActivity.this, "Tafadhali andika Mrejesho wako kabla ya kutuma", Toast.LENGTH_SHORT).show();
                }else {

                    data();

                }


            }
        });


    }

    public void data() {
        String sendfeedback = feedback.getText().toString();

        if (!sendfeedback.isEmpty()) {
            // Using a Map to store the username directly
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("Mrejesho", sendfeedback);
            dataMap.put("username", username);

            FirebaseDatabase.getInstance().getReference("feedback").child(sendfeedback)
                    .setValue(dataMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(feedbackActivity.this, "Asante kwa Mrejesho wako", Toast.LENGTH_SHORT).show();
                                feedback.setText("");
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(feedbackActivity.this, "Failed to save data: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            Toast.makeText(feedbackActivity.this, "Fill all spaces", Toast.LENGTH_SHORT).show();
        }
    }
}
