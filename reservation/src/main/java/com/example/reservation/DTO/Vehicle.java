package com.example.reservation.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@NoArgsConstructor
public class Vehicle {
    private long id;

    private String vehicleRegistration;

    private String vehicleType;

    private String brand;

    private String model;

    private String color;

    private Float reservationPrice;

    private Float kmPrice;

    private Float taxHorse;

    private int traveledKm;

    private int cylinder;

    private int volume;
}
