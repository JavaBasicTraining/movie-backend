package com.example.movie_backend.model.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class RegisterRequest {
    private String username;
    private String password;
    private String fistName;
    private String lastName;
    private String email;
    private String phoneNumber;

}
