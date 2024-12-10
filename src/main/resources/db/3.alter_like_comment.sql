-- Drop column if exists
ALTER TABLE like_comment DROP COLUMN like_count;

-- Add column if not exists (MySQL 8.0+)
ALTER TABLE like_comment ADD COLUMN liked boolean default false;

-- Remove the movie foreign key constraint and remove the movie id column
ALTER TABLE like_comment DROP FOREIGN KEY FKbrgcwao6rvtgvvpseamn44fes;
ALTER TABLE like_comment DROP COLUMN movie_id;
