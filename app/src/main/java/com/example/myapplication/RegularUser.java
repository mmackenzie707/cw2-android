package com.example.myapplication;

import java.util.Collections;
import java.util.List;

public class RegularUser extends User {

    public RegularUser(String username, String pin) {
        super(username, pin, false); // Set isAdmin to false for regular users
    }

    @Override
    public List<String> getRole() {
        return Collections.singletonList("Regular User");
    }
}
