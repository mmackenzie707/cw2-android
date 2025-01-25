package com.example.myapplication.utils;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import com.example.myapplication.LoginUtils;
import com.example.myapplication.UserSessionManager;

public class LogoutUtils {

    public static void logout(Activity activity) {
        // Show a toast message for logout
        Toast.makeText(activity, "Logout successful", Toast.LENGTH_SHORT).show();

        // Clear user session or any stored user data
        UserSessionManager sessionManager = new UserSessionManager(activity);
        sessionManager.clearSession();

        Intent intent = new Intent(activity, LoginUtils.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        activity.startActivity(intent);
        activity.finish();
    }
}