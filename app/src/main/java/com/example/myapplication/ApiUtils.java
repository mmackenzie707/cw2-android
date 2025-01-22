package com.example.myapplication;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiUtils {

    public static void sendDataToApi(String apiUrl, String jsonData) {
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(apiUrl);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Content-Type", "application/json");

            OutputStream os = urlConnection.getOutputStream();
            os.write(jsonData.getBytes("UTF-8"));
            os.close();

            int responseCode = urlConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Handle success
                System.out.println("Data sent successfully.");
            } else {
                // Handle error
                System.out.println("Failed to send data. Response code: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
    }
}