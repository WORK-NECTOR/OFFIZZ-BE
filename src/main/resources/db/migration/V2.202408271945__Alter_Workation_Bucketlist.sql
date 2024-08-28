-- Bucketlist address 컬럼 추가
ALTER TABLE bucketlist
    ADD COLUMN address VARCHAR(255);

-- Workation address 컬럼 추가
ALTER TABLE workation
    ADD COLUMN address VARCHAR(255);

-- Workation keyword 컬럼 삭제
ALTER TABLE workation
    DROP COLUMN keyword;