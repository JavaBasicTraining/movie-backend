package com.example.movie_backend.repository;

import com.example.movie_backend.entity.Episode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface EpisodeRepository extends JpaRepository<Episode, Long> {
    @Query(value = """
            SELECT * 
            FROM movie_website.episode
            WHERE movie_id = :movieId
            ORDER BY episode_count asc;
                                                                                                 """, nativeQuery = true)
    Set<Episode> getListEpisodeByMovieId(@Param("movieId") Long movieId);

    @Query(value = """
            SELECT * 
            FROM movie_website.episode 
            WHERE movie_id = :movieId  
            and episode_count = :episodeCount  
                                                                                                           """, nativeQuery = true)
    Optional<Episode> getEpisodeByMovieId(@Param("movieId") Long movieId,
                                          @Param("episodeCount") Long episodeCount);


}

