package com.sapient.parkinglot;

import com.sapient.parkinglot.models.ParkingLot;
import com.sapient.parkinglot.models.ParkingSpot;
import com.sapient.parkinglot.util.ParkingLotComparator;

import java.util.PriorityQueue;

public class ParkingLotBuilder {

    private String name;
    private int floors;
    private int spotsPerFloor;

    public ParkingLotBuilder (String name) {
        this.name = name;
    }

    public ParkingLotBuilder withFloors(int floors) {
        this.floors = floors;
        return this;
    }

    public ParkingLotBuilder withSpots(int spotsPerFloor) {
        this.spotsPerFloor = spotsPerFloor;
        return this;
    }

    public ParkingLot buildSimpleParkingLotForTesting() {
        PriorityQueue<ParkingSpot> parkingSpots = new PriorityQueue<>(new ParkingLotComparator());
        for(int i = 0; i < floors; i++)
            for(int j = 0; j < spotsPerFloor; j++)
                parkingSpots.add(new ParkingSpot(i, j));

        return ParkingLot.getInstance(name, parkingSpots);
    }

}
