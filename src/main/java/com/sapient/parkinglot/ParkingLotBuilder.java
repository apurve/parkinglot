package com.sapient.parkinglot;

import com.sapient.parkinglot.models.ParkingFloor;
import com.sapient.parkinglot.models.ParkingLot;

import java.util.ArrayList;
import java.util.List;

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
        List<ParkingFloor> floors = new ArrayList<>(this.floors);
        for(int i = 0; i < this.floors; i++) {
            ParkingFloor floor = new ParkingFloor(i, spotsPerFloor);
            floors.add(floor);
        }
        return ParkingLot.getInstance(name, floors);
    }

}
