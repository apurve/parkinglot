package com.sapient.parkinglot.models;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ParkingLot {


    private static ParkingLot parkingLot = null;

    private String name;
    private PriorityQueue<ParkingSpot> emptySpotsList = null;
    private Map<String, ParkingSpot> allocatedSpots = null;
    private ReentrantLock lock = null;
    private Condition condition = null;

    private ParkingLot() { }

    public static ParkingLot getInstance(String name, PriorityQueue<ParkingSpot> parkingSpots) {
        if (parkingLot == null) {
            parkingLot = new ParkingLot();
            parkingLot.name = name;
            parkingLot.emptySpotsList = parkingSpots;
            parkingLot.allocatedSpots = new HashMap<>();
            parkingLot.lock = new ReentrantLock();
            parkingLot.condition = parkingLot.lock.newCondition();
        }
        return parkingLot;
    }

    public ParkingTicket generateTicket(Vehicle vehicle) throws IllegalStateException, InterruptedException {
        ParkingSpot availableSpot = null;
        try {
            lock.lock();
            int waitingFor = 3;
            while (emptySpotsList.isEmpty()) {
                if(--waitingFor > 0) {
                    System.out.println("Parking is full, waiting!");
                    condition.await(1, TimeUnit.SECONDS);
                } else {
                    System.out.println("Parking is full!");
                  throw new IllegalStateException("Parking is Full!");
                }
            }
            availableSpot = emptySpotsList.poll();
            availableSpot.assignVehicle(vehicle);
            allocatedSpots.put(vehicle.getLicenseNumber(), availableSpot);
            condition.signal();
        } finally {
            lock.unlock();
        }
        return new SimpleParkingTicket(vehicle.getLicenseNumber(), availableSpot.getParkingNumber());
    }

    public void exitParkingLot(ParkingTicket ticket) throws IllegalStateException {
        ParkingSpot parkingSpot = null;
        try {
            lock.lock();
            parkingSpot = allocatedSpots.remove(ticket.getLicenseNumber());
            if(parkingSpot == null) {
                throw new IllegalStateException("Does not have vehicle " + ticket.getLicenseNumber());
            }
            parkingSpot.removeVehicle();
            emptySpotsList.add(parkingSpot);
        } finally {
            lock.unlock();
        }
    }

}
