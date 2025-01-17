package com.example.myapplication;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class AdminUsersPage extends AppCompatActivity {

    ListView userListView;
    ArrayAdapter<String> userAdapter;
    List<String> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_users);

        userListView = findViewById(R.id.userListView);
        userList = UserFactory.getUserList(); // Assuming UserFactory has a method to get the list of users
        userAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, userList);
        userListView.setAdapter(userAdapter);
    }
}