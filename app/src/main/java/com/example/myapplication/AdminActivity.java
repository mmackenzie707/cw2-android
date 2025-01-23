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
                Intent intent = new Intent(AdminActivity.this, EmployeeActivity.class);
                startActivity(intent);
            }
        });

        adminSettingsButton.setOnClickListener(new View.OnClickListener() { // New OnClickListener
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminActivity.this, AdminSettingsActivity.class); // Assuming AdminSettingsActivity is your target activity
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