package com.example.myapplication;

public class RegularUser extends User {

    public RegularUser(String username, String password) {
        super(username, password);
    }

    @Override
    public String getRole() {
        return "Regular User";
    }
}
