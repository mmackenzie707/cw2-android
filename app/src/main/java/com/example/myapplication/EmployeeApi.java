package com.example.myapplication.api;

public class EmployeeApi {
    private int id;
    private String surname;
    private String forename;

    public EmployeeApi(int id, String surname, String forename) {
        this.id = id;
        this.surname = surname;
        this.forename = forename;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }
}