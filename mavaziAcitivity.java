package com.example.registerloginapp;

import static android.app.ProgressDialog.show;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class mavaziAcitivity extends AppCompatActivity {

    private static final int PICK_FILE_REQUEST = 1;

    Button selectFileBtn, uploadFileBtn;
    ImageView selectedImageView;
    Uri fileUri;

    DrawerLayout drawerLayout;
    ImageButton buttondrawertoggle;
    NavigationView navigationView;

    String username;

    // Reference to Firebase Storage and Database
    FirebaseStorage storage;
    StorageReference storageReference;
    FirebaseDatabase database;
    DatabaseReference userDatabaseReference;

    private ImageView imageView;
    private EditText editTextKitu;
    private EditText editTextNumber;
    private EditText editTextMkoa;
    private TextView textViewKitu;
    private TextView textViewNumber;
    private TextView textViewMkoa;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.uza_layout);

        selectFileBtn = findViewById(R.id.selectFileBtn);
        uploadFileBtn = findViewById(R.id.uploadFileBtn);
        selectedImageView = findViewById(R.id.filePreview);
        drawerLayout = findViewById(R.id.drawerLayout);
        buttondrawertoggle = findViewById(R.id.buttondrawertoggle);
        navigationView = findViewById(R.id.navigationView);

        editTextKitu = findViewById(R.id.nameofthing);
        editTextNumber = findViewById(R.id.phoneno);
        editTextMkoa = findViewById(R.id.mkoa);
        textViewKitu = findViewById(R.id.displaykitu);
        textViewNumber = findViewById(R.id.displayphone);
        textViewMkoa = findViewById(R.id.displaymkoa);

        username = getIntent().getStringExtra("username");

        // Initialize Firebase Storage, Database, and Auth
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        database = FirebaseDatabase.getInstance();

        String text6 = "Help";
        String text7 = "Feedback";
        String text8 = "FAQ";


        buttondrawertoggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.open();
            }
        });




        // Reference to the logged-in user's node in the database


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int itenId = item.getItemId();

                if(itenId == R.id.Home){
                    Intent intent = new Intent(mavaziAcitivity.this, home_page_activity_main.class);
                    startActivity(intent);
                    drawerLayout.close();
                } else if (itenId == R.id.Chart) {
                    Intent intent = new Intent(mavaziAcitivity.this, edite_activity.class);
                    startActivity(intent);
                    drawerLayout.close();
                }else if (itenId == R.id.Uza) {
                    Intent intent = new Intent(mavaziAcitivity.this,uzaMainActivity.class);
                    startActivity(intent);
                    intent.putExtra("username", username); // Pass the username to the UploadActivity
                    startActivity(intent);
                    drawerLayout.close();
                }else if (itenId == R.id.Nunua) {
                    username = getIntent().getStringExtra("username");
                    Intent intent = new Intent(mavaziAcitivity.this,nunuaMainActivity.class);
                    startActivity(intent);
                    intent.putExtra("username", username); // Pass the username to the UploadActivity
                    startActivity(intent);
                    drawerLayout.close();
                }else if (itenId == R.id.Help) {
                    username = getIntent().getStringExtra("username");
                    Intent intent = new Intent(mavaziAcitivity.this,MainActivityfaq.class);
                    startActivity(intent);
                    intent.putExtra("username", username); // Pass the username to the UploadActivity
                    startActivity(intent);
                    drawerLayout.close();
                }else if (itenId == R.id.Feedback) {
                    Toast.makeText(mavaziAcitivity.this,text7, Toast.LENGTH_SHORT).show();
                }else if (itenId == R.id.FAQ) {
                    Toast.makeText(mavaziAcitivity.this, text8, Toast.LENGTH_SHORT).show();
                }

                return false;
            }
        });

        selectFileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectFile();
            }
        });

        uploadFileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String kitu = editTextKitu.getText().toString();
                String number = editTextNumber.getText().toString();
                String mkoawake = editTextMkoa.getText().toString();

                if (kitu.isEmpty() || number.isEmpty() || mkoawake.isEmpty()){
                    Toast.makeText(mavaziAcitivity.this, "Tafadhali jaza taarifa zote kuendelea", Toast.LENGTH_SHORT).show();
                }else {
                    textViewKitu.setText("Kitu: "+ kitu);
                    textViewNumber.setText( "Simu: "+ number);
                    textViewMkoa.setText( "Mkoa: "+ mkoawake);

                    textViewKitu.setVisibility(View.VISIBLE);
                    textViewNumber.setVisibility(View.VISIBLE);
                    textViewMkoa.setVisibility(View.VISIBLE);

                    if (fileUri != null) {
                        uploadFile(fileUri);
                    } else {
                        Toast.makeText(mavaziAcitivity.this, "Please select a file first", Toast.LENGTH_SHORT).show();
                    }

                }


            }
        });
    }

    // Method to open the file picker
    private void selectFile() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*"); // Only allow image files to be selected
        startActivityForResult(intent, PICK_FILE_REQUEST);
    }

    // Handle the result of the file picker
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_FILE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            fileUri = data.getData(); // Get the Uri of the selected file
            try {
                // Show the selected image in the ImageView
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), fileUri);
                selectedImageView.setImageBitmap(bitmap);
                selectedImageView.setVisibility(View.VISIBLE); // Make the ImageView visible
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this, "Failed to load image", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // Method to upload the selected file to Firebase Storage
    private void uploadFile(Uri fileUri) {
        if (fileUri != null) {
            // Create a reference to the file in Firebase Storage
            StorageReference fileRef = storageReference.child("uploads/" + username + "/" + System.currentTimeMillis() + "_" + fileUri.getLastPathSegment());

            fileRef.putFile(fileUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            // Get the download URL of the uploaded file
                            fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    String downloadUrl = uri.toString();
                                    // Save the download URL in the user's database node
                                    saveFileUrlToDatabase(downloadUrl);
                                }
                            });

                            Toast.makeText(mavaziAcitivity.this, "File uploaded successfully", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(mavaziAcitivity.this, "File upload failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

    // Method to save the file URL in the user's database node
    private void saveFileUrlToDatabase(String downloadUrl) {
        String kitu = editTextKitu.getText().toString();
        String mkoa = editTextMkoa.getText().toString();
        String phone = editTextNumber.getText().toString();

        if (!kitu.isEmpty() || !mkoa.isEmpty() || !phone.isEmpty()) {

            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("mavazi");

            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("profileImageUrl",downloadUrl);
            dataMap.put("kitu", kitu);
            dataMap.put("Mkoa", mkoa);
            dataMap.put("phone", phone);
            databaseReference.child(kitu+username).setValue(dataMap)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(mavaziAcitivity.this, "Data sent successfully", Toast.LENGTH_SHORT).show();
                                editTextKitu.setText("");
                                editTextNumber.setText("");
                                editTextMkoa.setText("");
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(mavaziAcitivity.this, "Failed to save data: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
            // Using a Map to store the username directly

        } else {
            Toast.makeText(mavaziAcitivity.this, "Fill all spaces", Toast.LENGTH_SHORT).show();
        }





    }

    public void data() {
        String kitu = editTextKitu.getText().toString();
        String mkoa = editTextMkoa.getText().toString();
        String phone = editTextNumber.getText().toString();

        if (!kitu.isEmpty() || !mkoa.isEmpty() || !phone.isEmpty()) {
            // Using a Map to store the username directly
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("kitu", kitu);
            dataMap.put("Mkoa", mkoa);
            dataMap.put("phone", phone);

            FirebaseDatabase.getInstance().getReference("mavazi").child(kitu)
                    .setValue(dataMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(mavaziAcitivity.this, "Data sent successfully", Toast.LENGTH_SHORT).show();
                                editTextKitu.setText("");
                                editTextNumber.setText("");
                                editTextMkoa.setText("");
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(mavaziAcitivity.this, "Failed to save data: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            Toast.makeText(mavaziAcitivity.this, "Fill all spaces", Toast.LENGTH_SHORT).show();
        }
    }
}
