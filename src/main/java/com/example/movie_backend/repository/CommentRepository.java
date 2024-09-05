package com.example.movie_backend.repository;

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
            SELECT *
            FROM movie_website.comment c
            LEFT JOIN movie_website.movie_comment mc
            ON c.id = mc.comment_id
            LEFT JOIN movie_website.movie m
            ON mc.movie_id = m.id
            WHERE m.id = :movieId            
            """, nativeQuery = true
    )
    List<Comment> getCommentByMovieId(@Param("movieId") Long movieId);


    @Query(
        value = """
            SELECT c.*
            FROM movie_website.comment c
            LEFT JOIN movie_comment mc ON c.id = mc.comment_id
            WHERE c.user_id = :userId AND mc.movie_id = :movieId
            """, nativeQuery = true
    )
    List<Comment> getListCommentByMovieIdUserId(@Param("userId") Long userId,
                                                @Param("movieId") Long movieId);

}
