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
        @Query(value = """
                SELECT new com.example.movie_backend.dto.comment.CommentDTO(
                    c,
                    CAST(COUNT(CASE WHEN lc.liked = true THEN 1 ELSE NULL END) as long)
                )
                FROM Comment c
                LEFT JOIN LikeComment lc ON c.id = lc.comment.id
                WHERE c.movie.id = :movieId
                AND c.parentComment IS NULL
                GROUP BY c.id, c.user.id, c.movie.id, c.user.username, c.content, c.currentDate
                ORDER BY c.currentDate DESC
                """)
        List<CommentDTO> getCommentByMovieId(@Param("movieId") Long movieId);

        @Query(value = """
                        SELECT c.*
                        FROM movie_website.comment c
                        WHERE c.user_id = :userId AND c.movie_id = :movieId
                        """, nativeQuery = true)
        List<Comment> getListCommentByMovieIdUserId(@Param("userId") Long userId,
                        @Param("movieId") Long movieId);
}
