package com.example.movie_backend.dto.comment;

import com.example.movie_backend.dto.movie.MovieDTO;
import com.example.movie_backend.entity.Movie;
import com.example.movie_backend.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class CommentDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private String content;

    private Long idUser;

    private Long idMovie;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String user;

    @JsonIgnore
    private MovieDTO movie;

}