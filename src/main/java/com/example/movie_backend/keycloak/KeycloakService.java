package com.example.movie_backend.keycloak;

import com.example.movie_backend.model.user.RegisterRequest;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.OAuth2Constants;
import org.keycloak.adapters.springboot.KeycloakSpringBootProperties;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.authorization.client.AuthzClient;
import org.keycloak.authorization.client.Configuration;
import org.keycloak.representations.AccessTokenResponse;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.Response;
import java.util.UUID;

@Component
public class KeycloakService implements IKeycloakService {
    private final AuthzClient authzClient;
    private final Keycloak keycloak;

    public KeycloakService(KeycloakSpringBootProperties properties) {
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
            .resteasyClient(new ResteasyClientBuilder().connectionPoolSize(10).build())
            .build();
    }

    @Override
    public UUID register(RegisterRequest request) {
        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setUsername(request.getUsername());
        userRepresentation.setEmail(request.getEmail());
        userRepresentation.setFirstName(request.getFistName());
        userRepresentation.setLastName(request.getLastName());
        userRepresentation.setEnabled(true);

        UsersResource usersResource = getUsersResource();
        Response response = usersResource.create(userRepresentation);
        String userId = CreatedResponseUtil.getCreatedId(response);

        UserResource userResource = usersResource.get(userId);
        userResource.resetPassword(
            buildPassword(request.getPassword())
        );

        return UUID.fromString(userId);
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
