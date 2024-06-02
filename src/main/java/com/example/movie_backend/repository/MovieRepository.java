package com.example.movie_backend.repository;

import com.example.movie_backend.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    @Query(
            value = """
                    SELECT m.* FROM movie m
                    left join movie_category mc on mc.movie_id = m.id 
                    left join category c on mc.category_id = c.id 
                    where c.name is null or c.name like concat('%', :name, '%')
                    """,
            nativeQuery = true // required , dang viet query native
    )
    Set<Movie> query(@Param("name") String name);
}
