-- Wed Aug 14 18:08:02 2024
-- Model: New Model    Version: 1.0

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

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
    AUTO_INCREMENT = 3830
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `offizz`.`area_code`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `offizz`.`area_code` (
                                                    `area_code_id` BIGINT NOT NULL AUTO_INCREMENT,
                                                    `code` VARCHAR(255) NULL DEFAULT NULL,
    `name` VARCHAR(255) NULL DEFAULT NULL,
    PRIMARY KEY (`area_code_id`))
    ENGINE = InnoDB
    AUTO_INCREMENT = 18
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `offizz`.`area_content`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `offizz`.`area_content` (
                                                       `area_content_id` BIGINT NOT NULL AUTO_INCREMENT,
                                                       `addr1` VARCHAR(255) NULL DEFAULT NULL,
    `addr2` VARCHAR(255) NULL DEFAULT NULL,
    `areacode` VARCHAR(255) NULL DEFAULT NULL,
    `booktour` VARCHAR(255) NULL DEFAULT NULL,
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
    `mlevel` VARCHAR(255) NULL DEFAULT NULL,
    `modifiedtime` VARCHAR(255) NULL DEFAULT NULL,
    `sigungucode` VARCHAR(255) NULL DEFAULT NULL,
    `tel` VARCHAR(255) NULL DEFAULT NULL,
    `title` VARCHAR(255) NULL DEFAULT NULL,
    PRIMARY KEY (`area_content_id`))
    ENGINE = InnoDB
    AUTO_INCREMENT = 53069
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `offizz`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `offizz`.`user` (
                                               `user_id` BIGINT NOT NULL AUTO_INCREMENT,
                                               `social_id` BIGINT NOT NULL,
                                               `nick_name` VARCHAR(255) NULL DEFAULT NULL,
    `status` ENUM('DELETED', 'USABLE') NOT NULL,
    `created_at` DATETIME(6) NOT NULL,
    `modified_at` DATETIME(6) NOT NULL,
    PRIMARY KEY (`user_id`),
    UNIQUE INDEX `UKm3tiv2iugdximo00e50thjcbh` (`social_id` ASC) VISIBLE)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `offizz`.`workation`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `offizz`.`workation` (
                                                    `workation_id` BIGINT NOT NULL AUTO_INCREMENT,
                                                    `user_id` BIGINT NOT NULL,
                                                    `reason` VARCHAR(255) NULL DEFAULT NULL,
    `locate` VARCHAR(255) NOT NULL,
    `start_date` DATE NOT NULL,
    `end_date` DATE NOT NULL,
    `start_core_time` TIME NOT NULL,
    `end_core_time` TIME NOT NULL,
    `keyword` ENUM('CULTURE_ART', 'EVENT_FESTIVAL', 'FOOD', 'HEALING', 'SHOPPING') NOT NULL,
    `goal` VARCHAR(255) NULL DEFAULT NULL,
    `created_at` DATETIME(6) NOT NULL,
    `modified_at` DATETIME(6) NOT NULL,
    PRIMARY KEY (`workation_id`),
    INDEX `FKffs6m8aioci4scx1ioalhsnte` (`user_id` ASC) VISIBLE,
    CONSTRAINT `FKffs6m8aioci4scx1ioalhsnte`
    FOREIGN KEY (`user_id`)
    REFERENCES `offizz`.`user` (`user_id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `offizz`.`bucketlist`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `offizz`.`bucketlist` (
                                                     `bucketlist_id` BIGINT NOT NULL AUTO_INCREMENT,
                                                     `workation_id` BIGINT NOT NULL,
                                                     `name` VARCHAR(255) NULL DEFAULT NULL,
    `is_complete` BIT(1) NOT NULL,
    `created_at` DATETIME(6) NOT NULL,
    `modified_at` DATETIME(6) NOT NULL,
    PRIMARY KEY (`bucketlist_id`),
    INDEX `FKkwvd5fl0qsq3wqeaxqejnxrcu` (`workation_id` ASC) VISIBLE,
    CONSTRAINT `FKkwvd5fl0qsq3wqeaxqejnxrcu`
    FOREIGN KEY (`workation_id`)
    REFERENCES `offizz`.`workation` (`workation_id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `offizz`.`course`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `offizz`.`course` (
                                                 `course_id` BIGINT NOT NULL AUTO_INCREMENT,
                                                 `crs_idx` VARCHAR(255) NULL DEFAULT NULL,
    `crs_kor_nm` VARCHAR(255) NULL DEFAULT NULL,
    `sigun` VARCHAR(255) NULL DEFAULT NULL,
    `crs_contents` TEXT NULL DEFAULT NULL,
    `crs_cycle` VARCHAR(255) NULL DEFAULT NULL,
    `crs_dstnc` VARCHAR(255) NULL DEFAULT NULL,
    `crs_level` VARCHAR(255) NULL DEFAULT NULL,
    `crs_summary` TEXT NULL DEFAULT NULL,
    `crs_totl_rqrm_hour` VARCHAR(255) NULL DEFAULT NULL,
    `crs_tour_info` TEXT NULL DEFAULT NULL,
    `gpxpath` TEXT NULL DEFAULT NULL,
    `route_idx` VARCHAR(255) NULL DEFAULT NULL,
    `travelerinfo` TEXT NULL DEFAULT NULL,
    `hit` BIGINT NOT NULL DEFAULT '0',
    `createdtime` VARCHAR(255) NULL DEFAULT NULL,
    `modifiedtime` VARCHAR(255) NULL DEFAULT NULL,
    PRIMARY KEY (`course_id`))
    ENGINE = InnoDB
    AUTO_INCREMENT = 265
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `offizz`.`daily`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `offizz`.`daily` (
                                                `daily_id` BIGINT NOT NULL AUTO_INCREMENT,
                                                `workation_id` BIGINT NOT NULL,
                                                `day` INT NOT NULL,
                                                `date` DATE NOT NULL,
                                                `work_condition` INT NOT NULL,
                                                `vacation_condition` INT NOT NULL,
                                                `daily_y` VARCHAR(255) NULL DEFAULT NULL,
    `daily_w` VARCHAR(255) NULL DEFAULT NULL,
    `daily_t` VARCHAR(255) NULL DEFAULT NULL,
    `created_at` DATETIME(6) NOT NULL,
    `modified_at` DATETIME(6) NOT NULL,
    PRIMARY KEY (`daily_id`),
    INDEX `FKrwpeph553dfbqqtp46a3sregt` (`workation_id` ASC) VISIBLE,
    CONSTRAINT `FKrwpeph553dfbqqtp46a3sregt`
    FOREIGN KEY (`workation_id`)
    REFERENCES `offizz`.`workation` (`workation_id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `offizz`.`title`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `offizz`.`title` (
                                                `title_id` BIGINT NOT NULL AUTO_INCREMENT,
                                                `name` VARCHAR(255) NOT NULL,
    `content` VARCHAR(255) NOT NULL,
    `message` VARCHAR(255) NOT NULL,
    `created_at` DATETIME(6) NOT NULL,
    `modified_at` DATETIME(6) NOT NULL,
    PRIMARY KEY (`title_id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `offizz`.`daily_title`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `offizz`.`daily_title` (
                                                      `daily_title_id` BIGINT NOT NULL AUTO_INCREMENT,
                                                      `daily_id` BIGINT NOT NULL,
                                                      `title_id` BIGINT NOT NULL,
                                                      `created_at` DATETIME(6) NOT NULL,
    `modified_at` DATETIME(6) NOT NULL,
    PRIMARY KEY (`daily_title_id`),
    INDEX `FKft6kl9i9swxy4j5043nfbm5w` (`daily_id` ASC) VISIBLE,
    INDEX `FKhyjkqnasspontvpahayirp9hi` (`title_id` ASC) VISIBLE,
    CONSTRAINT `FKft6kl9i9swxy4j5043nfbm5w`
    FOREIGN KEY (`daily_id`)
    REFERENCES `offizz`.`daily` (`daily_id`),
    CONSTRAINT `FKhyjkqnasspontvpahayirp9hi`
    FOREIGN KEY (`title_id`)
    REFERENCES `offizz`.`title` (`title_id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `offizz`.`likes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `offizz`.`likes` (
                                                `likes_id` BIGINT NOT NULL AUTO_INCREMENT,
                                                `user_id` BIGINT NOT NULL,
                                                `fk_id` BIGINT NOT NULL,
                                                `likes_category` ENUM('COURSE', 'OFFICE') NOT NULL,
    `created_at` DATETIME(6) NOT NULL,
    `modified_at` DATETIME(6) NOT NULL,
    PRIMARY KEY (`likes_id`),
    INDEX `FKi2wo4dyk4rok7v4kak8sgkwx0` (`user_id` ASC) VISIBLE,
    CONSTRAINT `FKi2wo4dyk4rok7v4kak8sgkwx0`
    FOREIGN KEY (`user_id`)
    REFERENCES `offizz`.`user` (`user_id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `offizz`.`nature`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `offizz`.`nature` (
                                                 `nature_id` BIGINT NOT NULL AUTO_INCREMENT,
                                                 `addr1` VARCHAR(255) NULL DEFAULT NULL,
    `areacode` VARCHAR(255) NULL DEFAULT NULL,
    `cat1` VARCHAR(255) NULL DEFAULT NULL,
    `cat2` VARCHAR(255) NULL DEFAULT NULL,
    `cat3` VARCHAR(255) NULL DEFAULT NULL,
    `contentid` VARCHAR(255) NULL DEFAULT NULL,
    `contenttypeid` VARCHAR(255) NULL DEFAULT NULL,
    `firstimage` VARCHAR(255) NULL DEFAULT NULL,
    `firstimage2` VARCHAR(255) NULL DEFAULT NULL,
    `mapx` VARCHAR(255) NULL DEFAULT NULL,
    `mapy` VARCHAR(255) NULL DEFAULT NULL,
    `sigungucode` VARCHAR(255) NULL DEFAULT NULL,
    `tel` VARCHAR(255) NULL DEFAULT NULL,
    `title` VARCHAR(255) NULL DEFAULT NULL,
    `hit` BIGINT NOT NULL DEFAULT '0',
    `createdtime` VARCHAR(255) NULL DEFAULT NULL,
    `modifiedtime` VARCHAR(255) NULL DEFAULT NULL,
    PRIMARY KEY (`nature_id`))
    ENGINE = InnoDB
    AUTO_INCREMENT = 3896
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `offizz`.`office`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `offizz`.`office` (
                                                 `id` BIGINT NOT NULL AUTO_INCREMENT,
                                                 `office_name` VARCHAR(255) NULL DEFAULT NULL,
    `street_address` VARCHAR(255) NULL DEFAULT NULL,
    `office_type` VARCHAR(255) NULL DEFAULT NULL,
    `hit` INT NOT NULL,
    `air_conditioning` BIT(1) NULL DEFAULT NULL,
    `cafe_restaurant` BIT(1) NULL DEFAULT NULL,
    `copier_printer` BIT(1) NULL DEFAULT NULL,
    `delivery_service` BIT(1) NULL DEFAULT NULL,
    `door_lock` BIT(1) NULL DEFAULT NULL,
    `fax` BIT(1) NULL DEFAULT NULL,
    `heating` BIT(1) NULL DEFAULT NULL,
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
    `capacity` VARCHAR(255) NULL DEFAULT NULL,
    `operating_hours_friday` VARCHAR(255) NULL DEFAULT NULL,
    `operating_hours_monday` VARCHAR(255) NULL DEFAULT NULL,
    `operating_hours_saturday` VARCHAR(255) NULL DEFAULT NULL,
    `operating_hours_sunday` VARCHAR(255) NULL DEFAULT NULL,
    `operating_hours_thursday` VARCHAR(255) NULL DEFAULT NULL,
    `operating_hours_tuesday` VARCHAR(255) NULL DEFAULT NULL,
    `operating_hours_wednesday` VARCHAR(255) NULL DEFAULT NULL,
    `last_updated_at` VARCHAR(255) NULL DEFAULT NULL,
    PRIMARY KEY (`id`))
    ENGINE = InnoDB
    AUTO_INCREMENT = 906
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `offizz`.`vacation_todo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `offizz`.`vacation_todo` (
                                                        `vacation_todo_id` BIGINT NOT NULL AUTO_INCREMENT,
                                                        `daily_id` BIGINT NOT NULL,
                                                        `icon` INT NOT NULL,
                                                        `name` VARCHAR(255) NOT NULL,
    `locate` VARCHAR(255) NULL DEFAULT NULL,
    `rating` DOUBLE NULL DEFAULT NULL,
    `comment` VARCHAR(255) NULL DEFAULT NULL,
    `is_complete` BIT(1) NOT NULL,
    `created_at` DATETIME(6) NOT NULL,
    `modified_at` DATETIME(6) NOT NULL,
    PRIMARY KEY (`vacation_todo_id`),
    INDEX `FKoukrn16ey7s2udpci1x6c42pi` (`daily_id` ASC) VISIBLE,
    CONSTRAINT `FKoukrn16ey7s2udpci1x6c42pi`
    FOREIGN KEY (`daily_id`)
    REFERENCES `offizz`.`daily` (`daily_id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `offizz`.`work_todo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `offizz`.`work_todo` (
                                                    `work_todo_id` BIGINT NOT NULL AUTO_INCREMENT,
                                                    `daily_id` BIGINT NOT NULL,
                                                    `icon` INT NOT NULL,
                                                    `name` VARCHAR(255) NOT NULL,
    `is_complete` BIT(1) NOT NULL,
    `plan_time` TIME NULL DEFAULT NULL,
    `actual_time` TIME NULL DEFAULT NULL,
    `created_at` DATETIME(6) NOT NULL,
    `modified_at` DATETIME(6) NOT NULL,
    PRIMARY KEY (`work_todo_id`),
    INDEX `FKq7vcoahufoyqyx0logvcb27fu` (`daily_id` ASC) VISIBLE,
    CONSTRAINT `FKq7vcoahufoyqyx0logvcb27fu`
    FOREIGN KEY (`daily_id`)
    REFERENCES `offizz`.`daily` (`daily_id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `offizz`.`work_timer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `offizz`.`work_timer` (
                                                     `work_timer_id` BIGINT NOT NULL AUTO_INCREMENT,
                                                     `work_todo_id` BIGINT NOT NULL,
                                                     `start_time` DATETIME(6) NULL DEFAULT NULL,
    `end_time` DATETIME(6) NULL DEFAULT NULL,
    `created_at` DATETIME(6) NOT NULL,
    `modified_at` DATETIME(6) NOT NULL,
    PRIMARY KEY (`work_timer_id`),
    INDEX `FKsydhokvocwh8ixw0sieuqemwg` (`work_todo_id` ASC) VISIBLE,
    CONSTRAINT `FKsydhokvocwh8ixw0sieuqemwg`
    FOREIGN KEY (`work_todo_id`)
    REFERENCES `offizz`.`work_todo` (`work_todo_id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8mb4
    COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
