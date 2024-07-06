package com.example.movie_backend.dto.user;

import com.example.movie_backend.entity.Authority;
import com.example.movie_backend.entity.User;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserMapper {
    public User toEntity(UserDTO dto) {
        return User.builder()
                .username(dto.getUserName())
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
                        .userName(entity.getUsername())
                        .authoritySet(entity.getAuthorities().stream()
                                .map(nameAuthority -> Authority.builder()
                                .name(nameAuthority.getName())
                                .build()).collect(Collectors.toSet()))
                        .build();
    }
}
