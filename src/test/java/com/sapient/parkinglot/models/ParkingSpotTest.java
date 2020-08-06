package com.sapient.parkinglot.models;

import com.sapient.parkinglot.factories.VehicleFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParkingSpotTest {

    private ParkingSpot spot = null;

    @Before
    public void initializeParkingSpot() {
        spot = new ParkingSpot(1, 1);

    }

    @Test
    public void testAssignVehicleToFullSpot() {
        Vehicle v1 = VehicleFactory.getSampleVehicle();
        Vehicle v2 = VehicleFactory.getSampleVehicle();
        spot.assignVehicle(v1);
        Assert.assertFalse(spot.assignVehicle(v2));
    }
}
