package com.example.safehopper.models;

import android.app.Person;

public class Contact extends Person {

    private boolean textAlert;
    private boolean emailAlert;

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
                "textAlert=" + textAlert +
                ", emailAlert=" + emailAlert +
                '}';
    }
}
