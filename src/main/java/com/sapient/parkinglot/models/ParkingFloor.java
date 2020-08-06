package com.sapient.parkinglot.models;

import com.sapient.parkinglot.util.ParkingSpotComparator;

import java.util.*;

public class ParkingFloor {
    private int floorNumber;
    private PriorityQueue<ParkingSpot> emptySpotsList = null;
    private Map<String, ParkingSpot> allocatedSpots = null;

    public ParkingFloor(int floorNumber, int spots) {
        this.floorNumber = floorNumber;
        this.emptySpotsList = new PriorityQueue<>(new ParkingSpotComparator());
        initializeSpots(spots);
        this.allocatedSpots = new HashMap<String, ParkingSpot>();
    }

    private void initializeSpots(int spots) {
        for(int i = 0; i < spots; i++) {
            emptySpotsList.add(new ParkingSpot(floorNumber+ "-" + i));
        }
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public List<ParkingSpot> getEmptySpots() {
        return Collections.unmodifiableList(new ArrayList<>(emptySpotsList));
    }

    public boolean isSpotAvailable() {
        return emptySpotsList.size() > 0;
    }

    public String allocateSpot(Vehicle vehicle) throws IllegalStateException {
        if(isSpotAvailable()) {
            ParkingSpot availableSpot = emptySpotsList.poll();
            availableSpot.assignVehicle(vehicle);
            allocatedSpots.put(vehicle.getLicenseNumber(), availableSpot);
            return availableSpot.getNumber();
        } else {
            throw new IllegalStateException("Parking floor is full.");
        }
    }

    public void vacateSpot(String licenseNumber) throws IllegalStateException {
        ParkingSpot spot = allocatedSpots.remove(licenseNumber);
        if(spot != null) {
            spot.removeVehicle();
            emptySpotsList.add(spot);
        } else {
            throw new IllegalStateException("The vehicle is not on "+floorNumber);
        }
    }

}
