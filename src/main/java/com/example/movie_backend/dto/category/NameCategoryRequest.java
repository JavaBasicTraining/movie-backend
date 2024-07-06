package com.example.movie_backend.dto.category;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder(toBuilder = true)
public class NameCategoryRequest {

    private String name;
}
