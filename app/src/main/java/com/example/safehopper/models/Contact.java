package com.example.safehopper.models;

public class Contact extends Person {

    private boolean textAlert;
    private boolean emailAlert;

    public Contact(String firstName, String lastName, String phoneNumber, String email, boolean textAlert, boolean emailAlert) {
        super(firstName, lastName, phoneNumber, email);
        this.textAlert = textAlert;
        this.emailAlert = emailAlert;
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

    @Override
    public String toString() {
        return "Contact{" +
                "firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", phoneNumber='" + getPhoneNumber() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", textAlert=" + textAlert +
                ", emailAlert=" + emailAlert +
                '}';
    }
}
