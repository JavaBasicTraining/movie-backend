package com.example.movie_backend.dto.comment;

import com.example.movie_backend.dto.like_comment.LikeCommentDTO;
import com.example.movie_backend.dto.movie.MovieDTO;
import com.example.movie_backend.dto.user.UserDTO;
import com.example.movie_backend.entity.Comment;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import javax.validation.constraints.NotBlank;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class CommentDTO {
    private Long id;

    @NotBlank(message = "Content cannot be empty")
    private String content;

    private Long idUser;

    private UserDTO user;

    private CommentDTO parentComment;

    private Long idMovie;

    private Instant createdDate;

    private Date currentDate;

    private MovieDTO movie;

    private Long totalLikes;

    private Long totalReplies;

    private Long parentCommentId;

    private LikeCommentDTO likeComment;

    @Builder.Default
    private List<CommentDTO> replies = new ArrayList<>();

    public CommentDTO(Comment comment, Long totalReplies) {
        this(comment);
        this.idUser = comment.getUser() != null ? comment.getUser().getId() : null;
        this.idMovie = comment.getMovie() != null ? comment.getMovie().getId() : null;
        this.parentCommentId = comment.getParentComment() != null ? comment.getParentComment().getId() : null;
        this.totalReplies = totalReplies;
    }

    public CommentDTO(Comment comment, Long totalReplies, Long totalLikes) {
        this(comment, totalReplies);
        this.totalLikes = totalLikes;
    }

    public CommentDTO(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.createdDate = comment.getCreatedDate();

        if (comment.getUser() != null) {
            this.user = new UserDTO(comment.getUser());
        }

        if (comment.getMovie() != null) {
            this.movie = new MovieDTO();
        }

        if(comment.getParentComment() != null) {
            this.parentComment = new CommentDTO(comment.getParentComment());
        }
    }

}