package com.example.movie_backend.dto.moviepakage;

import com.example.movie_backend.entity.enumerate.MoviePackageType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class MoviePackageDTO {

    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    private Long id;

    private MoviePackageType type;
}
