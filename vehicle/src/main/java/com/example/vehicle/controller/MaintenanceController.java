package com.example.vehicle.controller;

import com.example.vehicle.model.Maintenance;
import com.example.vehicle.model.Vehicle;
import com.example.vehicle.repository.MaintenanceRepository;
import com.example.vehicle.repository.VehicleRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@Validated
@RequestMapping(path = "/api") // route /api par default
public class MaintenanceController {
    private final MaintenanceRepository maintenanceRepository;
    private final VehicleRepository vehicleRepository;

    public MaintenanceController(MaintenanceRepository maintenanceRepository, VehicleRepository vehicleRepository) {
        this.maintenanceRepository = maintenanceRepository;
        this.vehicleRepository = vehicleRepository;
    }

    @GetMapping(value = ("/maintenance"))
    public ResponseEntity<List<Maintenance>> getAllMaintenance() {
        return ResponseEntity.ok(maintenanceRepository.findAll());
    }

    @GetMapping(value = ("/maintenance/alert"))
    public ResponseEntity<List<Maintenance>> getAllMaintenanceNotRealized() {
        return ResponseEntity.ok(maintenanceRepository.findByRealizedFalse());
    }

    @GetMapping(value = ("/maintenance/{id}"))
    public Maintenance getMaintenanceById(@PathVariable int id) {
        return maintenanceRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }


    @PostMapping(value = ("/maintenance/{vehicle_id}"))
    public ResponseEntity<Maintenance> createMaintenance(@PathVariable int vehicle_id, @Valid @RequestBody Maintenance maintenance) {
        Vehicle vehicle = vehicleRepository.findById(vehicle_id).orElseThrow(EntityNotFoundException::new);
        maintenance.setVehicle(vehicle);
        maintenance.setKmVehicle(vehicle.getTraveledKm());
        return ResponseEntity.ok(maintenanceRepository.save(maintenance));
    }

    @GetMapping(value = ("/maintenance/vehicle/{vehicle_id}"))
    public Maintenance getMaintenanceByVehicleId(@PathVariable int vehicle_id) {
        return maintenanceRepository.findByVehicleId(vehicle_id);
    }

    @GetMapping(value = ("/maintenances/vehicle/{vehicle_id}"))
    public List<Maintenance> getAllMaintenanceByVehicleId(@PathVariable int vehicle_id) {
        return maintenanceRepository.findAllByVehicleId(vehicle_id);
    }


    @PutMapping(value = ("/maintenance/{id}"))
    public Maintenance updateMaintenance(@PathVariable int id, @Valid @RequestBody Maintenance maintenance) {
        Optional<Maintenance> optionalMaintenance = Optional.ofNullable(maintenanceRepository.findById(id).orElseThrow(EntityNotFoundException::new));
        if (optionalMaintenance.isPresent()) {
            maintenance.setId(id);
            maintenance.setVehicle(maintenance.getVehicle());
            maintenance.setNotification(maintenance.getNotification());
            maintenance.setRealized(maintenance.getRealized());
            maintenance.setUpdatedAt(maintenance.getUpdatedAt());
            maintenance.setStartMaintenance(maintenance.getStartMaintenance());
            maintenance.setEndMaintenance(maintenance.getEndMaintenance());
          
            return  maintenanceRepository.save(maintenance);
        }
        return maintenance;

    }

    @DeleteMapping(value = ("/maintenance/{id}"))
    public ResponseEntity<String> deleteMaintenance(@PathVariable int id) {
        maintenanceRepository.deleteById(id);
        return new ResponseEntity<>("maintenance deleted", HttpStatus.OK);
    }

//    @GetMapping(value = ("/maintenance/{start_date}/{end_date}"))


}
