/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50630
Source Host           : localhost:3306
Source Database       : mfwmodel

Target Server Type    : MYSQL
Target Server Version : 50630
File Encoding         : 65001

Date: 2020-01-03 14:00:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for invoke_api_log
-- ----------------------------
DROP TABLE IF EXISTS `invoke_api_log`;
CREATE TABLE `invoke_api_log` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `app_id` varchar(50) NOT NULL COMMENT '应用id',
  `api_url` varchar(255) NOT NULL COMMENT '接口地址',
  `request_method` enum('GET','HEAD','POST','PUT','PATCH','DELETE','OPTIONS','TRACE') NOT NULL COMMENT '接口方法',
  `request_header` varchar(1024) DEFAULT NULL,
  `request_params` longtext COMMENT '请求参数',
  `response_status` int(11) NOT NULL COMMENT '响应状态码',
  `response_body` mediumtext COMMENT '相应内容',
  `spent_time` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '调用时间',
  `delete_flag` tinyint(4) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='接口日志';

-- ----------------------------
-- Records of invoke_api_log
-- ----------------------------
INSERT INTO `invoke_api_log` VALUES ('1', '1', '1', 'GET', null, '2', '2', '2', '1', '2020-01-03 13:10:04', '0', '2020-01-03 13:10:04');

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `operation` varchar(50) DEFAULT NULL COMMENT '用户操作',
  `method` varchar(200) DEFAULT NULL COMMENT '请求方法',
  `params` varchar(5000) DEFAULT NULL COMMENT '请求参数',
  `time` int(20) NOT NULL COMMENT '执行时长(毫秒)',
  `ip` varchar(64) DEFAULT NULL COMMENT 'IP地址',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `delete_flag` tinyint(4) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统日志';

-- ----------------------------
-- Records of sys_log
-- ----------------------------
