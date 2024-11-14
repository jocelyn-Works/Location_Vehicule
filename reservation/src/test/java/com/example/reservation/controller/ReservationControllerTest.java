package com.example.reservation.controller;


import com.example.reservation.model.Reservation;
import jakarta.persistence.Column;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
//@WebMvcTest(controllers = ReservationController.class)
class ReservationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReservationController reservationController;


    @Test
    public void getAllReservations() throws Exception {


//        this.mockMvc.perform(get("/reservation")).andDo(print())
//                .andExpect(status().isOk())
//                .andExpect( jsonPath("$[0].id", is(4)))
//                .andExpect( jsonPath("$[0].userId", is(10)))
//                .andExpect( jsonPath("$[0].vehicleId", is(20)))
//                .andExpect( jsonPath("$[0].startDate", is("2024-12-01 00:00:00.000000")))
//                .andExpect( jsonPath("$[0].endDate", is("2024-11-07 00:00:00.000000")))
//                .andExpect( jsonPath("$[0].kmToWish", is(0)))
//                .andExpect( jsonPath("$[0].kmRealized", is(0)))
//                .andExpect( jsonPath("$[0].totalPrice", is(200)));

    }
}