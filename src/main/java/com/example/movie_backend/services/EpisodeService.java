package com.example.movie_backend.services;

import com.example.movie_backend.dto.episode.EpisodeDTO;
import com.example.movie_backend.dto.episode.EpisodeMapper;
import com.example.movie_backend.entity.Episode;
import com.example.movie_backend.repository.EpisodeRepository;
import com.example.movie_backend.services.interfaces.IEpisodeService;
import org.springframework.stereotype.Service;

import javax.ws.rs.BadRequestException;

@Service
public class EpisodeService implements IEpisodeService {
    public final EpisodeRepository repository;

    public final EpisodeMapper mapper;

    public EpisodeService(EpisodeRepository repository, EpisodeMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public EpisodeDTO create(EpisodeDTO dto) {
        Episode comment = mapper.toEntity(dto);
        return mapper.toDTO(repository.save(comment));
    }

    @Override
    public EpisodeDTO update(EpisodeDTO dto, Long id) {
        Episode comment = mapper.toEntity(dto, id);
        return mapper.toDTO(repository.save(comment));
    }

    @Override
    public EpisodeDTO getById(Long id) {
        return this.repository.findById(id)
                .map(this.mapper::toDTO)
                .orElseThrow(
                        () -> new BadRequestException("Movie not found")
                );
    }

    @Override
    public Boolean delete(Long id) {
        repository.deleteById(id);
        return true;
    }
}
