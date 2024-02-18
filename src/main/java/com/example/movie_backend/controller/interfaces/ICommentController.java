package com.example.movie_backend.controller.interfaces;

import com.example.movie_backend.dto.comment.CommentDTO;
import com.example.movie_backend.dto.comment.CommentDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/comment/")
public interface ICommentController {
    @PostMapping("create")
    ResponseEntity<CommentDTO> create(@RequestBody CommentDTO comment);

    @PutMapping("update")
    ResponseEntity<CommentDTO> update(@RequestBody CommentDTO comment, @RequestParam Long id);

    @GetMapping("getById/{id}")
    ResponseEntity<CommentDTO> getById(@RequestParam Long id);
    @DeleteMapping("delete{id}")
    boolean delete(@RequestParam Long id);
}
