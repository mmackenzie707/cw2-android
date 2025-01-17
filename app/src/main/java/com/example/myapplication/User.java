package com.example.myapplication;

public class User {
    private String firstName;
    private String lastName;
    private String username;
    private String pin;
    private String role;

    public User(String username, String pin, String role) {
        if (username == null || pin == null || role == null) {
            throw new IllegalArgumentException("Username, PIN, and role cannot be null");
        }
        this.username = username;
        this.pin = pin;
        this.role = role;
    }

    public User(String username, String pin) {
        this.username = username;
        this.pin = pin;
        this.role = role;
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

    public String getRole(){
        return role;
    }
}