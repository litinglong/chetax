/*
SQLyog Community v13.1.6 (64 bit)
MySQL - 5.7.32 : Database - oauth
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`oauth` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `oauth`;

/*Data for the table `oauth_client_details` */

insert  into `oauth_client_details`(`client_id`,`resource_ids`,`client_secret`,`scope`,`authorized_grant_types`,`web_server_redirect_uri`,`authorities`,`access_token_validity`,`refresh_token_validity`,`additional_information`,`autoapprove`) values 
('client1','order','{bcrypt}$2a$10$zlS5VdbeM5GxYDGbnmx0uerwOPUTlqq.4Mk/NxjXLJJS9Tkab5PIC','select,server','client_credentials,refresh_token,authorization_code,password','http://www.baidu.com','oauth2',NULL,NULL,NULL,NULL);

/*Data for the table `oauth_role` */

insert  into `oauth_role`(`id`,`name`,`mark`) values 
(1,'ROLE_ADMIN','ROLE_ADMIN');

/*Data for the table `oauth_user_details` */

insert  into `oauth_user_details`(`id`,`username`,`password`) values 
(1,'litinglong','{bcrypt}$2a$10$zlS5VdbeM5GxYDGbnmx0uerwOPUTlqq.4Mk/NxjXLJJS9Tkab5PIC');

/*Data for the table `oauth_user_role` */

insert  into `oauth_user_role`(`id`,`user_id`,`role_id`) values 
(1,1,1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
