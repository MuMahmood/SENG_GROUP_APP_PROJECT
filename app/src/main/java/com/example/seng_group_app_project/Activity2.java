package com.example.seng_group_app_project;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

public class Activity2 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        Button btnInsurance = findViewById(R.id.btnInsurance);
        Button btnHome = findViewById(R.id.btnHome);

        btnInsurance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent to start the ActivityInsuranceInfo
                Intent intent = new Intent(Activity2.this, ActivityInsuranceInfo.class);
                startActivity(intent);
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity2.this, ActivityHome.class);
                startActivity(intent);
            }
        });
    }
}