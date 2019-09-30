CREATE SCHEMA 'internetshop' DEFAULT CHARACTER SET utf8 ;

CREATE TABLE `internetshop`.`items` (
  `item_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `model` VARCHAR(255) NOT NULL,
  `price` DECIMAL(6,2) NOT NULL,
  PRIMARY KEY (`item_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

INSERT INTO `internetshop`.`items` (`name`, `model`, `price`) VALUES ('Samsung', 's10', '900');
INSERT INTO `internetshop`.`items` (`name`, `model`, `price`) VALUES ('iPhone', 'xs', '1000');
