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
import java.io.Serializable;

@Entity
@Getter @Setter @NoArgsConstructor
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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


    public User(int id, String firstName, String lastName, String code, int years, int months, int days) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = new Date(years, months, days);
        this.permitCode = code;
    }
}
