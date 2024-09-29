package com.example.movie_backend.keycloack;


import com.example.movie_backend.model.user.RegisterRequest;
import org.keycloak.representations.AccessTokenResponse;

import java.util.UUID;

import java.util.UUID;

public interface IKeyCloak {

    AccessTokenResponse token(String username, String password);
}
