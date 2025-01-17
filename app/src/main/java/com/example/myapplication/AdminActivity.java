package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class AdminActivity extends AppCompatActivity {

    ListView userListView;
    ArrayAdapter<String> userAdapter;
    List<String> userList;
    Button userMaintenanceButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        userListView = findViewById(R.id.userListView);
        userMaintenanceButton = findViewById(R.id.buttonAdmin);

        userMaintenanceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userList = UserFactory.getUserList(); // Assuming UserFactory has a method to get the list of users
                userAdapter = new ArrayAdapter<>(AdminActivity.this, android.R.layout.simple_list_item_1, userList);
                userListView.setAdapter(userAdapter);
                userListView.setVisibility(View.VISIBLE);
            }
        });
    }
}