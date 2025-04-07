package com.example.movie_backend.config.common;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CustomAuthenticationToken extends JwtAuthenticationToken {
    public CustomAuthenticationToken(Jwt jwt, KeycloakProperties keycloakProperties) {
        super(jwt, extractAuthorities(jwt, keycloakProperties));
    }

    @SuppressWarnings("unchecked")
    private static Collection<GrantedAuthority> extractAuthorities(Jwt jwt, KeycloakProperties keycloakProperties) {
        Map<String, Object> resourceAccess = jwt.getClaim("resource_access");
        Map<String, Object> clientAccess = (Map<String, Object>) resourceAccess.get(keycloakProperties.getResource());
        List<String>  roles = (List<String>) clientAccess.get("roles");
        return roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
