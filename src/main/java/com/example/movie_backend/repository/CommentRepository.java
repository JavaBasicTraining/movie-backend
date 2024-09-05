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
               SELECT c.*
    FROM movie_website.comment c
    WHERE  c.movie_id = :movieId     
                    """, nativeQuery = true
    )
    List<Comment> getCommentByMovieId(@Param("movieId") Long movieId);


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
