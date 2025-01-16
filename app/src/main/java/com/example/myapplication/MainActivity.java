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

    EditText username, password;
    Button login, register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.editTextUser);
        password = findViewById(R.id.editTextPass);
        login = findViewById(R.id.buttonLogin);
        register = findViewById(R.id.buttonRegister);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username != null && password != null) {
                    User user = UserStorage.getInstance().getUser(username.getText().toString());
                    if (user != null && user.password.equals(password.getText().toString())) {
                        if (user.getRole().equals("Admin")) {
                            String value = "Welcome, admin!";
                            Intent i = new Intent(MainActivity.this, AdminActivity.class);
                            i.putExtra("message", value);
                            startActivity(i);
                        } else {
                            String value = "Welcome, regular user!";
                            Intent i = new Intent(MainActivity.this, RegularUserActivity.class);
                            i.putExtra("message", value);
                            startActivity(i);
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "Invalid Details",
                                Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Username or Password is null",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });
    }
}