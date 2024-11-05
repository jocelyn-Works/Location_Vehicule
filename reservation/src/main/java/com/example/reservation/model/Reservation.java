package com.example.reservation.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Locale;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name="user_id", nullable = false)
    private int userId;

    @Column(name="vehicule_id", nullable = false)
    private int vehicleId;

    @Column(name="start_date", nullable = false)
    private LocalDateTime startDate;

    @Column(name="end_date", nullable = false)
    private LocalDateTime endDate;

    @Column(name="km_to_wish", nullable = false)
    private int kmToWish;

    @Column(name="total_price", nullable = false)
    private Float totalPrice;
}
