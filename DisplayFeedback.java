package com.example.registerloginapp;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DisplayFeedback extends AppCompatActivity {

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

    }
}
