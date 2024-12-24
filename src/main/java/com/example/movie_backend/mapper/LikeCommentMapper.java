package com.example.movie_backend.mapper;

import com.example.movie_backend.dto.like_comment.LikeCommentDTO;
import com.example.movie_backend.entity.Comment;
import com.example.movie_backend.entity.LikeComment;
import com.example.movie_backend.entity.User;
import org.springframework.stereotype.Component;

@Component
public class LikeCommentMapper {

    public LikeComment toEntity(LikeCommentDTO dto) {
        return LikeComment.builder()
                .id(dto.getId()==null? null :dto.getId())
                .user(dto.getIdUser()== null ? null: User.builder()
                        .id(dto.getIdUser())
                        .build())
                .comment(dto.getIdComment()== null ? null: Comment.builder().id(dto.getIdComment()).build())
                .build();
    }

    public LikeComment toEntity(LikeCommentDTO dto, Long id) {
        return LikeComment.builder()
                .id(id)
                .user(dto.getIdUser()== null ? null: User.builder()
                        .id(dto.getIdUser())
                        .build())
                .comment(dto.getIdComment()== null ? null: Comment.builder().id(dto.getIdComment()).build())
                .build();
    }

    public LikeCommentDTO toDTO(LikeComment entity) {
        if (entity == null) {
            return null;
        }
        return LikeCommentDTO.builder()
                .id(entity.getId() == null ? null : entity.getId())
                .likeCount(entity.getLikeCount())
                .idUser(entity.getUser() == null ? null : entity.getUser().getId())
                .idComment(entity.getComment() == null ? null : entity.getComment().getId())
                .build();
    }
}
