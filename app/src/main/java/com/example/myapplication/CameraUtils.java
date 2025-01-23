package com.example.myapplication.utils;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CameraUtils {

    public static final int REQUEST_IMAGE_CAPTURE = 1;
    public static final int REQUEST_CAMERA_PERMISSION = 200;
    private static String currentPhotoPath;

    public static void dispatchTakePictureIntent(Activity activity) {
        if (ContextCompat.checkSelfPermission(activity, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, new String[]{android.Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
        } else {
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (takePictureIntent.resolveActivity(activity.getPackageManager()) != null) {
                File photoFile = null;
                try {
                    photoFile = createImageFile(activity);
                } catch (IOException ex) {
                    Toast.makeText(activity, "Error creating file", Toast.LENGTH_SHORT).show();
                }
                if (photoFile != null) {
                    Uri photoURI = FileProvider.getUriForFile(activity, "com.example.myapplication.fileprovider", photoFile);
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                    activity.startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }
            }
        }
    }

    private static File createImageFile(Activity activity) throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName, ".jpg", storageDir);
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    public static String getCurrentPhotoPath() {
        return currentPhotoPath;
    }
}