package com.example.myapplication;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.RemoteMessage;

import java.util.ArrayList;
import java.util.Calendar;

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

        startDateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(startDateEditText);
            }
        });

        endDateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(endDateEditText);
            }
        });

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

                // Send push notification
                sendPushNotification(reason, startDate, endDate);
            }
        });

        // Setup bottom navigation
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        User currentUser = getCurrentUser();
        NavigationUtils.setupBottomNavigationView(this, bottomNavigationView, currentUser);
    }

    private void showDatePickerDialog(final EditText dateEditText) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                HolidayRequestActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                        dateEditText.setText(selectedDate);
                    }
                },
                year, month, day);
        datePickerDialog.show();
    }

    private void sendPushNotification(String reason, String startDate, String endDate) {
        FirebaseMessaging.getInstance().send(new RemoteMessage.Builder("your_server_key@fcm.googleapis.com")
                .setMessageId(Integer.toString((int) System.currentTimeMillis()))
                .addData("title", "New Holiday Request")
                .addData("body", "Reason: " + reason + "\nStart Date: " + startDate + "\nEnd Date: " + endDate)
                .build());
    }

    private User getCurrentUser() {
        UserSessionManager sessionManager = new UserSessionManager(this);
        return sessionManager.getCurrentUser();
    }
}