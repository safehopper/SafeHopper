package com.example.safehopper.models;

import android.util.Log;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class Contact extends Person {

    private boolean sendTextAlert;
    private boolean sendEmailAlert;

    public Contact(String firstName, String lastName, String phoneNumber, String email, boolean sendTextAlert, boolean sendEmailAlert) {
        super(firstName, lastName, phoneNumber, email);
        this.sendTextAlert = sendTextAlert;
        this.sendEmailAlert = sendEmailAlert;
    }

    public Contact(JsonObject jsonObj){
        super(jsonObj.get("first_name").toString(), jsonObj.get("last_name").toString(), jsonObj.get("phone").toString(), jsonObj.get("email").toString());
        this.sendTextAlert = jsonObj.get("text_alerts").getAsBoolean();
        this.sendEmailAlert = jsonObj.get("email_alerts").getAsBoolean();
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
        return super.getFirstName();
    }

    @Override
    public void setFirstName(String firstName) {
        setFirstName(firstName);
    }

    @Override
    public String getLastName() {
        return super.getLastName();
    }

    @Override
    public void setLastName(String lastName) {
        setLastName(lastName);
    }

    @Override
    public String getPhoneNumber() {
        return super.getPhoneNumber();
    }

    @Override
    public void setPhoneNumber(String phoneNumber) {
        setPhoneNumber(phoneNumber);
    }

    @Override
    public String getEmail() {
        return super.getEmail();
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
