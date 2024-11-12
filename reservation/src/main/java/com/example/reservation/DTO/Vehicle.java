package com.example.reservation.DTO;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

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

    private Date maintenanceStartDate;

    private Date maintenanceEndDate;

    private int traveledKm;

    private int cylinder;

    private int volume;
}
