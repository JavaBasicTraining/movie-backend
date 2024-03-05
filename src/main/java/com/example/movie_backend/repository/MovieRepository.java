package com.example.movie_backend.repository;

import com.example.movie_backend.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MovieRepository extends JpaRepository<Movie, UUID> {
    Optional<Movie> getByViTitle(String title);
    
    @Query("""
            select m from Movie m
            left join MovieCategory mc on m.id = mc.movie.id
            left join Category c on c.id = mc.category.id
            where (:categoryName is null or c.name like concat('%', :categoryName ,'%'))
        """)
    Page<Movie> query(
        @Param("categoryName") String categoryName,
        Pageable pageable
    );
}
