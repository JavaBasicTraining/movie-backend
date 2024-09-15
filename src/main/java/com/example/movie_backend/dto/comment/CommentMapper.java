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
        User user = null;
        if (dto.getIdUser() != null) {
            user = User.builder().id(dto.getIdUser()).build(); // Chỉ khởi tạo id
        }

        Movie movie = null;
        if (dto.getIdMovie() != null) {
            movie = Movie.builder().id(dto.getIdMovie()).build(); // Chỉ khởi tạo id
        }

        return Comment.builder()
                .id(dto.getId())
                .content(dto.getContent())
                .user(user)
                .movie(movie)
                .currentDate(new Date())
                .build();
    }

    public CommentDTO toDTO(Comment entity) {
        UserDTO userDTO = null;
        if (entity.getUser() != null) {
            userDTO = UserDTO.builder().userName(entity.getUser().getUsername()).build();
        }

        return CommentDTO.builder()
                .id(entity.getId())
                .content(entity.getContent())
                .idUser(entity.getUser() != null ? entity.getUser().getId() : null)
                .idMovie(entity.getMovie() != null ? entity.getMovie().getId() : null)
                .user(userDTO)
                .build();
    }

    public Comment toEntity(CommentDTO dto, Long id) {
        User user = null;
        if (dto.getIdUser() != null) {
            user = User.builder().id(dto.getIdUser()).build(); // Chỉ khởi tạo id
        }

        Movie movie = null;
        if (dto.getIdMovie() != null) {
            movie = Movie.builder().id(dto.getIdMovie()).build(); // Chỉ khởi tạo id
        }

        return Comment.builder()
                .id(id)
                .content(dto.getContent())
                .user(user)
                .movie(movie)
                .currentDate(new Date())
                .build();
    }
}
