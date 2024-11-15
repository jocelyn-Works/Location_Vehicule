package com.example.vehicle.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "maintenance")
public class Maintenance {

    @Id
    //@GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name="vehicle_id", nullable = false)
    private Vehicle vehicle;

    @Column(name="km_vehicle", nullable = false)
    private int kmVehicle;

    @Column(name="notification", nullable = false)
    private String notification;

    @Column(name="start_maintenance", nullable = true)
    private LocalDate startMaintenance;

    @Column(name="end_maintenance", nullable = true)
    private LocalDate endMaintenance;

    @Column(name="realized", nullable = false)
    private Boolean realized = false;

    @CreationTimestamp
    private LocalDateTime createdDate;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Maintenance(int id, String notification) {
        this.id = id;
        this.notification = notification;
    }

    public Maintenance(int id, String notification, Vehicle vehicle) {
        this.id = id;
        this.notification = notification;
        this.vehicle = vehicle;
    }
}
