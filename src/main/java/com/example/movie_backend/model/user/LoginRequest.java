package com.example.movie_backend.model.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    private String username;
    private String password;
    private boolean rememberMe;
}
