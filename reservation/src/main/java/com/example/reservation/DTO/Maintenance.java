package com.example.reservation.DTO;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDate;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
public class Maintenance {

    private int id;

    private Vehicle vehicle;

    private int kmVehicle;

    private String notification;

    private Boolean realized;

    private LocalDate startMaintenance;

    private LocalDate endMaintenance;

    private LocalDateTime createdDate;

    private LocalDateTime updatedAt;


}
