package com.example.movie_backend.services;

import com.example.movie_backend.dto.like_comment.LikeCommentDTO;
import com.example.movie_backend.dto.like_comment.LikeCommentMapper;
import com.example.movie_backend.entity.Comment;
import com.example.movie_backend.entity.LikeComment;
import com.example.movie_backend.entity.Movie;
import com.example.movie_backend.entity.User;
import com.example.movie_backend.repository.CommentRepository;
import com.example.movie_backend.repository.LikeCommentRepository;
import com.example.movie_backend.repository.MovieRepository;
import com.example.movie_backend.repository.UserRepository;
import com.example.movie_backend.services.interfaces.ILikeCommentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.ws.rs.BadRequestException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LikeCommentService implements ILikeCommentService {
    public final LikeCommentRepository repository;
    public final UserRepository userRepository;
    public final LikeCommentMapper mapper;
    public final MovieRepository movieRepository;
    public final CommentRepository commentRepository;


    @Override
    @Transactional
    public LikeCommentDTO create(LikeCommentDTO dto) {
        LikeComment likeComment = mapper.toEntity(dto);
        Movie movie = movieRepository.findById(dto.getIdMovie()).orElseThrow(() -> new BadRequestException("Movie not found"));
        User user = userRepository.findById(dto.getIdUser()).orElseThrow(() -> new BadRequestException("User not found"));
        Comment comment = commentRepository.findById(dto.getIdComment()).orElseThrow(() -> new BadRequestException("Comment not found"));
        if (likeComment.getId() == null) {
            likeComment = new LikeComment();
            likeComment.setMovie(movie);
            likeComment.setUser(user);
            likeComment.setComment(comment);
            likeComment.setLikeCount(1L);
        } else {
            if (likeComment.getLikeCount() > 0) {
                likeComment.setLikeCount(likeComment.getLikeCount() - 1);
            } else {
                throw new BadRequestException("Like count cannot be negative");
            }
        }
        likeComment = repository.save(likeComment);
        return mapper.toDTO(likeComment);
    }



    @Override
    public LikeCommentDTO findById(Long id) {
        return repository.findById(id).map(this.mapper::toDTO)
                .orElseThrow(() -> new BadRequestException("LikeComment not found"));
    }

    @Override
    public List<LikeCommentDTO> findLikeCommentByUserIdAndMovieId(Long movieId, Long userId) {
        return repository.findLikeCommentByUserIdAndMovieId(movieId, userId).stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }
    @Override
    public Boolean delete(Long id) {
        repository.deleteById(id);
        return true;
    }





}
