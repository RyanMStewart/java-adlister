
drop database test_db;

create database test_db;

use test_db;
-- ****************** SqlDBM: MySQL ******************;
-- ***************************************************;

-- DROP TABLE `cat_ads`;


-- DROP TABLE `user_ad`;


-- DROP TABLE `ads`;


-- DROP TABLE `cats`;


-- DROP TABLE `user_credentials`;



-- ************************************** `cats`

CREATE TABLE `cats`
(
 `category_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
 `name` VARCHAR(45) NOT NULL,
 `id`   INT UNSIGNED,
PRIMARY KEY (`category_id`)
);

-- ************************************** `user_credentials`
insert into cats (category_id, name) values (6, 'furniture');

CREATE TABLE `user_credentials`
(
 `username` VARCHAR(45) UNIQUE NOT NULL,
 `email` VARCHAR(45) UNIQUE NOT NULL,
 `password`  VARCHAR(45) NOT NULL ,
 `id`       INT UNSIGNED NOT NULL AUTO_INCREMENT,
PRIMARY KEY (`id`)
);

-- ************************************** `ads`

CREATE TABLE `ads`
(
 `title` VARCHAR(240) NOT NULL,
 `description`  TEXT NOT NULL ,
 `user_id`   INT UNSIGNED,
 `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
PRIMARY KEY (`id`),
KEY `fkIdx_111` (`user_id`),
CONSTRAINT `FK_111` FOREIGN KEY `fkIdx_111` (`user_id`) REFERENCES `user_credentials` (`id`)
);

-- ************************************** `cat_ads`



-- ************************************** `user_ad`

CREATE TABLE `user_ad`
(
 -- `id`   INT UNSIGNED NOT NULL AUTO_INCREMENT,
 `user_id`   INT UNSIGNED NOT NULL,
 `user_ad_id` INT UNSIGNED NOT NULL,
 -- PRIMARY KEY (`id`)
KEY `fkIdx_107` (`user_ad_id`),
CONSTRAINT `FK_107` FOREIGN KEY `fkIdx_107` (`user_ad_id`) REFERENCES `ads` (`id`),
KEY `fkIdx_117` (`user_id`),
CONSTRAINT `FK_117` FOREIGN KEY `fkIdx_117` (`user_id`) REFERENCES `user_credentials` (`id`)
);


CREATE TABLE `ad_category`
(
 -- `id`   INT UNSIGNED NOT NULL AUTO_INCREMENT,
 `category_id` INT UNSIGNED,
 `ad_id` INT UNSIGNED,
 -- PRIMARY KEY (`id`)
KEY `fkIdx_64` (`ad_id`),
CONSTRAINT `FK_64` FOREIGN KEY `fkIdx_64` (`ad_id`) REFERENCES `ads` (`id`),
KEY `fkIdx_61` (`category_id`),
CONSTRAINT `FK_61` FOREIGN KEY `fkIdx_61` (`category_id`) REFERENCES `cats` (`category_id`)
);



