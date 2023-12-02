package com.example.seng_group_app_project;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Button;

public class Activitydriverinsuranceinfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activitydriverinsuranceinfo);

        Button btnInsurance = findViewById(R.id.btnInsurance);
        Button btnHome = findViewById(R.id.btnHome);

        btnInsurance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activitydriverinsuranceinfo.this, ActivityInsuranceInfo.class);
                startActivity(intent);
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activitydriverinsuranceinfo.this, ActivityHome.class);
                startActivity(intent);
            }
        });
    }
}
