package com.example.safehopper.ui.settings;

public class Settings {
    private String unit;
    private String securityLevel;

    public Settings(String unit, String securityLevel) {
        this.unit = unit;
        this.securityLevel = securityLevel;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getSecurityLevel() {
        return securityLevel;
    }

    public void setSecurityLevel(String securityLevel) {
        this.securityLevel = securityLevel;
    }
}