ALTER TABLE `movie`
ADD COLUMN `path` VARCHAR(255) UNIQUE;

ALTER TABLE `movie`
ADD COLUMN `trailer_url` VARCHAR(255) NULL ;
