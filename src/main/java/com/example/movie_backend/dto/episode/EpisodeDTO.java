package com.example.movie_backend.dto.episode;

import com.example.movie_backend.dto.movie.MovieDTO;
import com.example.movie_backend.entity.Episode;
import com.example.movie_backend.entity.Movie;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class EpisodeDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private Long episodeCount;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String posterUrl;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String videoUrl;

    private String descriptions;

    private Long movieId;

    @JsonIgnore
    private MovieDTO movieDTO;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Set<Episode> episodes;
}
