/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50173
Source Host           : localhost:3306
Source Database       : maven

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2016-06-16 14:43:49
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for operater
-- ----------------------------
DROP TABLE IF EXISTS `operater`;
CREATE TABLE `operater` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `status` int(1) DEFAULT '1' COMMENT '1=可用，0=不可用',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of operater, 密码统一为：123456
-- ----------------------------
INSERT INTO `operater` VALUES ('1', 'admin', 'e10adc3949ba59abbe56e057f20f883e', '1');--密码统一为：123456
INSERT INTO `operater` VALUES ('3', 'test', 'e10adc3949ba59abbe56e057f20f883e', '1');--密码统一为：123456
