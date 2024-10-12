//package com.example.movie_backend.keycloak;
//import lombok.Getter;
//import org.keycloak.admin.client.Keycloak;
//import org.keycloak.admin.client.KeycloakBuilder;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//import org.keycloak.representations.idm.UserRepresentation;
//
//import java.util.List;
//
//@Service
//@Getter
//public class KeycloakService {
//
//    @Value("${keycloak.auth-server-url}")
//    private String authServerUrl;
//
//    @Value("${keycloak.realm}")
//    private String realm;
//
//    @Value("${keycloak.client-id}")
//    private String clientId;
//
//    @Value("${keycloak.client-secret}")
//    private String clientSecret;
//
//    private final Keycloak keycloak;
//
//    public KeycloakService() {
//
//        this.keycloak = KeycloakBuilder.builder()
//                .serverUrl(authServerUrl)
//                .realm(realm)
//                .clientId(clientId)
//                .clientSecret(clientSecret)
//                .grantType("client_credentials")
//                .build();
//    }
//
//    public List<UserRepresentation> getUsers() {
//        return keycloak.realm(realm).users().list();
//    }
//}
