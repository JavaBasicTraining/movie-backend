package com.example.movie_backend.mapper;

import com.example.movie_backend.controller.dto.request.CreateMovieRequest;
import com.example.movie_backend.dto.category.CategoryDTO;
import com.example.movie_backend.dto.episode.EpisodeDTO;
import com.example.movie_backend.dto.genre.GenreDTO;
import com.example.movie_backend.dto.movie.MovieDTO;
import com.example.movie_backend.dto.movie.MovieDTOWithoutJoin;
import com.example.movie_backend.entity.Category;
import com.example.movie_backend.entity.Episode;
import com.example.movie_backend.entity.Genre;
import com.example.movie_backend.entity.Movie;
import org.mapstruct.*;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface MovieMapper {

    @Mapping(target = "videoPresignedUrl", ignore = true)
    @Mapping(target = "trailerPresignedUrl", ignore = true)
    @Mapping(target = "posterPresignedUrl", ignore = true)
    @Mapping(target = "comments", ignore = true)
    @Mapping(target = "evaluations", ignore = true)
    @Mapping(target = "episodes", source = "episodes", qualifiedByName = "episodesWithoutJoin")
    @Mapping(target = "genres", source = "genres", qualifiedByName = "genresWithoutMovies")
    @Mapping(target = "category", source = "category", qualifiedByName = "categoryDTOWithoutMovies")
    MovieDTO toDTO(Movie entity);

    @Mapping(target = "category", source = "categoryId", qualifiedByName = "categoryFromId")
    @Mapping(target = "genres", source = "genreIds", qualifiedByName = "genresFromIds")
    MovieDTO toDTO(CreateMovieRequest request);

    @Mapping(target = "genreName", ignore = true)
    @Mapping(target = "comments", ignore = true)
    @Mapping(target = "episodes", ignore = true)
    @Mapping(target = "evaluations", ignore = true)
    @Mapping(target = "genres", source = "genres", qualifiedByName = "genresWithoutMovies")
    @Mapping(target = "category", source = "category", qualifiedByName = "categoryDTOWithoutMovies")
    MovieDTOWithoutJoin toDTOWithoutJoin(Movie item);

    @Named("genresWithoutMovies")
    List<GenreDTO> mapGenresWithoutMovies(Set<Genre> genres);

    @Named("categoryDTOWithoutMovies")
    @Mapping(target = "movies", ignore = true)
    CategoryDTO mapCategoryDTOWithoutMovies(Category category);

    @Named("episodesWithoutJoin")
    @IterableMapping(qualifiedByName = "episodeDTOWithoutJoin")
    List<EpisodeDTO> mapEpisodesWithoutJoin(Set<Episode> episodes);

    @Mapping(target = "movieId", source = "movie.id")
    @Named("episodeDTOWithoutJoin")
    @Mapping(target = "movie", ignore = true)
    @Mapping(target = "videoPresignedUrl", ignore = true)
    @Mapping(target = "posterPresignedUrl", ignore = true)
    EpisodeDTO mapEpisodeDTOWithoutJoin(Episode episode);

    @Named("categoryFromId")
    default CategoryDTO mapCategoryFromId(Long categoryId) {
        CategoryDTO category = new CategoryDTO();
        category.setId(categoryId);
        return category;
    }

    @Named("genresFromIds")
    @IterableMapping(qualifiedByName = "genreFromId")
    List<GenreDTO> mapGenresFromIds(List<Long> genresIds);

    @Named("genreFromId")
    default GenreDTO mapGenreFromId(Long genreId) {
        GenreDTO genre = new GenreDTO();
        genre.setId(genreId);
        return genre;
    }

    // Entity Mapping

    @Mapping(target = "posterUrl", ignore = true)
    @Mapping(target = "videoUrl", ignore = true)
    @Mapping(target = "trailerUrl", ignore = true)
    @Mapping(target = "comments", ignore = true)
    @Mapping(target = "evaluations", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "episodes", expression = "java(episodeDTOListToEpisodeSet(request.getEpisodes()))")
    Movie toEntity(MovieDTO request);

    @Mapping(target = "posterUrl", ignore = true)
    @Mapping(target = "videoUrl", ignore = true)
    @Mapping(target = "trailerUrl", ignore = true)
    @Mapping(target = "comments", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "path", ignore = true)
    @Mapping(target = "episodes", expression = "java(episodeDTOListToEpisodeSet(request.getEpisodes()))")
    void toEntityForUpdate(MovieDTO request, @MappingTarget Movie movie);

    Set<Episode> episodeDTOListToEpisodeSet(List<EpisodeDTO> episodeDTOs);

    @Mapping(target = "movie", ignore = true)
    Episode episodeDTOToEntity(EpisodeDTO episodeDTO);

    @Mapping(target = "movies", ignore = true)
    Category mapCategoryDTOToEntity(CategoryDTO categoryDTO);
}
