package com.example.myapplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserFactory {

    private static final List<User> users = new ArrayList<>();

    static {
        users.add(new User("admin", "1234", Arrays.asList("ADMIN")));
        users.add(new User("employee", "5678", Arrays.asList("USER")));
    }

    public static String createUsername(String firstName, String lastName) {
        if (firstName == null || lastName == null) {
            throw new IllegalArgumentException("First name and last name cannot be null");
        }
        return firstName.toLowerCase() + "." + lastName.toLowerCase();
    }

    public static User getUser(String username) {
        if (username == null) {
            return null;
        }
        for (User user : users) {
            if (username.equals(user.getUsername())) {
                return user;
            }
        }
        return null;
    }

    public static User createUser(String username, String pin) {
        if (username == null || pin == null) {
            throw new IllegalArgumentException("Username and PIN cannot be null");
        }
        User user = new User(username, pin, false);
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
        if (username == null || pin == null) {
            return false;
        }
        for (User user : users) {
            if (username.equals(user.getUsername()) && pin.equals(user.getPin())) {
                return true;
            }
        }
        return false;
    }

    public static void addUser(User user) {
        users.add(user);
    }
}