package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AdminActivity extends AppCompatActivity {

    Button buttonEmployeeSubmit, adminbtn_holidayCenter, adminbtn_adminprofile, adminbtn_privacyPolicy;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        buttonEmployeeSubmit = findViewById(R.id.buttonEmployeeSubmit);
        adminbtn_holidayCenter = findViewById(R.id.adminbtn_holidayCenter);
        adminbtn_adminprofile = findViewById(R.id.adminbtn_adminprofile);
        adminbtn_privacyPolicy = findViewById(R.id.adminbtn_privacyPolicy);
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        buttonEmployeeSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent employeesubmitIntent = new Intent(AdminActivity.this, SubmitEmployeeActivity.class);
                startActivity(employeesubmitIntent);
            }
        });

        adminbtn_holidayCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent holidayIntent = new Intent(AdminActivity.this, HolidayRequestActivity.class);
                startActivity(holidayIntent);
            }
        });

        adminbtn_adminprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent adminIntent = new Intent(AdminActivity.this, AdminSettingsActivity.class);
                startActivity(adminIntent);
            }
        });

        adminbtn_privacyPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent privacyIntent = new Intent(AdminActivity.this, PrivacyPolicyActivity.class);
                startActivity(privacyIntent);
            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_dashboard:
                        Intent dashboardIntent = new Intent(AdminActivity.this, AdminActivity.class);
                        startActivity(dashboardIntent);
                        return true;
                    case R.id.navigation_settings:
                        Intent settingsIntent = new Intent(AdminActivity.this, AdminSettingsActivity.class);
                        startActivity(settingsIntent);
                        return true;
                    case R.id.navigation_logout:
                        com.example.myapplication.utils.LogoutUtils.logout(AdminActivity.this);
                        Intent mainIntent = new Intent(AdminActivity.this, MainActivity.class);
                        mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(mainIntent);
                        finish();
                        return true;
                }
                return false;
            }
        });
    }
}