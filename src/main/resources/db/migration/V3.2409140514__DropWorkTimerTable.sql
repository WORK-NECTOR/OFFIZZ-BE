DROP TABLE IF EXISTS work_timer;
ALTER TABLE work_todo ADD COLUMN start_time time;
ALTER TABLE work_todo ADD COLUMN end_time time;