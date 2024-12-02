package com.example.movie_backend.controller;

import com.example.movie_backend.controller.interfaces.ICommentController;
import com.example.movie_backend.dto.comment.CommentDTO;
import com.example.movie_backend.service.impl.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

public class CommentController implements ICommentController {

    public final CommentService service;

    public CommentController(CommentService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<CommentDTO> create(CommentDTO comment) {
        return ResponseEntity.ok(service.create(comment));
    }

    @Override
    public ResponseEntity<CommentDTO> update(CommentDTO comment, Long commentId) {
        return ResponseEntity.ok(service.update(comment, commentId));
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

    @Override
    public ResponseEntity<List<CommentDTO>> getCommentByMovieId(Long movieId) {
        return ResponseEntity.ok(service.getCommentByMovieId(movieId));
    }

    @Override
    public ResponseEntity<List<CommentDTO>> getListCommentByMovieIdUserId(Long userId, Long movieId) {
        return ResponseEntity.ok(service.getListCommentByMovieIdUserId(userId, movieId));
    }


}
