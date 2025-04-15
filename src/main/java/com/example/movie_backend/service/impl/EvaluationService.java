package com.example.movie_backend.service.impl;

import com.example.movie_backend.controller.exception.BadRequestException;
import com.example.movie_backend.dto.evaluation.EvaluationDTO;
import com.example.movie_backend.mapper.EvaluationMapper;
import com.example.movie_backend.entity.Evaluation;
import com.example.movie_backend.entity.Movie;
import com.example.movie_backend.repository.EvaluationRepository;
import com.example.movie_backend.repository.MovieRepository;
import com.example.movie_backend.service.IEvaluationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class EvaluationService implements IEvaluationService {
    public final EvaluationRepository repository;
    public final MovieRepository movieRepository;
    public final EvaluationMapper evaluationMapper;

    @Override
    public EvaluationDTO create(EvaluationDTO dto) {
        Evaluation evaluation = evaluationMapper.toEntity(dto);
        Movie movie = movieRepository.findById(dto.getMovieId()).orElse(null);
        if (Objects.nonNull(movie)) {
            movie.addEvaluation(evaluation);
        }
        evaluation = repository.save(evaluation);
        return evaluationMapper.toDTO(repository.save(evaluation));
    }

    @Override
    public EvaluationDTO update(EvaluationDTO dto, Long id) {
        return repository
            .findById(id)
            .map(evaluation -> {
                evaluation.setStar(dto.getStar());
                evaluation = repository.save(evaluation);
                return evaluationMapper.toDTO(evaluation);
            })
            .orElseThrow(
                () -> new RuntimeException("evaluation not found by id: " + id)
            );
    }

    @Override
    public EvaluationDTO getById(Long id) {
        return this.repository.findById(id)
            .map(this.evaluationMapper::toDTO)
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
