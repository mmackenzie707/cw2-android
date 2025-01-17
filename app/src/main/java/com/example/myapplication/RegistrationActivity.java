package com.example.myapplication;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegistrationActivity extends AppCompatActivity {

    EditText firstName, lastName;
    EditText[] pinDigits;
    Button submit, adminButton;

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
        adminButton = findViewById(R.id.buttonAdmin);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstNameInput = firstName.getText().toString();
                String lastNameInput = lastName.getText().toString();
                StringBuilder pinBuilder = new StringBuilder();
                for (EditText pinDigit : pinDigits) {
                    pinBuilder.append(pinDigit.getText().toString());
                }
                String pin = pinBuilder.toString();

                if (!firstNameInput.isEmpty() && !lastNameInput.isEmpty() && pin.length() == 4) {
                    String username = UserFactory.createUsername(firstNameInput, lastNameInput);
                    User newUser = UserFactory.createUser(username, pin);

                    // Handle the new user (e.g., save to database, display a message, etc.)
                    Toast.makeText(RegistrationActivity.this, "User created: " + newUser.getUsername(), Toast.LENGTH_SHORT).show();

                    // Navigate back to MainActivity or another appropriate activity
                    Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(RegistrationActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    Toast.makeText(RegistrationActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        adminButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistrationActivity.this, AdminActivity.class);
                startActivity(intent);
            }
        });
    }
}