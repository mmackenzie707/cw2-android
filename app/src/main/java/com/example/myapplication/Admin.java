package com.example.myapplication;

import java.util.List;

public class Admin extends Employees {

    public Admin(String username, String pin, List<String> roles) {
        super(username, pin, roles);
    }
}