package com.example.movie_backend.controller.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.movie_backend.dto.comment.CommentDTO;

@RequestMapping("/api/v1/comment")
public interface ICommentController {
    @PostMapping("create")
    ResponseEntity<CommentDTO> create(@RequestBody CommentDTO comment);

    @PutMapping("update")
    ResponseEntity<CommentDTO> update(@RequestBody CommentDTO comment, @RequestParam Long movieId);

    @GetMapping("{id}")
    ResponseEntity<CommentDTO> getById(@PathVariable Long id);

    @DeleteMapping("{id}")
    boolean delete(@PathVariable Long id);

    @GetMapping
    ResponseEntity<List<CommentDTO>> getCommentByMovieId(@RequestParam Long commentId);

    @GetMapping("getCommentByUserId")
    ResponseEntity<List<CommentDTO>> getListCommentByMovieIdUserId(@RequestParam Long userId,
            @RequestParam Long movieId);

}
