package com.example.movie_backend.controller;

import com.example.movie_backend.controller.interfaces.IEvaluationController;
import com.example.movie_backend.entity.Evaluation;
import com.example.movie_backend.services.interfaces.IBaseService;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class EvaluationController extends BaseController<Evaluation, UUID> implements IEvaluationController {
    public EvaluationController(IBaseService<Evaluation, UUID> service) {
        super(service);
    }
}
