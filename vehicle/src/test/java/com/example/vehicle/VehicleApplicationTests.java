package com.example.vehicle;

import com.example.vehicle.model.Vehicle;
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

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// FOR LAUNCH TEST CREATE FALSE DATA IN BDD WITH DATA.SQL //
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class VehicleApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    public void testGetAllVehicles() throws Exception {
        mockMvc.perform(get("/api/vehicle"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].vehicleRegistration", is("EF-744-LH")))
                .andExpect(jsonPath("$[0].vehicleType", is("utilitaire")))
                .andExpect(jsonPath("$[0].brand", is("Ford")))
                .andExpect(jsonPath("$[0].model", is("Focus")))
                .andExpect(jsonPath("$[0].color", is("Red")))
                .andExpect(jsonPath("$[0].reservationPrice", is(60.0)))
                .andExpect(jsonPath("$[0].kmPrice", is(0.2)))
                .andExpect(jsonPath("$[0].taxHorse", is(6.5)))
                .andExpect(jsonPath("$[0].traveledKm", is(20000)))
                .andExpect(jsonPath("$[0].cylinder", is(0)))
                .andExpect(jsonPath("$[0].volume", is(4)))

                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].vehicleRegistration", is("EG-754-KH")))
                .andExpect(jsonPath("$[1].vehicleType", is("voiture")))
                .andExpect(jsonPath("$[1].brand", is("Nissan")))
                .andExpect(jsonPath("$[1].model", is("NissanVoiture")))
                .andExpect(jsonPath("$[1].color", is("Black")))
                .andExpect(jsonPath("$[1].reservationPrice", is(50.0)))
                .andExpect(jsonPath("$[1].kmPrice", is(0.15)))
                .andExpect(jsonPath("$[1].taxHorse", is(7.5)))
                .andExpect(jsonPath("$[1].traveledKm", is(30000)))
                .andExpect(jsonPath("$[1].cylinder", is(0)))
                .andExpect(jsonPath("$[1].volume", is(0)));
    }

    @Test
    @Order(2)
    public void testGetVehicleById() throws Exception {
        mockMvc.perform(get("/api/vehicle/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id", is(1)))
                .andExpect(jsonPath("vehicleRegistration", is("EF-744-LH")))
                .andExpect(jsonPath("vehicleType", is("utilitaire")))
                .andExpect(jsonPath("brand", is("Ford")))
                .andExpect(jsonPath("model", is("Focus")))
                .andExpect(jsonPath("color", is("Red")))
                .andExpect(jsonPath("reservationPrice", is(60.0)))
                .andExpect(jsonPath("kmPrice", is(0.2)))
                .andExpect(jsonPath("taxHorse", is(6.5)))
                .andExpect(jsonPath("traveledKm", is(20000)))
                .andExpect(jsonPath("cylinder", is(0)))
                .andExpect(jsonPath("volume", is(4)));
    }

    @Test
    @Order(3)
    public void testPostVehicle() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/vehicle")
                    .content(asJsonString(new Vehicle(4, "AH-394-AH", "voiture", "Toyota", "Toyoyoyo", "Pink",45.0f, 0.3F, 6.5F, 17000, 0, 0)))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("id").exists());
    }

    @Test
    @Order(4)
    public void testPutVehicle() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/api/vehicle/{id}", 4)
                    .content(asJsonString(new Vehicle(4, "AH-394-AH", "voiture", "Toyota", "Toyoyoyo", "Pink",45.0f, 0.3F, 6.5F, 23000, 0, 0)))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("traveledKm", is(23000)));
    }

    @Test
    @Order(5)
    public void testDeleteVehicle() throws Exception {
        mockMvc.perform(delete("/api/vehicle/{id}", 4))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Vehicle deleted"));

    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
