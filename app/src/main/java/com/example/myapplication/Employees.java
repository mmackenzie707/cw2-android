package com.example.myapplication;

import java.util.List;

public class Employees {
    private final boolean isAdmin;
    private String username;
    private String pin;
    private List<String> roles;

    public Employees(String username, String pin, List<String> roles) {
        this.username = username;
        this.pin = pin;
        this.roles = roles;
        this.isAdmin = roles.contains("ADMIN");
    }

    public String getUsername() {
        return username;
    }

    public String getPin() {
        return pin;
    }

    public List<String> getRoles() {
        return roles;
    }

    public boolean isAdmin() {
        return roles.contains("ADMIN");
    }

}