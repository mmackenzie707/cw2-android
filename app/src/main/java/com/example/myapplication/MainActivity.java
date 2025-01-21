package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private EditText editTextUser;
    private EditText pinDigit1, pinDigit2, pinDigit3, pinDigit4;
    private Button[] pinPadButtons;
    private EditText[] pinDigits;
    private final StringBuilder pinBuilder = new StringBuilder();
    private int currentPinIndex = 0;
    private User currentUser;

    private static final int NAV_HOME = R.id.navigation_home;
    private static final int NAV_DASHBOARD = R.id.navigation_dashboard;
    private static final int NAV_NOTIFICATIONS = R.id.navigation_notifications;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextUser = findViewById(R.id.editTextUser);
        pinDigit1 = findViewById(R.id.pin_digit_1);
        pinDigit2 = findViewById(R.id.pin_digit_2);
        pinDigit3 = findViewById(R.id.pin_digit_3);
        pinDigit4 = findViewById(R.id.pin_digit_4);
        Button buttonLogin = findViewById(R.id.buttonLogin);
        Button buttonRegister = findViewById(R.id.buttonRegister);

        // Initialize pinDigits array
        pinDigits = new EditText[]{pinDigit1, pinDigit2, pinDigit3, pinDigit4};

        // Initialize pinPadButtons array
        pinPadButtons = new Button[]{
                findViewById(R.id.button0),
                findViewById(R.id.button1),
                findViewById(R.id.button2),
                findViewById(R.id.button3),
                findViewById(R.id.button4),
                findViewById(R.id.button5),
                findViewById(R.id.button6),
                findViewById(R.id.button7),
                findViewById(R.id.button8),
                findViewById(R.id.button9),
                findViewById(R.id.buttonDelete)
        };

        setupPinPad();

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTextUser.getText().toString();
                String pin = pinDigit1.getText().toString() + pinDigit2.getText().toString() +
                        pinDigit3.getText().toString() + pinDigit4.getText().toString();

                Log.d("MainActivity", "Username: " + username + ", PIN: " + pin);

                if (UserFactory.validateUser(username, pin)) {
                    currentUser = UserFactory.getUser(username); // Set currentUser after successful login
                    if (currentUser != null) {
                        if (currentUser.isAdmin()) {
                            // Navigate to Admin Dashboard
                            Intent intent = new Intent(MainActivity.this, AdminUsersPage.class);
                            startActivity(intent);
                        } else {
                            // Navigate to Employee Dashboard
                            Intent intent = new Intent(MainActivity.this, EmployeeDashboardActivity.class);
                            startActivity(intent);
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "User not found", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case NAV_HOME:
                        // Handle home action
                        return true;
                    case NAV_DASHBOARD:
                        if (currentUser != null) {
                            if (currentUser.isAdmin()) {
                                // Navigate to Admin Dashboard
                                Intent intent = new Intent(MainActivity.this, AdminUsersPage.class);
                                startActivity(intent);
                            } else {
                                // Navigate to Employee Dashboard
                                Intent intent = new Intent(MainActivity.this, EmployeeDashboardActivity.class);
                                startActivity(intent);
                            }
                        }
                        return true;
                    case NAV_NOTIFICATIONS:
                        // Handle notifications action
                        return true;
                }
                return false;
            }
        });

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setupPinPad() {
        for (Button pinPadButton : pinPadButtons) {
            pinPadButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (v.getId() == R.id.buttonDelete) {
                        if (currentPinIndex > 0) {
                            currentPinIndex--;
                            pinDigits[currentPinIndex].setText("");
                            pinBuilder.setLength(currentPinIndex);
                        }
                    } else {
                        if (currentPinIndex < 4) {
                            Button button = (Button) v;
                            String digit = button.getText().toString();
                            pinDigits[currentPinIndex].setText(digit);
                            pinBuilder.append(digit);
                            currentPinIndex++;
                        }
                    }
                }
            });
        }
    }
}