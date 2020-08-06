package com.sapient.parkinglot.models;

import java.time.LocalDateTime;

public class ParkingSpot {

    private int number;
    private int floor;
    private Vehicle vehicle;
    private LocalDateTime vacatedOn;

    public ParkingSpot(int floor, int number) {
        this.floor = floor;
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

    @Override
    public String toString() {
        return "ParkingSpot{" +
                "number=" + number +
                ", floor=" + floor +
                ", vehicle=" + vehicle.getLicenseNumber() +
                ", vacatedOn=" + vacatedOn +
                '}';
    }

    public String getParkingNumber() {
        return floor+"-"+number;
    }
    public Integer getNumber() {
        return number;
    }

    public Integer getFloor() {
        return floor;
    }

    public LocalDateTime getVacatedOn() {
        return vacatedOn;
    }



}
