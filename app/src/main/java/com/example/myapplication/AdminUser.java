package com.example.myapplication;

import java.util.Collections;
import java.util.List;

public class AdminUser extends User {

    public AdminUser(String username, String pin) {
        super(username, pin, true); // Set isAdmin to true for admin users
    }

    @Override
    public List<String> getRole() {
        return Collections.singletonList("Admin");
    }
}