package com.sapient.parkinglot.util;

import com.sapient.parkinglot.models.ParkingSpot;

import java.util.Comparator;

public class ParkingSpotComparator implements Comparator<ParkingSpot> {

    @Override
    public int compare(ParkingSpot o1, ParkingSpot o2) {
        return o1.getVacatedOn().compareTo(o2.getVacatedOn());
    }

}
