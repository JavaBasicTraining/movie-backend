package com.example.movie_backend.services;

import com.example.movie_backend.dto.evaluation.EvaluationDTO;
import com.example.movie_backend.dto.evaluation.EvaluationMapper;
import com.example.movie_backend.entity.Evaluation;
import com.example.movie_backend.entity.Movie;
import com.example.movie_backend.repository.EvaluationRepository;
import com.example.movie_backend.repository.MovieRepository;
import com.example.movie_backend.services.interfaces.IEvaluationService;
import org.springframework.stereotype.Service;

import javax.ws.rs.BadRequestException;
import java.util.Objects;

@Service
public class EvaluationService implements IEvaluationService {
    public final EvaluationRepository repository;
    public final MovieRepository movieRepository;
    public final EvaluationMapper mapper;

    public EvaluationService(EvaluationRepository repository, MovieRepository movieRepository, EvaluationMapper mapper) {
        this.repository = repository;
        this.movieRepository = movieRepository;
        this.mapper = mapper;
    }

    @Override
    public EvaluationDTO create(EvaluationDTO dto) {
        Evaluation evaluation = mapper.toEntity(dto);
        Movie movie = movieRepository.findById(dto.getMovieId()).orElse(null);
        if (Objects.nonNull(movie)) {
            movie.addEvaluation(evaluation);
        }
        evaluation = repository.save(evaluation);
        return mapper.toDTO(repository.save(evaluation));
    }

    @Override
    public EvaluationDTO update(EvaluationDTO dto, Long id) {
        return repository
                .findById(id)
                .map(evaluation -> {
                    evaluation.setStar(dto.getStar());
                    evaluation = repository.save(evaluation);
                    return mapper.toDTO(evaluation);
                })
                .orElseThrow(
                        () -> new RuntimeException("evaluation not found by id: " + id)
                );
    }

    @Override
    public EvaluationDTO getById(Long id) {
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

    public Float average(float movieId) {

        return repository.average(movieId);
    }

}
