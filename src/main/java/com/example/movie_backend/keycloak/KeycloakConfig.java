package com.example.movie_backend.keycloak;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.OAuth2Constants;
import org.keycloak.adapters.springboot.KeycloakSpringBootProperties;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.authorization.client.AuthzClient;
import org.keycloak.authorization.client.Configuration;
import org.keycloak.representations.AccessTokenResponse;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.springframework.stereotype.Component;

@Component
public class KeycloakConfig implements IKeyCloak {
    private AuthzClient authzClient;
    private Keycloak keycloak;

    public void KeycloakService(KeycloakSpringBootProperties properties) {
        this.authzClient = AuthzClient.create(
                new Configuration(
                        properties.getAuthServerUrl(),
                        properties.getRealm(),
                        properties.getResource(),
                        properties.getCredentials(),
                        null
                )
        );
        this.keycloak = KeycloakBuilder.builder()
                .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                .realm(properties.getRealm())
                .serverUrl(properties.getAuthServerUrl())
                .clientId(properties.getResource())
                .clientSecret(properties.getCredentials().get("secret").toString())
                .build();
    }

    public KeycloakConfig() {
    }

    @Override
    public AccessTokenResponse token(String username, String password) {
        return authzClient.obtainAccessToken(username, password);
    }
    private UsersResource getUsersResource() {
        return keycloak.realm(
                authzClient.getConfiguration().getRealm()
        ).users();
    }

    private CredentialRepresentation buildPassword(String password) {
        CredentialRepresentation passwordCred = new CredentialRepresentation();
        passwordCred.setTemporary(false);
        passwordCred.setType(CredentialRepresentation.PASSWORD);
        passwordCred.setValue(password);
        return passwordCred;
    }
}