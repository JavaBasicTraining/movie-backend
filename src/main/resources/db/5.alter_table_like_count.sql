-- Drop column if exists
ALTER TABLE like_comment DROP COLUMN like_count;

-- Add column if not exists (MySQL 8.0+)
ALTER TABLE like_comment ADD COLUMN liked boolean default false;
