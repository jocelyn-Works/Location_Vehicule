package com.example.vehicle.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "vehicle")
public class Vehicle {

    @Id
    //@GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @Column(name="vehicle_registration", nullable = false, unique = true)
    private String vehicleRegistration;

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
    private int cylinder = 0;

    @Column(name="volume", nullable = true)
    private int volume = 0;

    public Vehicle(int id, String vehicleRegistration, String vehicleType, String brand, String model, String color, Float reservationPrice, Float kmPrice, Float taxHorse, int traveledKm, int cylinder, int volume) {
        this.id = id;
        this.vehicleRegistration = vehicleRegistration;
        this.vehicleType = vehicleType;
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.reservationPrice = reservationPrice;
        this.kmPrice = kmPrice;
        this.taxHorse = taxHorse;
        this.traveledKm = traveledKm;
        this.cylinder = cylinder;
        this.volume = volume;
    }
}
