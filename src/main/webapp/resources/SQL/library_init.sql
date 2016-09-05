CREATE DATABASE IF NOT EXISTS `library`;

USE `library`;

CREATE TABLE IF NOT EXISTS `book` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `author` varchar(45) NOT NULL,
  `title` varchar(45) NOT NULL,
  `balance` int(11) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `login` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `birthday` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `rents` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `bookId` int(11) unsigned NOT NULL,
  `userId` int(11) unsigned NOT NULL,
  `rentDate` date NOT NULL,
  `returnDate` date NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `rentBookId_idx` (`bookId`),
  KEY `rentUserId_idx` (`userId`),
  CONSTRAINT `rentBookId` FOREIGN KEY (`bookId`) REFERENCES `book` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `rentUserId` FOREIGN KEY (`userId`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `report` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `bookId` int(11) unsigned NOT NULL,
  `userId` int(11) unsigned NOT NULL,
  `rentDate` date NOT NULL,
  `returnDate` date NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `reportBookId_idx` (`bookId`),
  KEY `reportUserId_idx` (`userId`),
  CONSTRAINT `reportBookId` FOREIGN KEY (`bookId`) REFERENCES `book` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `reportUserId` FOREIGN KEY (`userId`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;