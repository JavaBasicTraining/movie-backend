package com.example.movie_backend.controller.impl;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.movie_backend.controller.ILikeCommentController;
import com.example.movie_backend.dto.like_comment.LikeCommentDTO;
import com.example.movie_backend.service.impl.LikeCommentService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class LikeCommentController implements ILikeCommentController {

    private final LikeCommentService service;

    @Override
    public ResponseEntity<LikeCommentDTO> create(LikeCommentDTO likeCommentDTO) {
        return ResponseEntity.ok(service.createOrUpdate(likeCommentDTO));
    }

    @Override
    public ResponseEntity<LikeCommentDTO> update(LikeCommentDTO likeComment, Long id) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        service.delete(id);
        return true;
    }

    @GetMapping("/user/{userId}/movie/{movieId}")
    public ResponseEntity<List<LikeCommentDTO>> findLikeCommentByUserIdAndMovieId(@PathVariable("userId") Long userId,
                                                                                  @PathVariable("movieId") Long movieId) {
        return ResponseEntity.ok(service.findLikeCommentByUserIdAndMovieId(movieId, userId));
    }
}