package com.example.movie_backend.mapper;

import com.example.movie_backend.dto.comment.CommentDTO;
import com.example.movie_backend.dto.like_comment.LikeCommentDTO;
import com.example.movie_backend.dto.user.UserDTO;
import com.example.movie_backend.entity.Comment;
import com.example.movie_backend.entity.LikeComment;
import com.example.movie_backend.entity.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface LikeCommentMapper {

    @Mapping(target = "liked", ignore = true)
    @Mapping(target = "comment", source = "comment", qualifiedByName = "commentDtoToComment")
    @Mapping(target = "user", source = "user", qualifiedByName = "userDtoToUser")
    LikeComment toEntity(LikeCommentDTO dto);


    @Mapping(target = "liked", ignore = true)
    @Mapping(target = "comment", source = "dto.comment", qualifiedByName = "commentDtoToComment")
    @Mapping(target = "user", source = "dto.user", qualifiedByName = "userDtoToUser")
    @Mapping(target = "id", source = "id")
    LikeComment toEntity(LikeCommentDTO dto, Long id);

    @Mapping(target = "user", source = "user", qualifiedByName = "userToUserDto")
    @Mapping(target = "comment", source = "comment", qualifiedByName = "commentToCommentDto")
    LikeCommentDTO toDTO(LikeComment entity);

    @Named("userDtoToUser")
    static User mapToUserEntity(UserDTO userDTO) {
        if (userDTO == null || userDTO.getId() == null) {
            return null;
        }
        return User.builder().id(userDTO.getId()).build();
    }

    @Named("userToUserDto")
    static UserDTO mapToUserDTO(User user) {
        if (user == null || user.getId() == null) {
            return null;
        }
        return UserDTO.builder().id(user.getId()).build();
    }

    @Named("commentDtoToComment")
    static Comment mapToCommentEntity(CommentDTO commentDTO) {
        if (commentDTO == null || commentDTO.getId() == null) {
            return null;
        }
        return Comment.builder().id(commentDTO.getId()).build();
    }

    @Named("commentToCommentDto")
    static CommentDTO mapToCommentDTO(Comment comment) {
        if (comment == null || comment.getId() == null) {
            return null;
        }
        return CommentDTO.builder().id(comment.getId()).build();
    }
}
