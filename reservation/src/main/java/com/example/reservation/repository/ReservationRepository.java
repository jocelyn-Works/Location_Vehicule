package com.example.reservation.repository;

import com.example.reservation.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    List<Reservation> findAllByUserId(int user_id);

    List<Reservation> findAllByVehicleId(int vehicle_id);

    @Query(value = "select * from reservation where " +
            "(?1 >= start_date AND ?1 <= end_date) " +
            "OR (?2 >= start_date AND ?2 <= end_date)" +
            "OR (?1 <= start_date AND ?2 >= end_date)",
            nativeQuery = true)
    List<Reservation> findBetweenDates(LocalDateTime start_date, LocalDateTime end_date);
}

