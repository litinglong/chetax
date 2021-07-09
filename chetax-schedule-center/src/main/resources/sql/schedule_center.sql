/*
SQLyog Community v13.1.6 (64 bit)
MySQL - 5.7.33 : Database - schedule_center
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

/*Table structure for table `inv_plus_plan` */

DROP TABLE IF EXISTS `inv_plus_plan`;

CREATE TABLE `inv_plus_plan` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_id` bigint(20) DEFAULT NULL,
  `name` varchar(256) DEFAULT NULL,
  `total_num` bigint(20) DEFAULT NULL,
  `total_add` bigint(20) DEFAULT NULL,
  `day_num` bigint(20) DEFAULT NULL,
  `day_add` bigint(20) DEFAULT NULL,
  `last_add_day` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Table structure for table `inv_product` */

DROP TABLE IF EXISTS `inv_product`;

CREATE TABLE `inv_product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(256) DEFAULT NULL,
  `inventory_num` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Table structure for table `sys_schedule_info` */

DROP TABLE IF EXISTS `sys_schedule_info`;

CREATE TABLE `sys_schedule_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `job_name` varchar(128) DEFAULT NULL,
  `group_name` varchar(64) DEFAULT NULL,
  `description` varchar(256) DEFAULT NULL,
  `url` varchar(256) DEFAULT NULL COMMENT '服务url',
  `request_body` varchar(512) DEFAULT NULL COMMENT '请求体',
  `cron` varchar(32) DEFAULT NULL COMMENT '周期表达式',
  `concurrent_tag` int(11) DEFAULT '0',
  `status` int(11) DEFAULT '0' COMMENT '任务状态',
  `create_time` timestamp NULL DEFAULT NULL,
  `create_user` varchar(64) DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  `update_user` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Table structure for table `sys_schedule_result` */

DROP TABLE IF EXISTS `sys_schedule_result`;

CREATE TABLE `sys_schedule_result` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `sys_schedule_info_id` bigint(64) DEFAULT NULL COMMENT '关联的调度计划主键',
  `url` varchar(256) DEFAULT NULL COMMENT '服务URL',
  `request_body` varchar(512) DEFAULT NULL COMMENT '输入参数',
  `result_msg` text COMMENT '结果信息',
  `exception_msg` text COMMENT '异常信息',
  `start_time` datetime DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  `used_time` bigint(20) DEFAULT NULL COMMENT '任务耗时（毫秒数）',
  `used_time_formated` varchar(48) DEFAULT NULL COMMENT '任务耗时（格式化后）',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(64) DEFAULT NULL COMMENT '创建人',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `update_user` varchar(64) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1267 DEFAULT CHARSET=utf8;

/*Table structure for table `sys_trans_info` */

DROP TABLE IF EXISTS `sys_trans_info`;

CREATE TABLE `sys_trans_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `trans_jnl_no` varchar(64) DEFAULT NULL COMMENT '交易号',
  `trans_method` varchar(256) DEFAULT NULL COMMENT '交易方法',
  `trans_input` json DEFAULT NULL COMMENT '交易输入参数',
  `trans_output` json DEFAULT NULL COMMENT '交易输出参数',
  `trans_exception` text COMMENT '交易异常',
  `trans_start_time` datetime DEFAULT NULL COMMENT '交易开始时间',
  `trans_end_time` datetime DEFAULT NULL COMMENT '交易结束时间',
  `trans_used_time` bigint(20) DEFAULT NULL COMMENT '交易耗时(毫秒数)',
  `trans_used_time_formated` varchar(48) DEFAULT NULL COMMENT '交易耗时(格式化后)',
  `create_user` varchar(64) DEFAULT NULL COMMENT '创建人',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_user` varchar(64) DEFAULT NULL COMMENT '更新人',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=139 DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
