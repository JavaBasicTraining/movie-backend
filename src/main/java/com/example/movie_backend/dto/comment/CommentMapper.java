package com.example.movie_backend.dto.comment;

import com.example.movie_backend.entity.Comment;
import com.example.movie_backend.entity.Movie;
import com.example.movie_backend.entity.User;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {
    public Comment toEntity(CommentDTO dto) {
        return Comment.builder()
                .id(dto.getId())
                .content(dto.getContent())
                .user(User.builder().id(dto.getIdUser()).username(dto.getUser()).build())
                .movie(dto.getIdMovie() == null ? null : Movie.builder().id(dto.getIdMovie()).build())
                .build();

    }

    public CommentDTO toDTO(Comment entity) {
        return CommentDTO.builder()
                .id(entity.getId())
                .content(entity.getContent())
                .idUser(entity.getUser()== null ? null: entity.getUser().getId())
                .idMovie(entity.getMovie()== null? null : entity.getMovie().getId())
                .user(entity.getUser() == null ? null : entity.getUser().getUsername())
                .build();
    }

    public Comment toEntity(CommentDTO dto, Long id) {
        return Comment.builder()
                .id(id)
                .content(dto.getContent())
                .user(User.builder().id(dto.getIdUser()).username(dto.getUser()).build())
                .movie(dto.getIdMovie() == null ? null : Movie.builder().id(dto.getIdMovie()).build())
                .build();


    }

}
