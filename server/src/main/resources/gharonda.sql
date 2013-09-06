/*
SQLyog Community Edition- MySQL GUI v6.15
MySQL - 5.0.45-community-nt : Database - sahibidenev
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

create database if not exists `gem1453_sev`;

USE `gem1453_sev`;


/*Table structure for table `emailfrequencies` */

DROP TABLE IF EXISTS `emailfrequencies`;

CREATE TABLE `emailfrequencies` (
  `id` int(10)  NOT NULL auto_increment,
  `frequencyType` int(10)  default NULL,
  `userIds` blob,
  `propertyIds` blob,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `emailfrequencies` */

/*Table structure for table `contentholder` */

DROP TABLE IF EXISTS `contentholder`;

CREATE TABLE `contentholder` (
  `id` int(10) NOT NULL,
  `userId` int(10)  NOT NULL,
  `created` bigint(20) default NULL,
  `timeStamp` bigint(20) default NULL,
  `name` varchar(100) default NULL,
  `wipState` int(10)  default NULL,
  `otherSideId` int(10)  default NULL,
  `reservedInt` int(10)  default NULL,
  `reservedStr` varchar(1024) default NULL,
  PRIMARY KEY  (`id`),
  KEY `Property_FKIndex1` (`userId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `contentholder` */

/*Table structure for table `contentitems` */

DROP TABLE IF EXISTS `contentitems`;

CREATE TABLE `contentitems` (
  `id` int(10)  NOT NULL auto_increment,
  `name` varchar(100) default NULL,
  `pid` int(10) default NULL,
  `data` varchar(100) default NULL,
  PRIMARY KEY  (`id`),
  CONSTRAINT `contentitems_ibfk_1`  FOREIGN KEY (`pid`) REFERENCES `contentholder` (`id`) ON DELETE SET NULL
) ENGINE=MyISAM AUTO_INCREMENT=3007 DEFAULT CHARSET=utf8;

/*Data for the table `contentitems` */

/*Table structure for table `notifierprofiles` */

DROP TABLE IF EXISTS `notifierprofiles`;

CREATE TABLE `notifierprofiles` (
  `id` int(10)  NOT NULL auto_increment,
  `timeStamp` bigint(20) default NULL,
  `state` varchar(20) default NULL,
  `city` varchar(20) default NULL,
  `suburb` varchar(20) default NULL,
  `pType` int(10)  default NULL,
  `sftLVal` int(10)  default NULL,
  `sftComparator` int(10)  default NULL,
  `sftRVal` int(10)  default NULL,
  `priceLVal` int(10)  default NULL,
  `priceComparator` int(10)  default NULL,
  `priceRVal` int(10)  default NULL,
  `roomNoLVal` int(10)  default NULL,
  `roomNoComparator` int(10)  default NULL,
  `roomNoRVal` int(10)  default NULL,
  `buildDateLVal` int(10)  default NULL,
  `buildDateComparator` int(10)  default NULL,
  `buildDateRVal` int(10)  default NULL,
  `amenity1` int(10)  default NULL,
  `amenity2` int(10)  default NULL,
  `amenity3` int(10)  default NULL,
  `amenity4` int(10)  default NULL,
  `frequency` int(10)  default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `notifierprofiles` */

/*Table structure for table `properties` */

DROP TABLE IF EXISTS `properties`;

CREATE TABLE `properties` (
  `id` int(10)  NOT NULL auto_increment,
  `lat` double default NULL,
  `lng` double default NULL,
  `pType` int(10) default NULL,
  `pMode` int(10) default NULL,	
  `addrNum` varchar(10) default NULL,
  `streetName` varchar(100) default NULL,
  `suburb` varchar(100) default NULL,
  `city` varchar(100) default NULL,
  `postCode` varchar(20) default NULL,
  `state` varchar(100) default NULL,
  `title` varchar(1024) default NULL,
  `bedRooms` int(10)  default NULL,
  `heat` int(10)  default NULL,
  `bathRooms` int(10)  default NULL,
  `view` int(10)  default NULL,
   `buildDate` varchar(20) default NULL,
  `homeLoan` int(10) default NULL,
  `floor` int(10) default NULL,
  `description` varchar(1024) default NULL,
  `price` int(10)  default NULL,
  `sqrft` int(10)  default NULL,
  `currency` int(10) default NULL,
  `isactive` int(10)  default NULL,
  `planType` int(10)  default NULL,
  `isFeatured` int(10)  default NULL,
  `isBargain` int(10)  default NULL,
  `isYardSign` int(10)  default NULL,
  `isEBook` int(10)  default NULL,
  `amenity1` int(10)  default NULL,
  `amenity2` int(10)  default NULL,
  `amenity3` int(10)  default NULL,
  `amenity4` int(10)  default NULL,
  `reservedInt` int(10)  default NULL,
  `reservedStr` varchar(1024) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3007 DEFAULT CHARSET=utf8;

/*Data for the table `properties` */

/*Table structure for table `serviceproviderdetails` */

DROP TABLE IF EXISTS `serviceproviderdetails`;

CREATE TABLE `serviceproviderdetails` (
  `id` int(10)  NOT NULL auto_increment,
  `businessType` int(10)  NOT NULL,
  `company_name` varchar(100) default NULL,
  `addr_line1` varchar(200) default NULL,
  `addr_line2` varchar(200) default NULL,
  `city` varchar(200) default NULL,
  `zip` varchar(100) default NULL,
  `website` varchar(200) default NULL,
  `suburb` varchar(200) default NULL,
  `state` varchar(200) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `serviceproviderdetails` */

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `id` int(10)  NOT NULL auto_increment,
  `notifierProfiles_id` int(10)  default NULL,
  `email` varchar(100) NOT NULL,
  `serviceProviderDetails_id` int(10)  default NULL,
  `passwd` varchar(100) NOT NULL,
  `firstName` varchar(100) default NULL,
  `lastName` varchar(100) default NULL,
  `emailUpdates` int(10)  default NULL,
  `phone` varchar(100) default NULL,
  `cell` varchar(100) default NULL,
  PRIMARY KEY  (`id`),
  KEY `user_3_FKIndex1` (`notifierProfiles_id`),
  KEY `users_FKIndex2` (`serviceProviderDetails_id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `users` */


DROP TABLE IF EXISTS `favorites`;

CREATE TABLE `favorites` (
  `id` int(10)  NOT NULL auto_increment,
  `userId` int(10)  default NULL,
  `pid` int(10)  default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `neightbourhood`;
CREATE TABLE `neightbourhood` (
  `id` int(10)  NOT NULL auto_increment,
  `lat` double default NULL,
  `lng` double default NULL,
  `category` int(10) default NULL,
  `subCategory` varchar(10) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

