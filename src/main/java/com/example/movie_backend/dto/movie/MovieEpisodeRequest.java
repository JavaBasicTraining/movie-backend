package com.example.movie_backend.dto.movie;
import com.example.movie_backend.dto.episode.EpisodeDTO;

import com.example.movie_backend.entity.Episode;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class MovieEpisodeRequest {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long movieIdPk;

    private String nameMovie;

    private String viTitle;

    private String enTitle;

    private String description;

    private Long year ;

    private String country;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<Long> idGenre;

    private Set<Long> idComment;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<Long> idEvaluation;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long idCategory;



    private Set<EpisodeDTO> episodesDTO= new HashSet<>();

//
//    // Episode
//
//    private Long episodeCount;
//
//    private String descriptions;
//
//    private Long movieId;

    @JsonIgnore
    private MovieDTO movieDTO;
}
