package com.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;

public class UserSessionManager {
    private SharedPreferences prefs;
    private static final String PREFS_NAME = "user_session";
    private static final String KEY_USER_ID = "user_id";
    private static final String KEY_USER_ROLE = "user_role";

    public UserSessionManager(Context context) {
        prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public User getCurrentUser() {
        String userId = prefs.getString(KEY_USER_ID, null);
        String userRole = prefs.getString(KEY_USER_ROLE, null);
        if (userId != null && userRole != null) {
            return new User(userId, userRole);
        }
        return null;
    }

    public void setCurrentUser(User user) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(KEY_USER_ID, user.getId());
        editor.putString(KEY_USER_ROLE, user.getRole());
        editor.apply();
    }

    public void clearSession() {
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.apply();
    }
}