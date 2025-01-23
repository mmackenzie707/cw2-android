package com.example.myapplication.utils;

import android.widget.Button;
import android.widget.EditText;

public class PasswordResetUtils {

    public static void enablePinEditing(EditText pinEditText, Button resetPasswordButton) {
        pinEditText.setEnabled(true);
        pinEditText.requestFocus();
        resetPasswordButton.setText("Save PIN");
    }

    public static void saveNewPin(EditText pinEditText, Button resetPasswordButton) {
        // Add your save logic here (e.g., save the new PIN to a database or shared preferences)
        pinEditText.setEnabled(false);
        resetPasswordButton.setText("Reset Password");
    }
}