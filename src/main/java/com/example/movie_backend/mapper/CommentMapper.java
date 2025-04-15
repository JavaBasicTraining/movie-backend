package com.example.movie_backend.mapper;

import com.example.movie_backend.dto.comment.CommentDTO;
import com.example.movie_backend.dto.movie.MovieDTO;
import com.example.movie_backend.dto.user.UserDTO;
import com.example.movie_backend.entity.Comment;
import com.example.movie_backend.entity.Movie;
import com.example.movie_backend.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mapping(target = "totalReplies", ignore = true)
    @Mapping(target = "totalLikes", ignore = true)
    @Mapping(target = "likeComment", ignore = true)
    @Mapping(target = "idUser", ignore = true)
    @Mapping(target = "idMovie", ignore = true)
    @Mapping(target = "currentDate", ignore = true)
    @Mapping(target = "movie", ignore = true)
    @Mapping(target = "parentComment", ignore = true)
    @Mapping(target = "parentCommentId", source = "parentComment.id")
    CommentDTO toDTO(Comment comment);

    @Mapping(target = "likeComments", ignore = true)
    @Mapping(target = "parentComment", expression = "java(mapOnlyIdToComment(commentDTO.getParentComment()))")
    @Mapping(target = "user", expression = "java(mapToUser(commentDTO.getUser()))")
    @Mapping(target = "movie", expression = "java(mapToMovie(commentDTO.getMovie()))")
    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    Comment toEntity(CommentDTO commentDTO);

    @Mapping(target = "likeComments", ignore = true)
    @Mapping(target = "parentComment", expression = "java(mapOnlyIdToComment(commentDTO.getParentComment()))")
    @Mapping(target = "user", expression = "java(commentDTO.getIdUser() != null ? com.example.movie_backend.entity.User.builder().id(commentDTO.getIdUser()).username(commentDTO.getUser().getUserName()).build() : null)")
    @Mapping(target = "movie", expression = "java(commentDTO.getIdMovie() != null ? com.example.movie_backend.entity.Movie.builder().id(commentDTO.getIdMovie()).build() : null)")
    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    Comment toEntity(CommentDTO commentDTO, Long id);

    @Named("mapOnlyIdToComment")
    default Comment mapOnlyIdToComment(CommentDTO dto) {
        if (dto == null || dto.getId() == null) return null;
        Comment comment = new Comment();
        comment.setId(dto.getId());
        return comment;
    }

    default User mapToUser(UserDTO dto) {
        if (dto == null || dto.getId() == null) return null;
        User user = new User();
        user.setId(dto.getId());
        user.setUsername(dto.getUserName());
        return user;
    }

    default Movie mapToMovie(MovieDTO dto) {
        if (dto == null || dto.getId() == null) return null;
        Movie movie = new Movie();
        movie.setId(dto.getId());
        return movie;
    }


}
