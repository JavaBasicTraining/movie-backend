package com.example.movie_backend.controller.request;

import com.example.movie_backend.dto.comment.CommentDTO;
import com.example.movie_backend.dto.episode.EpisodeDTO;
import com.example.movie_backend.dto.evaluation.EvaluationDTO;
import com.example.movie_backend.dto.genre.GenreDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
public class CreateMovieRequest {


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

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String videoUrl;

    private Set<Long> idGenre;

    private Set<Long> idComment;

    private Set<Long> idEvaluation;

    private Long idCategory;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Set<EpisodeDTO> episodes;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Set<GenreDTO> genres;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Set<EvaluationDTO> evaluations;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Set<CommentDTO> comments;

    private  MultipartFile filePoster;

    private  MultipartFile fileMovie;
}
