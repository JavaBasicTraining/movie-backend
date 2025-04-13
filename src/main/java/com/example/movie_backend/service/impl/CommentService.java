package com.example.movie_backend.service.impl;

import com.example.movie_backend.controller.dto.request.RepliesCountResponse;
import com.example.movie_backend.controller.dto.request.TotalLikesResponse;
import com.example.movie_backend.controller.exception.BadRequestException;
import com.example.movie_backend.dto.comment.CommentDTO;
import com.example.movie_backend.dto.like_comment.LikeCommentDTO;
import com.example.movie_backend.entity.Comment;
import com.example.movie_backend.entity.LikeComment;
import com.example.movie_backend.entity.User;
import com.example.movie_backend.enumerate.CommentAction;
import com.example.movie_backend.mapper.CommentMapper;
import com.example.movie_backend.repository.CommentRepository;
import com.example.movie_backend.repository.LikeCommentRepository;
import com.example.movie_backend.repository.UserRepository;
import com.example.movie_backend.service.ICommentService;
import com.example.movie_backend.service.ILikeCommentService;
import com.example.movie_backend.util.SecurityUtils;
import com.example.movie_backend.wrapper.CommentMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Id;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
@RequiredArgsConstructor
public class CommentService implements ICommentService {
    public final CommentMapper mapper;
    public final CommentRepository repository;
    public final UserRepository userRepository;
    public final LikeCommentRepository likeCommentRepository;
    private final ILikeCommentService likeCommentService;
    private final SimpMessagingTemplate messagingTemplate;

    @Override
    public CommentDTO create(CommentDTO dto) {
        Comment comment = mapper.toEntity(dto);
        comment = repository.save(comment);
        dto = mapper.toDTO(comment);
        CommentMessage<CommentDTO> message = new CommentMessage<>(CommentAction.ADD, dto);
        messagingTemplate.convertAndSend("/topic/comment/" + comment.getMovie().getId(), message);
        return dto;
    }


    @Override
    public CommentDTO update(Long commentId, CommentDTO dto) {
        Comment existingComment = findByIdOrThrow(commentId);
        existingComment.setContent(dto.getContent());
        Comment updatedComment = repository.save(existingComment);
        CommentDTO updatedDTO = mapper.toDTO(updatedComment);
        CommentMessage<CommentDTO> message = new CommentMessage<>(CommentAction.UPDATE, updatedDTO);
        messagingTemplate.convertAndSend("/topic/comment/" + updatedComment.getMovie().getId(), message);
        return dto;
    }

    @Override
    public CommentDTO getById(Long id) {
        return this.repository.findById(id)
                .map(this.mapper::toDTO)
                .orElseThrow(
                        () -> new BadRequestException("Movie not found")
                );
    }

    @Override
    public Boolean delete(Long id) {
        Optional<Comment> optionalComment = repository.findById(id);
        Comment comment = optionalComment.get();
        Long movieId = comment.getMovie().getId();
        repository.deleteById(id);
        CommentMessage<Long> message = new CommentMessage<>(CommentAction.DELETE, id);
        messagingTemplate.convertAndSend("/topic/comment/" + movieId, message);

        return true;
    }


    @Override
    public Page<CommentDTO> getCommentByMovieId(Long movieId, Pageable pageable) {
        return repository.getCommentByMovieId(movieId, pageable);
    }

    @Override
    public List<CommentDTO> getListCommentByMovieIdUserId(Long userId, Long movieId) {
        return repository.getListCommentByMovieIdUserId(userId, movieId)
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    @Override
    public void like(Long id, LikeCommentDTO likeCommentDTO) {
        this.likeCommentService.createOrUpdate(likeCommentDTO);
    }

    @Override
    public void unLike(Long id, LikeCommentDTO likeCommentDTO) {
        this.likeCommentService.createOrUpdate(likeCommentDTO);
    }

    @Override
    public TotalLikesResponse likeOrUnlike(Long commentId, Boolean isLike) {
        String username = SecurityUtils.getCurrentUsername().orElseThrow(
                () -> new AuthenticationCredentialsNotFoundException("Authentication Failure")
        );

        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new AuthenticationCredentialsNotFoundException("User not found by Authentication")
        );

        Comment comment = repository.findById(commentId).orElseThrow(
                () -> new BadRequestException("Movie not found")
        );

        Optional<LikeComment> likeCommentOptional = likeCommentRepository.findByCommentIdAndUserId(commentId, user.getId());

        likeCommentOptional.ifPresentOrElse(
                likeComment -> {
                    likeComment.setLiked(isLike);
                    this.likeCommentRepository.save(likeComment);
                },
                () -> {
                    LikeComment likeComment = new LikeComment();
                    likeComment.setComment(comment);
                    likeComment.setUser(user);
                    likeComment.setLiked(isLike);
                    this.likeCommentRepository.save(likeComment);
                }
        );

        return getLikeCount(commentId);
    }

    @Override
    public TotalLikesResponse getLikeCount(Long commentId) {
        existByIdOrThrow(commentId);
        Long likeCount = likeCommentRepository.countByCommentIdAndLikedIsTrue(commentId);
        return TotalLikesResponse.builder()
                .totalLikes(likeCount)
                .build();
    }

    @Override
    public Page<CommentDTO> getReplies(Long id, Pageable pageable) {
        return repository.getReplies(id, pageable);
    }

    @Override
    public RepliesCountResponse getRepliesCount(Long commentId) {
        return null;
    }


    private boolean existById(Long id) {
        return repository.existsById(id);
    }

    private void existByIdOrThrow(Long id) {
        if (!existById(id)) {
            throw new BadRequestException("Comment not found");
        }
    }

    private Comment findByIdOrThrow(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new BadRequestException("Comment not found")
        );
    }
}


