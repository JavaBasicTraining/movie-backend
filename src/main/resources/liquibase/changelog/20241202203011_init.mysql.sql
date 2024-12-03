-- liquibase formatted sql

-- changeset huyhanhat:1733200217135-1
CREATE TABLE app_user_authority
(
    user_id        BIGINT      NOT NULL,
    authority_name VARCHAR(50) NOT NULL,
    CONSTRAINT app_user_authorityPK PRIMARY KEY (user_id, authority_name)
);

-- changeset huyhanhat:1733200217135-2
CREATE TABLE authority
(
    name VARCHAR(50) NOT NULL,
    CONSTRAINT authorityPK PRIMARY KEY (name)
);

-- changeset huyhanhat:1733200217135-3
CREATE TABLE category
(
    id   BIGINT AUTO_INCREMENT NOT NULL,
    name VARCHAR(255)          NULL,
    CONSTRAINT categoryPK PRIMARY KEY (id)
);

-- changeset huyhanhat:1733200217135-4
CREATE TABLE comment
(
    id                BIGINT AUTO_INCREMENT NOT NULL,
    content           VARCHAR(255)          NULL,
    time_comment      datetime(6)           NULL,
    movie_id          BIGINT                NULL,
    parent_comment_id BIGINT                NULL,
    user_id           BIGINT                NULL,
    CONSTRAINT commentPK PRIMARY KEY (id)
);

-- changeset huyhanhat:1733200217135-5
CREATE TABLE episode
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    descriptions  VARCHAR(255)          NULL,
    episode_count BIGINT                NULL,
    poster_url    VARCHAR(255)          NULL,
    video_url     VARCHAR(255)          NULL,
    movie_id      BIGINT                NULL,
    CONSTRAINT episodePK PRIMARY KEY (id)
);

-- changeset huyhanhat:1733200217135-6
CREATE TABLE evaluation
(
    id       BIGINT AUTO_INCREMENT NOT NULL,
    movie_id BIGINT                NULL,
    star     BIGINT                NULL,
    user_id  BIGINT                NULL,
    CONSTRAINT evaluationPK PRIMARY KEY (id)
);

-- changeset huyhanhat:1733200217135-7
CREATE TABLE genre
(
    id   BIGINT AUTO_INCREMENT NOT NULL,
    name VARCHAR(255)          NULL,
    CONSTRAINT genrePK PRIMARY KEY (id)
);

-- changeset huyhanhat:1733200217135-8
CREATE TABLE like_comment
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    like_count BIGINT                NULL,
    comment_id BIGINT                NULL,
    movie_id   BIGINT                NULL,
    user_id    BIGINT                NULL,
    CONSTRAINT like_commentPK PRIMARY KEY (id)
);

-- changeset huyhanhat:1733200217135-9
CREATE TABLE movie
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    country       VARCHAR(255)          NULL,
    `description` VARCHAR(255)          NULL,
    en_title      VARCHAR(255)          NULL,
    name          VARCHAR(255)          NULL,
    poster_url    VARCHAR(255)          NULL,
    trailer_url   VARCHAR(255)          NULL,
    vi_title      VARCHAR(255)          NULL,
    video_url     VARCHAR(255)          NULL,
    year          BIGINT                NULL,
    category_id   BIGINT                NULL,
    CONSTRAINT moviePK PRIMARY KEY (id)
);

-- changeset huyhanhat:1733200217135-10
CREATE TABLE movie_evaluation
(
    movie_id      BIGINT NOT NULL,
    evaluation_id BIGINT NOT NULL,
    CONSTRAINT movie_evaluationPK PRIMARY KEY (movie_id, evaluation_id)
);

-- changeset huyhanhat:1733200217135-11
CREATE TABLE movie_genres
(
    movie_id  BIGINT NOT NULL,
    genres_id BIGINT NOT NULL,
    CONSTRAINT movie_genresPK PRIMARY KEY (movie_id, genres_id)
);

-- changeset huyhanhat:1733200217135-12
CREATE TABLE movie_package
(
    id   BIGINT AUTO_INCREMENT NOT NULL,
    type VARCHAR(100)          NULL,
    CONSTRAINT movie_packagePK PRIMARY KEY (id)
);

-- changeset huyhanhat:1733200217135-13
CREATE TABLE user
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    first_name    VARCHAR(255)          NULL,
    last_name     VARCHAR(255)          NULL,
    password_hash VARCHAR(255)          NULL,
    username      VARCHAR(255)          NOT NULL,
    CONSTRAINT userPK PRIMARY KEY (id)
);

-- changeset huyhanhat:1733200217135-14
ALTER TABLE user
    ADD CONSTRAINT UC_USERUSERNAME_COL UNIQUE (username);

-- changeset huyhanhat:1733200217135-15
ALTER TABLE movie_evaluation
    ADD CONSTRAINT FK311ap4xesie2ey66ssvei45do FOREIGN KEY (evaluation_id) REFERENCES evaluation (id);

-- changeset huyhanhat:1733200217135-16
ALTER TABLE movie
    ADD CONSTRAINT FK5e3qbgdbp67ml7el1odwu65gi FOREIGN KEY (category_id) REFERENCES category (id);

-- changeset huyhanhat:1733200217135-17
ALTER TABLE comment
    ADD CONSTRAINT FK8kcum44fvpupyw6f5baccx25c FOREIGN KEY (user_id) REFERENCES user (id);

-- changeset huyhanhat:1733200217135-18
ALTER TABLE like_comment
    ADD CONSTRAINT FKbrgcwao6rvtgvvpseamn44fes FOREIGN KEY (movie_id) REFERENCES movie (id);

-- changeset huyhanhat:1733200217135-19
ALTER TABLE app_user_authority
    ADD CONSTRAINT FKbwthx8iyi1j1iw9ew6awsgt4q FOREIGN KEY (user_id) REFERENCES user (id);

-- changeset huyhanhat:1733200217135-20
ALTER TABLE like_comment
    ADD CONSTRAINT FKda02ibmlxln75b1lato8698nv FOREIGN KEY (user_id) REFERENCES user (id);

-- changeset huyhanhat:1733200217135-21
ALTER TABLE evaluation
    ADD CONSTRAINT FKg3adtoslyrqt73xfau3c86ykh FOREIGN KEY (user_id) REFERENCES user (id);

-- changeset huyhanhat:1733200217135-22
ALTER TABLE app_user_authority
    ADD CONSTRAINT FKg5hrmqy8eoe8rvyse47qld1xq FOREIGN KEY (authority_name) REFERENCES authority (name);

-- changeset huyhanhat:1733200217135-23
ALTER TABLE like_comment
    ADD CONSTRAINT FKh0r3rvwkfrav930797rs2d9y1 FOREIGN KEY (comment_id) REFERENCES comment (id);

-- changeset huyhanhat:1733200217135-24
ALTER TABLE comment
    ADD CONSTRAINT FKhvh0e2ybgg16bpu229a5teje7 FOREIGN KEY (parent_comment_id) REFERENCES comment (id);

-- changeset huyhanhat:1733200217135-25
ALTER TABLE movie_genres
    ADD CONSTRAINT FKi8gl10taq0yvv0aqnuxeal5x0 FOREIGN KEY (genres_id) REFERENCES genre (id);

-- changeset huyhanhat:1733200217135-26
ALTER TABLE movie_evaluation
    ADD CONSTRAINT FKipj1airfxlfwyfvh4kr3rsny1 FOREIGN KEY (movie_id) REFERENCES movie (id);

-- changeset huyhanhat:1733200217135-27
ALTER TABLE comment
    ADD CONSTRAINT FKj6owqni09n6r5rspfx1xtfu23 FOREIGN KEY (movie_id) REFERENCES movie (id);

-- changeset huyhanhat:1733200217135-28
ALTER TABLE movie_genres
    ADD CONSTRAINT FKs2xl3sirbon75mjcongwhrbi3 FOREIGN KEY (movie_id) REFERENCES movie (id);

-- changeset huyhanhat:1733200217135-29
ALTER TABLE episode
    ADD CONSTRAINT FKsbpb6q6d7t2jvwfwmlp0953e4 FOREIGN KEY (movie_id) REFERENCES movie (id);

-- changeset author:init-data id:20241202203011
-- comment: Insert initial data for authority and users
-- Insert authorities
INSERT INTO authority (name)
VALUES ('admin');
INSERT INTO authority (name)
VALUES ('user');

-- Insert categories
INSERT INTO category (name)
VALUES ('Phim Bộ');
INSERT INTO category (name)
VALUES ('Phim Lẻ');
INSERT INTO category (name)
VALUES ('Phim Chiếu Rạp');

-- Insert genres
INSERT INTO genre
VALUES (1, 'Hành động'),
       (2, 'Kinh dị'),
       (3, 'Hài Hước');