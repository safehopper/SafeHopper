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
