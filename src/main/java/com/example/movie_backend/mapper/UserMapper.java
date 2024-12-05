package com.example.movie_backend.mapper;

import com.example.movie_backend.dto.user.UserDTO;
import com.example.movie_backend.entity.Authority;
import com.example.movie_backend.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserMapper {
    public User toEntity(UserDTO dto) {
        return User.builder()
            .username(dto.getUsername())
            .authorities(
                dto.getAuthorities().stream()
                    .map(nameAuthority -> Authority.builder().name(nameAuthority)
                        .build()
                    )
                    .collect(Collectors.toSet())
            )
            .build();
    }

    public UserDTO toDTO(User entity) {
        return UserDTO.builder()
            .id(entity.getId())
            .username(entity.getUsername())
            .authorities(
                entity.getAuthorities()
                    .stream()
                    .map(Authority::getName)
                    .toList())
            .build();
    }

    public UserDTO toDTO(User entity, JwtAuthenticationToken token) {
        return UserDTO.builder()
            .id(entity.getId())
            .username(entity.getUsername())
            .authoritySet(entity.getAuthorities().stream()
                .map(nameAuthority -> Authority.builder()
                    .name(nameAuthority.getName())
                    .build()).collect(Collectors.toSet()))
            .authorities(
                token.getAuthorities()
                    .stream()
                    .map(GrantedAuthority::getAuthority)
                    .toList()
            )

            .build();
    }
}
