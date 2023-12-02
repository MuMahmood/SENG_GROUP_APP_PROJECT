package com.example.seng_group_app_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Activitysetting extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activitysetting);

        Button btnInsurance = findViewById(R.id.btnInsurance);
        Button btnHome = findViewById(R.id.btnHome);
        Button btnLogout = findViewById(R.id.btnLogout);

        btnInsurance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activitysetting.this, ActivityInsuranceInfo.class);
                startActivity(intent);
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activitysetting.this, ActivityHome.class);
                startActivity(intent);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activitysetting.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
