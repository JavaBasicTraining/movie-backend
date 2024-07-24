package com.example.movie_backend.controller;

import com.example.movie_backend.controller.interfaces.ICommentController;
import com.example.movie_backend.dto.comment.CommentDTO;
import com.example.movie_backend.services.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class CommentController implements ICommentController {

    public final CommentService service;

    public CommentController(CommentService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<CommentDTO>     create(CommentDTO comment) {
        return ResponseEntity.ok(service.create(comment));
    }

    @Override
    public ResponseEntity<CommentDTO> update(CommentDTO comment, Long id) {
        return ResponseEntity.ok(service.update(comment,id));
    }

    @Override
    public ResponseEntity<CommentDTO> getById(Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @Override
    public boolean delete(Long id) {
        ResponseEntity.ok(service.delete(id));
        return true;
    }
}
