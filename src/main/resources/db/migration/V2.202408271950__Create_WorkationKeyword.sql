-- WorkationKeyword 테이블 생성
CREATE TABLE workation_keyword (
                                   workation_keyword_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                   workation_id BIGINT NOT NULL,
                                   keyword VARCHAR(50) NOT NULL,
                                   created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                   modified_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                   FOREIGN KEY (workation_id) REFERENCES workation(workation_id) ON DELETE CASCADE
);