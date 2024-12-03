package com.example.movie_backend.dto.movie;

import com.example.movie_backend.dto.category.CategoryDTO;
import com.example.movie_backend.dto.comment.CommentDTO;
import com.example.movie_backend.dto.episode.EpisodeDTO;
import com.example.movie_backend.dto.evaluation.EvaluationDTO;
import com.example.movie_backend.dto.genre.GenreDTO;
import com.example.movie_backend.entity.Movie;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MovieDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private String nameMovie;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String posterUrl;

    private String viTitle;

    private String enTitle;

    private String description;

    private Long year;

    private String country;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY, required = false)
    private String videoUrl;

    private String videoPresignedUrl;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<Long> idGenre;

    private Set<Long> idComment;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<Long> idEvaluation;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long idCategory;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonIgnoreProperties("movie")
    private List<EpisodeDTO> episodes;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonIgnoreProperties(value = "movies", allowSetters = true)
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
