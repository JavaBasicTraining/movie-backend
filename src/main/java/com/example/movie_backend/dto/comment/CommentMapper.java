package com.example.movie_backend.dto.comment;

import com.example.movie_backend.entity.Comment;
import com.example.movie_backend.entity.Movie;
import com.example.movie_backend.entity.User;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CommentMapper {
    public Comment toEntity(CommentDTO dto) {
        return Comment.builder()
                .id(dto.getId())
                .content(dto.getContent())
                .user(User.builder().Id(dto.getIdUser()).build())
                .movies(dto.getIdMovies().stream().map(item ->
                        Movie.builder()
                                .id(item)
                                .build()).collect(Collectors.toSet()))
                .build();

    }

    public CommentDTO toDTO(Comment entity) {
        return CommentDTO.builder()
                .id(entity.getId())
                .content(entity.getContent())
                .idUser(entity.getUser().getId())

                .idMovies(entity.getMovies().stream()
                        .map(item -> item.getId())
                        .collect(Collectors.toSet()))
                .build();
    }

    public Comment toEntity(CommentDTO dto, Long id) {
        return Comment.builder()
                .id(id)
                .content(dto.getContent())
                .user(User.builder().Id(dto.getIdUser()).build())
                .movies(dto.getIdMovies().stream().map(item ->
                        Movie.builder()
                                .id(item)
                                .build()).collect(Collectors.toSet()))
                .build();

    }

}
