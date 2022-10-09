/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.3.57
 Source Server Type    : MySQL
 Source Server Version : 50735
 Source Host           : 192.168.3.57:3306
 Source Schema         : data-refill-center-credit

 Target Server Type    : MySQL
 Target Server Version : 50735
 File Encoding         : 65001

 Date: 09/10/2022 17:34:47
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for credit
-- ----------------------------
DROP TABLE IF EXISTS `credit`;
CREATE TABLE `credit`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_account_id` bigint(20) NOT NULL COMMENT '用户账号id',
  `point` decimal(10, 2) NOT NULL COMMENT '积分',
  `created_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `modified_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'credit 积分' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of credit
-- ----------------------------
INSERT INTO `credit` VALUES (1, 1, 12.40, '2022-10-09 03:32:28', '2022-10-09 09:34:04');

SET FOREIGN_KEY_CHECKS = 1;
