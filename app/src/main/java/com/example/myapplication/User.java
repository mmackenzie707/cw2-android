package com.example.myapplication;

public class User {
    private String firstName;
    private String lastName;
    private String username;
    private String pin;

    public User(String firstName, String lastName, String username, String pin) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.pin = pin;
    }

    public User(String username, String pin) {
        this.username = username;
        this.pin = pin;
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
}