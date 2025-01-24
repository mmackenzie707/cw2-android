package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private Button[] pinPadButtons;
    private EditText[] pinDigits;
    private final StringBuilder pinBuilder = new StringBuilder();
    private int currentPinIndex = 0;
    private User currentUser;
    private UserWrapper currentUserWrapper;

    private static final int NAV_HOME = R.id.navigation_home;
    private static final int NAV_DASHBOARD = R.id.navigation_dashboard;
    private static final int NAV_NOTIFICATIONS = R.id.navigation_settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editTextUser = findViewById(R.id.editTextUser);
        EditText pinDigit1 = findViewById(R.id.pin_digit_1);
        EditText pinDigit2 = findViewById(R.id.pin_digit_2);
        EditText pinDigit3 = findViewById(R.id.pin_digit_3);
        EditText pinDigit4 = findViewById(R.id.pin_digit_4);
        Button buttonLogin = findViewById(R.id.buttonLogin);

        // Initialize pinDigits array
        pinDigits = new EditText[]{pinDigit1, pinDigit2, pinDigit3, pinDigit4};

        // Initialize pinPadButtons array (assuming you have buttons with these IDs)
        pinPadButtons = new Button[]{
                findViewById(R.id.button0), findViewById(R.id.button1), findViewById(R.id.button2),
                findViewById(R.id.button3), findViewById(R.id.button4), findViewById(R.id.button5),
                findViewById(R.id.button6), findViewById(R.id.button7), findViewById(R.id.button8),
                findViewById(R.id.button9), findViewById(R.id.buttonDelete)
        };

        // Setup pin pad
        setupPinPad();

        // Initialize currentUserWrapper
        currentUserWrapper = new UserWrapper(null);

        // Setup login button using LoginUtils
        LoginUtils.setupLoginButton(this, buttonLogin, editTextUser, pinDigit1, pinDigit2, pinDigit3, pinDigit4, currentUserWrapper);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        NavigationUtils.setupBottomNavigationView(this, bottomNavigationView, currentUser);

        Button buttonRegister = findViewById(R.id.buttonRegister);
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