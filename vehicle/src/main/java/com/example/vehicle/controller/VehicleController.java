package com.example.vehicle.controller;

import com.example.vehicle.model.Vehicle;
import com.example.vehicle.repository.VehicleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@Validated
@RequestMapping(path = "/api") // route /api par default
public class VehicleController {

    private final VehicleRepository vehicleRepository;


    public VehicleController(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    /**
     * Get all vehicle
     *
     * @return
     */
    @GetMapping(value = "/vehicle")
    public Iterable<Vehicle> findAll() {
        return vehicleRepository.findAll();
    }

    /**
     * Get one Vehicle by vehicle_registration
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/vehicle/{id}")
    public Optional<Vehicle> getVehicleById(@PathVariable int id) {
        return vehicleRepository.findById(id);
    }

    /**
     * Create on Vehicle
     *
     * @param vehicle
     * @return
     */
    @PostMapping(value = "/vehicle")
    public ResponseEntity<Vehicle> addVehicle(@RequestBody Vehicle vehicle) {

        return ResponseEntity.ok(vehicleRepository.save(vehicle));
    }

    /**
     * Update Vehicle by Id
     *
     * @param id
     * @param vehicle
     * @return
     */
    @PutMapping(value = "/vehicle/{id}")
    public ResponseEntity<Vehicle> updateVehicle(@PathVariable int id, @RequestBody Vehicle vehicle) {
        Optional<Vehicle> vehicleOptional = Optional.of(vehicleRepository.findById(id).orElseThrow(EntityNotFoundException::new));

            vehicle.setId(id);
            vehicle.setVehicleRegistration(vehicle.getVehicleRegistration());
            vehicle.setVehicleType(vehicle.getVehicleType());
            vehicle.setBrand(vehicle.getBrand());
            vehicle.setModel(vehicle.getModel());
            vehicle.setColor(vehicle.getColor());
            vehicle.setReservationPrice(vehicle.getReservationPrice());
            vehicle.setKmPrice(vehicle.getKmPrice());
            vehicle.setTaxHorse(vehicle.getTaxHorse());
            vehicle.setTraveledKm(vehicle.getTraveledKm());
            vehicle.setCylinder(vehicle.getCylinder());
            vehicle.setVolume(vehicle.getVolume());
            return ResponseEntity.ok(vehicleRepository.save(vehicle));

    }

    @DeleteMapping(value = ("/vehicle/{id}"))
    public ResponseEntity<String> deleteVehicle(@PathVariable int id) {
        vehicleRepository.deleteById(id);
        return new ResponseEntity<>("Vehicle deleted", HttpStatus.OK);
    }

}
