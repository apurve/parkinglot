package com.sapient.parkinglot.models;

import com.sapient.parkinglot.ParkingLotBuilder;
import com.sapient.parkinglot.factories.VehicleFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ParingLotTest {

    private ParkingLot parkingLot = null;
    private List<ParkingTicket> tickets = new ArrayList<>();
    private Object lock = new Object();

    @Before
    public void initializeParkingLot() {
        ParkingLotBuilder builder = new ParkingLotBuilder("Gurgaon").withFloors(3).withSpots(10);
        parkingLot = builder.buildSimpleParkingLotForTesting();
    }

    @Test
    public void testGenerateTicketForTopFloor() {
        ParkingTicket ticket = parkingLot.generateTicket(VehicleFactory.getSampleVehicle());
        int floor = Integer.valueOf(ticket.getSpotNumber().split("-")[0]);
        Assert.assertEquals(2, floor);
    }

    @Test
    public void testAsynchronousSpotAllocation() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        executorService.submit(() -> {
            try {
                entryPointOne();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        executorService.submit(() -> {
            try {
                entryPointTwo();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        executorService.submit(() -> {
            exitPoint();
        });

        executorService.awaitTermination(5, TimeUnit.SECONDS);
        executorService.shutdown();
    }

    @Test
    public void testAsynchronousEntryWithAsynchronousExit() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        executorService.submit(() -> {
            try {
                entryPointOne();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        executorService.submit(() -> {
            try {
                entryPointTwo();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        executorService.submit(() -> {
            exitPoint();
        });

        executorService.awaitTermination(5, TimeUnit.SECONDS);
        executorService.shutdown();
    }

    private void exitPoint() {
        while (true) {

        }
    }

    private void entryPointOne() throws InterruptedException {
        while (true) {
            ParkingTicket ticket = parkingLot.generateTicket(VehicleFactory.getSampleVehicle());
            System.out.println(ticket);
            goToSleep();
        }
    }

    private void entryPointTwo() throws InterruptedException {
        while (true) {
            ParkingTicket ticket = parkingLot.generateTicket(VehicleFactory.getSampleVehicle());
            System.out.println(ticket);
            goToSleep();
        }
    }

    private void goToSleep() throws InterruptedException {
        Thread.sleep(300);
    }

}
