package com.example.vehicle.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "maintenance")
public class Maintenance {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name="vehicle_id", nullable = false)
    private Vehicle vehicle;


    @Column(name="notification", nullable = false)
    private String notification;


    @Column(name="realize", nullable = false)
    private Boolean realize;


    @CreationTimestamp
    private LocalDateTime createdDate;


    @UpdateTimestamp
    private LocalDateTime updatedAt;


}
