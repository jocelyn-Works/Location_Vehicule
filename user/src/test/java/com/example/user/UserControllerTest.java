package com.example.user;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.user.model.User;
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

import java.sql.Date;


// FOR LAUNCH TEST CREATE FALSE DATA IN BDD WITH DATA.SQL //
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    public void testGetUsers() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].firstName", is("Shima")))
                .andExpect(jsonPath("$[0].lastName", is("Hiro")))
                .andExpect(jsonPath("$[0].birthDate", is("1999-11-28")))
                .andExpect(jsonPath("$[0].permitCode", is("des1235fj")))
                .andExpect(jsonPath("$[0].dateOfObtaining", is("2021-11-28")))

                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].firstName", is("Zaki")))
                .andExpect(jsonPath("$[1].lastName", is("Naga")))
                .andExpect(jsonPath("$[1].birthDate", is("1945-03-09")))
                .andExpect(jsonPath("$[1].permitCode", is("hytf21sct5")))
                .andExpect(jsonPath("$[1].dateOfObtaining", is("1947-03-09")));
    }

    @Test
    @Order(2)
    public void testGetUser() throws Exception {
        mockMvc.perform(get("/user/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id", is(1)))
                .andExpect(jsonPath("firstName", is("Shima")))
                .andExpect(jsonPath("lastName", is("Hiro")))
                .andExpect(jsonPath("birthDate", is("1999-11-28")))
                .andExpect(jsonPath("permitCode", is("des1235fj")))
                .andExpect(jsonPath("dateOfObtaining", is("2021-11-28")));
    }

    @Test
    @Order(3)
    public void testPostUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/user")
                    .content(asJsonString(new User(4, "Robert", "DeniRoo", new Date(1938), "ab5ste6775", new Date(2000))))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("id").exists());
    }

    @Test
    @Order(4)
    public void testPutUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/user/{id}", 4)
                        .content(asJsonString(new User(4, "Robert", "DeniRoo", new Date(1988), "ab5ste6775", new Date(2003))))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id", is(4)))
                .andExpect(MockMvcResultMatchers.jsonPath("firstName", is("Robert")))
                .andExpect(MockMvcResultMatchers.jsonPath("lastName", is("DeniRoo")))
                .andExpect(MockMvcResultMatchers.jsonPath("birthDate", is("1970-01-01")))
                .andExpect(MockMvcResultMatchers.jsonPath("permitCode", is("ab5ste6775")))
                .andExpect(MockMvcResultMatchers.jsonPath("dateOfObtaining", is("1970-01-01")));
    }

    @Test
    @Order(5)
    public void testDeleteUser() throws Exception {
        mockMvc.perform(delete("/user/{id}", 4))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("User deleted"));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
