CREATE SCHEMA 'internetshop' DEFAULT CHARACTER SET utf8 ;

CREATE TABLE `items` (
  `item_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `model` varchar(255) NOT NULL,
  `price` decimal(8,2) NOT NULL,
  PRIMARY KEY (`item_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

CREATE TABLE `users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `surname` varchar(45) DEFAULT NULL,
  `login` varchar(45) NOT NULL,
  `password` varchar(130) NOT NULL,
  `salt` blob,
  `token` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `login_UNIQUE` (`login`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

CREATE TABLE `roles` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

CREATE TABLE `buckets` (
  `bucket_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`bucket_id`),
  KEY `bucket_users_fk_idx` (`user_id`),
  CONSTRAINT `bucket_users_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

CREATE TABLE `orders` (
  `order_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`order_id`),
  KEY `orders_users_fk_idx` (`user_id`),
  CONSTRAINT `orders_users_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;

CREATE TABLE `users_roles` (
  `users_roles_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`users_roles_id`),
  KEY `users_roles_users_fk_idx` (`user_id`),
  KEY `users_roles_roles_fk_idx` (`role_id`),
  CONSTRAINT `users_roles_roles_fk` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`),
  CONSTRAINT `users_roles_users_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

CREATE TABLE `buckets_items` (
  `buckets_items_id` int(11) NOT NULL AUTO_INCREMENT,
  `bucket_id` int(11) NOT NULL,
  `item_id` int(11) NOT NULL,
  PRIMARY KEY (`buckets_items_id`),
  KEY `buckets_items_bucket_fk_idx` (`bucket_id`),
  KEY `buckets_items_items_fk_idx` (`item_id`),
  CONSTRAINT `buckets_items_buckets_fk` FOREIGN KEY (`bucket_id`) REFERENCES `buckets` (`bucket_id`),
  CONSTRAINT `buckets_items_items_fk` FOREIGN KEY (`item_id`) REFERENCES `items` (`item_id`)
) ENGINE=InnoDB AUTO_INCREMENT=118 DEFAULT CHARSET=utf8;

CREATE TABLE `orders_items` (
  `orders_items_id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) NOT NULL,
  `item_id` int(11) NOT NULL,
  PRIMARY KEY (`orders_items_id`),
  KEY `orders_items_orders_fk_idx` (`order_id`),
  KEY `orders_items_items_fk_idx` (`item_id`),
  CONSTRAINT `orders_items_items_fk` FOREIGN KEY (`item_id`) REFERENCES `items` (`item_id`),
  CONSTRAINT `orders_items_orders_fk` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

INSERT INTO `internetshop`.`items` (`name`, `model`, `price`) VALUES ('Samsung', 's10', '900');
INSERT INTO `internetshop`.`items` (`name`, `model`, `price`) VALUES ('iPhone', 'xs', '1000');
INSERT INTO `internetshop`.`items` (`name`, `model`, `price`) VALUES ('Meizu', 'M3', '200');
INSERT INTO `internetshop`.`items` (`name`, `model`, `price`) VALUES ('Xiaomi', 'Note 7', '300');

INSERT INTO `internetshop`.`users` (`name`, `surname`, `login`, `password`)
VALUES ('Dima', 'Shumeiko', 'Korfat', '123');
INSERT INTO `internetshop`.`users` (`name`, `surname`, `login`, `password`)
VALUES ('admin', 'admin', 'admin', 'admin');

INSERT INTO `internetshop`.`users_roles` (`user_id`, `role_id`) VALUES ('1', '1');
INSERT INTO `internetshop`.`users_roles` (`user_id`, `role_id`) VALUES ('2', '2');
