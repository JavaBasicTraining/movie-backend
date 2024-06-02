package com.example.movie_backend.dto.movie;

import com.example.movie_backend.dto.category.CategoryDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    private Long id;

    private String name;

    private String posterUrl;

    private String viTitle;

    private String enTitle;

    private String description;

    private String videoMinioPath;

    private Set<Long> ids;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Set<CategoryDTO> categoryDTOSet;
}
