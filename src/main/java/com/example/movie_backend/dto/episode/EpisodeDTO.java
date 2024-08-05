package com.example.movie_backend.dto.episode;

import com.example.movie_backend.dto.movie.MovieDTO;
import com.example.movie_backend.entity.Episode;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL) // cái này nó sẽ bỏ hết những field null
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

    private MovieDTO movie;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Set<Episode> episodes;

    private String tempId;
}
