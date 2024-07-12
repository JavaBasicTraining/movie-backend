package com.example.movie_backend.dto.movie;

import com.example.movie_backend.dto.category.CategoryDTO;
import com.example.movie_backend.dto.genre.GenreDTO;
import com.example.movie_backend.entity.*;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class MovieMapper {
    public Movie toEntity(MovieDTO dto) {
        return Movie.builder()
                .nameMovie(dto.getNameMovie())
                .viTitle(dto.getViTitle())
                .enTitle(dto.getEnTitle())
                .description(dto.getDescription())
                .posterUrl(dto.getPosterUrl())
                .year(dto.getYear())
                .country(dto.getCountry())
                .videoUrl(dto.getVideoUrl())
                .episodes(dto.getIdEpisode()==null? null: dto.getIdEpisode().stream().map(item-> Episode.builder()
                                .id(item).build())
                        .collect(Collectors.toSet()))
                .category(Category.builder().id(dto.getId()).build())
                .genres(dto.getIdGenre() == null ? null : dto.getIdGenre()
                        .stream()
                        .map(ids -> Genre.builder()
                                .id(ids)
                                .build())
                        .collect(Collectors.toSet()))

                .comments(dto.getIdComment() == null ? null : dto.getIdComment()
                        .stream()
                        .map(ids -> Comment.builder()
                                .id(ids)
                                .build())
                        .collect(Collectors.toSet()))
                .evaluations(dto.getIdEvaluation() == null ? null : dto.getIdEvaluation()
                        .stream()
                        .map(ids -> Evaluation.builder()
                                .id(ids)
                                .build())
                        .collect(Collectors.toSet()))

                .build();
    }


    public Movie toEntity(MovieDTO dto, Long id) {
        return Movie.builder()
                .id(id)
                .nameMovie(dto.getNameMovie())
                .viTitle(dto.getViTitle())
                .country(dto.getCountry())
                .enTitle(dto.getEnTitle())
                .posterUrl(dto.getPosterUrl())
                .videoUrl(dto.getVideoUrl())
                .year(dto.getYear())
                .category(Category.builder().id(dto.getId()).build())
                .episodes(dto.getIdEpisode()==null? null: dto.getIdEpisode().stream().map(item-> Episode.builder()
                                .id(item).build())
                        .collect(Collectors.toSet()))
                .description(dto.getDescription())
                .genres(dto.getIdGenre() == null ? null : dto.getIdGenre().stream()
                        .map(ids -> Genre.builder()
                                .id(ids)
                                .build())
                        .collect(Collectors.toSet()))
                .comments(dto.getIdComment() == null ? null : dto.getIdComment()
                        .stream()
                        .map(ids -> Comment.builder()
                                .id(ids)
                                .build())
                        .collect(Collectors.toSet()))
                .evaluations(dto.getIdEvaluation() == null ? null : dto.getIdEvaluation()
                        .stream()
                        .map(ids -> Evaluation.builder()
                                .id(ids)
                                .build())
                        .collect(Collectors.toSet()))


                .build();
    }

    public Movie toEntity(MovieDTO dto, String name) {
        return Movie.builder()
                .nameMovie(name)
                .viTitle(dto.getViTitle())
                .enTitle(dto.getEnTitle())
                .posterUrl(dto.getPosterUrl())
                .videoUrl(dto.getVideoUrl())
                .year(dto.getYear())
                .category(Category.builder().id(dto.getId()).build())
                .episodes(dto.getIdEpisode()==null? null: dto.getIdEpisode().stream().map(item-> Episode.builder()
                                .id(item).build())
                        .collect(Collectors.toSet()))
                .description(dto.getDescription())
                .genres(dto.getIdGenre() == null ? null : dto.getIdGenre().stream()
                        .map(ids -> Genre.builder()
                                .id(ids)
                                .build())
                        .collect(Collectors.toSet()))
                .comments(dto.getIdComment() == null ? null : dto.getIdComment()
                        .stream()
                        .map(ids -> Comment.builder()
                                .id(ids)
                                .build())
                        .collect(Collectors.toSet()))
                .evaluations(dto.getIdEvaluation() == null ? null : dto.getIdEvaluation()
                        .stream()
                        .map(ids -> Evaluation.builder()
                                .id(ids)
                                .build())
                        .collect(Collectors.toSet()))
                .build();
    }

    public MovieDTO toDTO(Movie entity) {
        return MovieDTO.builder()
                .id(entity.getId())
                .nameMovie(entity.getNameMovie())
                .posterUrl(entity.getPosterUrl())
                .videoUrl(entity.getVideoUrl())
                .viTitle(entity.getViTitle())
                .enTitle(entity.getEnTitle())
                .country(entity.getCountry())
                .year(entity.getYear())
                .description(entity.getDescription())
                .category(CategoryDTO.builder().name(entity.getCategory()== null ? null: entity.getCategory().getName()).build())
                .genres(entity.getGenres()
                        .stream()
                        .map(item-> GenreDTO.builder()
                                .name(item.getName())
                                .build())
                        .collect(Collectors.toSet()))
                .idEpisode(entity.getEpisodes() == null ? null : entity.getEpisodes().stream()
                        .map(Episode::getId).filter(Objects::nonNull)
                        .collect(Collectors.toSet()))
                .idCategory(entity.getCategory()==null ? null : entity.getCategory().getId() )
                .idGenre(entity.getGenres() == null ? null : entity.getGenres().stream()
                        .map(Genre::getId).filter(Objects::nonNull)
                        .collect(Collectors.toSet()))
                .idComment(entity.getComments() == null ? null : entity.getComments().stream()
                        .map(Comment::getId).filter(Objects::nonNull)
                        .collect(Collectors.toSet()))

                .idEvaluation(entity.getEvaluations() == null ? null : entity.getEvaluations().stream()
                        .map(Evaluation::getId).filter(Objects::nonNull)
                        .collect(Collectors.toSet()))
                .build();
    }

    public MovieDTOWithoutJoin toDTOWithoutJoin(Movie entity) {
        return MovieDTOWithoutJoin.builder()
                .id(entity.getId())
                .nameMovie(entity.getNameMovie())
                .posterUrl(entity.getPosterUrl())
                .viTitle(entity.getViTitle())
                .enTitle(entity.getEnTitle())
                .country(entity.getCountry())
                .idCategory(entity.getCategory()==null ? null : entity.getCategory().getId() )
                .description(entity.getDescription())
                .idGenre(entity.getGenres() == null ? null : entity.getGenres().stream()
                        .map(Genre::getId).filter(Objects::nonNull)
                        .collect(Collectors.toSet()))
                .idComment(entity.getComments() == null ? null : entity.getComments().stream()
                        .map(Comment::getId).filter(Objects::nonNull)
                        .collect(Collectors.toSet()))

                .idEvaluation(entity.getEvaluations() == null ? null : entity.getEvaluations().stream()
                        .map(Evaluation::getId).filter(Objects::nonNull)
                        .collect(Collectors.toSet()))
                .build();
    }
}