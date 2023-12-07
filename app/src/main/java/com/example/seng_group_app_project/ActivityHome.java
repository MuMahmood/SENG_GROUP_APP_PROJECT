package com.example.seng_group_app_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivityHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button btnUserProfile = findViewById(R.id.btnUserProfileData);
        btnUserProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityHome.this, Activityuserprofile.class);
                startActivity(intent);
            }
        });

        // Inside your ActivityHome class

        Button btnactivitynetowrk = findViewById(R.id.btnDrivingProfileNetwork);
        btnactivitynetowrk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityHome.this, Activitynetwork.class);
                startActivity(intent);
            }
        });


        Button btnactivitydrivinganalytics = findViewById(R.id.btnDriverAnalytics);
        btnactivitydrivinganalytics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityHome.this, Activitydrivinganalytics.class);
                startActivity(intent);
            }
        });

        Button btnDriverInsuranceInfo = findViewById(R.id.btnDriverInsuranceInfo);
        btnDriverInsuranceInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityHome.this, Activitydriverinsuranceinfo.class);
                startActivity(intent);
            }
        });

        Button btnInsuranceInfo = findViewById(R.id.btnInsuranceInfo);
        btnInsuranceInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityHome.this, ActivityInsuranceInfo.class);
                startActivity(intent);
            }
        });

        Button btnAuthorizedDriverInfo = findViewById(R.id.btnAuthorizedDriverInfo);
        btnAuthorizedDriverInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityHome.this, Activity2.class);
                startActivity(intent);
            }
        });

        Button btnSettings = findViewById(R.id.btnSettings);
        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityHome.this, Activitysetting.class);
                startActivity(intent);
            }
        });


    }
}

