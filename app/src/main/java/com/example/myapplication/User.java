package com.example.myapplication;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class User {
    private String firstName;
    private String lastName;
    private String username;
    private String pin;
    private List<String> roles;

    public User(String username, String pin, List<String> roles) {
        if (username == null || pin == null || roles == null) {
            throw new IllegalArgumentException("Username, PIN, and roles cannot be null");
        }
        this.username = username;
        this.pin = pin;
        this.roles = roles;
    }

    public User(String username, String pin) {
        this(username, pin, Collections.singletonList("USER"));
    }

    public <T> User(String username, List<T> list) {
        this.username = username;
        this.pin = null; // Assuming pin is not provided in this constructor
        this.roles = Collections.singletonList("USER"); // Default role
    }

    public User(String username, String pin, boolean b) {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    private boolean checkUserAdminStatus() {
        User currentUser = getCurrentUser();
        return currentUser.getRoles().contains("ADMIN");
    }

    private User getCurrentUser() {
        return new User("username", "pin", Arrays.asList("USER", "ADMIN"));
    }

    public String getId() {
        return username;
    }

    public String getRole() {
        return roles.toString();
    }
}