package com.example.movie_backend.repository;

import com.example.movie_backend.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    @Query(
            value = """
                SELECT DISTINCT m.*
                FROM movie m
                LEFT JOIN movie_genres mc ON mc.movie_id = m.id
                LEFT JOIN genre c ON mc.genres_id = c.id
                WHERE (:keyword IS NULL
                       OR c.name IS NULL
                       OR c.name LIKE CONCAT('%', :keyword, '%'))
                  AND (:genre IS NULL OR c.name = :genre)
                  AND (:country IS NULL OR m.country = :country)
                """,
            nativeQuery = true
    )
    Page<Movie> query(
            @Param("keyword") String keyword,
            @Param("genre") String genre,
            @Param("country") String country,
            Pageable pageable
    );

    @Query(
            value = """
                    SELECT m.*
                    FROM movie_website.movie m
                    JOIN movie_website.evaluation e ON m.id = e.movie_id
                    ORDER BY e.star DESC
                    LIMIT 5;                  
                    """,
            nativeQuery = true
    )
    Set<Movie> nominatedFilm();

    @Query(
            value = """
                    SELECT distinct m.*
                    FROM movie_website.movie m
                    inner join movie_website.movie_genres mc
                    on m.id =mc.movie_id
                    inner join movie_website.genre c
                    on mc.genres_id = c .id
                    WHERE m.name LIKE %:name%
                    """,
            nativeQuery = true
    )
    Optional<Movie> filterMovie(@Param("name") String name);


}
