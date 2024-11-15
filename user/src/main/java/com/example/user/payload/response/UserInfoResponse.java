package com.example.user.payload.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class UserInfoResponse {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private List<String> roles;

    public UserInfoResponse(Long id,String firstName, String lastName, String email, List<String> roles) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.roles = roles;
    }


}
