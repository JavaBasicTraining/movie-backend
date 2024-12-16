CREATE TABLE like_comment (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    like_count BIGINT DEFAULT 0,
    user_id BIGINT,
    movie_id BIGINT,
    comment_id BIGINT,
    CONSTRAINT fk_like_comment_user FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE,
    CONSTRAINT fk_like_comment_movie FOREIGN KEY (movie_id) REFERENCES movie(id) ON DELETE CASCADE,
    CONSTRAINT fk_like_comment_comment FOREIGN KEY (comment_id) REFERENCES comment(id) ON DELETE CASCADE
);
