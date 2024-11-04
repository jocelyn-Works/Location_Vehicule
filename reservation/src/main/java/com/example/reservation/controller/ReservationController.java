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
@RequestMapping(path = "/api") // route /api par default
public class ReservationController {
    private final ReservationRepository reservationRepository;

    public ReservationController(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @PostMapping(value = ("/reservation"))
    public ResponseEntity<Reservation> addReservation(@RequestBody Reservation reservation) {
        return ResponseEntity.ok(reservationRepository.save(reservation));
    }

    @PutMapping(value = ("/reservation/{id}"))
    public ResponseEntity<String> updateReservation(@PathVariable int id, @RequestBody Reservation reservation) {
       Optional<Reservation> reservationOptional = Optional.ofNullable(reservationRepository.findById(id).orElseThrow(EntityNotFoundException::new));
       if (reservationOptional.isPresent()) {
           reservation.setId(id);
           reservation.setUserId(reservation.getUserId());
           reservation.setVehicleId(reservation.getVehicleId());
           reservation.setStartDate(reservation.getStartDate());
           reservation.setEndDate(reservation.getEndDate());
           reservation.setKmToWish(reservation.getKmToWish());
           reservation.setTotalPrice(reservation.getTotalPrice());
           reservationRepository.save(reservation);
           return new ResponseEntity<String>("Reservation updated", HttpStatus.OK);
       } else {
           return new ResponseEntity<>("Reservation not found", HttpStatus.BAD_REQUEST);
       }

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
