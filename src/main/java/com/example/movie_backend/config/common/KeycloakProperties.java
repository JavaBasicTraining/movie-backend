package com.example.movie_backend.config.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "keycloak")
@Getter
@Setter
@NoArgsConstructor
public class KeycloakProperties {
    @JsonProperty("auth-server-url")
    private String authServerUrl;

    @JsonProperty("realm")
    private String realm;

    @JsonProperty("resource")
    private String resource;

    @JsonProperty("credentials")
    private Credentials credentials;

    @Getter
    @Setter
    public static class Credentials {
        private String secret;
        public Credentials(String secret) {
            this.secret = secret;
        }
    }
}
