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

 Date: 22/01/2021 21:15:22
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_db_info
-- ----------------------------
DROP TABLE IF EXISTS `t_db_info`;
CREATE TABLE `t_db_info` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键Id',
  `connection_name` varchar(30) COLLATE utf8mb4_general_ci NOT NULL COMMENT '连接名',
  `type` varchar(10) COLLATE utf8mb4_general_ci NOT NULL COMMENT '数据库类型',
  `url` varchar(200) COLLATE utf8mb4_general_ci NOT NULL COMMENT '数据库url',
  `port` varchar(10) COLLATE utf8mb4_general_ci NOT NULL COMMENT '端口号',
  `name` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(255) COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `database_name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL COMMENT '数据库名称',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `del_flag` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除 0:未删除/1:删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


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
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='菜单表';

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
INSERT INTO `t_menu` VALUES (18, 5, 1222, '接口文档', 'api', 'link', 6, '接口文档，外链', b'0', '2021-01-18 22:14:10', '2021-01-18 22:33:40');
INSERT INTO `t_menu` VALUES (19, 5, 1, '白名单', 'whiteurl', 'log', NULL, NULL, b'0', '2021-01-19 21:40:22', '2021-01-19 21:40:22');
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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='角色表';

-- ----------------------------
-- Records of t_role
-- ----------------------------
BEGIN;
INSERT INTO `t_role` VALUES (1, 'admin', '可创建数据源，可生成代码', '2020-12-30 11:32:58', '2021-01-02 20:39:29', b'0');
INSERT INTO `t_role` VALUES (2, '开发人员', '可生成代码', '2020-12-30 11:33:31', '2020-12-30 11:33:31', b'0');
INSERT INTO `t_role` VALUES (3, '测试角色', '这个角色，通过接口生成', '2020-12-30 15:35:43', '2020-12-30 15:35:43', b'0');
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
) ENGINE=InnoDB AUTO_INCREMENT=142 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='角色与菜单绑定表';

-- ----------------------------
-- Records of t_role_menu_bind
-- ----------------------------
BEGIN;
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
INSERT INTO `t_role_menu_bind` VALUES (129, 1, 1, '2021-01-19 21:40:31', '2021-01-19 21:40:31');
INSERT INTO `t_role_menu_bind` VALUES (130, 1, 2, '2021-01-19 21:40:31', '2021-01-19 21:40:31');
INSERT INTO `t_role_menu_bind` VALUES (131, 1, 3, '2021-01-19 21:40:31', '2021-01-19 21:40:31');
INSERT INTO `t_role_menu_bind` VALUES (132, 1, 4, '2021-01-19 21:40:31', '2021-01-19 21:40:31');
INSERT INTO `t_role_menu_bind` VALUES (133, 1, 5, '2021-01-19 21:40:31', '2021-01-19 21:40:31');
INSERT INTO `t_role_menu_bind` VALUES (134, 1, 13, '2021-01-19 21:40:31', '2021-01-19 21:40:31');
INSERT INTO `t_role_menu_bind` VALUES (135, 1, 6, '2021-01-19 21:40:31', '2021-01-19 21:40:31');
INSERT INTO `t_role_menu_bind` VALUES (136, 1, 18, '2021-01-19 21:40:31', '2021-01-19 21:40:31');
INSERT INTO `t_role_menu_bind` VALUES (137, 1, 7, '2021-01-19 21:40:31', '2021-01-19 21:40:31');
INSERT INTO `t_role_menu_bind` VALUES (138, 1, 8, '2021-01-19 21:40:31', '2021-01-19 21:40:31');
INSERT INTO `t_role_menu_bind` VALUES (139, 1, 16, '2021-01-19 21:40:31', '2021-01-19 21:40:31');
INSERT INTO `t_role_menu_bind` VALUES (140, 1, 17, '2021-01-19 21:40:31', '2021-01-19 21:40:31');
INSERT INTO `t_role_menu_bind` VALUES (141, 1, 19, '2021-01-19 21:40:31', '2021-01-19 21:40:31');
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
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户表';

-- ----------------------------
-- Records of t_user
-- ----------------------------
BEGIN;
INSERT INTO `t_user` VALUES (5, 'admin', 'd36342829b8456b02a8e75f41d48126b', '13141016707', '13141016707@163.com', '2020-12-30 14:33:57', '2020-12-30 14:33:57', b'0');
INSERT INTO `t_user` VALUES (6, 'yizhicheng', 'd36342829b8456b02a8e75f41d48126b', '13141016707', '13141016707@163.com', '2020-12-30 14:43:19', '2021-01-11 20:34:46', b'0');
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
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户与角色绑定表';

-- ----------------------------
-- Records of t_user_role_bind
-- ----------------------------
BEGIN;
INSERT INTO `t_user_role_bind` VALUES (9, 5, 1, '2020-12-30 20:43:54', '2020-12-30 20:43:54');
INSERT INTO `t_user_role_bind` VALUES (21, 6, 10, '2021-01-11 20:34:46', '2021-01-11 20:34:46');
INSERT INTO `t_user_role_bind` VALUES (22, 7, 3, '2021-01-11 22:21:44', '2021-01-11 22:21:44');
COMMIT;

-- ----------------------------
-- Table structure for t_white_url
-- ----------------------------
DROP TABLE IF EXISTS `t_white_url`;
CREATE TABLE `t_white_url` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `url` varchar(30) COLLATE utf8mb4_general_ci NOT NULL COMMENT '白名单地址',
  `remark` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  `del_flag` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除 0:未删除/1:删除',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='白名单表';

-- ----------------------------
-- Records of t_white_url
-- ----------------------------
BEGIN;
INSERT INTO `t_white_url` VALUES (1, '/user/login', '登录接口，没有token', b'0', '2021-01-19 18:03:17', '2021-01-19 21:51:59');
INSERT INTO `t_white_url` VALUES (2, '/doc.html', '接口文档', b'0', '2021-01-19 18:03:36', '2021-01-19 18:03:36');
INSERT INTO `t_white_url` VALUES (3, '/swagger-ui.html', 'swagger接口文档', b'0', '2021-01-19 18:03:42', '2021-01-19 18:03:50');
INSERT INTO `t_white_url` VALUES (4, '/swagger-resources/**', 'swagger静态文件', b'0', '2021-01-19 18:04:01', '2021-01-19 18:04:01');
INSERT INTO `t_white_url` VALUES (5, '/swagger/**', 'swagger静态文件', b'0', '2021-01-19 18:04:15', '2021-01-19 18:04:15');
INSERT INTO `t_white_url` VALUES (6, '/v2/api-docs', 'swagger静态文件', b'0', '2021-01-19 18:04:27', '2021-01-19 18:04:27');
INSERT INTO `t_white_url` VALUES (7, '/webjars/**', 'swagger静态文件', b'0', '2021-01-19 18:04:39', '2021-01-19 18:04:39');
INSERT INTO `t_white_url` VALUES (8, '/favicon.ico', 'icon图标', b'0', '2021-01-19 18:04:57', '2021-01-19 18:04:57');
INSERT INTO `t_white_url` VALUES (9, ' /actuator/**', 'spring健康检查', b'0', '2021-01-19 18:05:10', '2021-01-19 18:05:10');
INSERT INTO `t_white_url` VALUES (10, '/druid/**', 'druid数据源', b'0', '2021-01-19 18:05:22', '2021-01-19 18:05:22');
INSERT INTO `t_white_url` VALUES (11, '/demo/test', '测试接口', b'1', '2021-01-19 21:52:13', '2021-01-19 21:52:13');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
