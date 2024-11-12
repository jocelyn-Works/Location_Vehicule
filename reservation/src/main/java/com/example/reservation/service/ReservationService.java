package com.example.reservation.service;

import com.example.reservation.model.Reservation;
import org.springframework.http.ResponseEntity;

public interface ReservationService {
    ResponseEntity<Reservation> create(Reservation reservation);
    ResponseEntity<String> modify(Reservation reservation, int id);
}
