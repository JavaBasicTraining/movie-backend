package com.example.movie_backend.dto.category;

import com.example.movie_backend.entity.Category;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.checkerframework.common.reflection.qual.GetClass;

import javax.persistence.Column;
import java.util.UUID;
@Getter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class CategoryDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    private String name;

}
