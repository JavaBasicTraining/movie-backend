package com.example.movie_backend.controller;

import com.example.movie_backend.controller.dto.request.RepliesCountResponse;
import com.example.movie_backend.controller.dto.request.TotalLikesResponse;
import com.example.movie_backend.dto.comment.CommentDTO;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/comment")
public interface ICommentController {
    @PostMapping
    ResponseEntity<CommentDTO> create(@RequestBody CommentDTO comment);

    @PutMapping("{id}")
    ResponseEntity<CommentDTO> update(@PathVariable("id") Long id, @RequestBody CommentDTO commentDTO);

    @PutMapping("update")
    ResponseEntity<CommentDTO> update(@RequestBody CommentDTO comment, @RequestParam Long movieId);

    @GetMapping("{id}")
    ResponseEntity<CommentDTO> getById(@PathVariable Long id);

    @DeleteMapping("{id}")
    ResponseEntity<Void> delete(@PathVariable Long id,@ParameterObject Pageable pageable);

    @GetMapping
    ResponseEntity<List<CommentDTO>> getCommentByMovieId(@RequestParam Long movieId, @ParameterObject Pageable pageable);

    @GetMapping("{id}/replies")
    ResponseEntity<List<CommentDTO>> getReplies(@PathVariable("id") Long id, @ParameterObject Pageable pageable);

    @GetMapping("getCommentByUserId")
    ResponseEntity<List<CommentDTO>> getListCommentByMovieIdUserId(@RequestParam Long userId,
                                                                   @RequestParam Long movieId);

    @PutMapping("{id}/like/{isLike}")
    ResponseEntity<TotalLikesResponse> likeOrUnlike(@PathVariable("id") Long commentId, @PathVariable("isLike") Boolean isLike);

    @GetMapping("{id}/like-count")
    ResponseEntity<TotalLikesResponse> getLikeCount(@PathVariable("id") Long commentId);

    @GetMapping("{id}/replies-count")
    ResponseEntity<RepliesCountResponse> getRepliesCount(@PathVariable("id") Long commentId);
}