package com.example.myapplication;

import java.util.ArrayList;
import java.util.List;

public class UserFactory {

    private static List<User> users = new ArrayList<>();

    public static String createUsername(String firstName, String lastName) {
        return firstName.toLowerCase() + "." + lastName.toLowerCase();
    }

    public static User createUser(String username, String pin) {
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
}