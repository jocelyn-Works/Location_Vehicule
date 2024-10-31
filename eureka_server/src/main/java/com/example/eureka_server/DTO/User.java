//package com.example.eureka_server.DTO;
//
//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.NotNull;
//import jakarta.validation.constraints.Size;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import java.sql.Date;
//import java.io.Serializable;
//
//@Getter @Setter @NoArgsConstructor
//public class User {
//    private int id;
//
//    @NotBlank
//    private String firstName;
//
//    @NotBlank
//    private String lastName;
//
//    @NotNull
//    private Date birthDate;
//
//    @NotNull
//    private String permitCode;
//
//    @NotNull
//    private Date dateOfObtaining;
//
//
//    public User(int id, String firstName, String lastName, String code, int years, int months, int days) {
//        this.id = id;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.birthDate = new Date(years, months, days);
//        this.permitCode = code;
//    }
//}
