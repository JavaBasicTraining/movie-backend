DROP TABLE IF EXISTS `movie_comment`;
ALTER TABLE `comment`
    ADD COLUMN `movie_id` bigint DEFAULT NULL,
    ADD CONSTRAINT `FK_comment_movie` FOREIGN KEY (`movie_id`) REFERENCES `movie` (`id`);

ALTER TABLE comment
ADD COLUMN time_comment DATETIME;


ALTER TABLE `comment`
ADD COLUMN `parent_comment_id` BIGINT DEFAULT NULL,
ADD CONSTRAINT `FK_comment_parent` FOREIGN KEY (`parent_comment_id`) REFERENCES `comment`(`id`) ON DELETE SET NULL ON UPDATE CASCADE;
