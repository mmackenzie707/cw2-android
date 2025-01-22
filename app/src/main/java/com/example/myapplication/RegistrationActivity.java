package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegistrationActivity extends AppCompatActivity {

    EditText firstName, lastName;
    EditText[] pinDigits;
    Button submit;
    StringBuilder pinBuilder = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        firstName = findViewById(R.id.editTextFirstName);
        lastName = findViewById(R.id.editTextLastName);
        pinDigits = new EditText[]{
                findViewById(R.id.pin_digit_1),
                findViewById(R.id.pin_digit_2),
                findViewById(R.id.pin_digit_3),
                findViewById(R.id.pin_digit_4)
        };
        submit = findViewById(R.id.buttonSubmit);

        setupPinPad();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstNameInput = firstName.getText().toString();
                String lastNameInput = lastName.getText().toString();
                String pinInput = pinBuilder.toString();

                if (!firstNameInput.isEmpty() && !lastNameInput.isEmpty() && pinInput.length() == 4) {
                    String username = UserFactory.createUsername(firstNameInput, lastNameInput);
                    boolean isAdmin = false; // or true, based on your logic
                    User newUser = UserFactory.createUser(username, pinInput, isAdmin);

                    // Handle the new user (e.g., save to database, display a message, etc.)
                    Toast.makeText(RegistrationActivity.this, "User created: " + username, Toast.LENGTH_SHORT).show();
                    finish(); // Close the activity after registration
                } else {
                    Toast.makeText(RegistrationActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setupPinPad() {
        for (EditText pinDigit : pinDigits) {
            pinDigit.setOnKeyListener((v, keyCode, event) -> {
                pinBuilder.setLength(0); // Clear the pinBuilder before appending new text
                pinBuilder.append(((EditText) v).getText().toString());
                return false;
            });
        }
    }
}