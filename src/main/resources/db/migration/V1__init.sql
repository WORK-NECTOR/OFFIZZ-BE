-- Model: New Model    Version: 1.0

-- -----------------------------------------------------
-- Schema offizz
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `offizz` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `offizz` ;

-- -----------------------------------------------------
-- Table `offizz`.`accommodation`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `offizz`.`accommodation` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `addr1` VARCHAR(255) NULL DEFAULT NULL,
    `addr2` VARCHAR(255) NULL DEFAULT NULL,
    `contentid` VARCHAR(255) NULL DEFAULT NULL,
    `firstimage` VARCHAR(255) NULL DEFAULT NULL,
    `firstimage2` VARCHAR(255) NULL DEFAULT NULL,
    `mapx` VARCHAR(255) NULL DEFAULT NULL,
    `mapy` VARCHAR(255) NULL DEFAULT NULL,
    `mlevel` VARCHAR(255) NULL DEFAULT NULL,
    `modifiedtime` VARCHAR(255) NULL DEFAULT NULL,
    `tel` VARCHAR(255) NULL DEFAULT NULL,
    `title` VARCHAR(255) NULL DEFAULT NULL,
    PRIMARY KEY (`id`))
    ENGINE = InnoDB
    AUTO_INCREMENT = 3829;


-- -----------------------------------------------------
-- Table `offizz`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `offizz`.`user` (
    `created_at` DATETIME(6) NOT NULL,
    `modified_at` DATETIME(6) NOT NULL,
    `social_id` BIGINT NOT NULL,
    `user_id` BIGINT NOT NULL AUTO_INCREMENT,
    `nick_name` VARCHAR(255) NULL DEFAULT NULL,
    `status` ENUM('DELETED', 'USABLE') NOT NULL,
    PRIMARY KEY (`user_id`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `offizz`.`workation`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `offizz`.`workation` (
    `end_core_time` TIME NOT NULL,
    `end_date` DATE NOT NULL,
    `start_core_time` TIME NOT NULL,
    `start_date` DATE NOT NULL,
    `created_at` DATETIME(6) NOT NULL,
    `modified_at` DATETIME(6) NOT NULL,
    `user_id` BIGINT NOT NULL,
    `workation_id` BIGINT NOT NULL AUTO_INCREMENT,
    `goal` VARCHAR(255) NULL DEFAULT NULL,
    `locate` VARCHAR(255) NOT NULL,
    `reason` VARCHAR(255) NULL DEFAULT NULL,
    `keyword` ENUM('CULTURE_ART', 'EVENT_FESTIVAL', 'FOOD', 'HEALING', 'SHOPPING') NOT NULL,
    PRIMARY KEY (`workation_id`),
    INDEX (`user_id`),
    CONSTRAINT FOREIGN KEY (`user_id`) REFERENCES `offizz`.`user` (`user_id`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `offizz`.`bucketlist`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `offizz`.`bucketlist` (
    `is_complete` BIT(1) NOT NULL,
    `bucketlist_id` BIGINT NOT NULL AUTO_INCREMENT,
    `created_at` DATETIME(6) NOT NULL,
    `modified_at` DATETIME(6) NOT NULL,
    `workation_id` BIGINT NOT NULL,
    `name` VARCHAR(255) NULL DEFAULT NULL,
    PRIMARY KEY (`bucketlist_id`),
    INDEX (`workation_id`),
    CONSTRAINT FOREIGN KEY (`workation_id`) REFERENCES `offizz`.`workation` (`workation_id`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `offizz`.`cafe`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `offizz`.`cafe` (
    `hit` INT NOT NULL,
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `cafe_name` VARCHAR(255) NULL DEFAULT NULL,
    `content_id` VARCHAR(255) NULL DEFAULT NULL,
    `image` VARCHAR(255) NULL DEFAULT NULL,
    `mapx` VARCHAR(255) NULL DEFAULT NULL,
    `mapy` VARCHAR(255) NULL DEFAULT NULL,
    `street_address` VARCHAR(255) NULL DEFAULT NULL,
    `tel` VARCHAR(255) NULL DEFAULT NULL,
    PRIMARY KEY (`id`))
    ENGINE = InnoDB
    AUTO_INCREMENT = 4041;


-- -----------------------------------------------------
-- Table `offizz`.`course`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `offizz`.`course` (
    `course_id` BIGINT NOT NULL AUTO_INCREMENT,
    `hit` BIGINT NOT NULL DEFAULT '0',
    `createdtime` VARCHAR(255) NULL DEFAULT NULL,
    `crs_contents` TEXT NULL DEFAULT NULL,
    `crs_dstnc` VARCHAR(255) NULL DEFAULT NULL,
    `crs_idx` VARCHAR(255) NULL DEFAULT NULL,
    `crs_kor_nm` VARCHAR(255) NULL DEFAULT NULL,
    `crs_level` VARCHAR(255) NULL DEFAULT NULL,
    `crs_summary` TEXT NULL DEFAULT NULL,
    `crs_totl_rqrm_hour` VARCHAR(255) NULL DEFAULT NULL,
    `crs_tour_info` TEXT NULL DEFAULT NULL,
    `gpxpath` TEXT NULL DEFAULT NULL,
    `modifiedtime` VARCHAR(255) NULL DEFAULT NULL,
    `route_idx` VARCHAR(255) NULL DEFAULT NULL,
    `sigun` VARCHAR(255) NULL DEFAULT NULL,
    `travelerinfo` TEXT NULL DEFAULT NULL,
    PRIMARY KEY (`course_id`))
    ENGINE = InnoDB
    AUTO_INCREMENT = 265;


-- -----------------------------------------------------
-- Table `offizz`.`daily`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `offizz`.`daily` (
    `date` DATE NOT NULL,
    `day` INT NOT NULL,
    `vacation_condition` INT NOT NULL,
    `work_condition` INT NOT NULL,
    `created_at` DATETIME(6) NOT NULL,
    `daily_id` BIGINT NOT NULL AUTO_INCREMENT,
    `modified_at` DATETIME(6) NOT NULL,
    `workation_id` BIGINT NOT NULL,
    `dailyt` VARCHAR(255) NULL DEFAULT NULL,
    `dailyw` VARCHAR(255) NULL DEFAULT NULL,
    `dailyy` VARCHAR(255) NULL DEFAULT NULL,
    PRIMARY KEY (`daily_id`),
    INDEX (`workation_id`),
    CONSTRAINT FOREIGN KEY (`workation_id`)
    REFERENCES `offizz`.`workation` (`workation_id`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `offizz`.`title`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `offizz`.`title` (
    `created_at` DATETIME(6) NOT NULL,
    `modified_at` DATETIME(6) NOT NULL,
    `title_id` BIGINT NOT NULL AUTO_INCREMENT,
    `content` VARCHAR(255) NOT NULL,
    `message` VARCHAR(255) NOT NULL,
    `name` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`title_id`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `offizz`.`daily_title`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `offizz`.`daily_title` (
    `created_at` DATETIME(6) NOT NULL,
    `daily_id` BIGINT NOT NULL,
    `daily_title_id` BIGINT NOT NULL AUTO_INCREMENT,
    `modified_at` DATETIME(6) NOT NULL,
    `title_id` BIGINT NOT NULL,
    PRIMARY KEY (`daily_title_id`),
    INDEX (`daily_id`),
    INDEX (`title_id`),
    CONSTRAINT FOREIGN KEY (`daily_id`)
    REFERENCES `offizz`.`daily` (`daily_id`),
    CONSTRAINT FOREIGN KEY (`title_id`)
    REFERENCES `offizz`.`title` (`title_id`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `offizz`.`likes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `offizz`.`likes` (
    `created_at` DATETIME(6) NOT NULL,
    `fk_id` BIGINT NOT NULL,
    `likes_id` BIGINT NOT NULL AUTO_INCREMENT,
    `modified_at` DATETIME(6) NOT NULL,
    `user_id` BIGINT NOT NULL,
    `likes_category` ENUM('COURSE', 'OFFICE') NOT NULL,
    PRIMARY KEY (`likes_id`),
    INDEX (`user_id`),
    CONSTRAINT FOREIGN KEY (`user_id`)
    REFERENCES `offizz`.`user` (`user_id`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `offizz`.`nature`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `offizz`.`nature` (
    `hit` BIGINT NOT NULL DEFAULT '0',
    `nature_id` BIGINT NOT NULL AUTO_INCREMENT,
    `addr1` VARCHAR(255) NULL DEFAULT NULL,
    `areacode` VARCHAR(255) NULL DEFAULT NULL,
    `cat1` VARCHAR(255) NULL DEFAULT NULL,
    `cat2` VARCHAR(255) NULL DEFAULT NULL,
    `cat3` VARCHAR(255) NULL DEFAULT NULL,
    `contentid` VARCHAR(255) NULL DEFAULT NULL,
    `contenttypeid` VARCHAR(255) NULL DEFAULT NULL,
    `createdtime` VARCHAR(255) NULL DEFAULT NULL,
    `firstimage` VARCHAR(255) NULL DEFAULT NULL,
    `firstimage2` VARCHAR(255) NULL DEFAULT NULL,
    `mapx` VARCHAR(255) NULL DEFAULT NULL,
    `mapy` VARCHAR(255) NULL DEFAULT NULL,
    `modifiedtime` VARCHAR(255) NULL DEFAULT NULL,
    `sigungucode` VARCHAR(255) NULL DEFAULT NULL,
    `tel` VARCHAR(255) NULL DEFAULT NULL,
    `title` VARCHAR(255) NULL DEFAULT NULL,
    PRIMARY KEY (`nature_id`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `offizz`.`office`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `offizz`.`office` (
    `air_conditioning` BIT(1) NULL DEFAULT NULL,
    `cafe_restaurant` BIT(1) NULL DEFAULT NULL,
    `copier_printer` BIT(1) NULL DEFAULT NULL,
    `delivery_service` BIT(1) NULL DEFAULT NULL,
    `door_lock` BIT(1) NULL DEFAULT NULL,
    `fax` BIT(1) NULL DEFAULT NULL,
    `heating` BIT(1) NULL DEFAULT NULL,
    `hit` INT NOT NULL,
    `internet_wifi` BIT(1) NULL DEFAULT NULL,
    `open_all_year` BIT(1) NULL DEFAULT NULL,
    `parking` BIT(1) NULL DEFAULT NULL,
    `personal_locker` BIT(1) NULL DEFAULT NULL,
    `power_outlet` BIT(1) NULL DEFAULT NULL,
    `price` INT NULL DEFAULT NULL,
    `price_type` TINYINT NULL DEFAULT NULL,
    `public_lounge` BIT(1) NULL DEFAULT NULL,
    `shared_kitchen` BIT(1) NULL DEFAULT NULL,
    `shower_facility` BIT(1) NULL DEFAULT NULL,
    `snacks_drinks` BIT(1) NULL DEFAULT NULL,
    `storage` BIT(1) NULL DEFAULT NULL,
    `terrace_rooftop` BIT(1) NULL DEFAULT NULL,
    `tv_projector` BIT(1) NULL DEFAULT NULL,
    `twenty_four_hours_operation` BIT(1) NULL DEFAULT NULL,
    `water_purifier` BIT(1) NULL DEFAULT NULL,
    `whiteboard` BIT(1) NULL DEFAULT NULL,
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `capacity` VARCHAR(255) NULL DEFAULT NULL,
    `last_updated_at` VARCHAR(255) NULL DEFAULT NULL,
    `office_name` VARCHAR(255) NULL DEFAULT NULL,
    `office_type` VARCHAR(255) NULL DEFAULT NULL,
    `operating_hours_friday` VARCHAR(255) NULL DEFAULT NULL,
    `operating_hours_monday` VARCHAR(255) NULL DEFAULT NULL,
    `operating_hours_saturday` VARCHAR(255) NULL DEFAULT NULL,
    `operating_hours_sunday` VARCHAR(255) NULL DEFAULT NULL,
    `operating_hours_thursday` VARCHAR(255) NULL DEFAULT NULL,
    `operating_hours_tuesday` VARCHAR(255) NULL DEFAULT NULL,
    `operating_hours_wednesday` VARCHAR(255) NULL DEFAULT NULL,
    `street_address` VARCHAR(255) NULL DEFAULT NULL,
    PRIMARY KEY (`id`))
    ENGINE = InnoDB
    AUTO_INCREMENT = 906;


-- -----------------------------------------------------
-- Table `offizz`.`vacation_todo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `offizz`.`vacation_todo` (
    `icon` INT NOT NULL,
    `is_complete` BIT(1) NOT NULL,
    `rating` DOUBLE NULL DEFAULT NULL,
    `created_at` DATETIME(6) NOT NULL,
    `daily_id` BIGINT NOT NULL,
    `modified_at` DATETIME(6) NOT NULL,
    `vacation_todo_id` BIGINT NOT NULL AUTO_INCREMENT,
    `comment` VARCHAR(255) NULL DEFAULT NULL,
    `locate` VARCHAR(255) NULL DEFAULT NULL,
    `name` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`vacation_todo_id`),
    INDEX (`daily_id`),
    CONSTRAINT FOREIGN KEY (`daily_id`)
    REFERENCES `offizz`.`daily` (`daily_id`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `offizz`.`work_todo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `offizz`.`work_todo` (
    `actual_time` TIME NULL DEFAULT NULL,
    `icon` INT NOT NULL,
    `is_complete` BIT(1) NOT NULL,
    `plan_time` TIME NULL DEFAULT NULL,
    `created_at` DATETIME(6) NOT NULL,
    `daily_id` BIGINT NOT NULL,
    `modified_at` DATETIME(6) NOT NULL,
    `work_todo_id` BIGINT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`work_todo_id`),
    INDEX (`daily_id`),
    CONSTRAINT FOREIGN KEY (`daily_id`)
    REFERENCES `offizz`.`daily` (`daily_id`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `offizz`.`work_timer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `offizz`.`work_timer` (
    `created_at` DATETIME(6) NOT NULL,
    `end_time` DATETIME(6) NULL DEFAULT NULL,
    `modified_at` DATETIME(6) NOT NULL,
    `start_time` DATETIME(6) NULL DEFAULT NULL,
    `work_timer_id` BIGINT NOT NULL AUTO_INCREMENT,
    `work_todo_id` BIGINT NOT NULL,
    PRIMARY KEY (`work_timer_id`),
    INDEX (`work_todo_id`),
    CONSTRAINT FOREIGN KEY (`work_todo_id`)
    REFERENCES `offizz`.`work_todo` (`work_todo_id`))
    ENGINE = InnoDB;
