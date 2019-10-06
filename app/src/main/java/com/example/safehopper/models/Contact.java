package com.example.safehopper.models;

public class Contact extends Person {

    private boolean sendTextAlert;
    private boolean sendEmailAlert;

    public Contact(String firstName, String lastName, String phoneNumber, String email, boolean sendTextAlert, boolean sendEmailAlert) {
        super(firstName, lastName, phoneNumber, email);
        this.sendTextAlert = sendTextAlert;
        this.sendEmailAlert = sendEmailAlert;
    }

    public boolean sendTextAlert() {
        return sendTextAlert;
    }

    public void setSendTextAlert(boolean sendTextAlert) {
        this.sendTextAlert = sendTextAlert;
    }

    public boolean sendEmailAlert() {
        return sendEmailAlert;
    }

    public void setSendEmailAlert(boolean sendEmailAlert) {
        this.sendEmailAlert = sendEmailAlert;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", phoneNumber='" + getPhoneNumber() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", sendTextAlert=" + sendTextAlert +
                ", sendEmailAlert=" + sendEmailAlert +
                '}';
    }
}
