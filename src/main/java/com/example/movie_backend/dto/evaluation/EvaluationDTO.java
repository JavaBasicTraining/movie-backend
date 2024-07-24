package com.example.movie_backend.dto.evaluation;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Size;

@Setter
@Getter
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class EvaluationDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @Size(max = 5)
    private Long star;

    private Long userId;

    private Long movieId;
}
