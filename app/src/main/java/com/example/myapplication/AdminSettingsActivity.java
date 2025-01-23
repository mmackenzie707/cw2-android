package com.example.myapplication;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.myapplication.utils.CameraUtils;
import com.example.myapplication.utils.EditUtils;
import com.example.myapplication.utils.PasswordResetUtils;

public class AdminSettingsActivity extends AppCompatActivity {

    private ImageView imageViewProfilePhoto;
    private TextView usernameTextView;
    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText pinEditText;
    private Button editButton;
    private Button resetPasswordButton;
    private Button dashboardButton;

    private static final int REQUEST_CAMERA_PERMISSION = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details); 

        imageViewProfilePhoto = findViewById(R.id.imageViewProfilePhoto);
        usernameTextView = findViewById(R.id.usernameTextView);
        firstNameEditText = findViewById(R.id.firstNameEditText);
        lastNameEditText = findViewById(R.id.lastNameEditText);
        pinEditText = findViewById(R.id.pinEditText);
        Button takePhotoButton = findViewById(R.id.buttonTakePhoto);
        editButton = findViewById(R.id.buttonEdit);
        resetPasswordButton = findViewById(R.id.buttonResetPassword);
        dashboardButton = findViewById(R.id.buttonBack);

        // Set the logged-in username
        String username = getCurrentUsername();
        usernameTextView.setText(username);

        takePhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(AdminSettingsActivity.this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(AdminSettingsActivity.this, new String[]{android.Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
                } else {
                    CameraUtils.dispatchTakePictureIntent(AdminSettingsActivity.this);
                }
            }
        });

        editButton.setOnClickListener(new View.OnClickListener() {
            private boolean isEditing = false;

            @Override
            public void onClick(View v) {
                if (isEditing) {
                    // Save the changes (you can add your save logic here)
                    EditUtils.disableEditing(firstNameEditText, lastNameEditText);
                    editButton.setText("Edit");
                } else {
                    EditUtils.enableEditing(firstNameEditText, lastNameEditText);
                    editButton.setText("Save");
                }
                isEditing = !isEditing;
            }
        });

        resetPasswordButton.setOnClickListener(new View.OnClickListener() {
            private boolean isResetting = false;

            @Override
            public void onClick(View v) {
                if (isResetting) {
                    PasswordResetUtils.saveNewPin(pinEditText, resetPasswordButton);
                } else {
                    PasswordResetUtils.enablePinEditing(pinEditText, resetPasswordButton);
                }
                isResetting = !isResetting;
            }
        });

        dashboardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminSettingsActivity.this, AdminActivity.class);
                startActivity(intent);
            }
        });
    }

    private String getCurrentUsername() {
        UserSessionManager sessionManager = new UserSessionManager(this);
        User currentUser = sessionManager.getCurrentUser();
        return currentUser != null ? currentUser.getUsername() : "Guest";
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CameraUtils.REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            String photoPath = CameraUtils.getCurrentPhotoPath();
            // Handle the photo (e.g., display it in an ImageView or save it)
            Log.d("AdminSettingsActivity", "Photo saved at: " + photoPath);
            setPic(photoPath);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CAMERA_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                CameraUtils.dispatchTakePictureIntent(this);
            } else {
                Toast.makeText(this, "Camera permission is required to take photos", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void setPic(String photoPath) {
        // Get the dimensions of the View
        int targetW = imageViewProfilePhoto.getWidth();
        int targetH = imageViewProfilePhoto.getHeight();

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(photoPath, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW / targetW, photoH / targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(photoPath, bmOptions);
        imageViewProfilePhoto.setImageBitmap(bitmap);
    }
}