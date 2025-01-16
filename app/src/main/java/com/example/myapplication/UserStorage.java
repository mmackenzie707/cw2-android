package com.example.myapplication;

import java.util.HashMap;

public class UserStorage {
    private static UserStorage instance;
    private HashMap<String, User> users;

    private UserStorage() {
        users = new HashMap<>();
    }

    public static synchronized UserStorage getInstance() {
        if (instance == null) {
            instance = new UserStorage();
        }
        return instance;
    }

    public void addUser(User user) {
        users.put(user.username, user);
    }

    public User getUser(String username) {
        return users.get(username);
    }
}
