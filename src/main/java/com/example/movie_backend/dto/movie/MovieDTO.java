package com.example.movie_backend.dto.movie;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class MovieDTO {

    @JsonProperty (access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private String name;

    private String posterUrl;

    private String viTitle;

    private String enTitle;

    private String description;

    private String moviePackageId;


}
