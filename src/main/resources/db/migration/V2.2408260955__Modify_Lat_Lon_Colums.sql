-- nature 테이블 mapx, mapy 컬럼명 및 타입 변경
ALTER TABLE nature CHANGE mapx lon DOUBLE DEFAULT 0.0;
ALTER TABLE nature CHANGE mapy lat DOUBLE DEFAULT 0.0;

-- course 테이블 lat, lon 컬럼 추가
ALTER TABLE course
ADD COLUMN lat DOUBLE DEFAULT 0.0,
ADD COLUMN lon DOUBLE DEFAULT 0.0;