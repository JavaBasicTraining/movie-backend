package com.example.movie_backend.model.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class RegisterRequest {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String fistName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String email;
    @NotBlank
    private String phoneNumber;

    @Builder.Default
    private List<String> authorities = new ArrayList<>();
}
