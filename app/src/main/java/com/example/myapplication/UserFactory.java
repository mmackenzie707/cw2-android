package com.example.myapplication;

public class UserFactory {

    public static User createUser(String type, String username, String password) {
        if (type.equalsIgnoreCase("Admin")) {
            return new AdminUser(username, password);
        } else if (type.equalsIgnoreCase("Regular")) {
            return new RegularUser(username, password);
        }
        return null;
    }
}