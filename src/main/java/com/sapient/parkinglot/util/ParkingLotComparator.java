package com.sapient.parkinglot.util;

import com.sapient.parkinglot.models.ParkingSpot;

import java.util.Comparator;

public class ParkingLotComparator implements Comparator<ParkingSpot> {

    @Override
    public int compare(ParkingSpot o1, ParkingSpot o2) {
        int floorResult = o2.getFloor().compareTo(o1.getFloor());
        if(floorResult == 0) {
            return o1.getVacatedOn().compareTo(o2.getVacatedOn());
        }
        return floorResult;
    }

}
