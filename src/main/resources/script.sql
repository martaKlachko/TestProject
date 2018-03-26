
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `bots_crew_test` DEFAULT CHARACTER SET utf8 ;
USE `bots_crew_test` ;

-- -----------------------------------------------------

-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bots_crew_test`.`lector` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `head_department` VARCHAR(45) NULL DEFAULT NULL,
  `salary` INT(11) NULL DEFAULT NULL,
  `degree` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 49
DEFAULT CHARACTER SET = utf8;



-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bots_crew_test`.`department` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `headId` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `headId` (`headId` ASC),
  CONSTRAINT `department_ibfk_1`
    FOREIGN KEY (`headId`)
    REFERENCES `bots_crew_test`.`lector` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bots_crew_test`.`lector_has_department` (
  `lector_id` INT(11) NOT NULL,
  `department_id` INT(11) NOT NULL,
  PRIMARY KEY (`lector_id`, `department_id`),
  INDEX `fk_lector_has_department_department1_idx` (`department_id` ASC),
  INDEX `fk_lector_has_department_lector_idx` (`lector_id` ASC),
  CONSTRAINT `fk_lector_has_department_lector`
    FOREIGN KEY (`lector_id`)
    REFERENCES `bots_crew_test`.`lector` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_lector_has_department_department1`
    FOREIGN KEY (`department_id`)
    REFERENCES `bots_crew_test`.`department` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
