/*
 Navicat Premium Data Transfer

 Source Server         : rm-wz9w6sgg78t2319s5mo.mysql.rds.aliyuncs.com
 Source Server Type    : MySQL
 Source Server Version : 50720
 Source Host           : rm-wz9w6sgg78t2319s5mo.mysql.rds.aliyuncs.com:3306
 Source Schema         : luckwine-bgw

 Target Server Type    : MySQL
 Target Server Version : 50720
 File Encoding         : 65001

 Date: 17/02/2019 21:41:39
*/
CREATE DATABASE `luckwine-bgw` CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
use `luckwine-bgw`;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for express
-- ----------------------------
DROP TABLE IF EXISTS `express`;
CREATE TABLE `express`  (
  `id` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '主键',
  `shipper_code` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '快递公司编码',
  `shipper_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '快递公司名称',
  `exp_bis_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '快递/快运业务类型',
  `exp_type` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '快递鸟接口参数，（ExpType：默认为1）',
  `create_date` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建日期',
  `update_date` timestamp(0) NULL COMMENT '更新日期',
  `state` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '状态1：有效，0：无效',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '快递公司表\r\n参见：\r\n（1）快递公司快递业务类型.xlsx\r\n（2）2018快递鸟接口支持快递公司编码.xlsx' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of express
-- ----------------------------
INSERT INTO `express` VALUES ('1', 'SF', '顺丰速运', '顺丰标快', '1', '2018-11-16 11:22:53', '0000-00-00 00:00:00', '1');
INSERT INTO `express` VALUES ('2', 'SF', '顺丰速运', '顺丰特惠', '2', '2018-11-16 11:25:43', '0000-00-00 00:00:00', '1');
INSERT INTO `express` VALUES ('3', 'YTO', '圆通速递', '上门揽收', '1', '2018-11-16 11:24:50', '0000-00-00 00:00:00', '1');

-- ----------------------------
-- Table structure for express_goods
-- ----------------------------
DROP TABLE IF EXISTS `express_goods`;
CREATE TABLE `express_goods`  (
  `goods_order_code` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '商品单号',
  `goods_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '商品名称',
  `goods_quantity` decimal(5, 0) NULL DEFAULT NULL COMMENT '商品件数',
  `goods_weight` decimal(12, 3) NULL DEFAULT NULL COMMENT '商品重量kg',
  `goods_volume` decimal(12, 3) NULL DEFAULT NULL COMMENT '商品体积m3',
  `goods_desc` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '商品描述',
  `goods_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '商品SKU编码',
  `goods_price` decimal(12, 3) NULL DEFAULT NULL COMMENT '商品价格',
  `sub_order_code` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '子单号',
  `main_order_code` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '主单号',
  `create_date` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建日期',
  `update_date` timestamp(0) NULL COMMENT '修改日期',
  PRIMARY KEY (`goods_order_code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '物流商品表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for express_order
-- ----------------------------
DROP TABLE IF EXISTS `express_order`;
CREATE TABLE `express_order`  (
  `sub_order_code` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '子单号',
  `main_order_code` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '主单号',
  `member_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '会员IDERP 系统、电商平台等系统或平台类型用户的会员 ID 或店铺账号等唯一性标识，用于区分其用户',
  `custom_area` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '商家自定义区域',
  `ware_house_id` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '发货仓编码(ShipperCode为JDKY 时必填)',
  `trans_type` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '运输方式:1-陆运，2-空运，不填默认为1',
  `is_return_sign_bill` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '是否要求签回单,0-不要求，1-要求',
  `operate_require` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '签回单操作要求(如：签名、盖章、身份证复印件等)',
  `is_notice` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '是否通知快递员上门揽件 0-通知，1-不通知，不填则默认为 1',
  `start_date` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '上门揽件开始时间(YYYY-MM-DD HH24:MM:SS)',
  `end_date` timestamp(0) NULL COMMENT '上门揽件结束时间(YYYY-MM-DD HH24:MM:SS)',
  `shipper_code` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '快递公司编码',
  `pay_type` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '\"运费支付方式（1现付、2到付、3月结、4第三方-仅SF支持）\"',
  `exp_type` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '快递类型（快递鸟接口参数）',
  `cost` decimal(12, 3) NULL DEFAULT NULL COMMENT '快递运费',
  `other_cost` decimal(12, 3) NULL DEFAULT NULL COMMENT '其他费用',
  `weight` decimal(10, 2) NULL DEFAULT NULL COMMENT '商品总重量kg',
  `quantity` int(5) NOT NULL COMMENT '商品包裹总数',
  `volume` decimal(12, 3) NULL DEFAULT NULL COMMENT '包裹总体积m3',
  `remark` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  `is_return_print_template` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '是否返回电子面单模板（0-不需要，1-需要）',
  `logistic_code` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '快递单号（物流公司返回的快递单号）',
  `kdn_order_code` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '快递鸟返回的订单号',
  `state` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '物流状态：0-无轨迹 1-已揽收 2-在途中 3-签收 4-问题件',
  `traces` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '物流轨迹',
  `result_msg` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '返回结果描述',
  `result_code` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '返回编码，必传',
  `create_date` timestamp(0) NULL COMMENT '创建日期',
  `update_date` timestamp(0) NULL COMMENT '修改日期',
  PRIMARY KEY (`sub_order_code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '物流单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of express_order
-- ----------------------------
INSERT INTO `express_order` VALUES ('1', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2018-12-02 11:34:20', NULL, 'EMS', '1', '1', 1.000, NULL, NULL, 0, NULL, NULL, NULL, '5042260908504', '', '2', '[{\"acceptStation\":\"深圳市横岗速递营销部已收件，（揽投员姓名：钟定基;联系电话：）\",\"acceptTime\":\"2015-03-06 21:16:58\",\"remark\":\"\"},{\"acceptStation\":\"离开深圳市 发往广州市\",\"acceptTime\":\"2015-03-07 14:25:00\",\"remark\":\"\"},{\"acceptStation\":\"到达广东速递物流公司广航中心处理中心（经转）\",\"acceptTime\":\"2015-03-08 00:17:00\",\"remark\":\"\"},{\"acceptStation\":\"离开广州市 发往北京市（经转）\",\"acceptTime\":\"2015-03-08 01:15:00\",\"remark\":\"\"},{\"acceptStation\":\"到达北京黄村转运站处理中心（经转）\",\"acceptTime\":\"2015-03-09 09:01:00\",\"remark\":\"\"},{\"acceptStation\":\"离开北京市 发往呼和浩特市（经转）\",\"acceptTime\":\"2015-03-09 18:39:00\",\"remark\":\"\"},{\"acceptStation\":\"到达  呼和浩特市 处理中心\",\"acceptTime\":\"2015-03-10 18:06:00\",\"remark\":\"\"},{\"acceptStation\":\"呼和浩特市邮政速递物流分公司金川揽投部安排投递（投递员姓名：安长虹;联系电话：18047140142）\",\"acceptTime\":\"2015-03-11 09:53:48\",\"remark\":\"\"}]', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for express_user
-- ----------------------------
DROP TABLE IF EXISTS `express_user`;
CREATE TABLE `express_user`  (
  `id` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '主键',
  `company` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '公司',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '名称',
  `mobile` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '手机',
  `province_name` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '省',
  `city_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '市',
  `exp_area_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '区/县',
  `address` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '详细地址',
  `post_code` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '邮编(ShipperCode 为EMS、YZPY、YZBK 时必填)',
  `type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '类型，0：发件人，1：收件人',
  `sub_order_code` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '子单号',
  `main_order_code` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '主单号',
  `create_date` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建日期',
  `update_date` timestamp(0) NULL COMMENT '修改日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '物流用户表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
