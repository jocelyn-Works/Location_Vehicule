package com.example.vehicle.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "vehicle")
public class Vehicle {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @Column(name="vehicle_registration", nullable = false, unique = true)
    private String vehicle_registration;

    @Column(name="vehicle_type", nullable = false)
    private String vehicleType;

    @Column(name="brand", nullable = false)
    private String brand;

    @Column(name="model", nullable = false)
    private String model;

    @Column(name="color", nullable = false)
    private String color;

    @Column(name="reservation_price", nullable = false)
    private Float reservationPrice;

    @Column(name="km_price", nullable = false)
    private Float kmPrice;

    @Column(name="tax_horse", nullable = false)
    private Float taxHorse;


    @Column(name="traveled_km", nullable = false)
    private int traveledKm;

    @Column(name="cylinder", nullable = true)
    private int cylinder;

    @Column(name="volume", nullable = true)
    private int volume;



}
