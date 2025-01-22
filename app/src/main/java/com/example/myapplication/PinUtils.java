package com.example.myapplication;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PinUtils {

    public static void initializePinPad(Activity activity, EditText[] pinDigits, Button[] pinPadButtons) {
        pinDigits[0] = activity.findViewById(R.id.pin_digit_1);
        pinDigits[1] = activity.findViewById(R.id.pin_digit_2);
        pinDigits[2] = activity.findViewById(R.id.pin_digit_3);
        pinDigits[3] = activity.findViewById(R.id.pin_digit_4);

        pinPadButtons[0] = activity.findViewById(R.id.button0);
        pinPadButtons[1] = activity.findViewById(R.id.button1);
        pinPadButtons[2] = activity.findViewById(R.id.button2);
        pinPadButtons[3] = activity.findViewById(R.id.button3);
        pinPadButtons[4] = activity.findViewById(R.id.button4);
        pinPadButtons[5] = activity.findViewById(R.id.button5);
        pinPadButtons[6] = activity.findViewById(R.id.button6);
        pinPadButtons[7] = activity.findViewById(R.id.button7);
        pinPadButtons[8] = activity.findViewById(R.id.button8);
        pinPadButtons[9] = activity.findViewById(R.id.button9);
        pinPadButtons[10] = activity.findViewById(R.id.buttonDelete);
    }
}