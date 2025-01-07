
package com.example.movie_backend.dto.like_comment;

import com.example.movie_backend.dto.comment.CommentDTO;
import com.example.movie_backend.dto.user.UserDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class LikeCommentDTO {
    private Long id;

    private Boolean liked;

    @NotNull
    private UserDTO user;

    @NotNull
    private CommentDTO comment;
}
