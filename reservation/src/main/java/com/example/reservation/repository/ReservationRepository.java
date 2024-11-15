package com.example.reservation.repository;

import com.example.reservation.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    List<Reservation> findAllByUserId(int user_id);

    List<Reservation> findAllByVehicleId(int vehicle_id);

    @Query(value = "select * from reservation where " +
            "(?3 = vehicule_id AND ?1 >= start_date AND ?1 <= end_date) " +
            "OR (?3 = vehicule_id AND ?2 >= start_date AND ?2 <= end_date)" +
            "OR (?3 = vehicule_id AND ?1 <= start_date AND ?2 >= end_date)",
            nativeQuery = true)
    List<Reservation> findAllReservationBetweenDatesWithVehicleId(LocalDate start_date, LocalDate end_date, int vehicle_id);

    @Query(value = "select * from reservation where " +
            "(?3 = user_id AND ?1 >= start_date AND ?1 <= end_date) " +
            "OR (?3 = user_id AND ?2 >= start_date AND ?2 <= end_date)" +
            "OR (?3 = user_id AND ?1 <= start_date AND ?2 >= end_date)",
            nativeQuery = true)
    List<Reservation> findAllReservationBetweenDatesWithUserId(LocalDate start_date, LocalDate end_date, int user_id);
}

