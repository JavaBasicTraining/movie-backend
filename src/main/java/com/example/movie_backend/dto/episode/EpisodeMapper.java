package com.example.movie_backend.dto.episode;

import com.example.movie_backend.entity.Episode;
import com.example.movie_backend.entity.Movie;
import org.springframework.stereotype.Component;

@Component
public class EpisodeMapper {
    public Episode toEntity(EpisodeDTO dto) {
        return Episode.builder()
            .id(dto.getId())
            .episodeCount(dto.getEpisodeCount())
            .descriptions(dto.getDescriptions())
            .videoUrl(dto.getVideoUrl())
            .posterUrl(dto.getPosterUrl())
            .movie(dto.getMovie().getId() == null ? null :
                Movie.builder().id(
                    dto.getMovieId()
                ).build()
            )
            .build();
    }

// nayx em sửa chỗ này là nó lỗi á, chứ lúc đầu chạy bth

    public Episode toEntity(EpisodeDTO dto, Long id) {
        return Episode.builder()
            .id(id)
            .episodeCount(dto.getEpisodeCount())
            .descriptions(dto.getDescriptions())
            .videoUrl(dto.getVideoUrl())
            .posterUrl(dto.getPosterUrl())
            .movie(dto.getMovie().getId() == null ? null :
                Movie.builder().id(
                    dto.getMovieId()
                ).build()
            )
            .build();
    }

    public EpisodeDTO toDTO(Episode entity) {
        return EpisodeDTO.builder()
            .id(entity.getId())
            .episodeCount(entity.getEpisodeCount())
            .descriptions(entity.getDescriptions())
            .posterUrl(entity.getPosterUrl())
            .videoUrl(entity.getVideoUrl())
            .movieId(entity.getMovie().getId() == null ? null : entity.getMovie().getId())
            .build();
    }
}
