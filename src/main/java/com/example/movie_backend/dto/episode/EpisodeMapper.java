package com.example.movie_backend.dto.episode;

import com.example.movie_backend.entity.Episode;
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
                .movieId(dto.getMovieId())
                .build();
    }

    public Episode toEntity(EpisodeDTO dto,Long id)
    {
        return Episode.builder()
                .episodeCount(dto.getEpisodeCount())
                .posterUrl(dto.getPosterUrl())
                .videoUrl(dto.getVideoUrl())
                .movieId(dto.getMovieId())
                .build();
    }

    public EpisodeDTO toDTO (Episode entity)
    {
        return EpisodeDTO.builder()
                .id(entity.getId())
                .posterUrl(entity.getPosterUrl())
                .videoUrl(entity.getVideoUrl())
                .movieId(entity.getMovieId())
                .build();
    }
}
