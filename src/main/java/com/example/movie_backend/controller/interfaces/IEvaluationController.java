package com.example.movie_backend.controller.interfaces;

import com.example.movie_backend.dto.evaluation.EvaluationDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/api/v1/evaluations")
public interface IEvaluationController {

    @PostMapping
    ResponseEntity<EvaluationDTO> create(@RequestBody @Valid EvaluationDTO evaluation);

    @PutMapping("{id}")
    ResponseEntity<EvaluationDTO> update(@RequestBody @Valid EvaluationDTO evaluation,
                                         @PathVariable("id") Long id);

    @GetMapping("{id}")
    ResponseEntity<EvaluationDTO> getById(@PathVariable("id") Long id);

    @DeleteMapping("{id}")
    boolean delete(@PathVariable("id") Long id);

}
