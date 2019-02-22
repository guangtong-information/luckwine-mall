/*
 Navicat Premium Data Transfer

 Source Server         : rm-wz9w6sgg78t2319s5mo.mysql.rds.aliyuncs.com
 Source Server Type    : MySQL
 Source Server Version : 50720
 Source Host           : rm-wz9w6sgg78t2319s5mo.mysql.rds.aliyuncs.com:3306
 Source Schema         : luckwine-marketing

 Target Server Type    : MySQL
 Target Server Version : 50720
 File Encoding         : 65001

 Date: 17/02/2019 21:42:17
*/
CREATE DATABASE `luckwine-marketing` CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
use `luckwine-marketing`;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for marketing_coupon
-- ----------------------------
DROP TABLE IF EXISTS `marketing_coupon`;
CREATE TABLE `marketing_coupon`  (
  `coupon_id` varchar(36) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '主键',
  `coupon_num` varchar(16) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '券号：YYMMDDHHmmss+4位随机数-> 16位md5加密',
  `login_account` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '登陆账号（关联cust_info表）',
  `scheme_id` varchar(36) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '方案id',
  `coupon_stat` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '卡券状态：未激活noactivate、未发放nogrant、未领取noget、未使用noused、已使用used、过期overdue',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `get_time` timestamp(0) NULL DEFAULT NULL COMMENT '领取时间',
  `use_time` timestamp(0) NULL DEFAULT NULL COMMENT '使用时间',
  PRIMARY KEY (`coupon_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '优惠券列表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of marketing_coupon
-- ----------------------------
INSERT INTO `marketing_coupon` VALUES ('000a7295-54ea-4ba8-a133-b5db7fe57ad3', '8faa4cd97a4ecf55', '123456', '1', 'overdue', '2018-11-18 12:01:11', '2018-11-28 11:33:21', '2018-11-18 12:01:11');
INSERT INTO `marketing_coupon` VALUES ('01b73bd3-1e33-403e-a622-5da7808cdc91', 'd134bae358652a3a', '1', '1', 'overdue', '2018-11-18 12:02:26', '2018-11-18 12:01:11', '2018-11-18 12:01:11');
INSERT INTO `marketing_coupon` VALUES ('0221cb51-3c19-4900-9cbb-9c1812d8db6f', '533af1d346f457c9', '1', '1', 'overdue', '2018-11-18 12:02:26', '2018-11-18 12:01:11', '2018-11-18 12:01:11');
INSERT INTO `marketing_coupon` VALUES ('04ed65f0-42ab-4537-ba82-238ca048ffc8', '6c93d4ac846bf83e', '1', '1', 'overdue', '2018-11-10 10:21:20', '2018-11-18 12:01:11', '2018-11-18 12:01:11');
INSERT INTO `marketing_coupon` VALUES ('0f2fb5bd-86e9-49e4-a6d7-b5f4aeed8fc5', '36bb8fe63896aa66', '1', '1', 'overdue', '2018-11-18 12:01:11', '2018-11-18 12:01:11', '2018-11-18 12:01:11');
INSERT INTO `marketing_coupon` VALUES ('1', '111', '1', '1', 'overdue', '2018-11-07 08:07:33', '2018-11-18 12:01:11', '2018-11-18 12:01:11');
INSERT INTO `marketing_coupon` VALUES ('10a315fd-e9ac-48ff-83cb-215bee5e3ce7', '01eeb715781e3ae9', '1', '1', 'overdue', '2018-11-18 12:02:26', '2018-11-18 12:01:11', '2018-11-18 12:01:11');
INSERT INTO `marketing_coupon` VALUES ('13b7abe6-926a-44ab-9b41-14573c3138b8', '2f5a682363943e58', '1', '1', 'overdue', '2018-11-18 12:01:11', '2018-11-18 12:01:11', '2018-11-18 12:01:11');
INSERT INTO `marketing_coupon` VALUES ('167946d1-68e6-43b9-816f-b3589b6cad21', 'dd7e48b75dd14c29', '1', '1', 'overdue', '2018-11-18 12:01:11', '2018-11-18 12:01:11', '2018-11-18 12:01:11');
INSERT INTO `marketing_coupon` VALUES ('16a46762-1277-49ea-b4b2-811da8023862', '33f79ab503c9d9d0', '1', '1', 'overdue', '2018-11-18 12:02:26', '2018-11-18 12:01:11', '2018-11-18 12:01:11');
INSERT INTO `marketing_coupon` VALUES ('16afc5fe-20e5-4654-932b-01c90b6629e6', '462abebb1e606492', '1', '1', 'overdue', '2018-11-21 10:07:35', '2018-11-18 12:01:11', '2018-11-18 12:01:11');
INSERT INTO `marketing_coupon` VALUES ('2', '222', '1', '2', 'overdue', '2018-11-07 08:07:40', '2018-11-18 12:01:11', '2018-11-18 12:01:11');
INSERT INTO `marketing_coupon` VALUES ('3', '333', '1', '3', 'overdue', '2018-11-07 08:08:07', '2018-11-18 12:01:11', '2018-11-18 12:01:11');
INSERT INTO `marketing_coupon` VALUES ('36b9feba-c27b-47d9-8484-8a363d0dc2f8', '89460a371f913597', '1', '4', 'overdue', '2018-11-10 10:21:20', '2018-11-18 12:01:11', '2018-11-18 12:01:11');
INSERT INTO `marketing_coupon` VALUES ('40bf2b9b-71d0-4c8c-ae36-23433925b03b', '5b151ba69f3c01c6', '1', '4', 'overdue', '2018-11-10 10:21:20', '2018-11-18 12:01:11', '2018-11-18 12:01:11');
INSERT INTO `marketing_coupon` VALUES ('451199be-398f-4f78-888c-95f80360995b', 'f9df394efcbcc977', '1', '1', 'overdue', '2018-11-18 12:01:11', '2018-11-18 12:01:11', '2018-11-18 12:01:11');
INSERT INTO `marketing_coupon` VALUES ('465e2f12-445b-46cf-8ae1-0605c760f108', '1ee0f99553ff049b', '1', '1', 'overdue', '2018-11-18 12:01:11', '2018-11-18 12:01:11', '2018-11-18 12:01:11');
INSERT INTO `marketing_coupon` VALUES ('487e4ff4-ddc9-45b4-8d71-636fc8e4f072', '15ac807480a267e2', '1', '1', 'overdue', '2018-11-21 10:07:35', '2018-11-18 12:01:11', '2018-11-18 12:01:11');
INSERT INTO `marketing_coupon` VALUES ('4dcba790-606a-421c-a66d-1b22d61b2d4f', '9e7a9305ccb3f8f1', '1', '1', 'overdue', '2018-11-18 12:02:26', '2018-11-18 12:01:11', '2018-11-18 12:01:11');
INSERT INTO `marketing_coupon` VALUES ('4f986860-d193-497f-a18f-935240bc3ff3', '07f165901298db98', '1', '1', 'overdue', '2018-11-21 10:07:35', '2018-11-18 12:01:11', '2018-11-18 12:01:11');
INSERT INTO `marketing_coupon` VALUES ('58ee0563-3fd9-4075-920a-e33024f0ea3e', 'af8b58ae331677fd', '1', '1', 'overdue', '2018-11-21 10:07:35', '2018-11-18 12:01:11', '2018-11-18 12:01:11');
INSERT INTO `marketing_coupon` VALUES ('6d9bd124-e177-4807-8233-4e8bd237ca28', '9a24ce3148f9cb83', '1', '1', 'overdue', '2018-11-18 12:01:11', '2018-11-18 12:01:11', '2018-11-18 12:01:11');
INSERT INTO `marketing_coupon` VALUES ('7198d9c3-ef39-4d72-8ed7-f3f088a3f10b', '7dc2d904e2a4726c', '1', '1', 'overdue', '2018-11-18 12:01:11', '2018-11-18 12:01:11', '2018-11-18 12:01:11');
INSERT INTO `marketing_coupon` VALUES ('80a5a694-05c7-404e-b0b2-b4cebc12f747', 'e2927a4738be560e', '1', '4', 'overdue', '2018-11-10 10:21:20', '2018-11-18 12:01:11', '2018-11-18 12:01:11');
INSERT INTO `marketing_coupon` VALUES ('8341ce42-b62f-429b-97f6-b35c3d818ee0', '8925365ec97fb832', '1', '1', 'overdue', '2018-11-18 12:01:11', '2018-11-18 12:01:11', '2018-11-18 12:01:11');
INSERT INTO `marketing_coupon` VALUES ('8586a2bd-7d34-4143-a5d1-f235c66192cc', '811ca1d8e561e832', '1', '4', 'overdue', '2018-11-10 10:21:20', '2018-11-18 12:01:11', '2018-11-18 12:01:11');
INSERT INTO `marketing_coupon` VALUES ('863d5530-7361-4cef-8836-300bc1f581e2', 'c33ada9710990544', '1', '1', 'overdue', '2018-11-18 12:01:11', '2018-11-18 12:01:11', '2018-11-18 12:01:11');
INSERT INTO `marketing_coupon` VALUES ('a4fea5c0-db0b-4ec2-8feb-829c2b2c3de0', '196794f258b938c8', '1', '1', 'overdue', '2018-11-18 12:02:26', '2018-11-18 12:01:11', '2018-11-18 12:01:11');
INSERT INTO `marketing_coupon` VALUES ('a63454be-603b-4204-9379-327c157ab220', '9a2e8ae81f67ff99', '1', '1', 'overdue', '2018-11-21 10:07:35', '2018-11-18 12:01:11', '2018-11-18 12:01:11');
INSERT INTO `marketing_coupon` VALUES ('b3fc33e4-b3cb-42ca-b50c-3d611f88a6bb', '66d2b0664e00b3dd', '1', '1', 'overdue', '2018-11-21 10:07:35', '2018-11-18 12:01:11', '2018-11-18 12:01:11');
INSERT INTO `marketing_coupon` VALUES ('ba3bf6cd-d048-445c-a89d-12e4637fdd6d', '091cdd060d07dfaf', '1', '1', 'overdue', '2018-11-21 10:07:35', '2018-11-18 12:01:11', '2018-11-18 12:01:11');
INSERT INTO `marketing_coupon` VALUES ('c1d51ef7-a170-4ab0-bbbe-36167d2858ab', 'e3bf0be44aab8622', '1', '5', 'overdue', '2018-11-10 10:21:20', '2018-11-18 12:01:11', '2018-11-18 12:01:11');
INSERT INTO `marketing_coupon` VALUES ('c3667935-e8b2-4f1d-a7c4-8ea758f6a842', '618e13aa521071d4', '1', '1', 'overdue', '2018-11-18 12:02:26', '2018-11-18 12:01:11', '2018-11-18 12:01:11');
INSERT INTO `marketing_coupon` VALUES ('d4e14f96-21d2-4a2a-b68c-df93a7f7cb8f', '4a9fc3f930ef4a52', '1', '5', 'overdue', '2018-11-10 10:21:20', '2018-11-18 12:01:11', '2018-11-18 12:01:11');
INSERT INTO `marketing_coupon` VALUES ('d6498f12-e28e-44a6-80a5-ca3cf67665e6', 'c8731bed2e35b976', '1', '1', 'overdue', '2018-11-18 12:02:26', '2018-11-18 12:01:11', '2018-11-18 12:01:11');
INSERT INTO `marketing_coupon` VALUES ('de1067ee-d377-45e5-86bc-b6ccc0d08e9a', 'c82e14179e2bfe50', '1', '5', 'overdue', '2018-11-10 10:21:20', '2018-11-18 12:01:11', '2018-11-18 12:01:11');
INSERT INTO `marketing_coupon` VALUES ('e335e253-5610-4401-8ba7-1ffc62a37f09', '77aecad7d46b33f0', '1', '1', 'overdue', '2018-11-21 10:07:35', '2018-11-18 12:01:11', '2018-11-18 12:01:11');
INSERT INTO `marketing_coupon` VALUES ('e85e90c0-484a-4e29-8c0f-a6a944c6c142', '430ddb328f295765', '1', '1', 'overdue', '2018-11-10 10:21:20', '2018-11-18 12:01:11', '2018-11-18 12:01:11');
INSERT INTO `marketing_coupon` VALUES ('f001f21c-e98d-4e28-aee0-86b43d17085b', 'fbd6c0324793a22c', '1', '1', 'overdue', '2018-11-18 12:02:26', '2018-11-18 12:01:11', '2018-11-18 12:01:11');
INSERT INTO `marketing_coupon` VALUES ('f3710eaa-5237-4677-92cd-1163fd93619e', '0ee0d8857730a713', '1', '1', 'overdue', '2018-11-21 10:07:35', '2018-11-18 12:01:11', '2018-11-18 12:01:11');
INSERT INTO `marketing_coupon` VALUES ('f73ad7f0-07bc-4b54-9d95-d65e319af2ff', 'bfaa0b5c049e09fa', '1', '1', 'overdue', '2018-11-10 10:21:20', '2018-11-18 12:01:11', '2018-11-18 12:01:11');
INSERT INTO `marketing_coupon` VALUES ('f9847667-2fb9-49b1-b935-aaddacafbc4e', '9947d3d70bebadec', '1', '1', 'overdue', '2018-11-18 12:02:26', '2018-11-18 12:01:11', '2018-11-18 12:01:11');
INSERT INTO `marketing_coupon` VALUES ('fe83b3ee-1e3b-4995-9456-2ec00688bad0', '65c0c7ea55d2c78a', '1', '1', 'overdue', '2018-11-21 10:07:35', '2018-11-18 12:01:11', '2018-11-18 12:01:11');

-- ----------------------------
-- Table structure for marketing_expenses
-- ----------------------------
DROP TABLE IF EXISTS `marketing_expenses`;
CREATE TABLE `marketing_expenses`  (
  `market_orderno` varchar(36) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '营销交易流水号',
  `ext_trs_seq` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '外部订单号-主单号',
  `request_seq` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '请求流水号-资金流水号',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '交易时间',
  `scheme_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '方案id',
  `coupon_num` varchar(16) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '券号',
  `user_account` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '登陆账号',
  `discount_amount` decimal(12, 2) NOT NULL DEFAULT 0.00 COMMENT '优惠金额/折扣',
  `status` varchar(1) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '0' COMMENT '0:成功 1:冲正',
  PRIMARY KEY (`market_orderno`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '营销交易流水表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of marketing_expenses
-- ----------------------------
INSERT INTO `marketing_expenses` VALUES ('1', '1', '1', '2018-08-29 20:37:00', '1', '1', '1', 1.00, '0');
INSERT INTO `marketing_expenses` VALUES ('26327f4a-b125-4d5a-8f25-e6ba4c437806', '1', '1111', '2018-11-18 12:02:27', '1', '111', '1', 100.00, '0');
INSERT INTO `marketing_expenses` VALUES ('618509b4-c9e6-4301-be25-ea27d756c878', '1', '1111', '2018-11-18 12:01:12', '1', '111', '1', 100.00, '0');
INSERT INTO `marketing_expenses` VALUES ('dad4ed6d-9c19-469d-8981-fd848a912999', '1', '1111', '2018-11-07 19:54:43', '1', '111', '1', 100.00, '0');
INSERT INTO `marketing_expenses` VALUES ('dd779fa7-653d-40eb-acd5-fb0ad6701e6a', '1', '1111', '2018-11-21 10:07:36', '1', '111', '1', 100.00, '0');
INSERT INTO `marketing_expenses` VALUES ('f1ed51a3-9050-4716-96f7-2347f2c1aae7', '1', '1111', '2018-11-07 19:47:07', '1', '111', '1', 100.00, '0');

-- ----------------------------
-- Table structure for marketing_scheme
-- ----------------------------
DROP TABLE IF EXISTS `marketing_scheme`;
CREATE TABLE `marketing_scheme`  (
  `scheme_id` varchar(36) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '方案id',
  `scheme_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '方案名称',
  `scheme_type` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '方案分类：卡券-coupon 优惠-discount',
  `scheme_obj` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '方案优惠对象：0.全场商品 1.指定商品（本期不实现） 2.商品分类（本期不实现）',
  `effective_starttime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '方案生效开始时间',
  `effective_endtime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '方案生效结束时间',
  `scheme_stat` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT 'disable' COMMENT '方案状态：启用enabled 停用disable 已失效invalid',
  `discount_total` int(10) NULL DEFAULT NULL COMMENT '优惠券总量：卡券才有用',
  `get_limit` int(10) NULL DEFAULT NULL COMMENT '限领张数：卡券才有用',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `submit_user_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '0' COMMENT '后台OSS创建人id',
  `submit_username` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '后台OSS创建人名称',
  `use_rule` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT 'unlimited' COMMENT '满足使用的规则：满额full_amount，满减full_discount',
  `use_amount` decimal(12, 2) NOT NULL DEFAULT 0.00 COMMENT '满足数值：满多少元',
  `discount_amount` decimal(12, 2) NOT NULL DEFAULT 0.00 COMMENT '优惠数值',
  `discount_unit` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '优惠单位：元rmb, 折扣discount',
  `gen_coupon` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否已经生成优惠券：0.未生成 1.已生成',
  `online_coupon_center` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否上架领券中心:0.未上架 1.上架',
  PRIMARY KEY (`scheme_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '营销方案表\r\n唯一标志：有效时间、scheme_type、scheme_obj、scheme_stat' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of marketing_scheme
-- ----------------------------
INSERT INTO `marketing_scheme` VALUES ('1', 'TEST1', 'coupon', '2', '2018-09-04 21:08:37', '2018-12-04 21:08:37', 'invalid', 10, 1, '2018-09-04 21:08:37', '2018-12-14 12:00:03', '0', 'bobo', 'full_amount', 1000.00, 100.00, 'rmb', 1, 1);
INSERT INTO `marketing_scheme` VALUES ('2', 'TEST2', 'coupon', '1', '2018-09-04 21:08:50', '2018-12-04 21:08:50', 'invalid', 100, 2, '2018-09-04 21:08:50', '2018-12-14 12:00:03', '0', 'bobo', 'full_amount', 800.00, 0.95, 'discount', 1, 1);
INSERT INTO `marketing_scheme` VALUES ('3', 'TEST3', 'coupon', '0', '2018-09-04 21:08:52', '2018-12-04 21:08:52', 'invalid', 1, 3, '2018-09-04 21:08:52', '2018-12-14 12:00:03', '0', 'bobo', 'full_discount', 200.00, 10.00, 'rmb', 1, 1);
INSERT INTO `marketing_scheme` VALUES ('4', 'TEST4', '', '0', '2018-09-04 21:09:00', '2018-09-04 21:09:00', 'invalid', 4, 4, '2018-09-04 21:09:00', '2018-12-14 12:00:03', '0', 'bobo', 'unlimited', 0.00, 0.00, '', 0, 0);
INSERT INTO `marketing_scheme` VALUES ('5', 'TEST5', '', '0', '2018-09-04 21:09:11', '2018-09-04 21:09:11', 'invalid', 8, 5, '2018-09-04 21:09:11', '2018-12-14 12:00:03', '0', 'bobo', 'unlimited', 0.00, 0.00, '', 0, 0);
INSERT INTO `marketing_scheme` VALUES ('6', 'TEST6', '', '0', '2018-09-04 21:09:27', '2018-09-04 21:09:27', 'invalid', 9, 6, '2018-09-04 21:09:27', '2018-12-14 12:00:03', '0', 'bobo', 'unlimited', 0.00, 0.00, '', 0, 0);

-- ----------------------------
-- Table structure for marketing_scheme_obj
-- ----------------------------
DROP TABLE IF EXISTS `marketing_scheme_obj`;
CREATE TABLE `marketing_scheme_obj`  (
  `id` int(11) NOT NULL COMMENT '主键',
  `scheme_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '方案id',
  `scheme_obj` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '方案优惠对象：指定商品1（本期不实现）、商品分类2（本期不实现）',
  `scheme_obj_value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '方案优惠对象的值(指定商品1，值为skuid；商品分类2，值为分类id，对应category表)',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '营销方案优惠对象表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
