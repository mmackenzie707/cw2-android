package com.example.myapplication;

public class AdminUser extends User {

    public AdminUser(String username, String password) {
        super(username, password);
    }

    public String getRole() {
        return "Admin";
    }
}