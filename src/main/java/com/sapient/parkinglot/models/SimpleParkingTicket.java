package com.sapient.parkinglot.models;

public class SimpleParkingTicket implements ParkingTicket {

    private String licenseNumber;
    private String spotNumber;

    public SimpleParkingTicket(String licenseNumber, String spotNumber) {
        this.licenseNumber = licenseNumber;
        this.spotNumber = spotNumber;
    }

    @Override
    public String getLicenseNumber() {
        return licenseNumber;
    }

    @Override
    public String getSpotNumber() {
        return spotNumber;
    }

    @Override
    public String toString() {
        return "SimpleParkingTicket{" +
                "licenseNumber='" + licenseNumber + '\'' +
                ", spotNumber (f-p)='" + spotNumber + '\'' +
                '}';
    }
}
