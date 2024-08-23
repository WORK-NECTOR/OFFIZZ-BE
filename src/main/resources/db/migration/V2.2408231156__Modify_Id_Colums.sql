-- accommodation 테이블 id 컬럼명 변경
ALTER TABLE accommodation CHANGE id accommodation_id BIGINT;

-- cafe 테이블 id 컬럼명 변경
ALTER TABLE cafe CHANGE id cafe_id BIGINT;

-- office 테이블 id 컬럼명 변경
ALTER TABLE office CHANGE id office_id BIGINT;