package com.example.movie_backend.dto.movie;

import com.example.movie_backend.dto.category.CategoryDTO;
import com.example.movie_backend.dto.episode.EpisodeDTO;
import com.example.movie_backend.dto.genre.GenreDTO;
import com.example.movie_backend.entity.*;
import org.springframework.stereotype.Component;

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

    public Set<Genre> convertGenresIds(Set<Long> idGenres) {
        return idGenres == null ? null : idGenres.stream()
                .map(id -> Genre.builder().id(id).build())
                .collect(Collectors.toSet());
    }

    public Set<Comment> convertCommentIds(Set<Long> idComments) {
        return idComments == null ? null : idComments.stream()
                .map(id -> Comment.builder().id(id).build())
                .collect(Collectors.toSet());
    }

    public Set<Evaluation> convertEvaluations(Set<Long> idEvaluations) {
        return idEvaluations == null ? null : idEvaluations.stream()
                .map(id -> Evaluation.builder().id(id).build())
                .collect(Collectors.toSet());
    }

    public List<Episode> convertEpisodes(List<EpisodeDTO> episodeDTOs) {
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


    public Movie toEntity(MovieDTO dto) {
        return Movie.builder()
                .nameMovie(dto.getNameMovie())
                .viTitle(dto.getViTitle())
                .enTitle(dto.getEnTitle())
                .description(dto.getDescription())
                .posterUrl(dto.getPosterUrl())
                .year(dto.getYear())
                .country(dto.getCountry())
                .videoUrl(dto.getVideoUrl() == null ? null : dto.getVideoUrl())
                .category(convertCategory(dto.getCategory()))
                .genres(convertGenresIds(dto.getIdGenre()))
                .comments(convertCommentIds(dto.getIdComment()))
                .evaluations(convertEvaluations(dto.getIdEvaluation()))
                .episodes(convertEpisodes(dto.getEpisodes()))
                .build();
    }


    public Movie toUpdateMovieWithEpisodes(MovieEpisodeRequest dto) {
        Movie movie = Movie.builder()
                .nameMovie(dto.getNameMovie())
                .enTitle(dto.getViTitle())
                .viTitle(dto.getEnTitle())
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

    public Movie toUpdateMovieWithEpisodes(MovieEpisodeRequest dto, Movie currentMovie) {
        Movie movie = currentMovie.toBuilder()
                .nameMovie(dto.getNameMovie())
                .enTitle(dto.getViTitle())
                .viTitle(dto.getEnTitle())
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

    public Movie toEntity(MovieDTO dto, Long id) {
        return Movie.builder()
                .nameMovie(dto.getNameMovie())
                .viTitle(dto.getViTitle())
                .enTitle(dto.getEnTitle())
                .description(dto.getDescription())
                .posterUrl(dto.getPosterUrl())
                .year(dto.getYear())
                .country(dto.getCountry())
                .videoUrl(dto.getVideoUrl() == null ? null : dto.getVideoUrl())
                .category(convertCategory(dto.getCategory()))
                .genres(convertGenresIds(dto.getIdGenre()))
                .comments(convertCommentIds(dto.getIdComment()))
                .evaluations(convertEvaluations(dto.getIdEvaluation()))
                .episodes(convertEpisodes(dto.getEpisodes()))
                .build();
    }

    public Movie toEntity(MovieDTO dto, String name) {
        return Movie
                .builder()
                .nameMovie(name)
                .viTitle(dto.getViTitle())
                .enTitle(dto.getEnTitle())
                .description(dto.getDescription())
                .posterUrl(dto.getPosterUrl())
                .year(dto.getYear())
                .country(dto.getCountry())
                .videoUrl(dto.getVideoUrl() == null ? null : dto.getVideoUrl())
                .category(convertCategory(dto.getCategory()))
                .genres(convertGenresIds(dto.getIdGenre()))
                .comments(convertCommentIds(dto.getIdComment()))
                .evaluations(convertEvaluations(dto.getIdEvaluation()))
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
        return MovieDTO.builder()
                .id(entity.getId())
                .nameMovie(entity.getNameMovie())
                .posterUrl(entity.getPosterUrl())
                .videoUrl(entity.getVideoUrl() == null ? null : entity.getVideoUrl())
                .viTitle(entity.getViTitle())
                .enTitle(entity.getEnTitle())
                .country(entity.getCountry())
                .year(entity.getYear())
                .description(entity.getDescription())
                .category(convertCategoryToDTO(entity.getCategory()))
                .genres(convertGenresToDTO(entity.getGenres()))
                .episodes(entity.getEpisodes() == null ? null : entity.getEpisodes().stream()
                        .map(this::toEpisodeDTO)
                        .collect(Collectors.toList()))
                .build();
    }


    public EpisodeDTO toEpisodeDTO(Episode episode) {
        return EpisodeDTO.builder()
                .id(episode.getId())
                .episodeCount(episode.getEpisodeCount())
                .descriptions(episode.getDescriptions())
                .videoUrl(episode.getVideoUrl())
                .posterUrl(episode.getPosterUrl())
                .build();
    }

    public Long convertCategoryIdToDTO(Category category) {
        return category == null ? null : category.getId();
    }

    public Set<Long> convertEvaluationIdsToDTO(Set<Evaluation> evaluations) {
        return evaluations == null ? null : evaluations.stream()
                .map(Evaluation::getId)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
    }

    public MovieDTOWithoutJoin toDTOWithoutJoin(Movie entity) {
        return MovieDTOWithoutJoin.builder()
                .id(entity.getId())
                .nameMovie(entity.getNameMovie())
                .posterUrl(entity.getPosterUrl())
                .viTitle(entity.getViTitle()).enTitle(entity.getEnTitle()).country(entity.getCountry()).idCategory(entity.getCategory() == null ? null : entity.getCategory().getId()).description(entity.getDescription()).idGenre(entity.getGenres() == null ? null : entity.getGenres().stream().map(Genre::getId).filter(Objects::nonNull).collect(Collectors.toSet())).idComment(entity.getComments() == null ? null : entity.getComments().stream().map(Comment::getId).filter(Objects::nonNull).collect(Collectors.toSet()))
                .genres(convertGenresToDTO(entity.getGenres()))
                .idEvaluation(convertEvaluationIdsToDTO(entity.getEvaluations()))
                .build();
    }
}
