package com.example.movie_backend.repository;

import com.example.movie_backend.entity.Episode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EpisodeRepository extends JpaRepository<Episode, Long> {
}

