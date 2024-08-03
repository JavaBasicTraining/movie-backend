package com.example.movie_backend.controller;

import com.example.movie_backend.controller.interfaces.IEvaluationController;
import com.example.movie_backend.dto.evaluation.EvaluationDTO;
import com.example.movie_backend.dto.evaluation.EvaluationMapper;
import com.example.movie_backend.entity.Evaluation;
import com.example.movie_backend.repository.EvaluationRepository;
import com.example.movie_backend.services.EvaluationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.GET;
import java.util.Objects;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class EvaluationController implements IEvaluationController {

    public final EvaluationService service;

    public final EvaluationRepository repository;

    public final EvaluationMapper mapper;


    @Override
    public ResponseEntity<EvaluationDTO> create(EvaluationDTO evaluation) {
        return ResponseEntity.ok(service.create(evaluation));

    }

    @Override
    public ResponseEntity<EvaluationDTO> update(EvaluationDTO evaluation, Long id) {
        return ResponseEntity.ok(service.update(evaluation, id));

    }

    @Override
    public ResponseEntity<EvaluationDTO> getById(Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @Override
    public boolean delete(Long id) {
        ResponseEntity.ok(service.getById(id));

        return true;
    }

    @GetMapping("/evaluations/user/{userId}/movie/{movieId}")
    public ResponseEntity<EvaluationDTO> getEvaluation(
            @PathVariable("userId") Long userId,
            @PathVariable("movieId") Long movieId) {

        Optional<Evaluation> evaluationOptional = repository.findByEvaluationByUserId(userId, movieId);
        if (!evaluationOptional.isPresent() && evaluationOptional.isEmpty()) {
            return null;
        }
        Evaluation evaluation = evaluationOptional.get();
        EvaluationDTO evaluationDTO = mapper.toDTO(evaluation);
        return ResponseEntity.ok(evaluationDTO);
    }

    @GetMapping ("/average/{movieId}")
    public ResponseEntity<Float> Average (@PathVariable float movieId)
    {
        return ResponseEntity.ok(service.average(movieId));
    }


    @GetMapping("numberOfReviews/{movieId}")
    public ResponseEntity<Long> numberOfReviews ( @PathVariable Long movieId)
    {
        if(Objects.isNull(movieId))
        {
            return null;
        }else
        return ResponseEntity.ok(repository.numberOfReviews(movieId));
    }

}
