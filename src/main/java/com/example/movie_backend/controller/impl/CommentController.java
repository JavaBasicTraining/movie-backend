package com.example.movie_backend.controller.impl;

import com.example.movie_backend.controller.ICommentController;
import com.example.movie_backend.controller.dto.request.RepliesCountResponse;
import com.example.movie_backend.controller.dto.request.TotalLikesResponse;
import com.example.movie_backend.dto.comment.CommentDTO;
import com.example.movie_backend.service.ICommentService;
import com.example.movie_backend.util.HeaderUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController implements ICommentController {

    public final ICommentService commentService;
    private final SimpMessagingTemplate messagingTemplate;
    @MessageMapping("/sendComment")
    @SendTo("/topic/comments")
    public CommentDTO sendComment(@Payload CommentDTO comment, SimpMessageHeaderAccessor headerAccessor) {
        if (headerAccessor.getSessionAttributes() == null) {
            throw new IllegalArgumentException("Session attributes are missing");
        }
        String userName = (String) headerAccessor.getSessionAttributes().get("user");
        List<String> roles = (List<String>) headerAccessor.getSessionAttributes().get("roles");
        if (userName == null || roles == null || roles.isEmpty()) {
            throw new IllegalArgumentException("User not authenticated");
        }
        if (!roles.contains("user") && !roles.contains("admin")) {
            throw new IllegalArgumentException("Permission denied: Only 'user' or 'admin' can send messages.");
        }
        CommentDTO savedComment = commentService.create(comment);
        messagingTemplate.convertAndSend("/topic/replies", savedComment);
        return savedComment;
    }
    @Override
    public ResponseEntity<CommentDTO> create(CommentDTO comment) {
        return ResponseEntity.ok(commentService.create(comment));
    }

    @Override
    public ResponseEntity<CommentDTO> update(Long id, CommentDTO commentDTO) {
        return ResponseEntity.ok(commentService.update(id, commentDTO));
    }

    @Override
    public ResponseEntity<CommentDTO> update(CommentDTO comment, Long commentId) {
        return ResponseEntity.ok(commentService.update(comment, commentId));
    }


    @Override
    public ResponseEntity<CommentDTO> getById(Long id) {
        return ResponseEntity.ok(commentService.getById(id));
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        commentService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<CommentDTO>> getCommentByMovieId(Long movieId, Pageable pageable) {
        Page<CommentDTO> commentDTOPage = commentService.getCommentByMovieId(movieId, pageable);
        return ResponseEntity.status(HttpStatus.OK)
                .headers(HeaderUtils.buildTotalSizeHeader(commentDTOPage.getTotalElements()))
                .body(commentDTOPage.getContent());
    }

    @Override
    public ResponseEntity<List<CommentDTO>> getReplies(Long id, Pageable pageable) {
        Page<CommentDTO> commentDTOPage = commentService.getReplies(id, pageable);
        return ResponseEntity.status(HttpStatus.OK)
                .headers(HeaderUtils.buildTotalSizeHeader(commentDTOPage.getTotalElements()))
                .body(commentDTOPage.getContent());
    }

    @Override
    public ResponseEntity<List<CommentDTO>> getListCommentByMovieIdUserId(Long userId, Long movieId) {
        return ResponseEntity.ok(commentService.getListCommentByMovieIdUserId(userId, movieId));
    }

    @Override
    public ResponseEntity<TotalLikesResponse> likeOrUnlike(Long commentId, Boolean isLike) {
        return ResponseEntity.ok(commentService.likeOrUnlike(commentId, isLike));
    }

    @Override
    public ResponseEntity<TotalLikesResponse> getLikeCount(Long commentId) {
        return ResponseEntity.ok(commentService.getLikeCount(commentId));
    }

    @Override
    public ResponseEntity<RepliesCountResponse> getRepliesCount(Long commentId) {
        return ResponseEntity.ok(commentService.getRepliesCount(commentId));
    }
}