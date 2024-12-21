package com.example.movie_backend.repository;

import com.example.movie_backend.entity.LikeComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeCommentRepository extends JpaRepository<LikeComment, Long> {
    @Query(value = """
               SELECT *
                FROM like_comment lc
                WHERE  lc.movie_id = :movieId
                and lc.user_id = :userId
            """, nativeQuery = true)
    List<LikeComment> findLikeCommentByUserIdAndMovieId(@Param("movieId") Long movieId,
                                                        @Param("userId") Long userId);
}
