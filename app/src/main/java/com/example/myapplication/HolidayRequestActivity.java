package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class HolidayRequestActivity extends AppCompatActivity {

    private EditText holidayReasonEditText;
    private EditText startDateEditText;
    private EditText endDateEditText;
    private Button submitHolidayRequestButton;
    private ListView holidayRequestsListView;

    private ArrayList<String> holidayRequests;
    private ArrayAdapter<String> holidayRequestsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_holiday_request);

        holidayReasonEditText = findViewById(R.id.holidayReasonEditText);
        startDateEditText = findViewById(R.id.startDateEditText);
        endDateEditText = findViewById(R.id.endDateEditText);
        submitHolidayRequestButton = findViewById(R.id.submitHolidayRequestButton);
        holidayRequestsListView = findViewById(R.id.holidayRequestsListView);

        holidayRequests = new ArrayList<>();
        holidayRequestsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, holidayRequests);
        holidayRequestsListView.setAdapter(holidayRequestsAdapter);

        submitHolidayRequestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String reason = holidayReasonEditText.getText().toString();
                String startDate = startDateEditText.getText().toString();
                String endDate = endDateEditText.getText().toString();

                if (reason.isEmpty() || startDate.isEmpty() || endDate.isEmpty()) {
                    Toast.makeText(HolidayRequestActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                String holidayRequest = "Reason: " + reason + "\nStart Date: " + startDate + "\nEnd Date: " + endDate;
                holidayRequests.add(holidayRequest);
                holidayRequestsAdapter.notifyDataSetChanged();

                // Clear the input fields
                holidayReasonEditText.setText("");
                startDateEditText.setText("");
                endDateEditText.setText("");

                Toast.makeText(HolidayRequestActivity.this, "Holiday request submitted", Toast.LENGTH_SHORT).show();
            }
        });
    }
}