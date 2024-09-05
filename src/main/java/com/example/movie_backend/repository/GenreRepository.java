package com.example.movie_backend.repository;

import com.example.movie_backend.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
    @Query(
        value = """
            SELECT *
            FROM genre c
            WHERE (:searchTerm is null or c.name LIKE %:searchTerm%)
            and (coalesce(:excludeIds, null) is null or c.id not in (:excludeIds))
            """,
        nativeQuery = true
    )
    Set<Genre> filterGenre(@Param("searchTerm") String searchTerm,
                           @Param("excludeIds") List<Long> excludeIds);

}
