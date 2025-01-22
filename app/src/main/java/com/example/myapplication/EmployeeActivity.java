package com.example.myapplication;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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

        employeeListView = findViewById(R.id.employeeListView);
        employeeList = new ArrayList<>();
        employeeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, employeeList);
        employeeListView.setAdapter(employeeAdapter);

        ApiClient.fetchEmployees("http://web.socem.plymouth.ac.uk/COMP2000/api/employees", new ApiClient.ApiCallback() {
            @Override
            public void onSuccess(List<Employee> employees) {
                for (Employee employee : employees) {
                    employeeList.add(employee.getName());
                }
                employeeAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Exception e) {
                Log.e("EmployeeActivity", "Error loading employee data", e);
                Toast.makeText(EmployeeActivity.this, "Error loading employee data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}