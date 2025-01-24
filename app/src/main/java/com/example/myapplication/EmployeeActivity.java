package com.example.myapplication.api;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.api.ApiClient;
import com.example.myapplication.api.Employee;
import com.example.myapplication.api.EmployeeApi;

import java.util.ArrayList;
import java.util.List;

public class EmployeeActivity extends AppCompatActivity {

    private ListView employeeListView;
    private ArrayAdapter<String> employeeAdapter;
    private List<String> employeeList;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regular_user);

        employeeListView = findViewById(R.id.userListView);
        employeeList = new ArrayList<>();
        employeeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, employeeList);
        employeeListView.setAdapter(employeeAdapter);

    }
}