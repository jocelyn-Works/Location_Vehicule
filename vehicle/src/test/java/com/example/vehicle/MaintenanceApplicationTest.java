package com.example.vehicle;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.vehicle.model.Maintenance;
import com.example.vehicle.model.Vehicle;
import com.example.vehicle.repository.VehicleRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
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
import java.util.Optional;

// FOR LAUNCH TEST CREATE FALSE DATA IN BDD WITH DATA.SQL //
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MaintenanceApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    public void testGetMaintenance() throws Exception {
        mockMvc.perform(get("/api/maintenance"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].vehicle.id", is(1)))
                .andExpect(jsonPath("$[0].vehicle.vehicleRegistration", is("EF-744-LH")))
                .andExpect(jsonPath("$[0].vehicle.vehicleType", is("utilitaire")))
                .andExpect(jsonPath("$[0].vehicle.brand", is("Ford")))
                .andExpect(jsonPath("$[0].vehicle.model", is("Focus")))
                .andExpect(jsonPath("$[0].vehicle.color", is("Red")))
                .andExpect(jsonPath("$[0].vehicle.reservationPrice", is(60.0)))
                .andExpect(jsonPath("$[0].vehicle.kmPrice", is(0.2)))
                .andExpect(jsonPath("$[0].vehicle.taxHorse", is(6.5)))
                .andExpect(jsonPath("$[0].vehicle.traveledKm", is(20000)))
                .andExpect(jsonPath("$[0].vehicle.cylinder", is(0)))
                .andExpect(jsonPath("$[0].vehicle.volume", is(4)))
                .andExpect(jsonPath("$[0].kmVehicle", is(20000)))
                .andExpect(jsonPath("$[0].notification", is("les suspensions doivent etre changer + 2 ans")))
                .andExpect(jsonPath("$[0].startMaintenance", is("2024-07-20")))
                .andExpect(jsonPath("$[0].endMaintenance", is("2024-07-23")))
                .andExpect(jsonPath("$[0].realized", is(true)))

                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].vehicle.id", is(2)))
                .andExpect(jsonPath("$[1].vehicle.vehicleRegistration", is("EG-754-KH")))
                .andExpect(jsonPath("$[1].vehicle.vehicleType", is("voiture")))
                .andExpect(jsonPath("$[1].vehicle.brand", is("Nissan")))
                .andExpect(jsonPath("$[1].vehicle.model", is("NissanVoiture")))
                .andExpect(jsonPath("$[1].vehicle.color", is("Black")))
                .andExpect(jsonPath("$[1].vehicle.reservationPrice", is(50.0)))
                .andExpect(jsonPath("$[1].vehicle.kmPrice", is(0.15)))
                .andExpect(jsonPath("$[1].vehicle.taxHorse", is(7.5)))
                .andExpect(jsonPath("$[1].vehicle.traveledKm", is(30000)))
                .andExpect(jsonPath("$[1].vehicle.cylinder", is(0)))
                .andExpect(jsonPath("$[1].vehicle.volume", is(0)))
                .andExpect(jsonPath("$[1].kmVehicle", is(30000)))
                .andExpect(jsonPath("$[1].notification", is("les pneus doit etre changer + 1 ans")))
                .andExpect(jsonPath("$[1].startMaintenance", is("2024-06-18")))
                .andExpect(jsonPath("$[1].endMaintenance", is("2024-06-19")))
                .andExpect(jsonPath("$[1].realized", is(true)));
    }

    @Test
    @Order(2)
    public void testGetMaintenanceById() throws Exception {
        mockMvc.perform(get("/api/maintenance/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.vehicle.id", is(1)))
                .andExpect(jsonPath("$.vehicle.vehicleRegistration", is("EF-744-LH")))
                .andExpect(jsonPath("$.vehicle.vehicleType", is("utilitaire")))
                .andExpect(jsonPath("$.vehicle.brand", is("Ford")))
                .andExpect(jsonPath("$.vehicle.model", is("Focus")))
                .andExpect(jsonPath("$.vehicle.color", is("Red")))
                .andExpect(jsonPath("$.vehicle.reservationPrice", is(60.0)))
                .andExpect(jsonPath("$.vehicle.kmPrice", is(0.2)))
                .andExpect(jsonPath("$.vehicle.taxHorse", is(6.5)))
                .andExpect(jsonPath("$.vehicle.traveledKm", is(20000)))
                .andExpect(jsonPath("$.vehicle.cylinder", is(0)))
                .andExpect(jsonPath("$.vehicle.volume", is(4)))
                .andExpect(jsonPath("$.kmVehicle", is(20000)))
                .andExpect(jsonPath("$.notification", is("les suspensions doivent etre changer + 2 ans")))
                .andExpect(jsonPath("$.startMaintenance", is("2024-07-20")))
                .andExpect(jsonPath("$.endMaintenance", is("2024-07-23")))
                .andExpect(jsonPath("$.realized", is(true)));
    }

    @Test
    @Order(3)
    public void testPostMaintenance() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/maintenance/{id_vehicle}", 2)
                    .content(asJsonString(new Maintenance(4, "Maintenance de test")))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("id").exists());
    }

    @Test
    @Order(4)
    public void testPutMaintenance() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/api/maintenance/{id}", 4)
                    .content(asJsonString(new Maintenance(4, "Maintenance de test MODIFIER", new Vehicle(2, "EG-754-KH", "voiture", "Nissan", "NissanVoiture", "Black",50.0f, 0.15F, 7.5F, 30000, 0, 0))))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("id", is(4)))
                .andExpect(MockMvcResultMatchers.jsonPath("notification", is("Maintenance de test MODIFIER")));
    }

    @Test
    @Order(5)
    public void testDeleteMaintenance() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/maintenance/{id}", 4))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("maintenance deleted"));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
