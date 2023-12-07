package com.example.seng_group_app_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Activityuserprofile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activityuserprofile);

        Button btnInsurance = findViewById(R.id.btnInsurance);
        Button btnHome = findViewById(R.id.btnHome);

        btnInsurance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activityuserprofile.this, ActivityInsuranceInfo.class);
                startActivity(intent);
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activityuserprofile.this, ActivityHome.class);
                startActivity(intent);
            }
        });
    }
}