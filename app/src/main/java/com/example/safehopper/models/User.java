package com.example.safehopper.models;

public class User extends Person {

    private String password;

    public User(String firstName, String lastName, String phoneNumber, String email, String password)
    {
        super(firstName, lastName, phoneNumber, email);
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", phoneNumber='" + getPhoneNumber() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", password=" + password +
                '}';
    }
}