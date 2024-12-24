package com.example.movie_backend.mapper;

import com.example.movie_backend.dto.category.CategoryDTO;
import com.example.movie_backend.dto.genre.GenreDTO;
import com.example.movie_backend.dto.movie.MovieDTO;
import com.example.movie_backend.dto.movie.MovieDTOWithoutJoin;
import com.example.movie_backend.dto.movie.MovieEpisodeRequest;
import com.example.movie_backend.entity.Category;
import com.example.movie_backend.entity.Genre;
import com.example.movie_backend.entity.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

@Mapper(componentModel = "spring")
public interface MovieMapper {
    MovieMapper INSTANCE = Mappers.getMapper(MovieMapper.class);

    @Mapping(target = "comments", ignore = true)
    @Mapping(target = "episodes", ignore = true)
    @Mapping(target = "evaluations", ignore = true)
    @Mapping(target = "genres", source = "genres", qualifiedByName = "genresWithoutMovies")
    @Mapping(target = "category", source = "category", qualifiedByName = "categoryWithoutMovies")
    MovieDTO toDTO(Movie entity);

    @Mapping(target = "comments", ignore = true)
    @Mapping(target = "evaluations", ignore = true)
    Movie toEntity(MovieDTO dto);

    Movie toEntity(MovieEpisodeRequest movie);

    @Mapping(target = "comments", ignore = true)
    @Mapping(target = "episodes", ignore = true)
    @Mapping(target = "genres", ignore = true)
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "evaluations", ignore = true)
    @Mapping(target = "id", ignore = true)
    Movie toEntity(MovieDTO request, @MappingTarget Movie movie);

    @Mapping(target = "comments", ignore = true)
    @Mapping(target = "episodes", ignore = true)
    @Mapping(target = "evaluations", ignore = true)
    @Mapping(target = "genres", source = "genres", qualifiedByName = "genresWithoutMovies")
    @Mapping(target = "category", source = "category", qualifiedByName = "categoryWithoutMovies")
    MovieDTOWithoutJoin toDTOWithoutJoin(Movie item);

    @Named("genresWithoutMovies")
    default List<GenreDTO> mapGenresWithoutMovies(Set<Genre> genres) {
        if (genres == null) {
            return Collections.emptyList();
        }
        Function<Genre, GenreDTO> mapFunc = genre -> GenreDTO.builder()
                .id(genre.getId())
                .name(genre.getName())
                .build();
        return genres.stream()
                .map(mapFunc)
                .toList();
    }

    @Named("categoryWithoutMovies")
    default CategoryDTO mapCategoryWithoutMovies(Category category) {
        if (category == null) {
            return null;
        }
        return CategoryDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}
