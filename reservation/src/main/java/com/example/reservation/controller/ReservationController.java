package com.example.reservation.controller;

import com.example.reservation.model.Reservation;
import com.example.reservation.repository.ReservationRepository;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Validated
public class ReservationController {
    private final ReservationRepository reservationRepository;

    public ReservationController(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @PostMapping(value = ("/reservation"))
    public ResponseEntity<Reservation> addReservation(@RequestBody Reservation reservation) {
        return ResponseEntity.ok(reservationRepository.save(reservation));
    }

    @GetMapping(value = ("/reservation"))
    public ResponseEntity<List<Reservation>> getAllReservations() {
        return ResponseEntity.ok(reservationRepository.findAll());
    }

    @GetMapping(value = ("/reservation/{id}"))
    public Reservation getReservationById(@PathVariable int id) {
        return reservationRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @GetMapping(value = ("/reservation/{user_id}"))
    public List<Reservation> getReservationsByUser(@PathVariable int user_id) {
        return reservationRepository.findAllByUserId(user_id);
    }

    @GetMapping(value = ("/reservation/{vehicle_id}"))
    public List<Reservation> getResevationByVehicle(@PathVariable int vehicle_id) {
        return reservationRepository.findAllByVehicleId(vehicle_id);
    }

    @DeleteMapping(value = ("/reservation/{id]"))
    public ResponseEntity<String> deleteReservation(@PathVariable int id) {
       reservationRepository.deleteById(id);
       return new ResponseEntity<>("Reservation deleted", HttpStatus.OK);
    }
}
