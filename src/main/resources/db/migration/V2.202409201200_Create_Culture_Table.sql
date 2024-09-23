CREATE TABLE Culture (
    cultureId BIGINT NOT NULL AUTO_INCREMENT,
    addr1 VARCHAR(255),
    lon DOUBLE,
    lat DOUBLE,
    modifiedtime VARCHAR(255),
    title VARCHAR(255),
    contentid VARCHAR(255),
    contenttypeid VARCHAR(255),
    createdtime VARCHAR(255),
    firstimage VARCHAR(255),
    areacode VARCHAR(255),
    sigungucode VARCHAR(255),
    PRIMARY KEY (cultureId)
);