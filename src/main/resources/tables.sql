/*CREATE DATABASE alexcoffee;*/

USE alexcoffee;

DROP TABLE IF EXISTS `statuses`;
CREATE TABLE `statuses` (
  `id`          INT UNSIGNED                                              NOT NULL AUTO_INCREMENT,
  `title`       ENUM ('NEW', 'WORK', 'DELIVERY', 'CLOSED', 'REJECTION') NOT NULL,
  `description` TEXT                                                               DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE (`title`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

/*----------------------------------------------------------------------------------*/

DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles` (
  `id`          INT UNSIGNED                      NOT NULL AUTO_INCREMENT,
  `title`       ENUM ('USER', 'ADMIN', 'MANAGER') NOT NULL,
  `description` TEXT                              NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE (`title`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

/*----------------------------------------------------------------------------------*/

DROP TABLE IF EXISTS `photos`;
CREATE TABLE `photos` (
  `id`               INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `title`            VARCHAR(50)  NOT NULL,
  `photo_link_short` VARCHAR(100) NOT NULL,
  `photo_link_long`  VARCHAR(100)          DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE (`title`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

/*----------------------------------------------------------------------------------*/

DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `id`               INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `number`           VARCHAR(10)  NOT NULL,
  `date`             VARCHAR(30)  NOT NULL,
  `status_id`        INT UNSIGNED NOT NULL,
  `client_id`        INT UNSIGNED NOT NULL,
  `manager_id`       INT UNSIGNED          DEFAULT NULL,
  `shipping_address` TEXT                  DEFAULT NULL,
  `shipping_details` TEXT                  DEFAULT NULL,
  `description`      TEXT                  DEFAULT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`status_id`) REFERENCES `statuses` (`id`),
  FOREIGN KEY (`client_id`) REFERENCES `users` (`id`),
  FOREIGN KEY (`manager_id`) REFERENCES `users` (`id`),
  UNIQUE (`number`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

/*----------------------------------------------------------------------------------*/

DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `id`               INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `number`           VARCHAR(10)  NOT NULL,
  `date`             VARCHAR(30)  NOT NULL,
  `status_id`        INT UNSIGNED NOT NULL,
  `client_id`        INT UNSIGNED NOT NULL,
  `shipping_address` TEXT                  DEFAULT NULL,
  `shipping_details` TEXT                  DEFAULT NULL,
  `description`      TEXT                  DEFAULT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`status_id`) REFERENCES `statuses` (`id`),
  FOREIGN KEY (`client_id`) REFERENCES `users` (`id`),
  UNIQUE (`number`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;


/*----------------------------------------------------------------------------------*/
DROP TABLE IF EXISTS `categories`;
CREATE TABLE `categories` (
  `id`          INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `url`         VARCHAR(50)  NOT NULL,
  `title`       VARCHAR(50)  NOT NULL,
  `description` TEXT         NOT NULL,
  `photo_id`    INT UNSIGNED          DEFAULT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`photo_id`) REFERENCES `photos` (`id`),
  UNIQUE (`url`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

/*----------------------------------------------------------------------------------*/

DROP TABLE IF EXISTS `products`;
CREATE TABLE `products` (
  `id`          INT UNSIGNED           NOT NULL AUTO_INCREMENT,
  `article`     INT UNSIGNED           NOT NULL,
  `title`       VARCHAR(100)           NOT NULL,
  `url`         VARCHAR(100)           NOT NULL,
  `parameters`  TEXT                            DEFAULT NULL,
  `description` TEXT                            DEFAULT NULL,
  `category_id` INT UNSIGNED           NOT NULL,
  `photo_id`    INT UNSIGNED                    DEFAULT NULL,
  `price`       DECIMAL(7, 2) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`),
  FOREIGN KEY (`photo_id`) REFERENCES `photos` (`id`),
  UNIQUE (`url`),
  UNIQUE (`article`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;


/*----------------------------------------------------------------------------------*/
DROP TABLE IF EXISTS `salePositions`;
CREATE TABLE `salePositions` (
  `id`         INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `product_id` INT UNSIGNED NOT NULL,
  `number`     INT UNSIGNED NOT NULL,
  `order_id`   INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
  FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
