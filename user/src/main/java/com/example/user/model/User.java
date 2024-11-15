package com.example.user.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.sql.Date;

@Entity
@Getter @Setter @NoArgsConstructor
public class User {
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotBlank
    @Size(max=64)
    private String firstName;

    @NotBlank
    @Size(max=64)
    private String lastName;

    @NotNull
    private Date birthDate;

    @NotNull
    //@Size(min = 9, max = 9)
    private String permitCode;

    @NotNull
    private Date dateOfObtaining;

    public User(int id, String firstName, String lastName, Date birthDate, String permitCode, Date dateOfObtaining) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.permitCode = permitCode;
        this.dateOfObtaining = dateOfObtaining;
    }

    public User(String firstName, String lastName, Date birthDate, String permitCode, Date dateOfObtaining) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.permitCode = permitCode;
        this.dateOfObtaining = dateOfObtaining;
    }
}
