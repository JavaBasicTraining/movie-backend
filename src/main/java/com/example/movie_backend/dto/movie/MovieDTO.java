package com.example.movie_backend.dto.movie;

import com.example.movie_backend.dto.category.CategoryDTO;
import com.example.movie_backend.dto.comment.CommentDTO;
import com.example.movie_backend.dto.episode.EpisodeDTO;
import com.example.movie_backend.dto.evaluation.EvaluationDTO;
import com.example.movie_backend.dto.genre.GenreDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MovieDTO {
    private Long id;

    private String nameMovie;

    private String posterUrl;

    private String viTitle;

    private String enTitle;

    private String description;

    private Long year;

    private String country;

    private String videoUrl;

    private String trailerUrl;

    private String path;

    private String videoPresignedUrl;
    private String posterPresignedUrl;
    private String trailerPresignedUrl;

    @Builder.Default
    @JsonIgnoreProperties(value = "movie", allowSetters = true)
    private List<EpisodeDTO> episodes = new ArrayList<>();

    @JsonIgnoreProperties(value = "movies", allowSetters = true)
    private List<GenreDTO> genres;

    @JsonIgnoreProperties(value = "movies", allowSetters = true)
    private CategoryDTO category;

    private List<EvaluationDTO> evaluations;

    @JsonIgnoreProperties(value = {"movie", "user"}, allowSetters = true)
    private List<CommentDTO> comments;
}
