package com.example.movie_backend.dto.comment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
@Getter
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class CommentDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private  Long id;

    private String content;

    private String episodeId;

    private Integer userId;
}