package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.api.ApiClient;
import com.example.myapplication.api.EmployeeApi;
import com.example.myapplication.api.EmployeeApiUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubmitEmployeeActivity extends AppCompatActivity {

    private EditText editTextId;
    private EditText editTextSurname;
    private EditText editTextForename;
    private Button buttonSubmit;
    private Button buttonBackToDashboard;
    private static final String TAG = "SubmitEmployeeActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_employee); // Ensure this layout file is correct

        editTextId = findViewById(R.id.editTextId);
        editTextSurname = findViewById(R.id.editTextSurname);
        editTextForename = findViewById(R.id.editTextForename);
        buttonSubmit = findViewById(R.id.buttonEmployeeSubmit); // Ensure this ID matches the layout
        buttonBackToDashboard = findViewById(R.id.buttonBackToDashboard); // Ensure this ID matches the layout

        if (buttonSubmit != null) {
            buttonSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "Submit button clicked");
                    submitEmployeeData();
                }
            });
        } else {
            Log.e(TAG, "Button is null");
        }

        if (buttonBackToDashboard != null) {
            buttonBackToDashboard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(SubmitEmployeeActivity.this, AdminActivity.class);
                    startActivity(intent);
                }
            });
        } else {
            Log.e(TAG, "Back to Dashboard button is null");
        }
    }

    private void submitEmployeeData() {
        String idText = editTextId.getText().toString();
        String surname = editTextSurname.getText().toString();
        String forename = editTextForename.getText().toString();

        if (idText.isEmpty() || surname.isEmpty() || forename.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        int id = Integer.parseInt(idText);
        EmployeeApi employee = new EmployeeApi(id, surname, forename);

        EmployeeApiUtil employeeApi = ApiClient.getRetrofitInstance().create(EmployeeApiUtil.class);
        Call<Void> call = employeeApi.uploadEmployee(employee);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(SubmitEmployeeActivity.this, "Employee data submitted successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SubmitEmployeeActivity.this, "Failed to submit employee data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(SubmitEmployeeActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}