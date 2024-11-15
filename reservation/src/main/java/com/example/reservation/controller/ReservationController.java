package com.example.reservation.controller;

import com.example.reservation.DTO.Maintenance;
import com.example.reservation.DTO.Vehicle;
import com.example.reservation.model.Reservation;
import com.example.reservation.repository.ReservationRepository;
import com.example.reservation.service.ReservationService;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.http.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;

@RestController
@Validated
@RequestMapping(path = "/api") // route /api par default
public class ReservationController {
    private final ReservationRepository reservationRepository;
    private final ReservationService reservationService;

    public ReservationController(ReservationRepository reservationRepository, ReservationService reservationService) {
        this.reservationRepository = reservationRepository;
        this.reservationService = reservationService;
    }

    @PostMapping(value = ("/reservation"))
    public ResponseEntity<Reservation> addReservation(@RequestBody Reservation reservation) {
        return reservationService.create(reservation);
    }

    @PutMapping(value = ("/reservation/{id}"))
    public ResponseEntity<String> updateReservation(@PathVariable int id, @RequestBody Reservation reservation) {
        return reservationService.modify(reservation, id);
    }

    // admin
    @PutMapping(value = ("/reservation/maintenance/{id}"))
    public Reservation updateReservationMaintenance(@PathVariable int id, @RequestBody Reservation reservation) {
        return reservationService.ReservationMaintenanceUpdate(id, reservation);
    }

    @GetMapping(value = ("/reservation"))
    public ResponseEntity<List<Reservation>> getAllReservations() {
        return ResponseEntity.ok(reservationRepository.findAll());
    }

    @GetMapping(value = ("/reservation/{id}"))
    public Reservation getReservationById(@PathVariable int id) {
        return reservationRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @GetMapping(value = ("/reservation/user/{user_id}"))
    public List<Reservation> getReservationsByUser(@PathVariable int user_id) {
        return reservationRepository.findAllByUserId(user_id);
    }

    @GetMapping(value = ("/reservation/vehicle/{vehicle_id}"))
    public List<Reservation> getResevationByVehicle(@PathVariable int vehicle_id) {
        return reservationRepository.findAllByVehicleId(vehicle_id);
    }

    @DeleteMapping(value = ("/reservation/{id}"))
    public ResponseEntity<String> deleteReservation(@PathVariable int id) {
        reservationRepository.deleteById(id);
        return new ResponseEntity<>("Reservation deleted", HttpStatus.OK);
    }
}
