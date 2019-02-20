/*
 Navicat Premium Data Transfer

 Source Server         : rm-wz9w6sgg78t2319s5mo.mysql.rds.aliyuncs.com
 Source Server Type    : MySQL
 Source Server Version : 50720
 Source Host           : rm-wz9w6sgg78t2319s5mo.mysql.rds.aliyuncs.com:3306
 Source Schema         : luckwine-customer

 Target Server Type    : MySQL
 Target Server Version : 50720
 File Encoding         : 65001

 Date: 17/02/2019 21:41:53
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cust_collect
-- ----------------------------
DROP TABLE IF EXISTS `cust_collect`;
CREATE TABLE `cust_collect`  (
  `login_account` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `goods_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`login_account`, `goods_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '客户商品收藏列表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cust_collect
-- ----------------------------
INSERT INTO `cust_collect` VALUES ('1', '2', '2018-09-22 17:40:51');
INSERT INTO `cust_collect` VALUES ('1', '3', '2018-09-22 18:01:27');
INSERT INTO `cust_collect` VALUES ('1', '4', '2018-09-22 17:40:59');

-- ----------------------------
-- Table structure for cust_goods_addr
-- ----------------------------
DROP TABLE IF EXISTS `cust_goods_addr`;
CREATE TABLE `cust_goods_addr`  (
  `goods_addr_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `login_account` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '登陆账号',
  `consignee` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '收货人',
  `province` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '省',
  `city` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '市',
  `district` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '区',
  `address` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '详细地址',
  `postcode` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '邮政编码',
  `moblie` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '手机号码',
  `telephone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '固定电话',
  PRIMARY KEY (`goods_addr_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '收货地址' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cust_goods_addr
-- ----------------------------
INSERT INTO `cust_goods_addr` VALUES ('00001', 'test', 'haohao', '广东省', '广州', '天河', '广州天河区1', '510000', '1341617888', '02010086');
INSERT INTO `cust_goods_addr` VALUES ('00002', 'test', 'haohao2', '广东省', '广州', '天河', '广州天河区2', '510000', '1341617889', '02010086');
INSERT INTO `cust_goods_addr` VALUES ('00003', 'test3', 'haohao', '广东省', '广州', '天河', '广州天河区', '510000', '1341617889', '02010086');
INSERT INTO `cust_goods_addr` VALUES ('00004', '15817161961', '杨先生', '广东省', '广州', '天河', '开发区19号', '510000', '15817161961', '02010086');
INSERT INTO `cust_goods_addr` VALUES ('7f009c3b2e9f48e3b9b0847004664bca', '13711658738', '2', '11222', 'gd', 'gz', '11222', NULL, '222222', NULL);
INSERT INTO `cust_goods_addr` VALUES ('d6868f3169494d95b44de4cf1bba139c', '13711658738', '111', '111', '1111', '1111', '111', NULL, '111', NULL);

-- ----------------------------
-- Table structure for cust_info
-- ----------------------------
DROP TABLE IF EXISTS `cust_info`;
CREATE TABLE `cust_info`  (
  `login_account` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '登陆账号(登录号就是手机号)',
  `login_pw` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '密码,请加密不可逆',
  `pay_pw` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '支付密码,请加密不可逆',
  `identity` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '身份证',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '注册时间',
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `nickname` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '昵称',
  `realname` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '真实姓名',
  `sex` int(2) NOT NULL DEFAULT 0 COMMENT '性别:1.男 2.女 0.未知',
  `birthday` timestamp(0) NULL DEFAULT NULL COMMENT '生日时间',
  `province` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '省',
  `city` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '市',
  `district` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '区',
  `address` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '详细地址',
  `drink_wine` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '常喝酒类：白酒,红酒',
  `like_wine` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '喜欢酒类品牌：牌子1,牌子2',
  `headerimg` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '头像地址',
  `marry` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '婚姻状况 1.已婚有子女 2.未婚无子女 3.恋爱中 4.单身 5.丧偶 6.离异',
  `education` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '教育程度 1.高中及以下 2.大学专科 3.大学本科 4.硕士 5.',
  `job` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '从事职业',
  `industry` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '所属行业',
  `income` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '月均收入',
  `cust_level` int(2) NOT NULL DEFAULT 1 COMMENT '客户等级：1.普通用户 2.普通实名',
  `status` int(2) NOT NULL DEFAULT 1 COMMENT '客户状态：1.正常 2.冻结',
  PRIMARY KEY (`login_account`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '客户信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cust_info
-- ----------------------------
INSERT INTO `cust_info` VALUES ('13711658738', '$2a$10$cA1f82c5MSTzhvB556cLPeEYYKjv4hjF4rn5mtV5rYKjiSpJWQgN2', NULL, NULL, '2018-11-10 11:24:48', NULL, '13711658738', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'https://luckwine-prod.oss-cn-shenzhen.aliyuncs.com/luckwine/CcdVQP.png', NULL, NULL, NULL, NULL, NULL, 1, 1);
INSERT INTO `cust_info` VALUES ('13725162166', '$2a$10$sMse3zdxDPDSKrmiIzk4MOTm.pdAazmZXBtmUIkq..zLpTKOOmSIS', NULL, '11010119900307299X', '2018-10-30 09:43:16', '2018-11-06 11:21:00', '13725162166', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'https://luckwine-prod.oss-cn-shenzhen.aliyuncs.com/luckwine/CcdVQP.png', NULL, NULL, NULL, NULL, NULL, 1, 1);
INSERT INTO `cust_info` VALUES ('15666666666', '$2a$10$UCa.Z8KtUoq67.m49Qz5OO0bvKMZyT.JuXW8gCuGE2mZvrkflP7y6', NULL, '110101199003072498', '2018-10-20 08:59:33', '2018-11-06 10:26:33', '15666666666', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'https://luckwine-prod.oss-cn-shenzhen.aliyuncs.com/luckwine/CcdVQP.png', NULL, NULL, NULL, NULL, NULL, 1, 1);
INSERT INTO `cust_info` VALUES ('15817061961', '$2a$10$yU.pi9ZZe7iAA8ujsn4U1e2ku3mcPeXBskd3VpRfPg6D4qC4myNxW', NULL, '110101199003070337', '2018-10-16 16:28:32', '2018-11-06 10:26:41', '15817061961', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'https://luckwine-prod.oss-cn-shenzhen.aliyuncs.com/luckwine/CcdVQP.png', NULL, NULL, NULL, NULL, NULL, 1, 1);
INSERT INTO `cust_info` VALUES ('15817161961', '$2a$10$yU.pi9ZZe7iAA8ujsn4U1e2ku3mcPeXBskd3VpRfPg6D4qC4myNxW', NULL, '110101199003075170', '2018-08-29 14:45:09', '2018-11-06 10:27:04', 'HowellYang', 'A', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'https://luckwine-prod.oss-cn-shenzhen.aliyuncs.com/luckwine/CcdVQP.png', NULL, NULL, NULL, NULL, NULL, 1, 1);
INSERT INTO `cust_info` VALUES ('15817161962', '$2a$10$vMzVtQMNYeu.YcVZOowJoOyJJOkkfbFL1jamnKch7qsK.l5rB0C5O', NULL, '11010119900307707X', '2018-08-29 14:45:08', '2018-11-06 10:27:04', 'B', 'B', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 1);
INSERT INTO `cust_info` VALUES ('15817161963', '$2a$10$yVplGWWABWpu14/J6W1rbu1FrIztbNN0wueNx0XtbQVZQadOr2YFy', NULL, '11010119900307547X', '2018-08-29 14:28:05', '2018-11-06 10:27:04', 'C', 'C', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 1);
INSERT INTO `cust_info` VALUES ('15817161964', '$2a$10$m/fW4KWXUN.17M/QCPTwFeLfZuRLSFFBxIfTUe71xGLdVBH7kCGxe', NULL, '110101199003074434', '2018-09-29 15:33:36', '2018-11-06 10:27:04', 'D', 'D', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 1);
INSERT INTO `cust_info` VALUES ('15817161965', '$2a$10$gFfqcEQp3W.rJ2Wu/21B7OOlGJ32X5PV.0qQR8IbWj0/L4ZMhWCIW', NULL, '110101199003070097', '2018-08-29 14:45:09', '2018-11-06 11:11:34', 'E', '李四', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 1);
INSERT INTO `cust_info` VALUES ('15817161966', '$2a$10$Ju3tzIXyjF0C93DFRjDXreQUBvPLJpaOK6pPo6ghWpmMM5XeTIsUK', NULL, '110101199003070695', '2018-08-29 14:45:07', '2018-11-06 10:27:04', 'F', 'F', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 2, 1);
INSERT INTO `cust_info` VALUES ('15817161967', '$2a$10$Vm6tTcGY7L9XzRQ4/0WXteurDxVSyZZK8yklw1JlB9Ix.77twIeAm', NULL, '110101199003074039', '2018-08-29 14:44:22', '2018-11-06 10:27:05', 'G', 'G', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 1);
INSERT INTO `cust_info` VALUES ('15817161968', '$2a$10$xcl6W9JIFoNr163STu2Dl.tsL.3EPbD8eRk7g95qxNoN5BqCp2dM6', NULL, '110101199003075410', '2018-08-29 14:45:08', '2018-11-06 10:27:05', 'H', 'H', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 1);
INSERT INTO `cust_info` VALUES ('15817161969', '$2a$10$xcl6W9JIFoNr163STu2Dl.tsL.3EPbD8eRk7g95qxNoN5BqCp2dM6', NULL, '110101199003078590', '2018-08-29 14:45:08', '2018-11-06 10:27:05', 'H', 'H', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 1);
INSERT INTO `cust_info` VALUES ('15817161970', '$2a$10$xcl6W9JIFoNr163STu2Dl.tsL.3EPbD8eRk7g95qxNoN5BqCp2dM6', NULL, '110101199003077854', '2018-08-29 14:45:08', '2018-11-06 10:27:05', 'H', 'H', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 1);
INSERT INTO `cust_info` VALUES ('15817161971', '$2a$10$xcl6W9JIFoNr163STu2Dl.tsL.3EPbD8eRk7g95qxNoN5BqCp2dM6', NULL, '110101199003077512', '2018-08-29 14:45:08', '2018-11-06 10:27:05', 'H', 'H', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 1);
INSERT INTO `cust_info` VALUES ('15817161972', '$2a$10$xcl6W9JIFoNr163STu2Dl.tsL.3EPbD8eRk7g95qxNoN5BqCp2dM6', NULL, '110101199003075891', '2018-08-29 14:45:08', '2018-11-06 10:27:05', 'H', 'H', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 1);
INSERT INTO `cust_info` VALUES ('15817161973', '$2a$10$xcl6W9JIFoNr163STu2Dl.tsL.3EPbD8eRk7g95qxNoN5BqCp2dM6', NULL, '130209199006098917', '2018-08-29 14:45:08', '2018-11-06 10:28:12', 'H', 'H', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 1);
INSERT INTO `cust_info` VALUES ('15844444444', '$2a$10$H/RLTEpdDjLlJGSZqKUPtes02YBSqQ6H8I/.Y5CHyeRR8T6cBBcM6', NULL, '130209199006098773', '2018-09-30 11:36:46', '2018-11-06 10:28:12', NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 1);
INSERT INTO `cust_info` VALUES ('15844455445', '$2a$10$Yh0/KVMg7CeM86j.0s0McOb3WpPMKQ8CHgBSWEtwThsi/qeKLXhFW', NULL, '130209199006097957', '2018-09-30 11:31:41', '2018-11-06 10:28:12', NULL, NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 1);
INSERT INTO `cust_info` VALUES ('15855523123', '$2a$10$5O7/qKWCa3BQ/gVb7UrnR.7LkU3TtpcpP5QE9KEmlLwDhq7f4ztJK', NULL, '440103199503123155', '2018-09-27 16:19:41', NULL, '嘻嘻嘻', '咸鱼', 1, '1990-05-09 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 1);
INSERT INTO `cust_info` VALUES ('15855555555', '$2a$10$vSKIp3j3lvEOW3W5NAnIy.ndVrZ0nX937yCwhpxQgGbus0czYNB9G', NULL, '350803196608054585', '2018-09-30 11:41:36', '2018-11-06 11:11:52', NULL, '张三', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 1);
INSERT INTO `cust_info` VALUES ('15874521321', '$2a$10$v2Lm778gkAoImuUS1axjde2rwLM0g6OVbdvYQqhapk9NLi4eaKjxG', NULL, '440103198805141545', '2018-11-21 10:54:14', '2018-11-21 14:15:54', '231312', '是是是', 2, '2018-11-27 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, 'https://luckwine-prod.oss-cn-shenzhen.aliyuncs.com/tmp/luckwine/oss/2018/11/21/06/15/QQ图片20181121141328.jpg', NULL, NULL, NULL, NULL, NULL, 1, 1);
INSERT INTO `cust_info` VALUES ('15877451212', '$2a$10$xcl6W9JIFoNr163STu2Dl.tsL.3EPbD8eRk7g95qxNoN5BqCp2dM6', NULL, '35080319660805944X', '2018-09-26 14:14:50', '2018-11-06 10:28:51', 'H', 'H', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 1);
INSERT INTO `cust_info` VALUES ('15877878451', '$2a$10$xcl6W9JIFoNr163STu2Dl.tsL.3EPbD8eRk7g95qxNoN5BqCp2dM6', NULL, '440103199112051231', '2018-09-27 14:29:03', '2018-09-27 14:32:21', 'Fish', '小小明', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 1);
INSERT INTO `cust_info` VALUES ('15966666666', '$2a$10$J2qzoA6dsayRC2qvf1g3VuXZb4NGqAYXDI62.CMIZ/tOWdMx68W86', NULL, '350803196608053064', '2018-09-29 16:01:41', '2018-11-06 10:28:22', '213213123123', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 1);
INSERT INTO `cust_info` VALUES ('15988656563', '$2a$10$xcl6W9JIFoNr163STu2Dl.tsL.3EPbD8eRk7g95qxNoN5BqCp2dM6', NULL, '350803196608059941', '2018-09-25 15:53:00', '2018-11-06 10:28:22', 'H', 'H', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 1);
INSERT INTO `cust_info` VALUES ('15999999999', '$2a$10$cdFyY18T4NeplPPUjH3bUOpF79z.UA5Z0lw9dJ51CuxjY6V5FcLa.', NULL, '350803196608059386', '2018-09-30 11:41:58', '2018-11-06 10:28:23', '444444', '77777', 2, '2018-09-16 00:00:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 1);
INSERT INTO `cust_info` VALUES ('furuihao', '$2a$10$Mqw2h8ldwhcAKeWY/9BB3uygOY9.B1GPQ6RczuPJlT7tmwfTY.wmu', NULL, '350803196608054702', '2018-10-20 09:09:39', '2018-11-06 10:28:35', 'furuihao', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'https://luckwine-prod.oss-cn-shenzhen.aliyuncs.com/luckwine/CcdVQP.png', NULL, NULL, NULL, NULL, NULL, 1, 1);
INSERT INTO `cust_info` VALUES ('tx', '$2a$10$znBGcmOrXMcMWY.WLZ1lo.BAVmV/c9XKvuPew8G8kXXCYfkm2A7tq', NULL, '350803196608052707', '2018-10-20 09:52:14', '2018-11-06 10:28:35', 'tx', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'https://luckwine-prod.oss-cn-shenzhen.aliyuncs.com/luckwine/CcdVQP.png', NULL, NULL, NULL, NULL, NULL, 1, 1);
INSERT INTO `cust_info` VALUES ('zengsijun', '$2a$10$LgSToMYVlfVxII91DF8wj.VlAVnL0I21F7zQmnGqv8GVUc9ft1/8C', NULL, '350803196608056046', '2018-10-20 09:09:35', '2018-11-06 10:28:35', 'zengsijun', NULL, 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'https://luckwine-prod.oss-cn-shenzhen.aliyuncs.com/luckwine/CcdVQP.png', NULL, NULL, NULL, NULL, NULL, 1, 1);

SET FOREIGN_KEY_CHECKS = 1;
