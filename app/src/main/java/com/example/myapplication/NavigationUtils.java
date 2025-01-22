package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class NavigationUtils {

    public static void setupBottomNavigationView(Activity activity, BottomNavigationView bottomNavigationView, User currentUser) {
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        // Handle home action
                        return true;
                    case R.id.navigation_dashboard:
                        if (currentUser != null) {
                            if (currentUser.isAdmin()) {
                                // Navigate to Admin Dashboard
                                Intent intent = new Intent(activity, AdminUsersPage.class);
                                activity.startActivity(intent);
                            } else {
                                // Navigate to Employee Dashboard
                                Intent intent = new Intent(activity, EmployeeDashboardActivity.class);
                                activity.startActivity(intent);
                            }
                        }
                        return true;
                    case R.id.navigation_notifications:
                        // Handle notifications action
                        return true;
                }
                return false;
            }
        });
    }
}
