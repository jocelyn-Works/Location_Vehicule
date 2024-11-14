package com.example.reservation.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Reservation {

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name="user_id", nullable = false)
    private int userId;

    @Column(name="vehicule_id", nullable = false)
    private int vehicleId;

    @Column(name="start_date", nullable = false)
    private LocalDate startDate;

    @Column(name="end_date", nullable = false)
    private LocalDate endDate;

    @Column(name="km_to_wish", nullable = false)
    private int kmToWish;

    @Column(name="km_realized")
    private int kmRealized;

    @Column(name="total_price", nullable = false)
    private Float totalPrice;

    public Reservation(int id, int userId, int vehicleId, String startDate, String endDate,int kmToWish, float totalPrice) {
        this.id = id;
        this.userId = userId;
        this.vehicleId = vehicleId;
        this.startDate = LocalDate.parse(startDate);
        this.endDate = LocalDate.parse(endDate);
        this.kmToWish = kmToWish;
        this.totalPrice = totalPrice;
    }
}
