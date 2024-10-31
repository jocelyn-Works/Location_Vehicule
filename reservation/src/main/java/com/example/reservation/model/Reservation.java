package com.example.reservation.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

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
    private Date startDate;

    @Column(name="end_date", nullable = false)
    private Date endDate;

    @Column(name="km_to_wish", nullable = false)
    private String kmToWish;

    @Column(name="vehicle_registration", nullable = false)
    private Float totalPrice;
}
