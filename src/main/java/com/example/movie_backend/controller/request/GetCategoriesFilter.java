package com.example.movie_backend.controller.request;

import com.example.movie_backend.dto.category.CategoryDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetCategoriesFilter {
    private String searchTerm;
    private List<Long> excludeIds;
}
