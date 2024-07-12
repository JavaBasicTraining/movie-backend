package com.example.movie_backend.repository;

import com.example.movie_backend.dto.movie.MovieDTO;
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
                    SELECT distinct  m.* FROM movie m
                    left join movie_genres mc on mc.movie_id = m.id
                    left join genre c on mc.genres_id = c.id
                    where (:keyword is null or c.name like concat('%', :keyword, '%'))
                    or (:keyword is null or m.name like concat('%', :keyword, '%'))
                    """,
            nativeQuery = true
    )
    Page<Movie> query(@Param("keyword") String keyword, Pageable pageable);

    @Query(
            value = """
                    SELECT *
                    FROM movie_website.movie m, 
                    WHERE m.name LIKE %:name%
                    """,
            nativeQuery = true
    )
    Set<Movie> filterListMovie(@Param("name") String name);

    @Query(
            value = """
                    SELECT *
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
