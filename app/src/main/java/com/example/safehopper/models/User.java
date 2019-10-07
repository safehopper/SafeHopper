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
    public String getFirstName() {
        return getFirstName();
    }

    @Override
    public void setFirstName(String firstName) {
        setFirstName(firstName);
    }

    @Override
    public String getLastName() {
        return getLastName();
    }

    @Override
    public void setLastName(String lastName) {
        setLastName(lastName);
    }

    @Override
    public String getPhoneNumber() {
        return getPhoneNumber();
    }

    @Override
    public void setPhoneNumber(String phoneNumber) {
        setPhoneNumber(phoneNumber);
    }

    @Override
    public String getEmail() {
        return getEmail();
    }

    @Override
    public void setEmail(String email) {
        setEmail(email);
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