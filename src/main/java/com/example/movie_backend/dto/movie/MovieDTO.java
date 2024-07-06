package com.example.movie_backend.dto.movie;

import com.example.movie_backend.dto.category.CategoryDTO;
import com.example.movie_backend.dto.navbar.NavbarDTO;
import com.example.movie_backend.entity.Movie;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class MovieDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private String nameMovie;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String posterUrl;

    private String viTitle;

    private String enTitle;

    private String description;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private  String videoUrl;

    private Set<Long> idCategory;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Set<Long> idNavbar;



    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Set<CategoryDTO> categories;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Set<NavbarDTO> navbarDTOSet;

    public MovieDTO(Movie movie) {
        this.id = movie.getId();
    }
}
