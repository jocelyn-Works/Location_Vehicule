package com.example.reservation.service;

import com.example.reservation.controller.ReservationController;
import com.example.reservation.model.Reservation;
import com.example.reservation.repository.ReservationRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;



@SpringBootTest
class ReservationServiceImplTest {

   @Autowired
   private ReservationService reservationService;

   @Autowired
   private ReservationRepository reservationRepository;

   @Autowired
   private ReservationController reservationController;



   void setuo(){

        Reservation reservation = new Reservation();
        reservation.setId(1);
        reservation.setUserId(2);
        reservation.setVehicleId(3);
        reservation.setStartDate(LocalDate.of(2024, 1, 1).atStartOfDay());
        reservation.setEndDate(LocalDate.of(2024, 1, 2).atStartOfDay());
        reservation.setKmToWish(1000);
        reservation.setKmRealized(0);
        reservation.setTotalPrice(500.00F);

    }

    @Test
    void findUserById() {



    }
    @Test
    void create() {

    }

    @Test
    void calculateAge() {
    }


    @Test
    void findVehicleById() {
    }

    @Test
    void reservationMaintenanceUpdate() {
    }
}