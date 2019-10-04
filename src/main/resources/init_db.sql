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
INSERT INTO `internetshop`.`items` (`name`, `model`, `price`) VALUES ('Meizu', 'M3', '200');
INSERT INTO `internetshop`.`items` (`name`, `model`, `price`) VALUES ('Xiaomi', 'Note 7', '300');

INSERT INTO `internetshop`.`users` (`name`, `surname`, `login`, `password`)
VALUES ('Dima', 'Shumeiko', 'Korfat', '123');
INSERT INTO `internetshop`.`users` (`name`, `surname`, `login`, `password`)
VALUES ('Vasya', 'Ivanov', 'user', '123');

INSERT INTO `internetshop`.`orders` (`user_id`) VALUES ('1');
INSERT INTO `internetshop`.`orders` (`user_id`) VALUES ('1');
INSERT INTO `internetshop`.`orders` (`user_id`) VALUES ('2');

INSERT INTO `internetshop`.`orders_items` (`order_id`, `item_id`) VALUES ('1', '1');
INSERT INTO `internetshop`.`orders_items` (`order_id`, `item_id`) VALUES ('1', '4');
INSERT INTO `internetshop`.`orders_items` (`order_id`, `item_id`) VALUES ('2', '1');
INSERT INTO `internetshop`.`orders_items` (`order_id`, `item_id`) VALUES ('2', '2');
INSERT INTO `internetshop`.`orders_items` (`order_id`, `item_id`) VALUES ('3', '3');