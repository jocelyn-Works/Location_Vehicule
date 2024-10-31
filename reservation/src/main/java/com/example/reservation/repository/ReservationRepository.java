package com.example.reservation.repository;

import com.example.reservation.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    List<Reservation> findAllByUserId(int user_id);

    List<Reservation> findAllByVehicleId(int vehicle_id);
}

