package com.example.movie_backend.dto.episode;

import com.example.movie_backend.entity.Episode;
import com.example.movie_backend.entity.Movie;
import org.springframework.stereotype.Component;

import javax.persistence.Column;

@Component
public class EpisodeMapper {
    public Episode toEntity(EpisodeDTO dto)
    {
        return Episode.builder()
                .episodeCount(dto.getEpisodeCount())
                .posterUrl(dto.getPosterUrl())
                .videoUrl(dto.getVideoUrl())
                .movie(Movie.builder().id(dto.getMovieId()).build())
                .build();
    }

    public Episode toEntity(EpisodeDTO dto,Long id)
    {
        return Episode.builder()
                .episodeCount(dto.getEpisodeCount())
                .posterUrl(dto.getPosterUrl())
                .videoUrl(dto.getVideoUrl())
                .movie(Movie.builder().id(dto.getMovieId()).build())
                .build();
    }

    public EpisodeDTO toDTO (Episode entity)
    {
        return EpisodeDTO.builder()
                .id(entity.getId())
                .posterUrl(entity.getPosterUrl())
                .videoUrl(entity.getVideoUrl())
                .movieId(entity.getMovie().getId())
                .build();
    }
}
