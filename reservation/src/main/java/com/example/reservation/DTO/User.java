package com.example.reservation.DTO;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

import java.sql.Date;

@Getter @Setter @NoArgsConstructor
public class User {
    private int id;

    @NotNull
    private Date birthDate;

    @NotNull
    private Date dateOfObtaining;
}
