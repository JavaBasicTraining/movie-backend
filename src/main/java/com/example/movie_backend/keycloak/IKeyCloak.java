package com.example.movie_backend.keycloak;


import org.keycloak.representations.AccessTokenResponse;

public interface IKeyCloak {

    AccessTokenResponse token(String username, String password);
}
