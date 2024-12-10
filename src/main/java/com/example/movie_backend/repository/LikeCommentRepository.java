package com.example.movie_backend.repository;

import com.example.movie_backend.entity.LikeComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeCommentRepository extends JpaRepository<LikeComment, Long> {
    Optional<LikeComment> findByCommentIdAndUserId(Long id, Long userId);

    Long countByCommentIdAndLikedIsTrue(Long commentId);
}
