package com.example.movie_backend.controller;

import com.example.movie_backend.controller.interfaces.IEvaluationController;
import com.example.movie_backend.dto.evaluation.EvaluationDTO;
import com.example.movie_backend.services.EvaluationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EvaluationController implements IEvaluationController {

    public final EvaluationService service;

    public EvaluationController(EvaluationService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<EvaluationDTO> create(EvaluationDTO evaluation) {
        return ResponseEntity.ok(service.create(evaluation));

    }

    @Override
    public ResponseEntity<EvaluationDTO> update(EvaluationDTO evaluation, Long id) {
        return ResponseEntity.ok(service.update(evaluation,id));

    }

    @Override
    public ResponseEntity<EvaluationDTO> getById(Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @Override
    public boolean delete(Long id) {
         ResponseEntity.ok(service.delete(id));

        return true;
    }
}
