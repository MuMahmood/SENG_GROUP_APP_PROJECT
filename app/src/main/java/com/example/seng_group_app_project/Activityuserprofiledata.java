package com.example.seng_group_app_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Activityuserprofiledata extends AppCompatActivity {
    private EditText nicknameEditText, fullNameEditText, emailEditText, phoneEditText, dobEditText, vehicleInfoEditText, policyNumberEditText, policyExpirationEditText;
    private Button btnInsurance, btnHome, btnSaveNickname;

    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;
    private FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activityuserprofiledata);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("users");

        initializeViews();
        setOnClickListeners();
    }

    private void initializeViews() {
        // Initialize EditTexts
        nicknameEditText = findViewById(R.id.nicknameEditText);
        fullNameEditText = findViewById(R.id.fullNameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        phoneEditText = findViewById(R.id.phoneEditText);
        dobEditText = findViewById(R.id.dobEditText);
        vehicleInfoEditText = findViewById(R.id.vehicleInfoEditText);
        policyNumberEditText = findViewById(R.id.policyNumberEditText);
        policyExpirationEditText = findViewById(R.id.policyExpirationEditText);

        // Initialize Buttons
        btnInsurance = findViewById(R.id.btnInsurance);
        btnHome = findViewById(R.id.btnHome);
        btnSaveNickname = findViewById(R.id.btnSaveNickname);
    }

    private void setOnClickListeners() {
        btnSaveNickname.setOnClickListener(v -> {
            String nickname = nicknameEditText.getText().toString();
            saveNickname(nickname);
        });

        btnInsurance.setOnClickListener(v -> startActivity(new Intent(this, ActivityInsuranceInfo.class)));
        btnHome.setOnClickListener(v -> startActivity(new Intent(this, ActivityHome.class)));
    }

    private void saveNickname(String nickname) {
        if (!nickname.isEmpty() && currentUser != null) {
            databaseReference.child(currentUser.getUid()).child("Muh")
                    .setValue(nickname)
                    .addOnSuccessListener(aVoid -> Toast.makeText(Activityuserprofiledata.this, "Nickname saved successfully", Toast.LENGTH_SHORT).show())
                    .addOnFailureListener(e -> Log.e("UserProfileData", "Error saving nickname: " + e.getMessage()));
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Ensure currentUser is not null and has a valid UID
        if (currentUser != null && currentUser.getUid() != null && !currentUser.getUid().isEmpty()) {
            loadUserProfileData(currentUser.getUid());
        } else {
            Log.e("UserProfileData", "No authenticated user found");
        }
    }

    private void loadUserProfileData(String userId) {
        // Log the user ID to confirm it's correct
        Log.d("UserProfileData", "Loading user profile for ID: " + userId);
        databaseReference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // Log the data snapshot to confirm data is being retrieved
                    Log.d("UserProfileData", "Data snapshot for user ID: " + userId + " - " + dataSnapshot.getValue());
                    updateUIWithUserData(dataSnapshot);
                } else {
                    Log.e("UserProfileData", "No user data found for ID: " + userId);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Log any errors encountered when trying to read data
                Log.e("UserProfileData", "Database error: " + databaseError.getMessage());
            }
        });
    }

    private void updateUIWithUserData(DataSnapshot dataSnapshot) {
        // Set the values from the dataSnapshot to the EditTexts
        String nickname = dataSnapshot.child("Muhammad1").getValue(String.class);
        String fullName = dataSnapshot.child("Muhammad").getValue(String.class);
        String email = dataSnapshot.child("Muhammad@gmail.com").getValue(String.class);
        String phone = dataSnapshot.child("1234567890").getValue(String.class);
        String dob = dataSnapshot.child("01012000").getValue(String.class);
        String vehicleMake = dataSnapshot.child("Toyota").getValue(String.class);
        String vehicleModel = dataSnapshot.child("Honda").getValue(String.class);
        String vehicleYear = dataSnapshot.child("2020").getValue(String.class);
        String policyNumber = dataSnapshot.child("123abc").getValue(String.class);
        String policyExpiration = dataSnapshot.child("01012024").getValue(String.class);

        nicknameEditText.setText(nickname != null ? nickname : "Muhammad1");
        fullNameEditText.setText(fullName != null ? fullName : "Muhammad");
        emailEditText.setText(email != null ? email : "Muhammad@gmail.com");
        phoneEditText.setText(phone != null ? phone : "1234567890");
        dobEditText.setText(dob != null ? dob : "01012000");
        vehicleInfoEditText.setText(String.format("%s %s %s",
                vehicleMake != null ? vehicleMake : "Toyota",
                vehicleModel != null ? vehicleModel : "Honda",
                vehicleYear != null ? vehicleYear : "2020"
        ));
        policyNumberEditText.setText(policyNumber != null ? policyNumber : "123abc");
        policyExpirationEditText.setText(policyExpiration != null ? policyExpiration : "01012024");
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
