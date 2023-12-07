package com.example.seng_group_app_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Activityregister extends AppCompatActivity {

    private EditText nameEditText, emailEditText, contactEditText;
    private EditText vehicleMakeEditText, vehicleModelEditText, vehicleYearEditText;
    private EditText driverDoBEditText, policyNumEditText, policyExpireEditText;
    private EditText usernameEditText, passwordEditText;
    private Button registerButton, homeButton; // Added homeButton

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activityregister);

        nameEditText = findViewById(R.id.nameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        contactEditText = findViewById(R.id.contactEditText);
        vehicleMakeEditText = findViewById(R.id.vehicleMakeEditText);
        vehicleModelEditText = findViewById(R.id.vehicleModelEditText);
        vehicleYearEditText = findViewById(R.id.vehicleYearEditText);
        driverDoBEditText = findViewById(R.id.driverDoB);
        policyNumEditText = findViewById(R.id.policyNum);
        policyExpireEditText = findViewById(R.id.policyExpire);
        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        registerButton = findViewById(R.id.registerButton);
        homeButton = findViewById(R.id.homeButton); // Initialize homeButton

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String contact = contactEditText.getText().toString();
                String vehicleMake = vehicleMakeEditText.getText().toString();
                String vehicleModel = vehicleModelEditText.getText().toString();
                String vehicleYear = vehicleYearEditText.getText().toString();
                String driverDoB = driverDoBEditText.getText().toString();
                String policyNum = policyNumEditText.getText().toString();
                String policyExpire = policyExpireEditText.getText().toString();
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                // Existing validation checks
                if (name.isEmpty() || email.isEmpty() || contact.isEmpty() ||
                        vehicleMake.isEmpty() || vehicleModel.isEmpty() || vehicleYear.isEmpty() ||
                        driverDoB.isEmpty() || policyNum.isEmpty() || policyExpire.isEmpty() ||
                        username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(Activityregister.this, "Please fill out all fields", Toast.LENGTH_LONG).show();
                    return;
                }

                if (!email.contains("@")) {
                    Toast.makeText(Activityregister.this, "Invalid email address", Toast.LENGTH_LONG).show();
                    return;
                }

                if (contact.length() > 10 || !contact.matches("[0-9]+")) {
                    Toast.makeText(Activityregister.this, "Invalid contact number", Toast.LENGTH_LONG).show();
                    return;
                }

                if (vehicleYear.length() != 4 || !vehicleYear.matches("[0-9]+")) {
                    Toast.makeText(Activityregister.this, "Invalid vehicle year", Toast.LENGTH_LONG).show();
                    return;
                }

                if (!driverDoB.matches("(0[1-9]|1[0-2])(0[1-9]|[12][0-9]|3[01])[0-9]{4}")) {
                    Toast.makeText(Activityregister.this, "Invalid date of birth format. Use mm/dd/yyyy", Toast.LENGTH_LONG).show();
                    return;
                }

                if (!policyNum.matches("[0-9]{3}[a-zA-Z]{3}")) {
                    Toast.makeText(Activityregister.this, "Invalid policy number. It should be 3 numbers followed by 3 letters", Toast.LENGTH_LONG).show();
                    return;
                }

                if (!policyExpire.matches("(0[1-9]|1[0-2])(0[1-9]|[12][0-9]|3[01])[0-9]{4}")) {
                    Toast.makeText(Activityregister.this, "Invalid policy expiration date format. Use mm/dd/yyyy", Toast.LENGTH_LONG).show();
                    return;
                }

                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

                Map<String, Object> user = new HashMap<>();
                user.put("name", name);
                user.put("email", email);
                user.put("contact", contact);
                user.put("vehicleMake", vehicleMake);
                user.put("vehicleModel", vehicleModel);
                user.put("vehicleYear", vehicleYear);
                user.put("driverDoB", driverDoB);
                user.put("policyNum", policyNum);
                user.put("policyExpire", policyExpire);
                user.put("username", username);
                user.put("password", password);

                databaseReference.child("users").push().setValue(user)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(Activityregister.this, "Registration was successful", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(Activityregister.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Activityregister.this, "Registration failed: " + e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });
            }
        });

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activityregister.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
