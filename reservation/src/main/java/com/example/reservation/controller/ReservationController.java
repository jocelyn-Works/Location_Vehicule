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
        Optional<Reservation> reservationOptional = Optional.ofNullable(reservationRepository.findById(id).orElseThrow(EntityNotFoundException::new));
        RestTemplate restTemplate = new RestTemplate();

        String url = "http://localhost:8080/api/maintenance/vehicle/" + reservation.getVehicleId();
        ResponseEntity<Maintenance> maintenanceResponse = restTemplate.getForEntity(url, Maintenance.class);

        String url2 = "http://127.0.0.1:8080/api/vehicle/" + reservation.getVehicleId();
        Vehicle vehiculeResponse = restTemplate.getForObject(url2, Vehicle.class);

        String url3 = "http://127.0.0.1:8080/api/vehicle/" + reservation.getVehicleId();

        vehiculeResponse.setId(reservation.getVehicleId());
        vehiculeResponse.setTraveledKm(vehiculeResponse.getTraveledKm() + reservationOptional.get().getKmToWish() + reservation.getKmRealized());

        HttpEntity<Vehicle> request = new HttpEntity<Vehicle>(vehiculeResponse);
        restTemplate.exchange(url3, HttpMethod.PUT, request, Vehicle.class);

        if (reservationOptional.isPresent()) {
            reservation.setId(id);
            reservation.setKmRealized(reservationOptional.get().getKmToWish() + reservation.getKmRealized());

            String url4 = "http://localhost:8080/api/maintenance/" + reservation.getVehicleId();
            Maintenance maintenance = new Maintenance();
            if (vehiculeResponse.getVehicleType().equals("moto")) {
                if ((vehiculeResponse.getTraveledKm() - maintenanceResponse.getBody().getKmVehicle()) >= 1000 || calculateDate(LocalDate.from(maintenanceResponse.getBody().getUpdatedAt()), reservationOptional.get().getEndDate()) >= 1) {
                    maintenance.setNotification("chaîne motos +1000 km");
                    maintenance.setRealize(false);
                    HttpEntity<Maintenance> request2 = new HttpEntity<Maintenance>(maintenance);
                    restTemplate.postForEntity(url4, request2, Maintenance.class);

                }
                if (calculateDate(LocalDate.from(maintenanceResponse.getBody().getUpdatedAt()), reservationOptional.get().getEndDate()) >= 1) {
                    maintenance.setNotification("liquide de frein");
                    maintenance.setRealize(false);
                    HttpEntity<Maintenance> request2 = new HttpEntity<Maintenance>(maintenance);
                    restTemplate.postForEntity(url4, request2, Maintenance.class);
                }
            }
            if (vehiculeResponse.getVehicleType().equals("voiture") || vehiculeResponse.getVehicleType().equals("utilitaire")) {
                if ((vehiculeResponse.getTraveledKm() - maintenanceResponse.getBody().getKmVehicle()) >= 100000) {
                    maintenance.setNotification("courroie de distribution +100 000 Km");
                    maintenance.setRealize(false);
                    HttpEntity<Maintenance> request2 = new HttpEntity<Maintenance>(maintenance);
                    restTemplate.postForEntity(url4, request2, Maintenance.class);
                }
                if (calculateDate(LocalDate.from(maintenanceResponse.getBody().getUpdatedAt()), reservationOptional.get().getEndDate()) >= 1) {
                    maintenance.setNotification("les pneus doit etre changer + 1 ans");
                    maintenance.setRealize(false);
                    HttpEntity<Maintenance> request2 = new HttpEntity<Maintenance>(maintenance);
                    restTemplate.postForEntity(url4, request2, Maintenance.class);
                }
            }
            if (vehiculeResponse.getVehicleType().equals("utilitaire")) {
                if (calculateDate(LocalDate.from(maintenanceResponse.getBody().getUpdatedAt()), reservationOptional.get().getEndDate()) >= 2) {
                    maintenance.setNotification("les suspensions doivent etre changer + 2 ans");
                    maintenance.setRealize(false);
                    HttpEntity<Maintenance> request2 = new HttpEntity<Maintenance>(maintenance);
                    restTemplate.postForEntity(url4, request2, Maintenance.class);
                }
            }
            return reservationRepository.save(reservation);
        }
        return null;
    }

    public int calculateDate(LocalDate maintenanceDate, LocalDate reservationDate) {
        return Period.between(maintenanceDate, reservationDate).getYears();
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
