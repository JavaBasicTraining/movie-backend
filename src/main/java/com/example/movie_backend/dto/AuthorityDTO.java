package com.example.movie_backend.dto;

import com.example.movie_backend.entity.Authority;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@Component
@SuperBuilder(toBuilder = true)
@RequiredArgsConstructor
@Getter
@Setter
public class AuthorityDTO {
    @NotNull
    @Size(max = 50)
    @Id
    @Column(name = "name", length = 50, nullable = false)
    private String name;

}
