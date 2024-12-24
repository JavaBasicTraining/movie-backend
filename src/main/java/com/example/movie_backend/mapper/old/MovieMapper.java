package com.example.movie_backend.mapper.old;

import com.example.movie_backend.dto.category.CategoryDTO;
import com.example.movie_backend.dto.comment.CommentDTO;
import com.example.movie_backend.dto.episode.EpisodeDTO;
import com.example.movie_backend.dto.evaluation.EvaluationDTO;
import com.example.movie_backend.dto.genre.GenreDTO;
import com.example.movie_backend.dto.movie.MovieDTO;
import com.example.movie_backend.dto.movie.MovieDTOWithoutJoin;
import com.example.movie_backend.dto.movie.MovieEpisodeRequest;
import com.example.movie_backend.entity.*;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class MovieMapper {
    public Category convertCategory(CategoryDTO categoryDTO) {
        if (categoryDTO.getId() != null) {
            return Category.builder()
                    .id(categoryDTO.getId())
                    .build();

        }
        return null;
    }

    public Movie toUpdateMovieWithEpisodes(MovieEpisodeRequest dto) {
        Movie movie = Movie.builder()
                .id(dto.getId())
                .nameMovie(dto.getNameMovie())
                .enTitle(dto.getViTitle())
                .viTitle(dto.getEnTitle())
                .path(dto.getPath())
                .description(dto.getDescription())
                .year(dto.getYear())
                .country(dto.getCountry())
                .category(dto.getIdCategory() == null ? null : Category.builder()
                        .id(dto.getIdCategory())
                        .build())
                .genres(dto.getIdGenre() == null ? null : dto.getIdGenre()
                        .stream()
                        .map(ids -> Genre.builder()
                                .id(ids)
                                .build())
                        .collect(Collectors.toSet()))
                .episodes(convertEpisodes(dto.getEpisodes()))
                .build();

        movie.setEpisodes(movie.getEpisodes());
        return movie;
    }

    public Movie toUpdateMovieWithEpisodes(MovieDTO dto, Movie currentMovie) {
        Movie movie = currentMovie.toBuilder()
                .id(currentMovie.getId())
                .nameMovie(dto.getNameMovie())
                .enTitle(dto.getViTitle())
                .viTitle(dto.getEnTitle())
                .description(dto.getDescription())
                .year(dto.getYear())
                .path(dto.getPath())
                .country(dto.getCountry())
                .episodes(convertEpisodes(dto.getEpisodes()))
                .category(toCategory(dto.getCategory()))
                .genres(toGenres(dto.getGenres()))
                .episodes(convertEpisodes(dto.getEpisodes()))
                .build();

        movie.setEpisodes(movie.getEpisodes());
        return movie;
    }

    public Movie toEntity(MovieDTO dto, String name) {
        return Movie
                .builder()
                .nameMovie(name)
                .viTitle(dto.getViTitle())
                .path(dto.getPath())
                .enTitle(dto.getEnTitle())
                .description(dto.getDescription())
                .posterUrl(dto.getPosterUrl())
                .year(dto.getYear())
                .country(dto.getCountry())
                .videoUrl(dto.getVideoUrl() == null ? null : dto.getVideoUrl())
                .trailerUrl(dto.getTrailerUrl() == null ? null : dto.getTrailerUrl())
                .category(convertCategory(dto.getCategory()))
                .genres(toGenres(dto.getGenres()))
                .comments(toComments(dto.getComments()))
                .evaluations(toEvaluations(dto.getEvaluations()))
                .episodes(convertEpisodes(dto.getEpisodes()))
                .build();
    }

    public CategoryDTO convertCategoryToDTO(Category category) {
        return category == null ? null : CategoryDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

    public Set<GenreDTO> convertGenresToDTO(Set<Genre> genres) {
        return genres == null ? null : genres.stream()
                .map(genre -> GenreDTO.builder()
                        .id(genre.getId())
                        .name(genre.getName())
                        .build())
                .collect(Collectors.toSet());
    }


    public MovieDTO toDTO(Movie entity) {
        List<EpisodeDTO> episodes = Objects.isNull(entity.getEpisodes()) ? null : entity.getEpisodes().stream()
                .map(this::toEpisodeDTO)
                .toList();

        return MovieDTO.builder()
                .id(entity.getId())
                .nameMovie(entity.getNameMovie())
                .path(entity.getPath())
                .posterUrl(entity.getPosterUrl())
                .videoUrl(entity.getVideoUrl())
                .trailerUrl(entity.getTrailerUrl() == null ? null : entity.getTrailerUrl())
                .viTitle(entity.getViTitle())
                .enTitle(entity.getEnTitle())
                .country(entity.getCountry())
                .year(entity.getYear())
                .description(entity.getDescription())
                .category(toCategoryDTO(entity.getCategory()))
                .genres(toGenreDTOS(entity.getGenres()))
                .episodes(episodes)
                .build();
    }


    public EpisodeDTO toEpisodeDTO(Episode episode) {
        return EpisodeDTO.builder()
                .id(episode.getId())
                .episodeCount(episode.getEpisodeCount())
                .descriptions(episode.getDescriptions())
                .videoUrl(episode.getVideoUrl())
                .posterUrl(episode.getPosterUrl())
                .tempId(episode.getTempId())
                .build();
    }

    public MovieDTOWithoutJoin toDTOWithoutJoin(Movie entity) {
        return MovieDTOWithoutJoin.builder()
                .id(entity.getId())
                .nameMovie(entity.getNameMovie())
                .path(entity.getPath())
                .posterUrl(entity.getPosterUrl())
                .viTitle(entity.getViTitle())
                .enTitle(entity.getEnTitle())
                .country(entity.getCountry())
                .category(toCategoryDTO(entity.getCategory()))
                .description(entity.getDescription())
                .genres(toGenreDTOS(entity.getGenres()))
                .build();
    }

    private List<GenreDTO> toGenreDTOS(Set<Genre> genres) {
        if (Objects.isNull(genres)) {
            return Collections.emptyList();
        }
        return genres.stream().map(this::toGenreDTO).toList();
    }

    private GenreDTO toGenreDTO(Genre genre) {
        if (Objects.isNull(genre)) {
            return null;
        }
        return GenreDTO.builder().id(genre.getId()).build();
    }

    private CategoryDTO toCategoryDTO(Category category) {
        if (Objects.isNull(category)) {
            return null;
        }
        return CategoryDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

    private Set<Genre> toGenres(List<GenreDTO> genreDTOS) {
        if (genreDTOS == null) {
            return Collections.emptySet();
        }
        return genreDTOS.stream()
                .map(genreDTO -> Genre.builder().id(genreDTO.getId()).build())
                .collect(Collectors.toSet());
    }

    private Set<Comment> toComments(List<CommentDTO> commentDTOS) {
        if (commentDTOS == null) {
            return Collections.emptySet();
        }
        return commentDTOS.stream()
                .map(comment -> Comment.builder().id(comment.getId()).build())
                .collect(Collectors.toSet());
    }

    private Set<Evaluation> toEvaluations(List<EvaluationDTO> evaluationDTOS) {
        return evaluationDTOS == null ? null : evaluationDTOS.stream()
                .map(evaluationDTO -> Evaluation.builder().id(evaluationDTO.getId()).build())
                .collect(Collectors.toSet());
    }

    private List<Episode> convertEpisodes(List<EpisodeDTO> episodeDTOs) {
        return episodeDTOs == null ? null : episodeDTOs.stream()
                .map(dto -> Episode.builder()
                        .id(dto.getId())
                        .episodeCount(dto.getEpisodeCount())
                        .descriptions(dto.getDescriptions())
                        .videoUrl(dto.getVideoUrl())
                        .posterUrl(dto.getPosterUrl())
                        .tempId(dto.getTempId())
                        .build())
                .collect(Collectors.toList());
    }

    private Category toCategory(CategoryDTO category) {
        if (category == null || category.getId() == null) {
            return null;
        }

        return Category.builder().id(category.getId()).build();
    }
}
