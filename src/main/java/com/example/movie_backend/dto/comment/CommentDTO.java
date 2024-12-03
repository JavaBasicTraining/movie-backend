package com.example.movie_backend.dto.comment;

import com.example.movie_backend.dto.like_comment.LikeCommentDTO;
import com.example.movie_backend.dto.movie.MovieDTO;
import com.example.movie_backend.dto.user.UserDTO;
import com.example.movie_backend.entity.Comment;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import jakarta.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class CommentDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotBlank(message = "Content cannot be empty")
    private String content;

    private Long idUser;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UserDTO user;

    private Long idMovie;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date currentDate;

    @JsonIgnore
    private MovieDTO movie;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long totalLikes;

    private Long parentCommentId;

    @JsonIgnore
    private LikeCommentDTO likeComment;

    @Builder.Default
    private List<CommentDTO> replies = new ArrayList<>();

    public CommentDTO(Comment comment, Long totalLikes) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.idUser = comment.getUser() != null ? comment.getUser().getId() : null;
        this.idMovie = comment.getMovie() != null ? comment.getMovie().getId() : null;
        this.totalLikes = totalLikes;
        if (comment.getUser() != null) {this.user = new UserDTO(comment.getUser());}
        this.currentDate = comment.getCurrentDate();
        this.parentCommentId = comment.getParentComment() != null ? comment.getParentComment().getId() : null;
        for (Comment sub : comment.getReplies()) {
            this.replies.add(new CommentDTO(sub, 0L));
        }
    }
}
