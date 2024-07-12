package com.example.movie_backend.dto.episode;

import com.example.movie_backend.entity.Movie;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class EpisodeDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private Integer episodeCount;

    private String posterUrl;

    private String videoUrl;

    private Long movieId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Movie movie;
}
