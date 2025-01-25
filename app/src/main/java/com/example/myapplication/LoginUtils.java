package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginUtils extends AppCompatActivity {

    public static void setupLoginButton(Activity activity, View buttonLogin, EditText editTextUser, EditText pinDigit1, EditText pinDigit2, EditText pinDigit3, EditText pinDigit4, UserWrapper currentUserWrapper) {
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTextUser.getText().toString();
                String pin = pinDigit1.getText().toString() + pinDigit2.getText().toString() +
                        pinDigit3.getText().toString() + pinDigit4.getText().toString();

                Log.d("LoginUtils", "Username: " + username + ", PIN: " + pin);

                if (UserFactory.validateUser(username, pin)) {
                    User currentUser = UserFactory.getUser(username); // Set currentUser after successful login
                    currentUserWrapper.setUser(currentUser);
                    if (currentUser != null) {
                        Toast.makeText(activity, "Login successful", Toast.LENGTH_SHORT).show();
                        if (currentUser.isAdmin()) {
                            // Navigate to Admin Dashboard
                            Intent intent = new Intent(activity, AdminActivity.class);
                            activity.startActivity(intent);
                        } else {
                            // Navigate to Employee Dashboard
                            Intent intent = new Intent(activity, EmployeeDashboardActivity.class);
                            activity.startActivity(intent);
                        }
                    } else {
                        Toast.makeText(activity, "User not found", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(activity, "Invalid credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}