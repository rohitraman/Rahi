package com.example.roshan.rahiapp;


public class User {
    public String email;
    public String pin;

    public User() {
    }

    public User(String email, String pin) {
        this.email = email;
        this.pin = pin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }
}
