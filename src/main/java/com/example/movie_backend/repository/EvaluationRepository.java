package com.example.movie_backend.repository;

import com.example.movie_backend.entity.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {
    @Query(value = """
                SELECT *
                FROM movie_website.evaluation
                where user_id = :userId and movie_id = :movieId
            """, nativeQuery = true)
    Optional<Evaluation> findByEvaluationByUserId(@Param("userId") Long userId,
                                                  @Param("movieId") Long movieId);

    @Query(value = """
            SELECT AVG(star)
            FROM evaluation
            WHERE movie_id = :movieId
            """, nativeQuery = true)
    Float average(@Param("movieId") float movieId);


    @Query(value = """
            SELECT COUNT(*) FROM evaluation where movie_id = :movieId 
                                         
            """, nativeQuery = true)
    Long numberOfReviews(@Param("movieId") Long movieId);

}
