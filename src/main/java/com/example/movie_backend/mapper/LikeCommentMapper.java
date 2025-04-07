package com.example.movie_backend.mapper;

import com.example.movie_backend.dto.comment.CommentDTO;
import com.example.movie_backend.dto.like_comment.LikeCommentDTO;
import com.example.movie_backend.dto.user.UserDTO;
import com.example.movie_backend.entity.Comment;
import com.example.movie_backend.entity.LikeComment;
import com.example.movie_backend.entity.User;
import org.springframework.stereotype.Component;

@Component
public class LikeCommentMapper {

    public LikeComment toEntity(LikeCommentDTO dto) {
        return LikeComment.builder()
                .id(dto.getId())
                .user(mapToUserEntity(dto.getUser()))
                .comment(mapToCommentEntity(dto.getComment()))
                .build();
    }

    public LikeComment toEntity(LikeCommentDTO dto, Long id) {
        return LikeComment.builder()
                .id(id)
                .user(mapToUserEntity(dto.getUser()))
                .comment(mapToCommentEntity(dto.getComment()))
                .build();
    }

    public LikeCommentDTO toDTO(LikeComment entity) {
        if (entity == null) {
            return null;
        }
        return LikeCommentDTO.builder()
                .id(entity.getId() == null ? null : entity.getId())
                .liked(entity.getLiked())
                .user(mapToUserDTO(entity.getUser()))
                .comment(mapToCommentDTO(entity.getComment()))
                .build();
    }

    private Comment mapToCommentEntity(CommentDTO comment) {
        if (comment == null || comment.getId() == null) {
            return null;
        }
        return Comment.builder().id(comment.getId()).build();
    }

    private CommentDTO mapToCommentDTO(Comment comment) {
        if (comment == null || comment.getId() == null) {
            return null;
        }
        return CommentDTO.builder().id(comment.getId()).build();
    }

    private User mapToUserEntity(UserDTO userDTO) {
        if (userDTO == null || userDTO.getId() == null) {
            return null;
        }
        return User.builder().id(userDTO.getId()).build();
    }

    private UserDTO mapToUserDTO(User user) {
        if (user == null || user.getId() == null) {
            return null;
        }
        return UserDTO.builder().id(user.getId()).build();
    }
}