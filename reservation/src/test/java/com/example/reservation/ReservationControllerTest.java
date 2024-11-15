package com.example.reservation;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.example.reservation.model.Reservation;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

// FOR LAUNCH TEST CREATE FALSE DATA IN BDD WITH DATA.SQL //
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ReservationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    public void testGetAllReservations() throws Exception {
        mockMvc.perform(get("/api/reservation"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].userId", is(1)))
                .andExpect(jsonPath("$[0].vehicleId", is(1)))
                .andExpect(jsonPath("$[0].startDate", is("2024-12-24")))
                .andExpect(jsonPath("$[0].endDate", is("2025-01-05")))
                .andExpect(jsonPath("$[0].kmToWish", is(500)))
                .andExpect(jsonPath("$[0].kmRealized", is(550)))
                .andExpect(jsonPath("$[0].totalPrice", is(700.0)))

                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].userId", is(2)))
                .andExpect(jsonPath("$[1].vehicleId", is(2)))
                .andExpect(jsonPath("$[1].startDate", is("2025-01-06")))
                .andExpect(jsonPath("$[1].endDate", is("2025-02-01")))
                .andExpect(jsonPath("$[1].kmToWish", is(1000)))
                .andExpect(jsonPath("$[1].kmRealized", is(1000)))
                .andExpect(jsonPath("$[1].totalPrice", is(900.0)));
    }

    @Test
    @Order(2)
    public void testGetReservationById() throws Exception {
        mockMvc.perform(get("/api/reservation/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id", is(1)))
                .andExpect(jsonPath("userId", is(1)))
                .andExpect(jsonPath("vehicleId", is(1)))
                .andExpect(jsonPath("startDate", is("2024-12-24")))
                .andExpect(jsonPath("endDate", is("2025-01-05")))
                .andExpect(jsonPath("kmToWish", is(500)))
                .andExpect(jsonPath("kmRealized", is(550)))
                .andExpect(jsonPath("totalPrice", is(700.0)));
    }

    @Test
    @Order(3)
    public void testPostReservation() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/reservation")
                    .content(asJsonString(new Reservation(4, 2, 2, "2050-10-10", "2050-10-10", 500, 80.0F)))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("id").exists());
    }

    @Test
    @Order(4)
    public void testGetReservationByUserId() throws Exception {
        mockMvc.perform(get("/api/reservation/user/{userId}", 2))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(2)))
                .andExpect(jsonPath("$[0].userId", is(2)))
                .andExpect(jsonPath("$[0].vehicleId", is(2)))
                .andExpect(jsonPath("$[0].startDate", is("2025-01-06")))
                .andExpect(jsonPath("$[0].endDate", is("2025-02-01")))
                .andExpect(jsonPath("$[0].kmToWish", is(1000)))
                .andExpect(jsonPath("$[0].kmRealized", is(1000)))
                .andExpect(jsonPath("$[0].totalPrice", is(900.0)))

                .andExpect(jsonPath("$[1].id", is(4)))
                .andExpect(jsonPath("$[1].userId", is(2)))
                .andExpect(jsonPath("$[1].vehicleId", is(2)))
                .andExpect(jsonPath("$[1].startDate", is("2050-10-10")))
                .andExpect(jsonPath("$[1].endDate", is("2050-10-10")))
                .andExpect(jsonPath("$[1].kmToWish", is(500)))
                .andExpect(jsonPath("$[1].kmRealized", is(0)))
                .andExpect(jsonPath("$[1].totalPrice", is(125.0)));
    }

    @Test
    @Order(5)
    public void testGetReservationByVehicleId() throws Exception {
        mockMvc.perform(get("/api/reservation/vehicle/{vehicleId}", 2))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(2)))
                .andExpect(jsonPath("$[0].userId", is(2)))
                .andExpect(jsonPath("$[0].vehicleId", is(2)))
                .andExpect(jsonPath("$[0].startDate", is("2025-01-06")))
                .andExpect(jsonPath("$[0].endDate", is("2025-02-01")))
                .andExpect(jsonPath("$[0].kmToWish", is(1000)))
                .andExpect(jsonPath("$[0].kmRealized", is(1000)))
                .andExpect(jsonPath("$[0].totalPrice", is(900.0)))

                .andExpect(jsonPath("$[1].id", is(4)))
                .andExpect(jsonPath("$[1].userId", is(2)))
                .andExpect(jsonPath("$[1].vehicleId", is(2)))
                .andExpect(jsonPath("$[1].startDate", is("2050-10-10")))
                .andExpect(jsonPath("$[1].endDate", is("2050-10-10")))
                .andExpect(jsonPath("$[1].kmToWish", is(500)))
                .andExpect(jsonPath("$[1].kmRealized", is(0)))
                .andExpect(jsonPath("$[1].totalPrice", is(125.0)));
    }

    @Test
    @Order(6)
    public void testPutReservation() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/api/reservation/{id}", 4)
                .content(asJsonString(new Reservation(4, 2, 2, "2060-10-10", "2060-10-10", 700, 80.0F)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Reservation updated"));
    }

    @Test
    @Order(7)
    public void testDeleteReservation() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/reservation/{id}", 4))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Reservation deleted"));
    }

    public static String asJsonString(final Object obj) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.findAndRegisterModules();
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
