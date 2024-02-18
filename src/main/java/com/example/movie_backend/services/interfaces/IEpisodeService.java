package com.example.movie_backend.services.interfaces;

import com.example.movie_backend.dto.episode.EpisodeDTO;
import com.example.movie_backend.dto.episode.EpisodeDTO;
import com.example.movie_backend.entity.Episode;

import java.util.UUID;

public interface IEpisodeService {
    EpisodeDTO create(EpisodeDTO dto);

    EpisodeDTO update(EpisodeDTO dto, Long id);

    EpisodeDTO getById(Long id);


    Boolean delete(Long id);
}
