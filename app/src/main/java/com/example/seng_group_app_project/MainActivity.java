package com.example.seng_group_app_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText usernameEditText = findViewById(R.id.username);
        final EditText passwordEditText = findViewById(R.id.password);
        MaterialButton loginbtn = findViewById(R.id.loginbtn);
        TextView registerNow = findViewById(R.id.registerNow);

        databaseReference = FirebaseDatabase.getInstance().getReference("users");

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredUsername = usernameEditText.getText().toString();
                String enteredPassword = passwordEditText.getText().toString();

                if (enteredUsername.isEmpty() || enteredPassword.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter both username and password", Toast.LENGTH_LONG).show();
                    return;
                }

                Query query = databaseReference.orderByChild("username").equalTo(enteredUsername);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            for (DataSnapshot user : dataSnapshot.getChildren()) {
                                String password = user.child("password").getValue(String.class);
                                String userType = user.child("userType").getValue(String.class); // Assuming 'userType' is stored in the database

                                if (password != null && password.equals(enteredPassword)) {
                                    Toast.makeText(MainActivity.this, "Login successful", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(MainActivity.this, ActivityHome.class);
                                    intent.putExtra("USER_TYPE", userType); // Send user type to ActivityHome
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(MainActivity.this, "Invalid username or password", Toast.LENGTH_LONG).show();
                                }
                            }
                        } else {
                            Toast.makeText(MainActivity.this, "User not found", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(MainActivity.this, "Database error: " + databaseError.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        registerNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Activityregister.class);
                startActivity(intent);
            }
        });
    }
}
