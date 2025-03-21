package com.example.movie_backend.config.websokcet_comment;

import com.example.movie_backend.config.common.KeycloakProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.List;
import java.util.Map;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    private final JwtDecoder jwtDecoder;
    private final KeycloakProperties keycloakProperties;
    public WebSocketConfig(JwtDecoder jwtDecoder, KeycloakProperties keycloakProperties) {
        this.jwtDecoder = jwtDecoder;
        this.keycloakProperties = keycloakProperties;
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws")
                .setAllowedOrigins(
                        "http://localhost:8081",
                        "http://localhost:3000",
                        "http://192.168.1.120:3000",
                        "http://192.168.1.120:8081")
                .addInterceptors(new com.example.web_socket.config.WebSocketAuthInterceptor(jwtDecoder, keycloakProperties))
                .withSockJS();
    }
}
