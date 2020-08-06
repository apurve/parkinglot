package com.sapient.parkinglot.models;

public class SimpleVehicle implements Vehicle {

    private String licenseNumber;

    public SimpleVehicle(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

}
