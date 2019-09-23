package com.example.safehopper.models;

public class Contact {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private boolean textAlert;
    private boolean emailAlert;

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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isTextAlert() {
        return textAlert;
    }

    public void setTextAlert(boolean textAlert) {
        this.textAlert = textAlert;
    }

    public boolean isEmailAlert() {
        return emailAlert;
    }

    public void setEmailAlert(boolean emailAlert) {
        this.emailAlert = emailAlert;
    }
}
