package com.example.myapplication;

public class RegularUser extends User {

    public RegularUser(String username, String password) {
        super(username, password);
    }
    
    public String getRole() {
        return "Regular User";
    }
}
