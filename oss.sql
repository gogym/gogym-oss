/*
SQLyog Enterprise v12.09 (64 bit)
MySQL - 5.7.19 : Database - gogym_oss
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
/*Table structure for table `oss_api_call_log` */

DROP TABLE IF EXISTS `oss_api_call_log`;

CREATE TABLE `oss_api_call_log` (
  `id` varchar(128) NOT NULL COMMENT '主键',
  `app_id` varchar(128) NOT NULL COMMENT '应用ID',
  `from_ip` varchar(128) DEFAULT NULL COMMENT '来源Ip',
  `store_path` varchar(256) NOT NULL COMMENT '访问路劲',
  `call_type` int(11) NOT NULL COMMENT '读写类型，1读 2写',
  `create_time` datetime NOT NULL COMMENT '访问时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='api访问日志';

/*Table structure for table `oss_app_info` */

DROP TABLE IF EXISTS `oss_app_info`;

CREATE TABLE `oss_app_info` (
  `id` varchar(128) NOT NULL COMMENT '主键',
  `dev_id` varchar(128) DEFAULT NULL COMMENT '开发者ID',
  `name` varchar(128) DEFAULT NULL COMMENT '名称',
  `code` varchar(128) DEFAULT NULL COMMENT '编码',
  `app_id` varchar(128) DEFAULT NULL COMMENT '应用ID',
  `app_secret` varchar(128) DEFAULT NULL COMMENT '应用密匙',
  `img_url` varchar(256) DEFAULT NULL COMMENT '图标url',
  `intro` varchar(512) DEFAULT NULL COMMENT '简介',
  `use_space` int(11) DEFAULT '0' COMMENT '已用空间（bit）',
  `use_space_str` varchar(256) DEFAULT '0' COMMENT '已用空间（带单位）',
  `func_write` bigint(20) DEFAULT '0' COMMENT '上传次数',
  `func_read` bigint(20) DEFAULT '0' COMMENT '读取次数',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='应用信息表';

/*Table structure for table `oss_developer` */

DROP TABLE IF EXISTS `oss_developer`;

CREATE TABLE `oss_developer` (
  `id` varchar(128) NOT NULL COMMENT '主键',
  `username` varchar(128) NOT NULL COMMENT '用户名',
  `password` varchar(128) NOT NULL COMMENT '密码',
  `nickname` varchar(128) DEFAULT NULL COMMENT '昵称',
  `img_url` varchar(256) DEFAULT NULL COMMENT '头像',
  `email` varchar(128) DEFAULT NULL COMMENT '邮箱',
  `tel` varchar(128) DEFAULT NULL COMMENT '电话',
  `intro` varchar(128) DEFAULT NULL COMMENT '简介',
  `status` varchar(128) NOT NULL DEFAULT '1' COMMENT '状态:1正常 2冻结',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='开发者明细表';

/*Table structure for table `oss_information` */

DROP TABLE IF EXISTS `oss_information`;

CREATE TABLE `oss_information` (
  `id` varchar(128) NOT NULL,
  `root` text,
  `invoking_root` text,
  `nginx_log_path` text,
  `create_time` text,
  `modified_time` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Table structure for table `oss_material_info` */

DROP TABLE IF EXISTS `oss_material_info`;

CREATE TABLE `oss_material_info` (
  `id` varchar(128) NOT NULL COMMENT '主键',
  `app_id` text COMMENT '应用ID',
  `original_name` text COMMENT '原始文件名',
  `store_path` text COMMENT '相对路径',
  `extension` text COMMENT '拓展名',
  `byte_str` text COMMENT '大小（有单位）',
  `len` int(11) DEFAULT NULL COMMENT '文件长度',
  `from_ip` text COMMENT '来源IP',
  `last_modified_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Table structure for table `oss_num` */

DROP TABLE IF EXISTS `oss_num`;

CREATE TABLE `oss_num` (
  `i` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用于做统计用的临时表';

/*Table structure for table `oss_sys_user` */

DROP TABLE IF EXISTS `oss_sys_user`;

CREATE TABLE `oss_sys_user` (
  `id` varchar(128) NOT NULL COMMENT '主键',
  `username` text COMMENT '用户名',
  `password` text COMMENT '密码',
  `intro` text,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
