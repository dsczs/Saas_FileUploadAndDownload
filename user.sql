/*
 Navicat MySQL Data Transfer

 Source Server         : 172.16.27.131
 Source Server Type    : MySQL
 Source Server Version : 50712
 Source Host           : 172.16.27.131:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 50712
 File Encoding         : 65001

 Date: 06/05/2019 17:30:50
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES (11, '测试');
INSERT INTO `user` VALUES (12, '哈哈');
INSERT INTO `user` VALUES (13, '测试');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
