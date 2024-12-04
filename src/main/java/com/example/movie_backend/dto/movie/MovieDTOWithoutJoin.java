package com.example.movie_backend.dto.movie;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Getter
@SuperBuilder
@NoArgsConstructor
@JsonIgnoreProperties({"categoryDTOSet", "ids"})
public class MovieDTOWithoutJoin extends MovieDTO {
    private Set<String> genreName;
}
