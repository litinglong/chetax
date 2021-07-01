/*
SQLyog Community v13.1.6 (64 bit)
MySQL - 5.7.32 : Database - schedule_center
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`schedule_center` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `schedule_center`;

/*Table structure for table `plan` */

DROP TABLE IF EXISTS `plan`;

CREATE TABLE `plan` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_id` bigint(20) DEFAULT NULL,
  `name` varchar(256) DEFAULT NULL,
  `total_num` bigint(20) DEFAULT NULL,
  `add_total` bigint(20) DEFAULT NULL,
  `day_num` bigint(20) DEFAULT NULL,
  `add_day` bigint(20) DEFAULT NULL,
  `last_add_day_date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `plan` */

insert  into `plan`(`id`,`product_id`,`name`,`total_num`,`add_total`,`day_num`,`add_day`,`last_add_day_date`) values 
(1,1,'商品1阶段1',500,500,100,50,'2021-07-02');

/*Table structure for table `product` */

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(256) DEFAULT NULL,
  `num` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `product` */

insert  into `product`(`id`,`name`,`num`) values 
(1,'商品1',500),
(2,'商品2',0);

/*Table structure for table `sys_schedule_info` */

DROP TABLE IF EXISTS `sys_schedule_info`;

CREATE TABLE `sys_schedule_info` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `url` varchar(256) DEFAULT NULL COMMENT '服务url',
  `request_body` varchar(512) DEFAULT NULL COMMENT '请求体',
  `cron` varchar(32) DEFAULT NULL COMMENT '周期表达式',
  `status` int(11) DEFAULT '0' COMMENT '任务状态',
  `create_time` varchar(64) DEFAULT NULL,
  `create_user` varchar(64) DEFAULT NULL,
  `update_time` varchar(64) DEFAULT NULL,
  `update_user` varchar(64) DEFAULT NULL,
  `group_name` varchar(64) DEFAULT NULL,
  `job_name` varchar(128) DEFAULT NULL,
  `description` varchar(256) DEFAULT NULL,
  `concurrent_tag` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `sys_schedule_info` */

insert  into `sys_schedule_info`(`id`,`url`,`request_body`,`cron`,`status`,`create_time`,`create_user`,`update_time`,`update_user`,`group_name`,`job_name`,`description`,`concurrent_tag`) values 
(1,'http://www.baidu.com',NULL,'0/5 * * * * ? *',0,NULL,NULL,NULL,NULL,'gg','nn',NULL,0),
(2,'http://www.baidu.com',NULL,'0/10 * * * * ? *',0,NULL,NULL,NULL,NULL,'gg','nnn',NULL,0),
(4,'http://127.0.0.1:8989/inv/plan/inventoryAdd','{\"addList\":[{\"planId\":\"1\",\"num\":\"50\"}]}','0/30 * * * * ? *',1,NULL,NULL,NULL,NULL,'加庫存','30秒加一次',NULL,0);

/*Table structure for table `test1` */

DROP TABLE IF EXISTS `test1`;

CREATE TABLE `test1` (
  `id` decimal(11,0) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `test1` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
