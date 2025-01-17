package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.example.myapplication.R;

public class MainActivity extends AppCompatActivity {

    EditText editTextUser;
    EditText[] pinDigits;
    Button buttonLogin, buttonRegister;
    Button[] pinPadButtons;
    StringBuilder pinBuilder = new StringBuilder();
    int currentPinIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextUser = findViewById(R.id.editTextUser);
        pinDigits = new EditText[]{
                findViewById(R.id.pin_digit_1),
                findViewById(R.id.pin_digit_2),
                findViewById(R.id.pin_digit_3),
                findViewById(R.id.pin_digit_4)
        };
        buttonLogin = findViewById(R.id.buttonLogin);
        buttonRegister = findViewById(R.id.buttonRegister);

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
                String usernameInput = editTextUser.getText().toString();
                String pinInput = pinBuilder.toString();

                // Log inputs for debugging
                Log.d("MainActivity", "Username: " + usernameInput);
                Log.d("MainActivity", "PIN: " + pinInput);

                if (!usernameInput.isEmpty() && pinInput.length() == 4) {
                    if (UserFactory.validateUser(usernameInput, pinInput)) {
                        Toast.makeText(MainActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Incorrect username or PIN", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                }
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