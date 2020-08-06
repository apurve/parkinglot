package com.sapient.parkinglot.models;

import com.sapient.parkinglot.ParkingLotBuilder;
import com.sapient.parkinglot.factories.VehicleFactory;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ParingLotTest {

    private ParkingLot parkingLot = null;
    private List<ParkingTicket> tickets = new ArrayList<>();

    @Test(expected = IllegalStateException.class)
    public void testAllocationWhenFull() throws InterruptedException {
        ParkingLotBuilder builder = new ParkingLotBuilder("Gurgaon").withFloors(1).withSpots(1);
        parkingLot = builder.buildSimpleParkingLotForTesting();

        Vehicle v1 = VehicleFactory.getSampleVehicle();
        Vehicle v2 = VehicleFactory.getSampleVehicle();

        ParkingTicket parkingTicket1 = parkingLot.generateTicket(v1);
        Assert.assertNotNull(parkingTicket1);
        ParkingTicket parkingTicket2 = parkingLot.generateTicket(v2);
    }

    @Test(expected = IllegalStateException.class)
    public void testVacationWhenEmpty() throws InterruptedException {
        ParkingLotBuilder builder = new ParkingLotBuilder("Gurgaon").withFloors(1).withSpots(1);
        parkingLot = builder.buildSimpleParkingLotForTesting();

        Vehicle v1 = VehicleFactory.getSampleVehicle();
        Vehicle v2 = VehicleFactory.getSampleVehicle();

        ParkingTicket p1 = parkingLot.generateTicket(v1);
        Assert.assertNotNull(p1);

        parkingLot.exitParkingLot(p1);
        parkingLot.exitParkingLot(p1);
    }

    @Test
    public void testEarliestVacantSpotsAllocatedFirst() throws InterruptedException {
        ParkingLotBuilder builder = new ParkingLotBuilder("Gurgaon").withFloors(1).withSpots(3);
        parkingLot = builder.buildSimpleParkingLotForTesting();

        Vehicle v1 = VehicleFactory.getSampleVehicle();
        Vehicle v2 = VehicleFactory.getSampleVehicle();

        ParkingTicket parkingTicket1 = parkingLot.generateTicket(v1);
        ParkingTicket parkingTicket2 = parkingLot.generateTicket(v2);
        parkingLot.generateTicket(VehicleFactory.getSampleVehicle());

        parkingLot.exitParkingLot(parkingTicket2);
        parkingLot.exitParkingLot(parkingTicket1);

        Assert.assertEquals(parkingTicket2.getSpotNumber(), parkingLot.generateTicket(VehicleFactory.getSampleVehicle()).getSpotNumber());
    }

    @Test
    public void testGenerateTicketForTopFloor() throws InterruptedException {
        ParkingLotBuilder builder = new ParkingLotBuilder("Gurgaon").withFloors(3).withSpots(10);
        parkingLot = builder.buildSimpleParkingLotForTesting();
        ParkingTicket ticket = parkingLot.generateTicket(VehicleFactory.getSampleVehicle());
        int floor = Integer.valueOf(ticket.getSpotNumber().split("-")[0]);
        Assert.assertEquals(2, floor);
    }

    @Test
    public void testAsynchronousSpotAllocation() throws InterruptedException {
        ParkingLotBuilder builder = new ParkingLotBuilder("Gurgaon").withFloors(3).withSpots(10);
        parkingLot = builder.buildSimpleParkingLotForTesting();
        ArrayBlockingQueue<ParkingTicket> queue = new ArrayBlockingQueue<ParkingTicket>(30);
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for(int i=0; i<5; i++)
            executorService.submit(new EntryAndExitPoint(parkingLot, queue));
        executorService.awaitTermination(10, TimeUnit.SECONDS);
        executorService.shutdown();
    }

}
