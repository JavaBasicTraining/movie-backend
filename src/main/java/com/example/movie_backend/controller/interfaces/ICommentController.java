package com.example.movie_backend.controller.interfaces;

import com.example.movie_backend.dto.comment.CommentDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/api/v1/comment/")
public interface ICommentController {
    @PostMapping(value = "create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<CommentDTO> create(@ModelAttribute @Valid CommentDTO comment);

    @PutMapping("update")
    ResponseEntity<CommentDTO> update(@RequestBody CommentDTO comment, @RequestParam Long id);

    @GetMapping("getById/{id}")
    ResponseEntity<CommentDTO> getById(@RequestParam Long id);

    @DeleteMapping("delete{id}")
    boolean delete(@RequestParam Long id);
}
