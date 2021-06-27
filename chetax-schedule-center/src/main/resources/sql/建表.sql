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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
