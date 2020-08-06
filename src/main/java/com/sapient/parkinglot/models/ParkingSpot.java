package com.sapient.parkinglot.models;

import java.time.LocalDateTime;

public class ParkingSpot {

    private String number;
    private Vehicle vehicle;
    private LocalDateTime vacatedOn;

    public ParkingSpot(String number) {
        this.number = number;
        this.vacatedOn = LocalDateTime.now();
    }

    public boolean isFree() {
        return vehicle == null;
    }

    public boolean assignVehicle(Vehicle vehicle) {
        if(this.isFree()) {
            this.vehicle = vehicle;
            return true;
        }
        return false;
    }

    public boolean removeVehicle() {
        if(!this.isFree()) {
            this.vehicle = null;
            this.vacatedOn = LocalDateTime.now();
            return true;
        }
        return false;
    }

    public String getNumber() {
        return number;
    }

    public LocalDateTime getVacatedOn() {
        return vacatedOn;
    }

}
