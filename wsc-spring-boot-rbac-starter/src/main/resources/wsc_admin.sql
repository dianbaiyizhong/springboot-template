/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 80030
 Source Host           : localhost:3306
 Source Schema         : wsc_admin

 Target Server Type    : MySQL
 Target Server Version : 80030
 File Encoding         : 65001

 Date: 11/02/2023 14:23:57
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_admin_user
-- ----------------------------
DROP TABLE IF EXISTS `t_admin_user`;
CREATE TABLE `t_admin_user`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `pass_word` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `gmt_create` datetime(6) NULL DEFAULT NULL,
  `current_role_id` bigint(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_user_name`(`user_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 55 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_admin_user
-- ----------------------------
INSERT INTO `t_admin_user` VALUES (1, 'admin', '$2a$10$nsZirEDHw/qv7j8d1oP6YeLWQDc2sZUZophuYFLbIoBXap1JHEmXm', '2022-01-25 20:29:26.000000', 1);

-- ----------------------------
-- Table structure for t_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission`  (
  `id` bigint(0) UNSIGNED NOT NULL AUTO_INCREMENT,
  `pid` bigint(0) NOT NULL DEFAULT 0,
  `request_url` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '后端请求路径',
  `request_method` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '后端请求方式',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '菜单名称',
  `title` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '菜单名称i18n',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '描述',
  `path` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '前端页面路径',
  `component` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '前端页面组件',
  `icon` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '图标',
  `show_flag` tinyint(0) NOT NULL DEFAULT 1 COMMENT '是否显示:0-隐藏;1-显示',
  `type` tinyint(0) NOT NULL DEFAULT 1 COMMENT '权限类型:0-目录;1-菜单;2-按钮',
  `status` tinyint(0) NOT NULL DEFAULT 1 COMMENT '状态:0-禁用;1-启用',
  `order_no` tinyint(0) NOT NULL DEFAULT 1 COMMENT '排序',
  `created_at` datetime(0) NULL DEFAULT NULL,
  `updated_at` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_permission
-- ----------------------------
INSERT INTO `t_permission` VALUES (1, 0, '/api/dashboard', 'GET', '控制台', 'routes.dashboard.dashboard', '控制台', '/dashboard', 'LAYOUT', 'mdi-view-dashboard-outline', 1, 0, 1, 1, '2022-11-19 15:49:01', '2022-11-19 15:49:01');
INSERT INTO `t_permission` VALUES (2, 0, '/api/system', 'GET', 'rbac权限菜单', 'routes.demo.system.moduleName', '系统管理目录', '/system', 'LAYOUT', 'mdi-key-outline', 1, 0, 1, 1, '2022-11-19 15:49:01', '2022-11-19 15:49:01');
INSERT INTO `t_permission` VALUES (3, 2, '/api/users', 'GET', '账号管理', 'routes.demo.system.account', '账号管理菜单', 'account', '/demo/system/account/index', '', 1, 1, 1, 1, '2022-11-19 15:49:01', '2022-11-19 15:49:01');
INSERT INTO `t_permission` VALUES (4, 3, '/api/users', 'POST', '新增账号', '', '新增账号按钮', '', '', '', 1, 2, 1, 1, '2022-11-19 15:49:01', '2022-11-19 15:49:01');
INSERT INTO `t_permission` VALUES (5, 2, '/api/users', 'GET', '菜单管理', 'routes.demo.system.menu', '菜单管理菜单', 'menu', '/demo/system/menu/index', '', 1, 1, 1, 1, '2022-11-19 15:49:01', '2022-11-19 15:49:01');
INSERT INTO `t_permission` VALUES (6, 5, '/api/users', 'POST', '新增菜单', '', '新增菜单按钮', '', '', '', 1, 2, 1, 1, '2022-11-19 15:49:01', '2022-11-19 15:49:01');
INSERT INTO `t_permission` VALUES (7, 2, '/api/users', 'GET', '角色管理', 'routes.demo.system.role', '角色管理菜单', 'role', '/demo/system/role/index', '', 1, 1, 1, 1, '2022-11-19 15:49:01', '2022-11-19 15:49:01');
INSERT INTO `t_permission` VALUES (8, 2, '/admin/list', 'GET', '用户管理', 'routes.demo.system.role', '用户表查询接口', '', '', '', 1, 1, 1, 1, '2023-02-11 13:26:33', '2023-02-11 13:26:36');

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `comment` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `gmt_create` datetime(6) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES (1, 'admin', '管理员', '2022-09-04 08:26:49.000000');
INSERT INTO `t_role` VALUES (2, 'audit', '设计员', '2022-09-04 08:26:50.000000');

-- ----------------------------
-- Table structure for t_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_role_permission`;
CREATE TABLE `t_role_permission`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(0) NULL DEFAULT NULL,
  `permission_id` bigint(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_role_permission
-- ----------------------------
INSERT INTO `t_role_permission` VALUES (1, 1, 1);
INSERT INTO `t_role_permission` VALUES (2, 1, 3);
INSERT INTO `t_role_permission` VALUES (3, 2, 4);
INSERT INTO `t_role_permission` VALUES (4, 1, 8);

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(0) NULL DEFAULT NULL,
  `role_id` bigint(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES (24, 1, 1);
INSERT INTO `t_user_role` VALUES (25, 1, 2);

SET FOREIGN_KEY_CHECKS = 1;
