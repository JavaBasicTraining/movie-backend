package com.example.movie_backend.services;

import com.example.movie_backend.dto.evaluation.EvaluationDTO;
import com.example.movie_backend.dto.evaluation.EvaluationMapper;
import com.example.movie_backend.entity.Evaluation;
import com.example.movie_backend.repository.EvaluationRepository;
import com.example.movie_backend.services.interfaces.IEvaluationService;
import org.springframework.stereotype.Service;

import javax.ws.rs.BadRequestException;

@Service
public class EvaluationService implements IEvaluationService {
    public  final EvaluationRepository repository;

    public final EvaluationMapper mapper;
    public EvaluationService(EvaluationRepository repository, EvaluationMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public EvaluationDTO create(EvaluationDTO dto) {
        Evaluation comment = mapper.toEntity(dto);
        return mapper.toDTO(repository.save(comment));
    }

    @Override
    public EvaluationDTO update(EvaluationDTO dto, Long id) {
        Evaluation comment = mapper.toEntity(dto,id);
        return mapper.toDTO(repository.save(comment));
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
}
