package com.sapient.parkinglot.models;

import com.sapient.parkinglot.factories.VehicleFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParkingFloorTest {

    ParkingFloor floor = null;

    @Test(expected = IllegalStateException.class)
    public void testAllocationWhenFull() {
        floor = new ParkingFloor(1, 1);
        Vehicle v1 = VehicleFactory.getSampleVehicle();
        Vehicle v2 = VehicleFactory.getSampleVehicle();

        String spotNumberV1 = floor.allocateSpot(v1);

        Assert.assertNotNull(spotNumberV1);

        floor.allocateSpot(v2);
    }

    @Test(expected = IllegalStateException.class)
    public void testVacationWhenEmpty() {
        floor = new ParkingFloor(1, 1);
        Vehicle v1 = VehicleFactory.getSampleVehicle();
        Vehicle v2 = VehicleFactory.getSampleVehicle();

        Assert.assertNotNull(floor.allocateSpot(v1));

        floor.vacateSpot(v1.getLicenseNumber());

        floor.vacateSpot(v1.getLicenseNumber());
    }

    @Test
    public void testEarliestVacantSpotsAllocatedFirst() {
        floor = new ParkingFloor(1, 3);
        Vehicle v1 = VehicleFactory.getSampleVehicle();
        Vehicle v2 = VehicleFactory.getSampleVehicle();

        String spotNumberV1 = floor.allocateSpot(v1);
        String spotNumberV2 = floor.allocateSpot(v2);
        String spotNumberV3 = floor.allocateSpot(VehicleFactory.getSampleVehicle());

        floor.vacateSpot(v2.getLicenseNumber());
        floor.vacateSpot(v1.getLicenseNumber());

        Assert.assertEquals(spotNumberV2, floor.allocateSpot(VehicleFactory.getSampleVehicle()));
    }

}
