use ad_lister_db;
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
 `id`   INT UNSIGNED NOT NULL AUTO_INCREMENT ,
 `name` VARCHAR(45) NOT NULL ,
PRIMARY KEY (`id`)
);

-- ************************************** `user_credentials`

CREATE TABLE `user_credentials`
(
 `username` VARCHAR(45) UNIQUE NOT NULL,
 `email` VARCHAR(45) UNIQUE NOT NULL,
 `password`  VARCHAR(45) NOT NULL ,
 `id`       INT UNSIGNED NOT NULL AUTO_INCREMENT NOT NULL,
PRIMARY KEY (`id`)
);

-- ************************************** `ads`

CREATE TABLE `ads`
(
 `id` INT UNSIGNED NOT NULL AUTO_INCREMENT
 `user_id`   INT UNSIGNED NOT NULL ,
 `title` VARCHAR(45) NOT NULL ,
 `desc`  TEXT NOT NULL ,
 `cat`   VARCHAR(45) NOT NULL ,
PRIMARY KEY (`user_id`),
KEY `fkIdx_111` (`id`),
CONSTRAINT `FK_111` FOREIGN KEY `fkIdx_111` (`id`) REFERENCES `user_credentials` (`id`)
);

-- ************************************** `cat_ads`

CREATE TABLE `ad_category`
(
 `category_id` INT UNSIGNED NOT NULL,
 `ad_id` INT UNSIGNED NOT NULL,
KEY `fkIdx_61` (`cat_id`),
CONSTRAINT `FK_61` FOREIGN KEY `fkIdx_61` (`category_id`) REFERENCES `cats` (`id`),
KEY `fkIdx_64` (`ad_id`),
CONSTRAINT `FK_64` FOREIGN KEY `fkIdx_64` (`ad_id`) REFERENCES `ads` (`id`)
);

-- ************************************** `user_ad`

CREATE TABLE `user_ad`
(
 `user_id`   INT UNSIGNED NOT NULL,
 `user_ad_id` INT UNSIGNED NOT NULL,
KEY `fkIdx_107` (`user_ad_id`),
CONSTRAINT `FK_107` FOREIGN KEY `fkIdx_107` (`user_ad_id`) REFERENCES `ads` (`id`),
KEY `fkIdx_117` (`user_id`),
CONSTRAINT `FK_117` FOREIGN KEY `fkIdx_117` (`user_id`) REFERENCES `user_credentials` (`id`)
);





