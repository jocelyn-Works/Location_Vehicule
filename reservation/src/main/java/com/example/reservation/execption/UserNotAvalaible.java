package com.example.reservation.execption;

public class UserNotAvalaible extends RuntimeException {
    public UserNotAvalaible(String message) {
        super(message);
    }
}
