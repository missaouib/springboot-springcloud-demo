create database db2019;


DROP TABLE IF EXISTS `payment`;
CREATE TABLE `payment`  (
                            `id` bigint(20) NOT NULL AUTO_INCREMENT,
                            `serial` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
                            PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of payment
-- ----------------------------
INSERT INTO `payment` VALUES (1, '11111');
INSERT INTO `payment` VALUES (2, '23456789');