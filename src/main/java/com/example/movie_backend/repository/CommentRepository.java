package com.example.movie_backend.repository;

import com.example.movie_backend.dto.comment.CommentDTO;
import com.example.movie_backend.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
                    CAST(COUNT(DISTINCT c2.id) as long)
                )
                FROM Comment c
                LEFT JOIN Comment c2 ON c.id = c2.parentComment.id
                WHERE c.movie.id = :movieId
                AND c.parentComment IS NULL
                GROUP BY c.id
                """)
    Page<CommentDTO> getCommentByMovieId(@Param("movieId") Long movieId, Pageable pageable);


    @Query(
            value = """
                    SELECT c.*
                    FROM comment c
                    WHERE c.user_id = :userId AND c.movie_id = :movieId
                    """, nativeQuery = true
    )
    List<Comment> getListCommentByMovieIdUserId(@Param("userId") Long userId,
                                                @Param("movieId") Long movieId);

    @Query(value = """
                SELECT new com.example.movie_backend.dto.comment.CommentDTO(
                    c,
                    CAST(COUNT(COALESCE(c2.id, NULL)) AS long)
                )
                FROM Comment c
                LEFT JOIN Comment c2 ON c.id = c2.parentComment.id
                WHERE c.parentComment.id = :id
                GROUP BY c.id
                """)
    Page<CommentDTO> getReplies(Long id, Pageable pageable);
}
