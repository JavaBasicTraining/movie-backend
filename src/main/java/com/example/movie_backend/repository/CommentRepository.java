package com.example.movie_backend.repository;

import com.example.movie_backend.dto.comment.CommentDTO;
import com.example.movie_backend.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query(
            value = """
                    SELECT new com.example.movie_backend.dto.comment.CommentDTO(c, COALESCE(SUM(lc.likeCount), 0))
                    FROM Comment c
                    LEFT JOIN LikeComment lc ON c.id = lc.comment.id
                    WHERE c.movie.id = :movieId
                    GROUP BY c.id, c.user.id, c.movie.id, c.user.username, c.content, c.currentDate
                    """
    )
    List<CommentDTO> getCommentByMovieId(@Param("movieId") Long movieId);

    @Query(
            value = """
                    SELECT c.*
                    FROM movie_website.comment c
                    WHERE c.user_id = :userId AND c.movie_id = :movieId
                    """, nativeQuery = true
    )
    List<Comment> getListCommentByMovieIdUserId(@Param("userId") Long userId,
                                                @Param("movieId") Long movieId);


}
