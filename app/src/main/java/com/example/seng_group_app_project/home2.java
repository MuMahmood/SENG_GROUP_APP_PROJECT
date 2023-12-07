package com.example.seng_group_app_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class home2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button btnactivityuserprofile2 = findViewById(R.id.btnUserProfileData);
        btnactivityuserprofile2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home2.this, activityuserprofile2.class);
                startActivity(intent);
            }
        });


        Button btnactivitynetowrk = findViewById(R.id.btnDrivingProfileNetwork);
        btnactivitynetowrk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home2.this, activitynetwork2.class);
                startActivity(intent);
            }
        });


        Button btnactivitydrivinganalytics = findViewById(R.id.btnDriverAnalytics);
        btnactivitydrivinganalytics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home2.this, drivinganalytics2.class);
                startActivity(intent);
            }
        });

        Button btnactivitydriverinsuranceinfo2 = findViewById(R.id.btnDriverInsuranceInfo);
        btnactivitydriverinsuranceinfo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home2.this, activitydriverinsuranceinfo2.class);
                startActivity(intent);
            }
        });

        Button btnactivityinsuranceinfo2 = findViewById(R.id.btnInsuranceInfo);
        btnactivityinsuranceinfo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home2.this, activityinsuranceinfo2.class);
                startActivity(intent);
            }
        });

        Button btnAuthorizedDriverInfo = findViewById(R.id.btnAuthorizedDriverInfo);
        btnAuthorizedDriverInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home2.this, activityauthordriverinfo.class);
                startActivity(intent);
            }
        });

        Button btnSettings = findViewById(R.id.btnSettings);
        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home2.this, setting2.class);
                startActivity(intent);
            }
        });


    }
}

