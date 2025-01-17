package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    EditText firstName, lastName;
    EditText[] pinDigits;
    Button submit;
    StringBuilder pinBuilder = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                    User newUser = UserFactory.createUser(username, pinInput);

                    // Handle the new user (e.g., save to database, display a message, etc.)
                    Toast.makeText(MainActivity.this, "User created: " + username, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setupPinPad() {
        for (EditText pinDigit : pinDigits) {
            pinDigit.setOnKeyListener((v, keyCode, event) -> {
                if (pinBuilder.length() < 4) {
                    pinBuilder.append(((EditText) v).getText().toString());
                }
                return false;
            });
        }
    }
}