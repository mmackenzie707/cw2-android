package com.example.myapplication.utils;

import android.widget.EditText;

public class EditUtils {

    public static void enableEditing(EditText... editTexts) {
        for (EditText editText : editTexts) {
            editText.setEnabled(true);
            editText.requestFocus();
        }
    }

    public static void disableEditing(EditText... editTexts) {
        for (EditText editText : editTexts) {
            editText.setEnabled(false);
        }
    }
}