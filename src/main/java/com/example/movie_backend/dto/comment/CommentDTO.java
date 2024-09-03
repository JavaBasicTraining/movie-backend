package com.example.movie_backend.dto.comment;

import com.example.movie_backend.entity.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import java.util.Set;
@Setter
@Getter
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class CommentDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private  Long id;

    private String content;

    private Long idUser;

    private  Set<Long> idMovies;
    private String user;

}