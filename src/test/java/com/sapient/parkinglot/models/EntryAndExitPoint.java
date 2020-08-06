package com.sapient.parkinglot.models;

import com.sapient.parkinglot.factories.VehicleFactory;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

public class EntryAndExitPoint implements Runnable {

    private ParkingLot parkingLot;
    private ArrayBlockingQueue<ParkingTicket> queue;

    public EntryAndExitPoint(ParkingLot parkingLot, ArrayBlockingQueue<ParkingTicket> queue) {
        this.parkingLot = parkingLot;
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            Random random = new Random();
            if(random.nextBoolean()) {
                try {
                    ParkingTicket ticket = parkingLot.generateTicket(VehicleFactory.getSampleVehicle());
                    System.out.println("Ticket Generated : " + ticket);
                    queue.add(ticket);
                } catch (IllegalStateException exception) {
                    System.out.println(exception.getMessage());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                ParkingTicket ticket = queue.poll();
                parkingLot.exitParkingLot(ticket);
                System.out.println("Ticket Exited : " + ticket);
            }
            try {
                goToSleep();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void goToSleep() throws InterruptedException {
        Thread.sleep(300);
    }
}
