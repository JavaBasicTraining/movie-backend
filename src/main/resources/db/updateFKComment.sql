DROP TABLE IF EXISTS `movie_comment`;
ALTER TABLE `comment`
ADD COLUMN `movie_id` bigint DEFAULT NULL,
ADD CONSTRAINT `FK_comment_movie` FOREIGN KEY (`movie_id`) REFERENCES `movie` (`id`);
