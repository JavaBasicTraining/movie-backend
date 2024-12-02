package com.example.movie_backend.controller.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetCategoriesFilter {
    private String searchTerm;
    private List<Long> excludeIds;
}
