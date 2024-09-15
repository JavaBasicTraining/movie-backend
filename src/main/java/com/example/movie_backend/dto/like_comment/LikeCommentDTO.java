package com.example.movie_backend.dto.like_comment;

import com.example.movie_backend.entity.Movie;
import com.example.movie_backend.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
@Setter
@Getter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class LikeCommentDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)

    private Long likeCount;

    private Long idUser;

    private Long idMovie;

    private Long idComment;


}
