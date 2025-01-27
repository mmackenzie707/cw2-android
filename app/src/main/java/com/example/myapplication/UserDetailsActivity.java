package com.example.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AppCompatActivity;

public class UserDetailsActivity extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    EditText firstNameEditText, lastNameEditText;
    TextView usernameTextView, pinTextView;
    ImageView imageViewProfilePhoto;
    Button buttonTakePhoto, buttonEdit, buttonResetPassword, buttonBack;
    User loggedInUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        firstNameEditText = findViewById(R.id.firstNameEditText);
        lastNameEditText = findViewById(R.id.lastNameEditText);
        usernameTextView = findViewById(R.id.usernameTextView);
        pinTextView = findViewById(R.id.pinEditText);
        imageViewProfilePhoto = findViewById(R.id.imageViewProfilePhoto);
        buttonTakePhoto = findViewById(R.id.buttonTakePhoto);
        buttonEdit = findViewById(R.id.buttonEdit);
        buttonResetPassword = findViewById(R.id.buttonResetPassword);
        buttonBack = findViewById(R.id.buttonBack);


        loggedInUser = UserStorage.getInstance().getUser("loggedInUsername"); // Replace with actual logic

        if (loggedInUser != null) {
            firstNameEditText.setText(loggedInUser.getFirstName());
            lastNameEditText.setText(loggedInUser.getLastName());
            usernameTextView.setText("Username: " + loggedInUser.getUsername());
            pinTextView.setText("PIN: " + loggedInUser.getPin());
        }

        buttonTakePhoto.setOnClickListener(v -> {
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        });

        buttonEdit.setOnClickListener(v -> {
            boolean isEditable = firstNameEditText.isEnabled();
            firstNameEditText.setEnabled(!isEditable);
            lastNameEditText.setEnabled(!isEditable);
            buttonEdit.setText(isEditable ? "Edit" : "Save");

            if (isEditable) {
                loggedInUser.setFirstName(firstNameEditText.getText().toString());
                loggedInUser.setLastName(lastNameEditText.getText().toString());
                UserStorage.getInstance().updateUser(loggedInUser);
            }
        });

        buttonResetPassword.setOnClickListener(v -> {
        });

        buttonBack.setOnClickListener(v -> {
            Intent intent = new Intent(UserDetailsActivity.this, EmployeeDashboardActivity.class);
            startActivity(intent);
            finish();
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageViewProfilePhoto.setImageBitmap(imageBitmap);
        }
    }
}