package com.example.movie_backend.config.web_socket;

import com.example.movie_backend.config.CommonProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Configuration
@EnableWebSocketMessageBroker
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    private final CommonProperties commonProperties;

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        List<String> origins = Optional.ofNullable(commonProperties.getCors())
                .map(CommonProperties.Cors::getOrigins)
                .orElse(new ArrayList<>());

        registry.addEndpoint("/ws")
                .setAllowedOrigins(origins.toArray(new String[0]))
                .withSockJS();
    }
}
