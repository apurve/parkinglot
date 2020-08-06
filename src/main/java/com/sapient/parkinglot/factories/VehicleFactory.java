package com.sapient.parkinglot.factories;

import com.sapient.parkinglot.models.SimpleVehicle;
import com.sapient.parkinglot.models.Vehicle;

import java.util.concurrent.ThreadLocalRandom;

public class VehicleFactory {

    public static Vehicle getSampleVehicle() {
        return new SimpleVehicle(generateRandomLicenseNumber());
    }

    private static String generateRandomLicenseNumber() {
        return String.valueOf(ThreadLocalRandom.current().nextInt(1000));
    }

}
