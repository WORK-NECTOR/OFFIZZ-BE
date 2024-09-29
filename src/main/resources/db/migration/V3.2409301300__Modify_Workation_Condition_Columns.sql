ALTER TABLE daily
DROP COLUMN work_condition,
DROP COLUMN vacation_condition;

ALTER TABLE daily
ADD COLUMN work_condition ENUM('GREAT', 'GOOD', 'OKAY', 'BAD', 'HARD'),
ADD COLUMN vacation_condition ENUM('GREAT', 'GOOD', 'OKAY', 'BAD', 'HARD');
