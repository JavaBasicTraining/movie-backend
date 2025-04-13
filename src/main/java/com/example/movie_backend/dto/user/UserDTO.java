package com.example.movie_backend.dto.user;

import com.example.movie_backend.dto.AuthorityDTO;
import com.example.movie_backend.entity.Authority;
import com.example.movie_backend.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String userName;
    private String firstName;
    private String lastName;
    private List<AuthorityDTO> authorities;

    public UserDTO(User user) {
        this.id = user.getId();
        this.userName = user.getUsername();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.authorities = user.getAuthorities().stream()
                .map(authority -> AuthorityDTO.builder()
                        .name(authority.getName())
                        .build())
                .collect(Collectors.toList());

    }
}
