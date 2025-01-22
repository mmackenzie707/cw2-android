package com.example.myapplication;

import java.util.Collections;

public class RegularUser extends User {

    public RegularUser(String username, String pin) {
        super(username, pin, false); // Set isAdmin to false for regular users
    }

    @Override
    public String getRole() {
        return Collections.singletonList("Regular User").toString();
    }
}
