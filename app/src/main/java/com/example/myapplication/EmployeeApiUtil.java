package com.example.myapplication.api;

import java.util.List;

import com.example.myapplication.api.EmployeeApi;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface EmployeeApiUtil {
    @POST("employees")
    Call<Void> uploadEmployee(@Body EmployeeApi employee);

    @GET("employees")
    Call<List<EmployeeApi>> fetchEmployees();
}