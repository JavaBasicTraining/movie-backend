package com.example.movie_backend.dto.comment;

import com.example.movie_backend.dto.user.UserDTO;
import com.example.movie_backend.entity.Comment;
import com.example.movie_backend.entity.Movie;
import com.example.movie_backend.entity.User;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class CommentMapper {

    public Comment toEntity(CommentDTO dto) {
        return Comment.builder()
                .id(dto.getId())
                .content(dto.getContent())
                .user(dto.getIdUser() != null ? User.builder().id(dto.getIdUser()).build() : null)
                .movie(dto.getIdMovie() != null ? Movie.builder().id(dto.getIdMovie()).build() : null)
                .currentDate(new Date())
                .build();
    }

    public CommentDTO toDTO(Comment entity) {
        return CommentDTO.builder()
                .id(entity.getId())
                .content(entity.getContent())
                .idUser(entity.getUser() != null ? entity.getUser().getId() : null)
                .idMovie(entity.getMovie() != null ? entity.getMovie().getId() : null)
                .user(entity.getUser() != null ? UserDTO.builder()
                        .id(entity.getUser().getId())
                        .userName(entity.getUser().getUsername())
                        .build() : null)
                .currentDate(entity.getCurrentDate())
                .build();
    }


    public Comment toEntity(CommentDTO dto, Long id) {
        return Comment.builder()
                .id(id)
                .content(dto.getContent())
                .user(dto.getIdUser() != null ? User.builder().id(dto.getIdUser()).build() : null)
                .movie(dto.getIdMovie() != null ? Movie.builder().id(dto.getIdMovie()).build() : null)
                .currentDate(new Date())
                .build();
    }
}
