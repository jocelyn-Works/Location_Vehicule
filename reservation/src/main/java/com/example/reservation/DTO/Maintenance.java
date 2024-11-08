package com.example.reservation.DTO;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
public class Maintenance {

    private int id;

    private Vehicle vehicle;

    private int kmVehicle;

    private String notification;

    private Boolean realize;

    private LocalDateTime createdDate;

    private LocalDateTime updatedAt;


}
