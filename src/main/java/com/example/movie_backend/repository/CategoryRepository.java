package com.example.movie_backend.repository;

import com.example.movie_backend.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query(
        value = """
                SELECT *
                FROM category c
                WHERE (:searchTerm IS NULL OR c.name LIKE %:searchTerm%)
                and (COALESCE(:excludeIds, NULL) IS NULL OR c.id NOT IN (:excludeIds))
                """,
        nativeQuery = true
    )
    Set<Category> filterCategory(@Param("searchTerm") String searchTerm,
                                 @Param("excludeIds") List<Long> excludeIds);

}
