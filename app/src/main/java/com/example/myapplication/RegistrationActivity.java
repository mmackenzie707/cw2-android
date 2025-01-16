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

    EditText newUser, newPass, userType;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        newUser = findViewById(R.id.editTextNewUser);
        newPass = findViewById(R.id.editTextNewPass);
        userType = findViewById(R.id.editTextUserType);
        submit = findViewById(R.id.buttonSubmit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = newUser.getText().toString();
                String password = newPass.getText().toString();
                String type = userType.getText().toString();

                User newUser = UserFactory.createUser(type, username, password);
                if (newUser != null) {
                    UserStorage.getInstance().addUser(newUser);
                    Toast.makeText(RegistrationActivity.this, "User Registered: " + newUser.getRole(),
                            Toast.LENGTH_SHORT).show();
                    // Navigate back to MainActivity or another appropriate activity
                    Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(RegistrationActivity.this, "Registration Failed",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
