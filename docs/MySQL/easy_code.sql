/*
 Navicat Premium Data Transfer

 Source Server         : my-test-8.0
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : 127.0.0.1:3306
 Source Schema         : easy_code

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 12/01/2021 11:30:12
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `menu_parent_id` int(11) NOT NULL COMMENT '父类id 一级目录统一为0，二级目录的parentId为一级的主键',
  `menu_level` int(11) NOT NULL COMMENT '是否为目录 目录为0级/其余为1级',
  `menu_name` varchar(30) COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单名称',
  `menu_url` varchar(30) COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单url',
  `menu_icon` varchar(30) COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单icon',
  `menu_sort` int(11) DEFAULT NULL COMMENT '菜单排序',
  `menu_describe` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '菜单描述',
  `del_flag` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除 0:未删除/1:删除',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='菜单表';

-- ----------------------------
-- Records of t_menu
-- ----------------------------
BEGIN;
INSERT INTO `t_menu` VALUES (1, 0, 0, '数据源管理', 'datasource', 'el-icon-s-data', 1, '添加数据源', b'0', '2020-12-31 09:11:00', '2021-01-11 20:39:32');
INSERT INTO `t_menu` VALUES (2, 1, 1, '数据源列表', 'datasourcelist', 'datasourcelist', 2, '数据源列表', b'0', '2020-12-31 09:11:27', '2021-01-11 20:39:26');
INSERT INTO `t_menu` VALUES (3, 0, 0, '生成管理', 'generate', 'el-icon-download', 3, '生成管理', b'0', '2020-12-31 09:12:04', '2021-01-08 20:33:34');
INSERT INTO `t_menu` VALUES (4, 3, 1, '生成代码', 'generatecode', 'el-icon-document', 4, '生成代码', b'0', '2020-12-31 09:12:43', '2021-01-08 20:34:22');
INSERT INTO `t_menu` VALUES (5, 0, 0, '权限管理', 'auth', 'el-icon-lock', 5, '权限管理', b'0', '2020-12-31 09:13:20', '2021-01-08 20:36:41');
INSERT INTO `t_menu` VALUES (6, 5, 1, '用户管理', 'user', 'user', 6, '用户管理', b'0', '2020-12-31 09:13:36', '2020-12-31 10:21:43');
INSERT INTO `t_menu` VALUES (7, 5, 1, '角色管理', 'role', 'el-icon-coordinate', 7, '角色管理', b'0', '2020-12-31 09:14:01', '2021-01-08 20:31:01');
INSERT INTO `t_menu` VALUES (8, 5, 1, '菜单管理', 'menu', 'el-icon-menu', 8, '菜单管理', b'0', '2020-12-31 09:14:23', '2021-01-08 20:28:03');
INSERT INTO `t_menu` VALUES (13, 5, 1, 'icons', 'icon', 'icon', 0, 'icon图标', b'0', '2021-01-08 20:24:18', '2021-01-08 20:25:10');
INSERT INTO `t_menu` VALUES (16, 5, 1, '登录日志', 'el-icon-document-copy', 'log', NULL, '登录日志', b'0', '2021-01-10 00:11:55', '2021-01-10 00:17:28');
INSERT INTO `t_menu` VALUES (17, 5, 1, '错误日志', 'bug', 'log', NULL, '错误日志', b'0', '2021-01-10 00:12:17', '2021-01-10 00:15:07');
COMMIT;

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名称',
  `role_describe` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '角色描述',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `del_flag` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除 0:未删除/1:删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='角色表';

-- ----------------------------
-- Records of t_role
-- ----------------------------
BEGIN;
INSERT INTO `t_role` VALUES (1, 'admin', '可创建数据源，可生成代码', '2020-12-30 11:32:58', '2021-01-02 20:39:29', b'0');
INSERT INTO `t_role` VALUES (2, '开发人员', '可生成代码', '2020-12-30 11:33:31', '2020-12-30 11:33:31', b'0');
INSERT INTO `t_role` VALUES (3, '测试角色', '这个角色，通过接口生成', '2020-12-30 15:35:43', '2020-12-30 15:35:43', b'0');
INSERT INTO `t_role` VALUES (10, '开发人员2', '只能生成代码，没有权限管理', '2021-01-08 21:07:43', '2021-01-08 21:07:43', b'0');
INSERT INTO `t_role` VALUES (11, '易志成', '升级了，我也有root权限了', '2021-01-09 13:20:37', '2021-01-09 13:23:43', b'0');
COMMIT;

-- ----------------------------
-- Table structure for t_role_menu_bind
-- ----------------------------
DROP TABLE IF EXISTS `t_role_menu_bind`;
CREATE TABLE `t_role_menu_bind` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` bigint(11) NOT NULL COMMENT '角色id',
  `menu_id` bigint(11) NOT NULL COMMENT '菜单id',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='角色与菜单绑定表';

-- ----------------------------
-- Records of t_role_menu_bind
-- ----------------------------
BEGIN;
INSERT INTO `t_role_menu_bind` VALUES (60, 1, 1, '2021-01-10 00:12:29', '2021-01-10 00:12:29');
INSERT INTO `t_role_menu_bind` VALUES (61, 1, 2, '2021-01-10 00:12:29', '2021-01-10 00:12:29');
INSERT INTO `t_role_menu_bind` VALUES (62, 1, 3, '2021-01-10 00:12:29', '2021-01-10 00:12:29');
INSERT INTO `t_role_menu_bind` VALUES (63, 1, 4, '2021-01-10 00:12:29', '2021-01-10 00:12:29');
INSERT INTO `t_role_menu_bind` VALUES (64, 1, 5, '2021-01-10 00:12:29', '2021-01-10 00:12:29');
INSERT INTO `t_role_menu_bind` VALUES (65, 1, 13, '2021-01-10 00:12:29', '2021-01-10 00:12:29');
INSERT INTO `t_role_menu_bind` VALUES (66, 1, 6, '2021-01-10 00:12:29', '2021-01-10 00:12:29');
INSERT INTO `t_role_menu_bind` VALUES (67, 1, 7, '2021-01-10 00:12:29', '2021-01-10 00:12:29');
INSERT INTO `t_role_menu_bind` VALUES (68, 1, 8, '2021-01-10 00:12:29', '2021-01-10 00:12:29');
INSERT INTO `t_role_menu_bind` VALUES (69, 1, 16, '2021-01-10 00:12:29', '2021-01-10 00:12:29');
INSERT INTO `t_role_menu_bind` VALUES (70, 1, 17, '2021-01-10 00:12:29', '2021-01-10 00:12:29');
INSERT INTO `t_role_menu_bind` VALUES (91, 10, 1, '2021-01-11 20:33:49', '2021-01-11 20:33:49');
INSERT INTO `t_role_menu_bind` VALUES (92, 10, 2, '2021-01-11 20:33:49', '2021-01-11 20:33:49');
INSERT INTO `t_role_menu_bind` VALUES (93, 10, 3, '2021-01-11 20:33:49', '2021-01-11 20:33:49');
INSERT INTO `t_role_menu_bind` VALUES (94, 10, 4, '2021-01-11 20:33:49', '2021-01-11 20:33:49');
INSERT INTO `t_role_menu_bind` VALUES (95, 3, 3, '2021-01-11 22:21:19', '2021-01-11 22:21:19');
INSERT INTO `t_role_menu_bind` VALUES (96, 3, 4, '2021-01-11 22:21:19', '2021-01-11 22:21:19');
INSERT INTO `t_role_menu_bind` VALUES (97, 11, 1, '2021-01-11 22:31:15', '2021-01-11 22:31:15');
INSERT INTO `t_role_menu_bind` VALUES (98, 11, 2, '2021-01-11 22:31:15', '2021-01-11 22:31:15');
INSERT INTO `t_role_menu_bind` VALUES (99, 11, 3, '2021-01-11 22:31:15', '2021-01-11 22:31:15');
INSERT INTO `t_role_menu_bind` VALUES (100, 11, 4, '2021-01-11 22:31:15', '2021-01-11 22:31:15');
INSERT INTO `t_role_menu_bind` VALUES (101, 11, 13, '2021-01-11 22:31:15', '2021-01-11 22:31:15');
INSERT INTO `t_role_menu_bind` VALUES (102, 11, 6, '2021-01-11 22:31:15', '2021-01-11 22:31:15');
INSERT INTO `t_role_menu_bind` VALUES (103, 11, 7, '2021-01-11 22:31:15', '2021-01-11 22:31:15');
INSERT INTO `t_role_menu_bind` VALUES (104, 11, 8, '2021-01-11 22:31:15', '2021-01-11 22:31:15');
COMMIT;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `user_id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '用户Id',
  `user_name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户姓名',
  `password` varchar(255) COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户密码',
  `mobile` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '用户手机号',
  `email` varchar(30) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '用户邮箱',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `del_flag` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除 0:未删除/1:删除',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户表';

-- ----------------------------
-- Records of t_user
-- ----------------------------
BEGIN;
INSERT INTO `t_user` VALUES (5, 'admin', 'd36342829b8456b02a8e75f41d48126b', '13141016707', '13141016707@163.com', '2020-12-30 14:33:57', '2020-12-30 14:33:57', b'0');
COMMIT;

-- ----------------------------
-- Table structure for t_user_role_bind
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role_bind`;
CREATE TABLE `t_user_role_bind` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(11) NOT NULL COMMENT '用户id',
  `role_id` bigint(11) NOT NULL COMMENT '角色id',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户与角色绑定表';

-- ----------------------------
-- Records of t_user_role_bind
-- ----------------------------
BEGIN;
INSERT INTO `t_user_role_bind` VALUES (9, 5, 1, '2020-12-30 20:43:54', '2020-12-30 20:43:54');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
