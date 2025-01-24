package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class NavigationUtils {

    public static void setupBottomNavigationView(Activity activity, BottomNavigationView bottomNavigationView, User currentUser) {
        // Initialize the BottomNavigationView
        initializeBottomNavigationView(activity, bottomNavigationView, currentUser);

        // Set the navigation item selected listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_dashboard:
                        // Always navigate to Admin Dashboard
                        Intent dashboardIntent = new Intent(activity, AdminActivity.class);
                        activity.startActivity(dashboardIntent);
                        return true;
                    case R.id.navigation_home:
                        // Navigate to Holiday Request Activity
                        Intent holidayIntent = new Intent(activity, HolidayRequestActivity.class);
                        activity.startActivity(holidayIntent);
                        return true;
                    case R.id.navigation_settings:
                        // Navigate to User Profile
                        Intent settingsIntent = new Intent(activity, AdminSettingsActivity.class);
                        activity.startActivity(settingsIntent);
                        return true;
                }
                return false;
            }
        });
    }

    private static void initializeBottomNavigationView(Activity activity, BottomNavigationView bottomNavigationView, User currentUser) {
        // Set the default selected item
        bottomNavigationView.setSelectedItemId(R.id.navigation_dashboard);
    }
}