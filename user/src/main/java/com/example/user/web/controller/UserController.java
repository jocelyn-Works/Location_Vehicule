package com.example.user.web.controller;

import com.example.user.dao.UserRepository;
import com.example.user.model.User;
import com.example.user.web.controller.Exception.CodeNotValidException;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;


@RestController
@Validated
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    @Operation(summary  = "Select all user", description = "This method select all user")
    public List<User> getUsersList() {
        return userRepository.findAll();
    }

    @GetMapping(value ="/user/{id}")
    @Operation(summary  = "Select one user", description = "This method select one user")
    public User getUser(@PathVariable int id) {
        return userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @PostMapping(value = "/user")
    @Operation(summary  = "Create user", description = "This method creates a new user")
    public ResponseEntity<User> addUser(@RequestBody @Valid User user) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:9192/licenses/{u.getCode()}";
        ResponseEntity<Boolean> response = restTemplate.getForEntity(url, Boolean.class, user.getPermitCode());
        if (Boolean.TRUE.equals(response.getBody())){
            return ResponseEntity.ok(userRepository.save(user));
        }else {
            throw new CodeNotValidException("Code not valid");
        }
    }

    @PutMapping(value = "/user/{id}")
    @Operation(summary  = "Update one user", description = "This method update one user")
    public Optional<ResponseEntity<User>> updateUser(@RequestBody @Valid User newUser, @PathVariable int id) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:9192/licenses/{u.getCode()}";
        ResponseEntity<Boolean> response = restTemplate.getForEntity(url, Boolean.class, newUser.getPermitCode());
        if (Boolean.TRUE.equals(response.getBody())) {
            return Optional.of(userRepository.findById(id)
                    .map(user -> {
                        user.setBirthDate(newUser.getBirthDate());
                        user.setPermitCode(newUser.getPermitCode()) ;
                        user.setFirstName(newUser.getFirstName());
                        user.setLastName(newUser.getLastName());
                        return ResponseEntity.ok(userRepository.save(user));
                    }).orElseGet(() -> ResponseEntity.ok(userRepository.save(newUser))));

        }else {
            throw new CodeNotValidException("Code not valid");
        }
    }

    @DeleteMapping(value = "/user/{id}")
    @Operation(summary  = "Delete one user", description = "This method delete one user")
    public String deleteUser(@PathVariable int id) {
        userRepository.deleteById(id);
        return "User deleted";
    }
}
