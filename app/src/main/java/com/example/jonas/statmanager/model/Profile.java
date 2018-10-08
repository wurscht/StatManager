package com.example.jonas.statmanager.model;

public class Profile {
    private int id;
    private String username;

    // Constructor
    public Profile (int id, String username) {
        this.id = id;
        this.username = username;
    }

    // Getter and setter
    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }
}
