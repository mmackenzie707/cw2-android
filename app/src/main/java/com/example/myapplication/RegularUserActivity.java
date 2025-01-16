package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegularUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regular_user);

        // Retrieve the message from the Intent
        String message = getIntent().getStringExtra("message");
        // Display the message or use it as needed
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();

        // Find the logout button
        Button logoutButton = findViewById(R.id.logoutButton);

        // Set an OnClickListener for the logout button
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Perform logout action
                Intent intent = new Intent(RegularUserActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish(); // Close the RegularUserActivity
            }
        });
    }
}
