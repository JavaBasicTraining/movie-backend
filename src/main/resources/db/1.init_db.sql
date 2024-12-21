-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: localhost    Database: movie_website
-- ------------------------------------------------------
-- Server version	8.0.33

/*!40101 SET @OLD_CHARACTER_SET_CLIENT = @@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS = @@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION = @@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE = @@TIME_ZONE */;
/*!40103 SET TIME_ZONE = '+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0 */;
/*!40101 SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES = @@SQL_NOTES, SQL_NOTES = 0 */;

--
-- Table structure for table `app_user_authority`
--

DROP TABLE IF EXISTS `app_user_authority`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `app_user_authority`
(
    `user_id`        bigint      NOT NULL,
    `authority_name` varchar(50) NOT NULL,
    PRIMARY KEY (`user_id`, `authority_name`),
    KEY `FKg5hrmqy8eoe8rvyse47qld1xq` (`authority_name`),
    CONSTRAINT `FKbwthx8iyi1j1iw9ew6awsgt4q` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
    CONSTRAINT `FKg5hrmqy8eoe8rvyse47qld1xq` FOREIGN KEY (`authority_name`) REFERENCES `authority` (`name`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `app_user_authority`
--

LOCK TABLES `app_user_authority` WRITE;
/*!40000 ALTER TABLE `app_user_authority`
    DISABLE KEYS */;
INSERT INTO `app_user_authority`
VALUES (2, 'admin'),
       (1, 'user');
/*!40000 ALTER TABLE `app_user_authority`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `authority`
--

DROP TABLE IF EXISTS `authority`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `authority`
(
    `name` varchar(50) NOT NULL,
    PRIMARY KEY (`name`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authority`
--

LOCK TABLES `authority` WRITE;
/*!40000 ALTER TABLE `authority`
    DISABLE KEYS */;
INSERT INTO `authority`
VALUES ('admin'),
       ('user');
/*!40000 ALTER TABLE `authority`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category`
(
    `id`   bigint NOT NULL AUTO_INCREMENT,
    `name` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 4
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category`
    DISABLE KEYS */;
INSERT INTO `category`
VALUES (1, 'Phim bộ'),
       (2, 'Phim Lẻ'),
       (3, 'Phim Chiếu Rạp');
/*!40000 ALTER TABLE `category`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment`
(
    `id`      bigint NOT NULL AUTO_INCREMENT,
    `content` varchar(255) DEFAULT NULL,
    `user_id` bigint       DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `FK8kcum44fvpupyw6f5baccx25c` (`user_id`),
    CONSTRAINT `FK8kcum44fvpupyw6f5baccx25c` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment`
    DISABLE KEYS */;
INSERT INTO `comment`
VALUES (1, 'dsf', 2);
/*!40000 ALTER TABLE `comment`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `episode`
--

DROP TABLE IF EXISTS `episode`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `episode`
(
    `id`            bigint NOT NULL AUTO_INCREMENT,
    `descriptions`  varchar(255)  DEFAULT NULL,
    `episode_count` bigint        DEFAULT NULL,
    `poster_url`    varchar(2000) DEFAULT NULL,
    `video_url`     varchar(2000) DEFAULT NULL,
    `movie_id`      bigint        DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `FKsbpb6q6d7t2jvwfwmlp0953e4` (`movie_id`),
    CONSTRAINT `FKsbpb6q6d7t2jvwfwmlp0953e4` FOREIGN KEY (`movie_id`) REFERENCES `movie` (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 218
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `episode`
--

LOCK TABLES `episode` WRITE;
/*!40000 ALTER TABLE `episode`
    DISABLE KEYS */;
INSERT INTO `episode`
VALUES (214, 'ádf', 2, NULL, NULL, 116),
       (215, 'Hay', 1, NULL, NULL, 116),
       (217, 'sadf', 3, NULL, NULL, 116);
/*!40000 ALTER TABLE `episode`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `evaluation`
--

DROP TABLE IF EXISTS `evaluation`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `evaluation`
(
    `id`       bigint NOT NULL AUTO_INCREMENT,
    `movie_id` bigint DEFAULT NULL,
    `star`     bigint DEFAULT NULL,
    `user_id`  bigint DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `FKg3adtoslyrqt73xfau3c86ykh` (`user_id`),
    CONSTRAINT `FKg3adtoslyrqt73xfau3c86ykh` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 14
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `evaluation`
--

LOCK TABLES `evaluation` WRITE;
/*!40000 ALTER TABLE `evaluation`
    DISABLE KEYS */;
INSERT INTO `evaluation`
VALUES (12, 101, 3, 2),
       (13, 112, 4, 2);
/*!40000 ALTER TABLE `evaluation`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `genre`
--

DROP TABLE IF EXISTS `genre`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `genre`
(
    `id`   bigint NOT NULL AUTO_INCREMENT,
    `name` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 4
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `genre`
--

LOCK TABLES `genre` WRITE;
/*!40000 ALTER TABLE `genre`
    DISABLE KEYS */;
INSERT INTO `genre`
VALUES (1, 'Hành động'),
       (2, 'Kinh dị'),
       (3, 'Hài Hước');
/*!40000 ALTER TABLE `genre`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie`
--

DROP TABLE IF EXISTS `movie`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movie`
(
    `id`          bigint NOT NULL AUTO_INCREMENT,
    `country`     varchar(255) DEFAULT NULL,
    `description` varchar(255) DEFAULT NULL,
    `en_title`    varchar(255) DEFAULT NULL,
    `name`        varchar(255) DEFAULT NULL,
    `poster_url`  varchar(255) DEFAULT NULL,
    `vi_title`    varchar(255) DEFAULT NULL,
    `video_url`   varchar(255) DEFAULT NULL,
    `year`        bigint       DEFAULT NULL,
    `category_id` bigint       DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `FK5e3qbgdbp67ml7el1odwu65gi` (`category_id`),
    CONSTRAINT `FK5e3qbgdbp67ml7el1odwu65gi` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 117
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie`
--

LOCK TABLES `movie` WRITE;
/*!40000 ALTER TABLE `movie`
    DISABLE KEYS */;
INSERT INTO `movie`
VALUES (112, 'Mỹ', 'Walk Alone', 'Walk Alone', 'Walk Alone', 'movies/112/poster/alone.jpg', 'Walk Alone',
        'movies/112/video/Let Us In - Official Trailer.mp4', 1992, 3, NULL),
       (113, 'Mỹ', 'Joker', 'Joker', 'Joker', NULL, 'Joker', NULL, 1998, NULL, NULL),
       (116, 'Hồng Kông', 'Đội  Bóng Thiếu Lâm', 'Đội  Bóng Thiếu Lâm', 'Đội  Bóng Thiếu Lâm',
        'movies/116/poster/tải xuống (5).jpg', 'Đội  Bóng Thiếu Lâm', 'movies/116/video/tải xuống (5).jpg', 1998, 1,
        NULL);
/*!40000 ALTER TABLE `movie`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie_comment`
--

DROP TABLE IF EXISTS `movie_comment`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movie_comment`
(
    `movie_id`   bigint NOT NULL,
    `comment_id` bigint NOT NULL,
    PRIMARY KEY (`movie_id`, `comment_id`),
    KEY `FKhavrfmlacdbkjmyakmx8h2tfy` (`comment_id`),
    CONSTRAINT `FK6stiym8dxfg4w4qvm1vbrk7pu` FOREIGN KEY (`movie_id`) REFERENCES `movie` (`id`),
    CONSTRAINT `FKhavrfmlacdbkjmyakmx8h2tfy` FOREIGN KEY (`comment_id`) REFERENCES `comment` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie_comment`
--

LOCK TABLES `movie_comment` WRITE;
/*!40000 ALTER TABLE `movie_comment`
    DISABLE KEYS */;
/*!40000 ALTER TABLE `movie_comment`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie_evaluation`
--

DROP TABLE IF EXISTS `movie_evaluation`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movie_evaluation`
(
    `movie_id`      bigint NOT NULL,
    `evaluation_id` bigint NOT NULL,
    PRIMARY KEY (`movie_id`, `evaluation_id`),
    KEY `FK311ap4xesie2ey66ssvei45do` (`evaluation_id`),
    CONSTRAINT `FK311ap4xesie2ey66ssvei45do` FOREIGN KEY (`evaluation_id`) REFERENCES `evaluation` (`id`),
    CONSTRAINT `FKipj1airfxlfwyfvh4kr3rsny1` FOREIGN KEY (`movie_id`) REFERENCES `movie` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie_evaluation`
--

LOCK TABLES `movie_evaluation` WRITE;
/*!40000 ALTER TABLE `movie_evaluation`
    DISABLE KEYS */;
/*!40000 ALTER TABLE `movie_evaluation`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie_genres`
--

DROP TABLE IF EXISTS `movie_genres`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movie_genres`
(
    `movie_id`  bigint NOT NULL,
    `genres_id` bigint NOT NULL,
    PRIMARY KEY (`movie_id`, `genres_id`),
    KEY `FKi8gl10taq0yvv0aqnuxeal5x0` (`genres_id`),
    CONSTRAINT `FKi8gl10taq0yvv0aqnuxeal5x0` FOREIGN KEY (`genres_id`) REFERENCES `genre` (`id`),
    CONSTRAINT `FKs2xl3sirbon75mjcongwhrbi3` FOREIGN KEY (`movie_id`) REFERENCES `movie` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie_genres`
--

LOCK TABLES `movie_genres` WRITE;
/*!40000 ALTER TABLE `movie_genres`
    DISABLE KEYS */;
INSERT INTO `movie_genres`
VALUES (113, 1),
       (116, 1),
       (113, 2),
       (112, 3),
       (113, 3);
/*!40000 ALTER TABLE `movie_genres`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie_package`
--

DROP TABLE IF EXISTS `movie_package`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movie_package`
(
    `id`   bigint NOT NULL AUTO_INCREMENT,
    `type` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie_package`
--

LOCK TABLES `movie_package` WRITE;
/*!40000 ALTER TABLE `movie_package`
    DISABLE KEYS */;
/*!40000 ALTER TABLE `movie_package`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user`
(
    `id`            bigint       NOT NULL AUTO_INCREMENT,
    `first_name`    varchar(255) DEFAULT NULL,
    `last_name`     varchar(255) DEFAULT NULL,
    `password_hash` varchar(255) DEFAULT NULL,
    `username`      varchar(255) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UK_sb8bbouer5wak8vyiiy4pf2bx` (`username`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user`
    DISABLE KEYS */;
INSERT INTO `user`
VALUES (1, 'string', 'string', '$2a$10$Q53wEDhdcejQAzeKsUbJf../6TFyXvNVEB/DXdGK2F3BwYDmkSsgC', 'user'),
       (2, 'string', 'string', '$2a$10$MBX.VU61fURB7eQvEVMNFOt22MgKGuw/4YwNOQfKyUWS8PvEMoNja', 'admin');
/*!40000 ALTER TABLE `user`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'movie_website'
--

--
-- Dumping routines for database 'movie_website'
--
/*!40103 SET TIME_ZONE = @OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE = @OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT = @OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS = @OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION = @OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES = @OLD_SQL_NOTES */;

-- Dump completed on 2024-08-19 15:44:40
