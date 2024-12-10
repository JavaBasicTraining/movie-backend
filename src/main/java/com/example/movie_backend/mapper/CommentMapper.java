package com.example.movie_backend.mapper;

import com.example.movie_backend.dto.comment.CommentDTO;
import com.example.movie_backend.dto.movie.MovieDTO;
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
                .user(mapToUser(dto.getUser()))
                .movie(mapToMovie(dto.getMovie()))
                .currentDate(new Date())
                .parentComment(mapToComment(dto.getParentComment()))
                .build();
    }

    public Comment toEntity(CommentDTO dto, Long id) {
        return Comment.builder()
                .id(id)
                .content(dto.getContent())
                .user(dto.getIdUser() != null ? User.builder().id(dto.getIdUser()).build() : null)
                .movie(dto.getIdMovie() != null ? Movie.builder().id(dto.getIdMovie()).build() : null)
                .parentComment(mapToComment(dto.getParentComment()))
                .currentDate(new Date())
                .build();
    }

    public CommentDTO toDTO(Comment entity) {
        return CommentDTO.builder()
                .id(entity.getId())
                .content(entity.getContent())
//                .idUser(entity.getUser() != null ? entity.getUser().getId() : null)
//                .idMovie(entity.getMovie() != null ? entity.getMovie().getId() : null)
                .user(entity.getUser() != null ? UserDTO.builder()
                        .id(entity.getUser().getId())
                        .userName(entity.getUser().getUsername())
                        .build() : null)
                .currentDate(entity.getCurrentDate())
//                .subordinates(entity.getSubordinates().stream()
//                        .map(this::toDTO) //
//                        .collect(Collectors.toList()))
                .parentCommentId(entity.getParentComment() != null ?
                        entity.getParentComment().getId() : null)
                .build();
    }

    private Comment mapToComment(CommentDTO dto) {
        if (dto == null) return null;
        return Comment.builder().id(dto.getId()).build();
    }

    private User mapToUser(UserDTO dto) {
        if (dto == null) {
            return null;
        }
        return User.builder().id(dto.getId()).build();
    }

    private Movie mapToMovie(MovieDTO movie) {
        if (movie == null) return null;
        return Movie.builder().id(movie.getId()).build();
    }
}
