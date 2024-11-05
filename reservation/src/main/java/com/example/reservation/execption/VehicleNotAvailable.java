package com.example.reservation.execption;

public class VehicleNotAvailable extends RuntimeException {
    public VehicleNotAvailable(String message) {
        super(message);
    }
}
