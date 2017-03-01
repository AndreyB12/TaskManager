-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema test
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema test
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `test` DEFAULT CHARACTER SET utf8 ;
USE `test` ;

-- -----------------------------------------------------
-- Table `test`.`statuses`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `test`.`statuses` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `test`.`todo_list`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `test`.`todo_list` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `todo` VARCHAR(512) NULL DEFAULT 'empty task',
  `status` INT(11) NULL DEFAULT '1',
  `create_date` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `status_idx` (`status` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 77
DEFAULT CHARACTER SET = utf8;

USE `test` ;

-- -----------------------------------------------------
-- Placeholder table for view `test`.`todo_view`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `test`.`todo_view` (`create_date` INT, `todo` INT, `status` INT, `change_date` INT);

-- -----------------------------------------------------
-- View `test`.`todo_view`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `test`.`todo_view`;
USE `test`;
CREATE  OR REPLACE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `test`.`todo_view` AS select `td`.`create_date` AS `create_date`,`td`.`todo` AS `todo`,`st`.`name` AS `status`,`td`.`change_date` AS `change_date` from (`test`.`todo_list` `td` join `test`.`statuses` `st` on((`td`.`status` = `st`.`id`)));

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
