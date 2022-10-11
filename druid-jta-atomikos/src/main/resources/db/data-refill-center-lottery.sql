/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.3.57
 Source Server Type    : MySQL
 Source Server Version : 50735
 Source Host           : 192.168.3.57:3306
 Source Schema         : data-refill-center-lottery

 Target Server Type    : MySQL
 Target Server Version : 50735
 File Encoding         : 65001

 Date: 09/10/2022 17:35:01
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for lottery_draw
-- ----------------------------
DROP TABLE IF EXISTS `lottery_draw`;
CREATE TABLE `lottery_draw`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_account_id` bigint(20) NOT NULL COMMENT '用户账号id',
  `lottery_draw_count` bigint(20) NOT NULL COMMENT '抽奖次数',
  `created_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `modified_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'lottery_draw ' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of lottery_draw
-- ----------------------------
INSERT INTO `lottery_draw` VALUES (1, 1, 12, '2022-10-09 03:32:04', '2022-10-09 09:34:04');

SET FOREIGN_KEY_CHECKS = 1;
