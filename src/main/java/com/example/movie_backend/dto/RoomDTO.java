package com.example.movie_backend.dto;

import com.example.movie_backend.dto.user.UserDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class RoomDTO implements Serializable {
    private UUID id;

    @NotBlank
    private String name;

    @NotNull
    private UserDTO host;

    private String password;

    private List<UserDTO> participants;
}
