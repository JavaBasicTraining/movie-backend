package com.example.movie_backend.dto.user;

import com.example.movie_backend.entity.Authority;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Set;
@Getter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class UserDTO {
    private String userName;
    private List<String> authorities;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Set<Authority> authoritySet;
}
