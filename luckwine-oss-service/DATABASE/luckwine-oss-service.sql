/*
 Navicat Premium Data Transfer

 Source Server         : rm-wz9w6sgg78t2319s5mo.mysql.rds.aliyuncs.com
 Source Server Type    : MySQL
 Source Server Version : 50720
 Source Host           : rm-wz9w6sgg78t2319s5mo.mysql.rds.aliyuncs.com:3306
 Source Schema         : luckwine-oss

 Target Server Type    : MySQL
 Target Server Version : 50720
 File Encoding         : 65001

 Date: 17/02/2019 21:42:30
*/
CREATE DATABASE `luckwine-oss` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
use `luckwine-oss`;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_log
-- ----------------------------
DROP TABLE IF EXISTS `t_log`;
CREATE TABLE `t_log`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `del_flag` int(11) NULL DEFAULT NULL,
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `cost_time` int(11) NULL DEFAULT NULL,
  `ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ip_info` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `request_param` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `request_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `request_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_log
-- ----------------------------
INSERT INTO `t_log` VALUES ('16752943696973824', NULL, '2018-06-06 19:42:34', 0, NULL, '2018-06-06 19:42:34', 312, '127.0.0.1', '本机地址', '登录系统', '{\"password\":\"你是看不见我的\",\"username\":\"admin\"}', 'POST', '/xboot/login', 'admin');

-- ----------------------------
-- Table structure for t_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `del_flag` int(11) NULL DEFAULT NULL,
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `parent_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` int(11) NULL DEFAULT NULL,
  `sort_order` decimal(10, 2) NULL DEFAULT NULL,
  `component` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `level` int(11) NULL DEFAULT NULL,
  `button_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_permission
-- ----------------------------
INSERT INTO `t_permission` VALUES ('15701400130424832', NULL, '2018-06-03 22:04:06', 0, NULL, '2018-09-15 11:13:09', NULL, NULL, '5129710648430593', 1, 1.11, NULL, '/oss/user/admin/add', '添加用户', NULL, 3, 'add', 0);
INSERT INTO `t_permission` VALUES ('15701915807518720', '', '2018-06-03 22:06:09', 0, '', '2018-06-06 14:46:51', '', '', '5129710648430593', 1, 1.13, '', '/oss/user/admin/disable/**', '禁用用户', '', 3, 'disable', 0);
INSERT INTO `t_permission` VALUES ('15708892205944832', '', '2018-06-03 22:33:52', 0, '', '2018-06-06 14:46:55', '', '', '5129710648430593', 1, 1.14, '', '/oss/user/admin/enable/**', '启用用户', '', 3, 'undefined', 0);
INSERT INTO `t_permission` VALUES ('16392452747300864', NULL, '2018-06-05 19:50:06', 0, NULL, '2018-09-07 22:07:02', NULL, 'access', NULL, 0, 100.00, 'Main', '/access', '权限按钮测试页', 'locked', 1, NULL, 0);
INSERT INTO `t_permission` VALUES ('16392767785668608', '', '2018-06-05 19:51:21', 0, '', '2018-06-05 21:10:15', '', 'access_index', '16392452747300864', 0, 2.10, 'access/access', 'index', '权限按钮测试页', 'locked', 2, '', 0);
INSERT INTO `t_permission` VALUES ('16438800255291392', '', '2018-06-05 22:54:18', 0, '', '2018-06-05 22:54:59', '', '', '16392767785668608', 1, 2.11, '', 'test-add', '添加按钮测试', '', 3, 'add', 0);
INSERT INTO `t_permission` VALUES ('16438962738434048', NULL, '2018-06-05 22:54:55', 0, NULL, '2018-06-05 22:54:55', NULL, NULL, '16392767785668608', 1, 2.12, NULL, 'edit-test', '编辑按钮测试', NULL, 3, 'edit', 0);
INSERT INTO `t_permission` VALUES ('16439068543946752', '', '2018-06-05 22:55:20', 0, '', '2018-06-05 22:55:33', '', '', '16392767785668608', 1, 2.13, '', 'delete-test', '删除按钮测试', '', 3, 'delete', 0);
INSERT INTO `t_permission` VALUES ('16678126574637056', '', '2018-06-06 14:45:16', 0, '', '2018-06-06 14:46:45', '', '', '5129710648430593', 1, 1.12, '', '/oss/user/admin/edit', '编辑用户', '', 3, 'edit', 0);
INSERT INTO `t_permission` VALUES ('16678447719911424', '', '2018-06-06 14:46:32', 0, '', '2018-06-06 18:49:43', '', '', '5129710648430593', 1, 1.15, '', '/oss/user/delByIds**', '删除用户', '', 3, 'delete', 0);
INSERT INTO `t_permission` VALUES ('16687383932047360', NULL, '2018-06-06 15:22:03', 0, NULL, '2018-06-06 15:22:03', NULL, NULL, '5129710648430594', 1, 1.21, NULL, '/oss/role/save', '添加角色', NULL, 3, 'add', 0);
INSERT INTO `t_permission` VALUES ('16689632049631232', NULL, '2018-06-06 15:30:59', 0, NULL, '2018-06-06 15:30:59', NULL, NULL, '5129710648430594', 1, 1.22, NULL, '/oss/role/edit', '编辑角色', NULL, 3, 'edit', 0);
INSERT INTO `t_permission` VALUES ('16689745006432256', '', '2018-06-06 15:31:26', 0, '', '2018-06-06 18:49:51', '', '', '5129710648430594', 1, 1.23, '', '/oss/role/delAllByIds**', '删除角色', '', 3, 'delete', 0);
INSERT INTO `t_permission` VALUES ('16689883993083904', NULL, '2018-06-06 15:31:59', 0, NULL, '2018-06-06 15:31:59', NULL, NULL, '5129710648430594', 1, 1.24, NULL, '/oss/role/editRolePerm/**', '分配权限', NULL, 3, 'editPerm', 0);
INSERT INTO `t_permission` VALUES ('16690313745666048', NULL, '2018-06-06 15:33:41', 0, NULL, '2018-06-06 15:33:41', NULL, NULL, '5129710648430594', 1, 1.25, NULL, '/oss/role/setDefault', '设为默认角色', NULL, 3, 'setDefault', 0);
INSERT INTO `t_permission` VALUES ('16694861252005888', NULL, '2018-06-06 15:51:46', 0, NULL, '2018-06-06 15:51:46', NULL, NULL, '5129710648430595', 1, 1.31, NULL, '/oss/permission/add', '添加菜单', NULL, 3, 'add', 0);
INSERT INTO `t_permission` VALUES ('16695107491205120', NULL, '2018-06-06 15:52:44', 0, NULL, '2018-06-06 15:52:44', NULL, NULL, '5129710648430595', 1, 1.32, NULL, '/oss/permission/edit', '编辑菜单', NULL, 3, 'edit', 0);
INSERT INTO `t_permission` VALUES ('16695243126607872', '', '2018-06-06 15:53:17', 0, '', '2018-06-06 18:49:57', '', '', '5129710648430595', 1, 1.33, '', '/oss/permission/delByIds**', '删除菜单', '', 3, 'delete', 0);
INSERT INTO `t_permission` VALUES ('16695482789138432', '', '2018-06-06 15:54:14', 0, '', '2018-06-06 18:50:03', '', '', '5129710648430596', 1, 1.41, '', '/oss/log/delByIds**', '删除日志', '', 3, 'delete', 0);
INSERT INTO `t_permission` VALUES ('16695638456537088', NULL, '2018-06-06 15:54:51', 0, NULL, '2018-06-06 15:54:51', NULL, NULL, '5129710648430596', 1, 1.42, NULL, '/oss/log/delAll', '清空日志', NULL, 3, 'clearAll', 0);
INSERT INTO `t_permission` VALUES ('46456040622919680', NULL, '2018-08-27 18:52:04', 0, NULL, '2018-09-07 22:05:28', NULL, 'customer', NULL, 0, 1.00, 'Main', '/customer', '客户中心', 'bonfire', 1, NULL, 0);
INSERT INTO `t_permission` VALUES ('46457364794052608', NULL, '2018-08-27 18:57:20', 0, NULL, '2018-09-07 22:05:35', NULL, 'user-info', '46456040622919680', 0, 1.00, 'customer/user-info/userInfo', 'user-info', '客户管理', 'ios-people', 2, NULL, 0);
INSERT INTO `t_permission` VALUES ('49762812121059328', NULL, '2018-09-05 21:52:00', 0, NULL, '2018-09-07 22:06:44', NULL, 'acct', NULL, 0, 3.00, 'Main', '/acct', '账户中心', 'bonfire', 1, NULL, 0);
INSERT INTO `t_permission` VALUES ('49945268354813952', NULL, '2018-09-06 09:57:01', 0, NULL, '2018-09-08 10:49:50', NULL, 'acct-list', '49762812121059328', 0, 1.00, 'acct/acct-list/acctList', 'acct-list', '账户管理', 'ios-people', 2, NULL, 0);
INSERT INTO `t_permission` VALUES ('50682820967796736', NULL, '2018-09-08 10:47:47', 0, NULL, '2018-09-08 10:47:47', NULL, 'acct-expenses', '49762812121059328', 0, 2.00, 'acct/acct-list/acct-expenses', 'acct-expenses', '账户收支明细', 'ios-people', 2, NULL, 0);
INSERT INTO `t_permission` VALUES ('50683099968704512', NULL, '2018-09-08 10:48:54', 0, NULL, '2018-09-08 10:48:54', NULL, 'trans', NULL, 0, 4.00, 'Main', '/trans', '交易中心', 'bonfire', 1, NULL, 0);
INSERT INTO `t_permission` VALUES ('50683435768877056', NULL, '2018-09-08 10:50:14', 0, NULL, '2018-09-08 10:50:14', NULL, 'trans-list', '50683099968704512', 0, 1.00, 'trans/trans-list/transList', 'trans-list', '交易管理', 'ios-people', 2, NULL, 0);
INSERT INTO `t_permission` VALUES ('50683718964088832', NULL, '2018-09-08 10:51:21', 0, NULL, '2018-09-08 10:51:21', NULL, 'marketing', NULL, 0, 5.00, 'Main', '/marketing', '营销中心', 'bonfire', 1, NULL, 0);
INSERT INTO `t_permission` VALUES ('50684168132104192', NULL, '2018-09-08 10:53:08', 0, NULL, '2018-11-18 11:38:47', NULL, 'scheme', '50683718964088832', 0, 6.00, 'marketing/scheme/schemeList', '/scheme', '营销方案', 'ios-people', 2, NULL, 0);
INSERT INTO `t_permission` VALUES ('50684472823123968', NULL, '2018-09-08 10:54:21', 0, NULL, '2018-11-25 15:42:43', NULL, 'coupon', '50683718964088832', 0, 2.00, 'marketing/coupon/couponLlist', '/coupon', '优惠券列表', 'ios-people', 2, NULL, 0);
INSERT INTO `t_permission` VALUES ('50684536186474496', NULL, '2018-09-08 10:54:36', 0, NULL, '2018-11-27 16:47:37', NULL, 'expenses', '50683718964088832', 0, 3.00, 'marketing/expenses/expensesList', '/expenses', '营销流水', 'ios-people', 2, NULL, 0);
INSERT INTO `t_permission` VALUES ('50684699772719104', NULL, '2018-09-08 10:55:15', 0, NULL, '2018-09-08 10:55:15', NULL, '1', NULL, 0, 6.00, 'Main', '1', '支付管理', 'bonfire', 1, NULL, 0);
INSERT INTO `t_permission` VALUES ('50684810833694720', NULL, '2018-09-08 10:55:42', 0, NULL, '2018-09-08 10:55:42', NULL, '1', '50684699772719104', 0, 1.00, '1', '1', '支付流水', 'ios-people', 2, NULL, 0);
INSERT INTO `t_permission` VALUES ('50684972712857600', NULL, '2018-09-08 10:56:20', 0, NULL, '2018-10-11 15:45:52', NULL, 'adManager', NULL, 0, 7.00, 'Main', '/adManager', '广告管理', 'bonfire', 1, NULL, 0);
INSERT INTO `t_permission` VALUES ('50685051158925312', NULL, '2018-09-08 10:56:39', 0, NULL, '2018-10-27 01:45:33', NULL, 'adModule', '50684972712857600', 0, 1.00, 'synthesize/admodule/adModuleList', '/adModule', '广告模块管理', 'ios-people', 2, NULL, 0);
INSERT INTO `t_permission` VALUES ('50685187905818624', NULL, '2018-09-08 10:57:11', 0, NULL, '2018-10-27 01:45:42', NULL, 'adContent', '50684972712857600', 0, 2.00, 'synthesize/adcontent/adContentList', '/adContent', '广告内容管理', 'ios-people', 2, NULL, 0);
INSERT INTO `t_permission` VALUES ('50685282026000384', NULL, '2018-09-08 10:57:34', 0, NULL, '2018-10-12 14:56:42', NULL, 'smsManager', NULL, 0, 8.00, 'Main', '/smsManager', '短信管理', 'bonfire', 1, NULL, 0);
INSERT INTO `t_permission` VALUES ('50685440478416896', NULL, '2018-09-08 10:58:12', 0, NULL, '2018-10-27 01:46:34', NULL, 'smsTemplate', '50685282026000384', 0, 1.00, 'synthesize/smstemplate/smsTemplateList', '/smsTemplate', '短信模板', 'ios-people', 2, NULL, 0);
INSERT INTO `t_permission` VALUES ('50685511886442496', NULL, '2018-09-08 10:58:29', 0, NULL, '2018-10-27 01:46:03', NULL, 'smsLog', '50685282026000384', 0, 2.00, 'synthesize/smslog/smsLogList', '/smsLog', '短信发送记录', 'ios-people', 2, NULL, 0);
INSERT INTO `t_permission` VALUES ('5129710648430592', NULL, '2018-06-04 19:02:29', 0, NULL, '2018-09-10 11:23:30', NULL, 'sys', NULL, 0, 11.00, 'Main', '/form', '系统管理', 'ios-gear', 1, NULL, 0);
INSERT INTO `t_permission` VALUES ('5129710648430593', NULL, '2018-06-04 19:02:32', 0, NULL, '2018-06-04 19:02:43', NULL, 'user-manage', '5129710648430592', 0, 1.10, 'sys/user-manage/userManage', 'user-manage', '用户管理', 'android-person', 2, NULL, 0);
INSERT INTO `t_permission` VALUES ('5129710648430594', NULL, '2018-06-04 19:02:35', 0, NULL, '2018-06-04 19:02:45', NULL, 'role-manage', '5129710648430592', 0, 1.20, 'sys/role-manage/roleManage', 'role-manage', '角色管理', 'android-contacts', 2, NULL, 0);
INSERT INTO `t_permission` VALUES ('5129710648430595', NULL, '2018-06-04 19:02:37', 0, NULL, '2018-06-04 19:02:49', NULL, 'menu-manage', '5129710648430592', 0, 1.30, 'sys/menu-manage/menuManage', 'menu-manage', '菜单权限管理', 'navicon-round', 2, NULL, 0);
INSERT INTO `t_permission` VALUES ('5129710648430596', NULL, '2018-06-04 19:02:40', 0, NULL, '2018-06-04 19:02:53', NULL, 'log-manage', '5129710648430592', 0, 1.40, 'sys/log-manage/logManage', 'log-manage', '日志管理', 'android-list', 2, NULL, 0);
INSERT INTO `t_permission` VALUES ('52640222852157440', NULL, '2018-09-13 20:25:48', 0, NULL, '2018-09-14 09:03:08', NULL, 'goodsManage', NULL, 0, 1.00, 'Main', 'goodsManage', '商品管理', 'bonfire', 1, NULL, 0);
INSERT INTO `t_permission` VALUES ('52640523852189696', NULL, '2018-09-13 20:27:00', 0, NULL, '2018-09-14 09:02:44', NULL, 'brand', '52640222852157440', 0, 1.00, 'goods/brand/brandList', 'brand', '品牌管理', 'ios-people', 2, NULL, 0);
INSERT INTO `t_permission` VALUES ('52640924424998912', NULL, '2018-09-13 20:28:35', 0, NULL, '2018-09-14 09:02:40', NULL, 'category', '52640222852157440', 0, 1.00, 'goods/category/categoryList', 'category', '分类管理', 'ios-people', 2, NULL, 0);
INSERT INTO `t_permission` VALUES ('52882213208854528', NULL, '2018-09-14 12:27:24', 0, NULL, '2018-09-22 16:09:32', NULL, 'spu', '52640222852157440', 0, 1.00, 'goods/spu/spuList', 'spu', '商品管理', 'ios-people', 2, NULL, 0);
INSERT INTO `t_permission` VALUES ('52882540599447552', NULL, '2018-09-14 12:28:41', 0, NULL, '2018-09-14 12:29:28', NULL, 'props', '52640222852157440', 0, 1.00, 'goods/props/propsList', 'props', '属性配置', 'ios-people', 2, NULL, 0);
INSERT INTO `t_permission` VALUES ('55511312473526272', NULL, '2018-09-21 18:34:29', 0, NULL, '2018-09-21 18:34:29', NULL, 'goodsEs', '52640222852157440', 0, 1.00, 'goods/es/esList', 'goodsEs', 'Es', 'ios-people', 2, NULL, 0);

-- ----------------------------
-- Table structure for t_permission_copy1
-- ----------------------------
DROP TABLE IF EXISTS `t_permission_copy1`;
CREATE TABLE `t_permission_copy1`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `del_flag` int(11) NULL DEFAULT NULL,
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `parent_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` int(11) NULL DEFAULT NULL,
  `sort_order` decimal(10, 2) NULL DEFAULT NULL,
  `component` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `level` int(11) NULL DEFAULT NULL,
  `button_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_permission_copy1
-- ----------------------------
INSERT INTO `t_permission_copy1` VALUES ('15701400130424832', '', '2018-06-03 22:04:06', 0, '', '2018-06-06 18:17:20', '', '', '5129710648430593', 1, 1.11, '', '/xboot/user/admin/add', '添加用户', '', 3, 'add', 0);
INSERT INTO `t_permission_copy1` VALUES ('15701915807518720', '', '2018-06-03 22:06:09', 0, '', '2018-06-06 14:46:51', '', '', '5129710648430593', 1, 1.13, '', '/xboot/user/admin/disable/**', '禁用用户', '', 3, 'disable', 0);
INSERT INTO `t_permission_copy1` VALUES ('15708892205944832', '', '2018-06-03 22:33:52', 0, '', '2018-06-06 14:46:55', '', '', '5129710648430593', 1, 1.14, '', '/xboot/user/admin/enable/**', '启用用户', '', 3, 'undefined', 0);
INSERT INTO `t_permission_copy1` VALUES ('16392452747300864', NULL, '2018-06-05 19:50:06', 0, NULL, '2018-09-07 22:07:02', NULL, 'access', NULL, 0, 100.00, 'Main', '/access', '权限按钮测试页', 'locked', 1, NULL, 0);
INSERT INTO `t_permission_copy1` VALUES ('16392767785668608', '', '2018-06-05 19:51:21', 0, '', '2018-06-05 21:10:15', '', 'access_index', '16392452747300864', 0, 2.10, 'access/access', 'index', '权限按钮测试页', 'locked', 2, '', 0);
INSERT INTO `t_permission_copy1` VALUES ('16438800255291392', '', '2018-06-05 22:54:18', 0, '', '2018-06-05 22:54:59', '', '', '16392767785668608', 1, 2.11, '', 'test-add', '添加按钮测试', '', 3, 'add', 0);
INSERT INTO `t_permission_copy1` VALUES ('16438962738434048', NULL, '2018-06-05 22:54:55', 0, NULL, '2018-06-05 22:54:55', NULL, NULL, '16392767785668608', 1, 2.12, NULL, 'edit-test', '编辑按钮测试', NULL, 3, 'edit', 0);
INSERT INTO `t_permission_copy1` VALUES ('16439068543946752', '', '2018-06-05 22:55:20', 0, '', '2018-06-05 22:55:33', '', '', '16392767785668608', 1, 2.13, '', 'delete-test', '删除按钮测试', '', 3, 'delete', 0);
INSERT INTO `t_permission_copy1` VALUES ('16678126574637056', '', '2018-06-06 14:45:16', 0, '', '2018-06-06 14:46:45', '', '', '5129710648430593', 1, 1.12, '', '/xboot/user/admin/edit', '编辑用户', '', 3, 'edit', 0);
INSERT INTO `t_permission_copy1` VALUES ('16678447719911424', '', '2018-06-06 14:46:32', 0, '', '2018-06-06 18:49:43', '', '', '5129710648430593', 1, 1.15, '', '/xboot/user/delByIds**', '删除用户', '', 3, 'delete', 0);
INSERT INTO `t_permission_copy1` VALUES ('16687383932047360', NULL, '2018-06-06 15:22:03', 0, NULL, '2018-06-06 15:22:03', NULL, NULL, '5129710648430594', 1, 1.21, NULL, '/xboot/role/save', '添加角色', NULL, 3, 'add', 0);
INSERT INTO `t_permission_copy1` VALUES ('16689632049631232', NULL, '2018-06-06 15:30:59', 0, NULL, '2018-06-06 15:30:59', NULL, NULL, '5129710648430594', 1, 1.22, NULL, '/xboot/role/edit', '编辑角色', NULL, 3, 'edit', 0);
INSERT INTO `t_permission_copy1` VALUES ('16689745006432256', '', '2018-06-06 15:31:26', 0, '', '2018-06-06 18:49:51', '', '', '5129710648430594', 1, 1.23, '', '/xboot/role/delAllByIds**', '删除角色', '', 3, 'delete', 0);
INSERT INTO `t_permission_copy1` VALUES ('16689883993083904', NULL, '2018-06-06 15:31:59', 0, NULL, '2018-06-06 15:31:59', NULL, NULL, '5129710648430594', 1, 1.24, NULL, '/xboot/role/editRolePerm/**', '分配权限', NULL, 3, 'editPerm', 0);
INSERT INTO `t_permission_copy1` VALUES ('16690313745666048', NULL, '2018-06-06 15:33:41', 0, NULL, '2018-06-06 15:33:41', NULL, NULL, '5129710648430594', 1, 1.25, NULL, '/xboot/role/setDefault', '设为默认角色', NULL, 3, 'setDefault', 0);
INSERT INTO `t_permission_copy1` VALUES ('16694861252005888', NULL, '2018-06-06 15:51:46', 0, NULL, '2018-06-06 15:51:46', NULL, NULL, '5129710648430595', 1, 1.31, NULL, '/xboot/permission/add', '添加菜单', NULL, 3, 'add', 0);
INSERT INTO `t_permission_copy1` VALUES ('16695107491205120', NULL, '2018-06-06 15:52:44', 0, NULL, '2018-06-06 15:52:44', NULL, NULL, '5129710648430595', 1, 1.32, NULL, '/xboot/permission/edit', '编辑菜单', NULL, 3, 'edit', 0);
INSERT INTO `t_permission_copy1` VALUES ('16695243126607872', '', '2018-06-06 15:53:17', 0, '', '2018-06-06 18:49:57', '', '', '5129710648430595', 1, 1.33, '', '/xboot/permission/delByIds**', '删除菜单', '', 3, 'delete', 0);
INSERT INTO `t_permission_copy1` VALUES ('16695482789138432', '', '2018-06-06 15:54:14', 0, '', '2018-06-06 18:50:03', '', '', '5129710648430596', 1, 1.41, '', '/xboot/log/delByIds**', '删除日志', '', 3, 'delete', 0);
INSERT INTO `t_permission_copy1` VALUES ('16695638456537088', NULL, '2018-06-06 15:54:51', 0, NULL, '2018-06-06 15:54:51', NULL, NULL, '5129710648430596', 1, 1.42, NULL, '/xboot/log/delAll', '清空日志', NULL, 3, 'clearAll', 0);
INSERT INTO `t_permission_copy1` VALUES ('22499552766464000', '', '2018-08-27 18:13:28', 0, '', '2018-08-27 18:14:25', '', 'goods', '', 0, 1.00, 'Main', '/goods', '商品管理', 'bonfire', 1, '', 0);
INSERT INTO `t_permission_copy1` VALUES ('46456040622919680', NULL, '2018-08-27 18:52:04', 0, NULL, '2018-09-07 22:05:28', NULL, 'customer', NULL, 0, 1.00, 'Main', '/customer', '客户中心', 'bonfire', 1, NULL, 0);
INSERT INTO `t_permission_copy1` VALUES ('46457364794052608', NULL, '2018-08-27 18:57:20', 0, NULL, '2018-09-07 22:05:35', NULL, 'user-info', '46456040622919680', 0, 1.00, 'customer/user-info/userInfo', 'user-info', '客户管理', 'ios-people', 2, NULL, 0);
INSERT INTO `t_permission_copy1` VALUES ('49762812121059328', NULL, '2018-09-05 21:52:00', 0, NULL, '2018-09-07 22:06:44', NULL, 'acct', NULL, 0, 3.00, 'Main', '/acct', '账户中心', 'bonfire', 1, NULL, 0);
INSERT INTO `t_permission_copy1` VALUES ('49945268354813952', NULL, '2018-09-06 09:57:01', 0, NULL, '2018-09-08 10:49:50', NULL, 'acct-list', '49762812121059328', 0, 1.00, 'acct/acct-list/acctList', 'acct-list', '账户管理', 'ios-people', 2, NULL, 0);
INSERT INTO `t_permission_copy1` VALUES ('50491263249551360', NULL, '2018-09-07 22:06:36', 0, NULL, '2018-09-07 22:06:36', NULL, 'goods', NULL, 0, 2.00, 'Main', '/goods', '商品中心', 'bonfire', 1, NULL, 0);
INSERT INTO `t_permission_copy1` VALUES ('50491748459220992', NULL, '2018-09-07 22:08:32', 0, NULL, '2018-09-07 22:08:32', NULL, 'acct-list', '50491263249551360', 0, 4.00, '1', 'acct-list', '商品管理', 'ios-people', 2, NULL, 0);
INSERT INTO `t_permission_copy1` VALUES ('50682820967796736', NULL, '2018-09-08 10:47:47', 0, NULL, '2018-09-08 10:47:47', NULL, 'acct-expenses', '49762812121059328', 0, 2.00, 'acct/acct-list/acct-expenses', 'acct-expenses', '账户收支明细', 'ios-people', 2, NULL, 0);
INSERT INTO `t_permission_copy1` VALUES ('50683099968704512', NULL, '2018-09-08 10:48:54', 0, NULL, '2018-09-08 10:48:54', NULL, 'trans', NULL, 0, 4.00, 'Main', '/trans', '交易中心', 'bonfire', 1, NULL, 0);
INSERT INTO `t_permission_copy1` VALUES ('50683435768877056', NULL, '2018-09-08 10:50:14', 0, NULL, '2018-09-08 10:50:14', NULL, 'trans-list', '50683099968704512', 0, 1.00, 'trans/trans-list/transList', 'trans-list', '交易管理', 'ios-people', 2, NULL, 0);
INSERT INTO `t_permission_copy1` VALUES ('50683718964088832', NULL, '2018-09-08 10:51:21', 0, NULL, '2018-09-08 10:51:21', NULL, 'marketing', NULL, 0, 5.00, 'Main', '/marketing', '营销中心', 'bonfire', 1, NULL, 0);
INSERT INTO `t_permission_copy1` VALUES ('50684168132104192', NULL, '2018-09-08 10:53:08', 0, NULL, '2018-09-08 10:53:57', NULL, '1', '50683718964088832', 0, 6.00, '1', '1', '营销方案', 'ios-people', 2, NULL, 0);
INSERT INTO `t_permission_copy1` VALUES ('50684472823123968', NULL, '2018-09-08 10:54:21', 0, NULL, '2018-09-08 10:54:21', NULL, '1', '50683718964088832', 0, 2.00, '1', '1', '优惠券列表', 'ios-people', 2, NULL, 0);
INSERT INTO `t_permission_copy1` VALUES ('50684536186474496', NULL, '2018-09-08 10:54:36', 0, NULL, '2018-09-08 10:54:36', NULL, '1', '50683718964088832', 0, 3.00, '1', '1', '营销流水', 'ios-people', 2, NULL, 0);
INSERT INTO `t_permission_copy1` VALUES ('50684699772719104', NULL, '2018-09-08 10:55:15', 0, NULL, '2018-09-08 10:55:15', NULL, '1', NULL, 0, 6.00, 'Main', '1', '支付管理', 'bonfire', 1, NULL, 0);
INSERT INTO `t_permission_copy1` VALUES ('50684810833694720', NULL, '2018-09-08 10:55:42', 0, NULL, '2018-09-08 10:55:42', NULL, '1', '50684699772719104', 0, 1.00, '1', '1', '支付流水', 'ios-people', 2, NULL, 0);
INSERT INTO `t_permission_copy1` VALUES ('50684972712857600', NULL, '2018-09-08 10:56:20', 0, NULL, '2018-09-08 10:56:20', NULL, '1', NULL, 0, 7.00, 'Main', '1', '广告管理', 'bonfire', 1, NULL, 0);
INSERT INTO `t_permission_copy1` VALUES ('50685051158925312', NULL, '2018-09-08 10:56:39', 0, NULL, '2018-09-08 10:56:39', NULL, '1', '50684972712857600', 0, 1.00, '1', '1', '广告模块管理', 'ios-people', 2, NULL, 0);
INSERT INTO `t_permission_copy1` VALUES ('50685187905818624', NULL, '2018-09-08 10:57:11', 0, NULL, '2018-09-08 10:57:11', NULL, '1', '50684972712857600', 0, 2.00, '1', '1', '广告内容管理', 'ios-people', 2, NULL, 0);
INSERT INTO `t_permission_copy1` VALUES ('50685282026000384', NULL, '2018-09-08 10:57:34', 0, NULL, '2018-09-08 10:57:34', NULL, '1', NULL, 0, 8.00, 'Main', '1', '短信管理', 'bonfire', 1, NULL, 0);
INSERT INTO `t_permission_copy1` VALUES ('50685440478416896', NULL, '2018-09-08 10:58:12', 0, NULL, '2018-09-08 10:58:12', NULL, '1', '50685282026000384', 0, 1.00, '1', '1', '短信模板', 'ios-people', 2, NULL, 0);
INSERT INTO `t_permission_copy1` VALUES ('50685511886442496', NULL, '2018-09-08 10:58:29', 0, NULL, '2018-09-08 10:58:29', NULL, '1', '50685282026000384', 0, 2.00, '1', '1', '短信发送记录', 'ios-people', 2, NULL, 0);
INSERT INTO `t_permission_copy1` VALUES ('50685586498916352', NULL, '2018-09-08 10:58:47', 0, NULL, '2018-09-08 10:58:47', NULL, '1', '22499552766464000', 0, 1.00, '1', '1', '商品管理', 'ios-people', 2, NULL, 0);
INSERT INTO `t_permission_copy1` VALUES ('5129710648430592', NULL, '2018-06-04 19:02:29', 0, NULL, '2018-09-08 11:00:44', NULL, 'sys', NULL, 0, 0.00, 'Main', '/form', '系统管理', 'ios-gear', 1, NULL, 0);
INSERT INTO `t_permission_copy1` VALUES ('5129710648430593', NULL, '2018-06-04 19:02:32', 0, NULL, '2018-06-04 19:02:43', NULL, 'user-manage', '5129710648430592', 0, 1.10, 'sys/user-manage/userManage', 'user-manage', '用户管理', 'android-person', 2, NULL, 0);
INSERT INTO `t_permission_copy1` VALUES ('5129710648430594', NULL, '2018-06-04 19:02:35', 0, NULL, '2018-06-04 19:02:45', NULL, 'role-manage', '5129710648430592', 0, 1.20, 'sys/role-manage/roleManage', 'role-manage', '角色管理', 'android-contacts', 2, NULL, 0);
INSERT INTO `t_permission_copy1` VALUES ('5129710648430595', NULL, '2018-06-04 19:02:37', 0, NULL, '2018-06-04 19:02:49', NULL, 'menu-manage', '5129710648430592', 0, 1.30, 'sys/menu-manage/menuManage', 'menu-manage', '菜单权限管理', 'navicon-round', 2, NULL, 0);
INSERT INTO `t_permission_copy1` VALUES ('5129710648430596', NULL, '2018-06-04 19:02:40', 0, NULL, '2018-06-04 19:02:53', NULL, 'log-manage', '5129710648430592', 0, 1.40, 'sys/log-manage/logManage', 'log-manage', '日志管理', 'android-list', 2, NULL, 0);

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `del_flag` int(11) NULL DEFAULT NULL,
  `default_role` bit(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('16457350655250432', NULL, '2018-06-06 00:08:00', NULL, '2018-06-06 00:08:00', 'ROLE_TEST', 0, NULL);
INSERT INTO `t_role` VALUES ('496138616573952', '', '2018-04-22 23:03:49', '', '2018-05-04 12:56:23', 'ROLE_ADMIN', 0, NULL);
INSERT INTO `t_role` VALUES ('496138616573953', '', '2018-05-02 21:40:03', '', '2018-05-08 10:33:32', 'ROLE_USER', 0, b'1');

-- ----------------------------
-- Table structure for t_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_role_permission`;
CREATE TABLE `t_role_permission`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `del_flag` int(11) NULL DEFAULT NULL,
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `permission_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `role_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_role_permission
-- ----------------------------
INSERT INTO `t_role_permission` VALUES ('52639226017091584', NULL, '2018-09-13 20:21:51', 0, NULL, '2018-09-13 20:21:51', '46456040622919680', '496138616573953');
INSERT INTO `t_role_permission` VALUES ('52639226235195392', NULL, '2018-09-13 20:21:51', 0, NULL, '2018-09-13 20:21:51', '46457364794052608', '496138616573953');
INSERT INTO `t_role_permission` VALUES ('52639226465882112', NULL, '2018-09-13 20:21:51', 0, NULL, '2018-09-13 20:21:51', '5129710648430592', '496138616573953');
INSERT INTO `t_role_permission` VALUES ('52639226679791616', NULL, '2018-09-13 20:21:51', 0, NULL, '2018-09-13 20:21:51', '5129710648430593', '496138616573953');
INSERT INTO `t_role_permission` VALUES ('52639226893701120', NULL, '2018-09-13 20:21:51', 0, NULL, '2018-09-13 20:21:51', '5129710648430594', '496138616573953');
INSERT INTO `t_role_permission` VALUES ('52639227103416320', NULL, '2018-09-13 20:21:51', 0, NULL, '2018-09-13 20:21:51', '5129710648430595', '496138616573953');
INSERT INTO `t_role_permission` VALUES ('52639227342491648', NULL, '2018-09-13 20:21:51', 0, NULL, '2018-09-13 20:21:51', '5129710648430596', '496138616573953');
INSERT INTO `t_role_permission` VALUES ('52639227556401152', NULL, '2018-09-13 20:21:51', 0, NULL, '2018-09-13 20:21:51', '16392452747300864', '496138616573953');
INSERT INTO `t_role_permission` VALUES ('52639227770310656', NULL, '2018-09-13 20:21:51', 0, NULL, '2018-09-13 20:21:51', '16392767785668608', '496138616573953');
INSERT INTO `t_role_permission` VALUES ('52639227980025856', NULL, '2018-09-13 20:21:51', 0, NULL, '2018-09-13 20:21:51', '16438800255291392', '496138616573953');
INSERT INTO `t_role_permission` VALUES ('52639228193935360', NULL, '2018-09-13 20:21:51', 0, NULL, '2018-09-13 20:21:51', '16438962738434048', '496138616573953');
INSERT INTO `t_role_permission` VALUES ('52639228407844864', NULL, '2018-09-13 20:21:51', 0, NULL, '2018-09-13 20:21:51', '16439068543946752', '496138616573953');
INSERT INTO `t_role_permission` VALUES ('52639242181939200', NULL, '2018-09-13 20:21:54', 0, NULL, '2018-09-13 20:21:54', '46456040622919680', '16457350655250432');
INSERT INTO `t_role_permission` VALUES ('52639242387460096', NULL, '2018-09-13 20:21:54', 0, NULL, '2018-09-13 20:21:54', '46457364794052608', '16457350655250432');
INSERT INTO `t_role_permission` VALUES ('52639242592980992', NULL, '2018-09-13 20:21:54', 0, NULL, '2018-09-13 20:21:54', '16392452747300864', '16457350655250432');
INSERT INTO `t_role_permission` VALUES ('52639242815279104', NULL, '2018-09-13 20:21:55', 0, NULL, '2018-09-13 20:21:55', '16392767785668608', '16457350655250432');
INSERT INTO `t_role_permission` VALUES ('52639243029188608', NULL, '2018-09-13 20:21:55', 0, NULL, '2018-09-13 20:21:55', '16439068543946752', '16457350655250432');
INSERT INTO `t_role_permission` VALUES ('64925520554364928', NULL, '2018-10-17 18:03:12', 0, NULL, '2018-10-17 18:03:12', '46456040622919680', '496138616573952');
INSERT INTO `t_role_permission` VALUES ('64925521510666240', NULL, '2018-10-17 18:03:12', 0, NULL, '2018-10-17 18:03:12', '46457364794052608', '496138616573952');
INSERT INTO `t_role_permission` VALUES ('64925521707798528', NULL, '2018-10-17 18:03:12', 0, NULL, '2018-10-17 18:03:12', '52640222852157440', '496138616573952');
INSERT INTO `t_role_permission` VALUES ('64925521904930816', NULL, '2018-10-17 18:03:12', 0, NULL, '2018-10-17 18:03:12', '52640523852189696', '496138616573952');
INSERT INTO `t_role_permission` VALUES ('64925522114646016', NULL, '2018-10-17 18:03:12', 0, NULL, '2018-10-17 18:03:12', '52640924424998912', '496138616573952');
INSERT INTO `t_role_permission` VALUES ('64925522324361216', NULL, '2018-10-17 18:03:12', 0, NULL, '2018-10-17 18:03:12', '52882213208854528', '496138616573952');
INSERT INTO `t_role_permission` VALUES ('64925522538270720', NULL, '2018-10-17 18:03:12', 0, NULL, '2018-10-17 18:03:12', '52882540599447552', '496138616573952');
INSERT INTO `t_role_permission` VALUES ('64925522764763136', NULL, '2018-10-17 18:03:12', 0, NULL, '2018-10-17 18:03:12', '55511312473526272', '496138616573952');
INSERT INTO `t_role_permission` VALUES ('64925522957701120', NULL, '2018-10-17 18:03:12', 0, NULL, '2018-10-17 18:03:12', '49762812121059328', '496138616573952');
INSERT INTO `t_role_permission` VALUES ('64925523154833408', NULL, '2018-10-17 18:03:12', 0, NULL, '2018-10-17 18:03:12', '49945268354813952', '496138616573952');
INSERT INTO `t_role_permission` VALUES ('64925523347771392', NULL, '2018-10-17 18:03:12', 0, NULL, '2018-10-17 18:03:12', '50682820967796736', '496138616573952');
INSERT INTO `t_role_permission` VALUES ('64925523540709376', NULL, '2018-10-17 18:03:12', 0, NULL, '2018-10-17 18:03:12', '50683099968704512', '496138616573952');
INSERT INTO `t_role_permission` VALUES ('64925523737841664', NULL, '2018-10-17 18:03:12', 0, NULL, '2018-10-17 18:03:12', '50683435768877056', '496138616573952');
INSERT INTO `t_role_permission` VALUES ('64925523934973952', NULL, '2018-10-17 18:03:12', 0, NULL, '2018-10-17 18:03:12', '50683718964088832', '496138616573952');
INSERT INTO `t_role_permission` VALUES ('64925524132106240', NULL, '2018-10-17 18:03:12', 0, NULL, '2018-10-17 18:03:12', '50684472823123968', '496138616573952');
INSERT INTO `t_role_permission` VALUES ('64925524333432832', NULL, '2018-10-17 18:03:12', 0, NULL, '2018-10-17 18:03:12', '50684536186474496', '496138616573952');
INSERT INTO `t_role_permission` VALUES ('64925524564119552', NULL, '2018-10-17 18:03:12', 0, NULL, '2018-10-17 18:03:12', '50684168132104192', '496138616573952');
INSERT INTO `t_role_permission` VALUES ('64925524752863232', NULL, '2018-10-17 18:03:12', 0, NULL, '2018-10-17 18:03:12', '50684699772719104', '496138616573952');
INSERT INTO `t_role_permission` VALUES ('64925524949995520', NULL, '2018-10-17 18:03:12', 0, NULL, '2018-10-17 18:03:12', '50684810833694720', '496138616573952');
INSERT INTO `t_role_permission` VALUES ('64925525142933504', NULL, '2018-10-17 18:03:12', 0, NULL, '2018-10-17 18:03:12', '50684972712857600', '496138616573952');
INSERT INTO `t_role_permission` VALUES ('64925525340065792', NULL, '2018-10-17 18:03:13', 0, NULL, '2018-10-17 18:03:13', '50685051158925312', '496138616573952');
INSERT INTO `t_role_permission` VALUES ('64925525545586688', NULL, '2018-10-17 18:03:13', 0, NULL, '2018-10-17 18:03:13', '50685187905818624', '496138616573952');
INSERT INTO `t_role_permission` VALUES ('64925525738524672', NULL, '2018-10-17 18:03:13', 0, NULL, '2018-10-17 18:03:13', '50685282026000384', '496138616573952');
INSERT INTO `t_role_permission` VALUES ('64925525965017088', NULL, '2018-10-17 18:03:13', 0, NULL, '2018-10-17 18:03:13', '50685440478416896', '496138616573952');
INSERT INTO `t_role_permission` VALUES ('64925526195703808', NULL, '2018-10-17 18:03:13', 0, NULL, '2018-10-17 18:03:13', '50685511886442496', '496138616573952');
INSERT INTO `t_role_permission` VALUES ('64925526397030400', NULL, '2018-10-17 18:03:13', 0, NULL, '2018-10-17 18:03:13', '5129710648430592', '496138616573952');
INSERT INTO `t_role_permission` VALUES ('64925526602551296', NULL, '2018-10-17 18:03:13', 0, NULL, '2018-10-17 18:03:13', '5129710648430593', '496138616573952');
INSERT INTO `t_role_permission` VALUES ('64925526799683584', NULL, '2018-10-17 18:03:13', 0, NULL, '2018-10-17 18:03:13', '15701400130424832', '496138616573952');
INSERT INTO `t_role_permission` VALUES ('64925527005204480', NULL, '2018-10-17 18:03:13', 0, NULL, '2018-10-17 18:03:13', '16678126574637056', '496138616573952');
INSERT INTO `t_role_permission` VALUES ('64925527206531072', NULL, '2018-10-17 18:03:13', 0, NULL, '2018-10-17 18:03:13', '15701915807518720', '496138616573952');
INSERT INTO `t_role_permission` VALUES ('64925527399469056', NULL, '2018-10-17 18:03:13', 0, NULL, '2018-10-17 18:03:13', '15708892205944832', '496138616573952');
INSERT INTO `t_role_permission` VALUES ('64925527613378560', NULL, '2018-10-17 18:03:13', 0, NULL, '2018-10-17 18:03:13', '16678447719911424', '496138616573952');
INSERT INTO `t_role_permission` VALUES ('64925527802122240', NULL, '2018-10-17 18:03:13', 0, NULL, '2018-10-17 18:03:13', '5129710648430594', '496138616573952');
INSERT INTO `t_role_permission` VALUES ('64925527990865920', NULL, '2018-10-17 18:03:13', 0, NULL, '2018-10-17 18:03:13', '16687383932047360', '496138616573952');
INSERT INTO `t_role_permission` VALUES ('64925528187998208', NULL, '2018-10-17 18:03:13', 0, NULL, '2018-10-17 18:03:13', '16689632049631232', '496138616573952');
INSERT INTO `t_role_permission` VALUES ('64925528385130496', NULL, '2018-10-17 18:03:13', 0, NULL, '2018-10-17 18:03:13', '16689745006432256', '496138616573952');
INSERT INTO `t_role_permission` VALUES ('64925528573874176', NULL, '2018-10-17 18:03:13', 0, NULL, '2018-10-17 18:03:13', '16689883993083904', '496138616573952');
INSERT INTO `t_role_permission` VALUES ('64925528775200768', NULL, '2018-10-17 18:03:13', 0, NULL, '2018-10-17 18:03:13', '16690313745666048', '496138616573952');
INSERT INTO `t_role_permission` VALUES ('64925528972333056', NULL, '2018-10-17 18:03:13', 0, NULL, '2018-10-17 18:03:13', '5129710648430595', '496138616573952');
INSERT INTO `t_role_permission` VALUES ('64925529161076736', NULL, '2018-10-17 18:03:13', 0, NULL, '2018-10-17 18:03:13', '16694861252005888', '496138616573952');
INSERT INTO `t_role_permission` VALUES ('64925529358209024', NULL, '2018-10-17 18:03:13', 0, NULL, '2018-10-17 18:03:13', '16695107491205120', '496138616573952');
INSERT INTO `t_role_permission` VALUES ('64925529551147008', NULL, '2018-10-17 18:03:14', 0, NULL, '2018-10-17 18:03:14', '16695243126607872', '496138616573952');
INSERT INTO `t_role_permission` VALUES ('64925529752473600', NULL, '2018-10-17 18:03:14', 0, NULL, '2018-10-17 18:03:14', '5129710648430596', '496138616573952');
INSERT INTO `t_role_permission` VALUES ('64925529945411584', NULL, '2018-10-17 18:03:14', 0, NULL, '2018-10-17 18:03:14', '16695482789138432', '496138616573952');
INSERT INTO `t_role_permission` VALUES ('64925530138349568', NULL, '2018-10-17 18:03:14', 0, NULL, '2018-10-17 18:03:14', '16695638456537088', '496138616573952');
INSERT INTO `t_role_permission` VALUES ('64925530331287552', NULL, '2018-10-17 18:03:14', 0, NULL, '2018-10-17 18:03:14', '16392452747300864', '496138616573952');
INSERT INTO `t_role_permission` VALUES ('64925530524225536', NULL, '2018-10-17 18:03:14', 0, NULL, '2018-10-17 18:03:14', '16392767785668608', '496138616573952');
INSERT INTO `t_role_permission` VALUES ('64925530717163520', NULL, '2018-10-17 18:03:14', 0, NULL, '2018-10-17 18:03:14', '16438800255291392', '496138616573952');
INSERT INTO `t_role_permission` VALUES ('64925530905907200', NULL, '2018-10-17 18:03:14', 0, NULL, '2018-10-17 18:03:14', '16438962738434048', '496138616573952');
INSERT INTO `t_role_permission` VALUES ('64925531098845184', NULL, '2018-10-17 18:03:14', 0, NULL, '2018-10-17 18:03:14', '16439068543946752', '496138616573952');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `avatar` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `mobile` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `nick_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sex` int(11) NULL DEFAULT NULL,
  `status` int(11) NULL DEFAULT NULL,
  `type` int(11) NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `del_flag` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('4363087427670016', '', '2018-05-03 15:09:42', '', '2018-08-27 18:14:58', '[\"510000\",\"510100\",\"510114\"]', 'https://s1.ax1x.com/2018/05/19/CcdVQP.png', '', '1726474652@qq.com', '15810202015', '', '$2a$10$LPNcntYGxcWSngwZJj08D.A2biv.k2sQUsGrxLc2HkvL9DA1LbLaO', 1, 0, 1, 'test', 0);
INSERT INTO `t_user` VALUES ('682265633886208', '', '2018-05-01 16:13:51', '', '2018-06-01 19:32:59', '[\"510000\",\"510100\",\"510104\"]', 'http://p77xsahe9.bkt.clouddn.com/788eb3e78206429eb34fc7cd3e1e46f4.jpg', NULL, '1726474652@qq.com', '15810202015', NULL, '$2a$10$PS04ecXfknNd3V8d.ymLTObQciapMU4xU8.GADBZZsuTZr7ymnagy', 1, 0, 1, 'admin', 0);
INSERT INTO `t_user` VALUES ('682265633886209', '', '2018-04-30 23:28:42', '', '2018-09-20 17:15:35', '', 'https://s1.ax1x.com/2018/05/19/CcdVQP.png', '', '1726474652@qq.com', '15810202015', '', '$2a$10$PS04ecXfknNd3V8d.ymLTObQciapMU4xU8.GADBZZsuTZr7ymnagy', 0, 0, 1, 'Howell', 0);

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `del_flag` int(11) NULL DEFAULT NULL,
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `role_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES ('15358614818525184', NULL, '2018-06-02 23:22:00', 0, NULL, '2018-06-02 23:22:00', '496138616573952', '15325672666828800');
INSERT INTO `t_user_role` VALUES ('16056421829316608', NULL, '2018-06-04 21:34:50', 0, NULL, '2018-06-04 21:34:50', '496138616573953', '6118792149078016');
INSERT INTO `t_user_role` VALUES ('16457761273417728', NULL, '2018-06-06 00:09:39', 0, NULL, '2018-06-06 00:09:39', '16457350655250432', '16457750745714688');
INSERT INTO `t_user_role` VALUES ('16674372659974144', NULL, '2018-06-06 14:30:21', 0, NULL, '2018-06-06 14:30:21', '496138616573952', '682265633886209');
INSERT INTO `t_user_role` VALUES ('16674372718694400', NULL, '2018-06-06 14:30:21', 0, NULL, '2018-06-06 14:30:21', '496138616573953', '682265633886209');
INSERT INTO `t_user_role` VALUES ('16738254220955648', NULL, '2018-06-06 18:44:11', 0, NULL, '2018-06-06 18:44:11', '16457350655250432', '16738253642141696');
INSERT INTO `t_user_role` VALUES ('16741709752832000', NULL, '2018-06-06 18:57:55', 0, NULL, '2018-06-06 18:57:55', '16457350655250432', '16739222421508096');
INSERT INTO `t_user_role` VALUES ('4365473638518788', NULL, '2018-05-08 11:25:45', 0, NULL, '2018-05-08 11:25:50', '496138616573952', '682265633886208');
INSERT INTO `t_user_role` VALUES ('46446702822952960', NULL, '2018-08-27 18:14:58', 0, NULL, '2018-08-27 18:14:58', '496138616573952', '4363087427670016');

SET FOREIGN_KEY_CHECKS = 1;
