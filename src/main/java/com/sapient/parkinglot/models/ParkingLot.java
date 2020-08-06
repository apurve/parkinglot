package com.sapient.parkinglot.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingLot {


    private static ParkingLot parkingLot = null;

    private String name;
    private Map<String, String> activeTickets;
    private List<ParkingFloor> floorList;

    private ParkingLot() { }

    public static ParkingLot getInstance(String name, List<ParkingFloor> floors) {
        if (parkingLot == null) {
            parkingLot = new ParkingLot();
            parkingLot.name = name;
            parkingLot.activeTickets = new HashMap<>();
            parkingLot.floorList = floors;
        }
        return parkingLot;
    }

    public synchronized ParkingTicket generateTicket(Vehicle vehicle) throws IllegalStateException {
        for(int i = this.floorList.size()-1; i >=0; i--) {
            ParkingFloor floor = this.floorList.get(i);
            try {
                String spotNumber = floor.allocateSpot(vehicle);
                ParkingTicket ticket = new SimpleParkingTicket(vehicle.getLicenseNumber(), spotNumber);
                activeTickets.put(vehicle.getLicenseNumber(), spotNumber);
                return ticket;
            } catch (IllegalStateException exception) {
                continue;
            }
        }
        throw new IllegalStateException("Parking lot is Full!");
    }

    public synchronized void exitParkingLot(ParkingTicket ticket) throws IllegalStateException {

    }

}
