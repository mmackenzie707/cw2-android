package com.example.myapplication;

import java.util.ArrayList;
import java.util.List;

public class UserFactory {

    private static List<User> users = new ArrayList<>();

    public static String createUsername(String firstName, String lastName) {
        if (firstName == null || lastName == null) {
            throw new IllegalArgumentException("First name and last name cannot be null");
        }
        return firstName.toLowerCase() + "." + lastName.toLowerCase();
    }

    public static User createUser(String username, String pin) {
        if (username == null || pin == null) {
            throw new IllegalArgumentException("Username and PIN cannot be null");
        }
        User user = new User(username, pin);
        users.add(user);
        return user;
    }

    public static List<String> getUserList() {
        List<String> userList = new ArrayList<>();
        for (User user : users) {
            userList.add(user.getUsername());
        }
        return userList;
    }

    public static boolean validateUser(String username, String pin) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPin().equals(pin)) {
                return true;
            }
        }
        return false;
    }
}