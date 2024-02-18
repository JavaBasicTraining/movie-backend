package com.example.movie_backend.repository;

import com.example.movie_backend.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    Optional<Movie> getByViTitle(String title);
}
