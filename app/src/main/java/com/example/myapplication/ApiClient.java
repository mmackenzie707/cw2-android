package com.example.myapplication;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ApiClient {

    public interface ApiCallback {
        void onSuccess(List<Employee> employees);
        void onFailure(Exception e);
    }

    public static void fetchEmployees(String apiUrl, ApiCallback callback) {
        new FetchEmployeeDataTask(apiUrl, callback).execute();
    }

    private static class FetchEmployeeDataTask extends AsyncTask<Void, Void, List<Employee>> {
        private String apiUrl;
        private ApiCallback callback;
        private Exception exception;

        FetchEmployeeDataTask(String apiUrl, ApiCallback callback) {
            this.apiUrl = apiUrl;
            this.callback = callback;
        }

        @Override
        protected List<Employee> doInBackground(Void... voids) {
            List<Employee> employees = new ArrayList<>();
            try {
                URL url = new URL(apiUrl);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder result = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }
                reader.close();

                JSONArray jsonArray = new JSONArray(result.toString());
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String id = jsonObject.getString("id");
                    String name = jsonObject.getString("name");
                    String position = jsonObject.getString("position");
                    String department = jsonObject.getString("department");
                    employees.add(new Employee(id, name, position, department));
                }
            } catch (Exception e) {
                exception = e;
                Log.e("ApiClient", "Error fetching employee data", e);
            }
            return employees;
        }

        @Override
        protected void onPostExecute(List<Employee> employees) {
            if (exception != null) {
                callback.onFailure(exception);
            } else {
                callback.onSuccess(employees);
            }
        }
    }
}