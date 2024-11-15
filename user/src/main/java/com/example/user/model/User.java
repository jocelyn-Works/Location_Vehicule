package com.example.user.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.sql.Date;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter @NoArgsConstructor
@Table(name = "user")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
  
    @NotBlank
    @Size(max=64)
    private String firstName;

    @NotBlank
    @Size(max=64)
    private String lastName;

    @NotBlank
    @Size(max = 50)
    @Email
    @Column(unique = true)
    private String email;

    @NotBlank
    @Size(max = 120)
    private String password;

    @Getter
    @Setter
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @NotNull
    private Date birthDate;

    @NotNull
    //@Size(min = 9, max = 9)
    private String permitCode;

    @NotNull
    private Date dateOfObtaining;


//    public User(int id, String firstName, String lastName,String email, String password, String code, int years, int months, int days) {
//        this.id = id;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.email = email;
//        this.password = password;
//        this.birthDate = new Date(years, months, days);
//        this.permitCode = code;
//    }


    public User(@NotBlank @Size(max = 64) String firstName, @NotBlank @Size(max = 64) String lastName, @NotBlank @Size(max = 50) @Email String email, @NotNull Date birthDate, @NotNull String permitCode,  @NotNull Date dateOfObtaining, String encode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = encode;
        this.birthDate = birthDate;
        this.permitCode = permitCode;
        this.dateOfObtaining = dateOfObtaining;
    }

 
}
