package com.example.movie_backend.dto.movie;

import com.example.movie_backend.dto.category.CategoryDTO;
import com.example.movie_backend.dto.comment.CommentDTO;
import com.example.movie_backend.dto.evaluation.EvaluationDTO;
import com.example.movie_backend.dto.genre.GenreDTO;
import com.example.movie_backend.entity.Episode;
import com.example.movie_backend.entity.Movie;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class MovieDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private String nameMovie;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String posterUrl;

    private String viTitle;

    private String enTitle;

    private String description;

    private Long year ;

    private String country;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private  String videoUrl;

    private Set<Long> idGenre;

    private Set<Long> idComment;

    private Set<Long> idEvaluation;

    private Long idCategory;

    private Set<Long>  idEpisode;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Set<Episode> episodes;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Set<GenreDTO> genres;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private CategoryDTO category;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Set<EvaluationDTO> evaluations;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Set<CommentDTO> comments;




    public MovieDTO(Movie movie) {
        this.id = movie.getId();
    }
}
