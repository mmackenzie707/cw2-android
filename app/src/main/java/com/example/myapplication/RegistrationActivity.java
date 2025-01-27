package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegistrationActivity extends AppCompatActivity {

    EditText firstName, lastName, username;
    EditText[] pinDigits;
    Button submit;
    StringBuilder pinBuilder = new StringBuilder();
    int currentPinIndex = 0;

    //Initialization of Fields
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        firstName = findViewById(R.id.editTextFirstName);
        lastName = findViewById(R.id.editTextLastName);
        username = findViewById(R.id.editTextUsername);
        pinDigits = new EditText[]{
                findViewById(R.id.pin_digit_1),
                findViewById(R.id.pin_digit_2),
                findViewById(R.id.pin_digit_3),
                findViewById(R.id.pin_digit_4)
        };
        submit = findViewById(R.id.buttonSubmit);

        setupKeypad();

        //First Name Text Action
        firstName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                updateUsername();
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        //Last Name Text Action
        lastName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                updateUsername();
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        //Submit Button Action
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstNameInput = firstName.getText().toString();
                String lastNameInput = lastName.getText().toString();
                String pinInput = pinBuilder.toString();

                if (!firstNameInput.isEmpty() && !lastNameInput.isEmpty() && pinInput.length() == 4) {
                    String usernameInput = firstNameInput + "." + lastNameInput;
                    boolean isAdmin = false; // or true, based on your logic
                    User newUser = UserFactory.createUser(usernameInput, pinInput, isAdmin);

                    // Store the user locally using SharedPreferences
                    SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("username", usernameInput);
                    editor.putString("password", pinInput);
                    editor.apply();

                    // Display a message
                    Toast.makeText(RegistrationActivity.this, "User created: " + usernameInput, Toast.LENGTH_SHORT).show();

                    // Return to MainActivity
                    Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                } else {
                    Toast.makeText(RegistrationActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //Keypad Input Block
    private void setupKeypad() {
        int[] buttonIds = {
                R.id.button0, R.id.button1, R.id.button2, R.id.button3,
                R.id.button4, R.id.button5, R.id.button6, R.id.button7,
                R.id.button8, R.id.button9, R.id.buttonDelete
        };

        for (int id : buttonIds) {
            findViewById(id).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    handleKeypadInput(((Button) v).getText().toString());
                }
            });
        }
    }

    private void handleKeypadInput(String input) {
        if (input.equals(getString(R.string.text_delete))) {
            if (currentPinIndex > 0) {
                currentPinIndex--;
                pinDigits[currentPinIndex].setText("");
                pinBuilder.setLength(currentPinIndex);
            }
        } else {
            if (currentPinIndex < pinDigits.length) {
                pinDigits[currentPinIndex].setText(input);
                pinBuilder.append(input);
                currentPinIndex++;
            }
        }
    }

    //Updating the Username
    private void updateUsername() {
        String firstNameInput = firstName.getText().toString().trim();
        String lastNameInput = lastName.getText().toString().trim();
        if (!firstNameInput.isEmpty() && !lastNameInput.isEmpty()) {
            String usernameInput = firstNameInput + "." + lastNameInput;
            username.setText(usernameInput);
        } else {
            username.setText("");
        }
    }
}