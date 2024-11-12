package com.example.reservation.service;

import com.example.reservation.model.Reservation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface ReservationService {
    ResponseEntity<Reservation> create(Reservation reservation);
    ResponseEntity<String> modify(Reservation reservation, int id);
    Reservation ReservationMaintenanceUpdate( int id,  Reservation reservation);
}
