package com.example.movie_backend.dto.movie;

import com.example.movie_backend.controller.request.CreateMovieRequest;
import com.example.movie_backend.dto.category.CategoryDTO;
import com.example.movie_backend.dto.comment.CommentDTO;
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


                .category(Category.builder().id(dto.getId() == null ? null : dto.getId()).build()).genres(dto.getIdGenre() == null ? null : dto.getIdGenre().stream().map(ids -> Genre.builder().id(ids).build()).collect(Collectors.toSet()))

                .comments(dto.getIdComment() == null ? null : dto.getIdComment().stream().map(ids -> Comment.builder().id(ids).build()).collect(Collectors.toSet())).evaluations(dto.getIdEvaluation() == null ? null : dto.getIdEvaluation().stream().map(ids -> Evaluation.builder().id(ids).build()).collect(Collectors.toSet()))

                .episodes(dto.getEpisodes() == null ? null : dto.getEpisodes().stream()
                        .map(item -> Episode.builder()
                                .id(item.getId())
                                .episodeCount(item.getEpisodeCount())
                                .descriptions(item.getDescriptions())
                                .videoUrl(item.getVideoUrl())
                                .posterUrl(item.getPosterUrl())
                                .build())
                        .collect(Collectors.toSet()))
                .build();
    }

    public Movie toEntityCreateMovieRequest(CreateMovieRequest dto) {
        return Movie.builder()
                .nameMovie(dto.getNameMovie())
                .viTitle(dto.getViTitle())
                .enTitle(dto.getEnTitle())
                .description(dto.getDescription())
                .posterUrl(dto.getPosterUrl())
                .year(dto.getYear())
                .country(dto.getCountry())

                .category(dto.getIdCategory()== null ? null: Category.builder().id(dto.getIdCategory()).build()).videoUrl(dto.getVideoUrl())

                .genres(dto.getIdGenre() == null ? null : dto.getIdGenre().stream().map(ids -> Genre.builder().id(ids).build()).collect(Collectors.toSet()))

                .comments(dto.getIdComment() == null ? null : dto.getIdComment().stream().map(ids -> Comment.builder().id(ids).build()).collect(Collectors.toSet())).evaluations(dto.getIdEvaluation() == null ? null : dto.getIdEvaluation().stream().map(ids -> Evaluation.builder().id(ids).build()).collect(Collectors.toSet()))

                .episodes(dto.getEpisodes() == null ? null : dto.getEpisodes().stream()
                        .map(item -> Episode.builder()
                                .id(item.getId())
                                .episodeCount(item.getEpisodeCount())
                                .descriptions(item.getDescriptions())
                                .videoUrl(item.getVideoUrl())
                                .posterUrl(item.getPosterUrl())
                                .build())
                        .collect(Collectors.toSet()))


                .build();
    }


    public Movie toEntityMovieEpisode(MovieEpisodeRequest dto) {
        return Movie.builder()
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


                .episodes(dto.getEpisodes() == null ? null : dto.getEpisodes().stream()
                        .map(item -> Episode.builder()
                                .id(item.getId())
                                .episodeCount(item.getEpisodeCount())
                                .descriptions(item.getDescriptions())
                                .videoUrl(item.getVideoUrl())
                                .posterUrl(item.getPosterUrl())
                                .build())
                        .collect(Collectors.toSet()))

                .build();
    }




    public Movie toEntityCreateMovieRequest(CreateMovieRequest dto, Long id) {
        return Movie.builder().id(id).nameMovie(dto.getNameMovie()).viTitle(dto.getViTitle())
                .enTitle(dto.getEnTitle()).description(dto.getDescription())
                .posterUrl(dto.getPosterUrl())
                .year(dto.getYear())
                .country(dto.getCountry())
                .category(Category.builder().id(dto.getIdCategory()).build()).videoUrl(dto.getVideoUrl())

                .genres(dto.getIdGenre() == null ? null : dto.getIdGenre().stream().map(ids -> Genre.builder().id(ids).build()).collect(Collectors.toSet()))

                .comments(dto.getIdComment() == null ? null : dto.getIdComment().stream().map(ids -> Comment.builder().id(ids).build()).collect(Collectors.toSet())).evaluations(dto.getIdEvaluation() == null ? null : dto.getIdEvaluation().stream().map(ids -> Evaluation.builder().id(ids).build()).collect(Collectors.toSet()))

                .build();
    }


    public Movie toEntity(MovieDTO dto, Long id) {
        return Movie.builder()
                .id(id)
                .nameMovie(dto
                        .getNameMovie())
                .viTitle(dto.getViTitle())
                .country(dto.getCountry())
                .enTitle(dto.getEnTitle())
                .posterUrl(dto.getPosterUrl())
                .videoUrl(dto.getVideoUrl())
                .year(dto.getYear())
                .category(Category.builder().id(dto.getId() == null ? null : dto.getId()).build())

                .description(dto.getDescription())
                .genres(dto.getIdGenre() == null ? null : dto.getIdGenre()
                        .stream()
                        .map(ids ->
                                Genre.builder()
                                        .id(ids)
                                        .build())
                        .collect(Collectors.toSet()))
                .comments(dto.getIdComment() == null ? null : dto.getIdComment()
                        .stream().map(ids ->
                                Comment.builder()
                                        .id(ids).build())
                        .collect(Collectors.toSet()))
                .evaluations(dto.getIdEvaluation() == null ? null : dto.getIdEvaluation()
                        .stream()
                        .map(ids ->
                                Evaluation
                                        .builder()
                                        .id(ids)
                                        .build())
                        .collect(Collectors.toSet()))
                .build();
    }

    public Movie toEntity(MovieDTO dto, String name) {
        return Movie
                .builder()
                .nameMovie(name)
                .viTitle(dto.getViTitle())
                .enTitle(dto.getEnTitle())
                .posterUrl(dto.getPosterUrl())
                .videoUrl(dto.getVideoUrl())
                .year(dto.getYear())
                .category(Category
                        .builder()
                        .id(dto.getId() == null ? null : dto.getId())
                        .build())

                .description(dto.getDescription())
                .genres(dto.getIdGenre() == null ? null : dto.getIdGenre()
                        .stream()
                        .map(ids -> Genre
                                .builder()
                                .id(ids)
                                .build())
                        .collect(Collectors.toSet()))
                .comments(dto.getIdComment() == null ? null : dto.getIdComment()
                        .stream()
                        .map(ids -> Comment
                                .builder()
                                .id(ids)
                                .build())
                        .collect(Collectors.toSet()))
                .evaluations(dto.getIdEvaluation() == null ? null : dto.getIdEvaluation()
                        .stream()
                        .map(ids -> Evaluation.builder()
                                .id(ids).build())
                        .collect(Collectors.toSet())).build();
    }


//
//    public  Movie toEntity (CreateRequestFileMovie fileMovie)
//    {
//        return MovieDTO.builder()
//                .videoUrl(file)
//                .build();
//    }


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
                .category(Objects.isNull(entity.getCategory()) ? null : CategoryDTO.builder().id(entity.getCategory().getId()).name(entity.getCategory().getName()).build())
//                .comments(entity.getGenres() ==null ? null : entity.getComments().stream().map(item -> CommentDTO.builder().id(item.getId()).name(item.getName()).build()).collect(Collectors.toSet()))

                .genres(entity.getGenres() ==null ? null : entity.getGenres().stream().map(item -> GenreDTO.builder().id(item.getId()).name(item.getName()).build()).collect(Collectors.toSet()))
                .episodes(entity.getEpisodes() == null ? null : entity.getEpisodes())
                .build();

    }

    public MovieDTOWithoutJoin toDTOWithoutJoin(Movie entity) {
        return MovieDTOWithoutJoin.builder().id(entity.getId()).nameMovie(entity.getNameMovie()).posterUrl(entity.getPosterUrl()).viTitle(entity.getViTitle()).enTitle(entity.getEnTitle()).country(entity.getCountry()).idCategory(entity.getCategory() == null ? null : entity.getCategory().getId()).description(entity.getDescription()).idGenre(entity.getGenres() == null ? null : entity.getGenres().stream().map(Genre::getId).filter(Objects::nonNull).collect(Collectors.toSet())).idComment(entity.getComments() == null ? null : entity.getComments().stream().map(Comment::getId).filter(Objects::nonNull).collect(Collectors.toSet()))

                .idEvaluation(entity.getEvaluations() == null ? null : entity.getEvaluations().stream().map(Evaluation::getId).filter(Objects::nonNull).collect(Collectors.toSet())).build();
    }
}