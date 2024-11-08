package com.example.vehicle.repository;

import com.example.vehicle.model.Maintenance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaintenanceRepository extends JpaRepository<Maintenance, Integer> {

    @Query(value = "select * from maintenance where ?1 = vehicle_id order by updated_at desc limit 1", nativeQuery = true)
    Maintenance findByVehicleId(int vehicleId);

}
