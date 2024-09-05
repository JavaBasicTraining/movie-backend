package com.example.movie_backend.services.interfaces;

import com.example.movie_backend.dto.evaluation.EvaluationDTO;

public interface IEvaluationService {

    EvaluationDTO create(EvaluationDTO dto);

    EvaluationDTO update(EvaluationDTO dto, Long id);

    EvaluationDTO getById(Long id);


    Boolean delete(Long id);
}
