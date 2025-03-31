package com.example.movie_backend.dto.movie;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@JsonIgnoreProperties({"categoryDTOSet", "ids"})
public class MovieDTOWithoutJoin extends MovieDTO {
    private Set<String> genreName;
}
