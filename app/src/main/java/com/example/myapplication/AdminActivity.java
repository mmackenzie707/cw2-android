package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class AdminActivity extends AppCompatActivity {

    ListView userListView;
    ArrayAdapter<String> userAdapter;
    List<String> userList;
    Button userMaintenanceButton;
    Button holidayCenterButton; // Holiday Center button
    Button privacyPolicyButton; // Privacy Policy button

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        userListView = findViewById(R.id.userListView);
        userMaintenanceButton = findViewById(R.id.adminbtn_holidayCenter);
        Button logoutButton = findViewById(R.id.logoutButton);
        Button employeeSubmitButton = findViewById(R.id.buttonEmployeeSubmit);
        Button adminSettingsButton = findViewById(R.id.adminbtn_adminprofile);
        holidayCenterButton = findViewById(R.id.adminbtn_holidayCenter); // Ensure this button is in your layout
        privacyPolicyButton = findViewById(R.id.privacyPolicyButton); // Ensure this button is in your layout

        userMaintenanceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    userList = UserFactory.getUserList();
                    if (userList == null) {
                        throw new NullPointerException("User list is null");
                    }
                    userAdapter = new ArrayAdapter<>(AdminActivity.this, android.R.layout.simple_list_item_1, userList);
                    userListView.setAdapter(userAdapter);
                    userListView.setVisibility(View.VISIBLE);
                } catch (Exception e) {
                    Log.e("AdminActivity", "Error setting user list", e);
                    Toast.makeText(AdminActivity.this, "Error loading user list", Toast.LENGTH_SHORT).show();
                }
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogoutUtils.logout(AdminActivity.this);
            }
        });

        employeeSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminActivity.this, SubmitEmployeeActivity.class);
                startActivity(intent);
            }
        });

        adminSettingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminActivity.this, AdminSettingsActivity.class);
                startActivity(intent);
            }
        });

        holidayCenterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminActivity.this, HolidayRequestActivity.class);
                startActivity(intent);
            }
        });

        privacyPolicyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminActivity.this, PrivacyPolicyActivity.class);
                startActivity(intent);
            }
        });

        // Setup bottom navigation
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        User currentUser = getCurrentUser();
        NavigationUtils.setupBottomNavigationView(this, bottomNavigationView, currentUser);
    }

    private User getCurrentUser() {
        UserSessionManager sessionManager = new UserSessionManager(this);
        return sessionManager.getCurrentUser();
    }
}