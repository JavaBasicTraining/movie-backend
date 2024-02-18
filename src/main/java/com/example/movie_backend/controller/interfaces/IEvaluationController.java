package com.example.movie_backend.controller.interfaces;

import com.example.movie_backend.dto.evaluation.EvaluationDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/evaluation/")
public interface IEvaluationController {

    @PostMapping("create")
    ResponseEntity<EvaluationDTO> create(@RequestBody EvaluationDTO evaluation);

    @PutMapping("update")
    ResponseEntity<EvaluationDTO> update(@RequestBody EvaluationDTO evaluation, @RequestParam Long id);

    @GetMapping("getById/{id}")
    ResponseEntity<EvaluationDTO> getById(@RequestParam Long id);
    @DeleteMapping("delete{id}")
    boolean delete(@RequestParam Long id);
}
