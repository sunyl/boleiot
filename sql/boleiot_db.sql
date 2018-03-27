/*
Navicat MySQL Data Transfer

Source Server         : sunny
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : boleiot_db

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-03-27 17:36:04
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_device`
-- ----------------------------
DROP TABLE IF EXISTS `t_device`;
CREATE TABLE `t_device` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `no` varchar(32) NOT NULL COMMENT '设备编号',
  `password` varchar(10) NOT NULL COMMENT '通讯密码',
  `name` varchar(30) NOT NULL COMMENT '设备名称',
  `over_time` bigint(20) DEFAULT '0' COMMENT '过期时间',
  `comments` varchar(200) DEFAULT NULL COMMENT '设备描述',
  `longitude` varchar(10) DEFAULT NULL COMMENT '经度',
  `latitude` varchar(10) DEFAULT NULL COMMENT '纬度',
  `address` varchar(30) DEFAULT NULL COMMENT '地址',
  `status` int(11) DEFAULT '0' COMMENT '在线状态 0：离线 1：在线',
  `hostname` varchar(20) DEFAULT NULL COMMENT 'ip地址',
  `port` int(11) DEFAULT '0' COMMENT '端口号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_device
-- ----------------------------
INSERT INTO `t_device` VALUES ('5', 'mGYtWzzt', '123456', '重庆一号', '1000', '这是重庆第一号', null, null, '重庆市江北区', '1', '127.0.0.1', '53613');
INSERT INTO `t_device` VALUES ('6', '9JdDVMzT', '456', '说好的', null, '112', null, null, '3443', null, null, null);

-- ----------------------------
-- Table structure for `t_logger`
-- ----------------------------
DROP TABLE IF EXISTS `t_logger`;
CREATE TABLE `t_logger` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `client_ip` varchar(30) DEFAULT NULL COMMENT '客户端请求IP地址',
  `uri` varchar(100) DEFAULT NULL COMMENT '日志请求地址',
  `type` varchar(50) DEFAULT NULL COMMENT '终端请求方式,普通请求,ajax请求',
  `method` varchar(10) DEFAULT NULL COMMENT '请求方式method,post,get等',
  `param_data` longtext COMMENT '请求参数内容,json',
  `session_id` varchar(100) DEFAULT NULL COMMENT '请求接口唯一session标识',
  `time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '请求时间',
  `returm_time` varchar(50) DEFAULT NULL COMMENT '接口返回时间',
  `return_data` longtext COMMENT '接口返回数据json',
  `http_status_code` varchar(10) DEFAULT NULL COMMENT '请求时httpStatusCode代码',
  `time_consuming` int(8) DEFAULT '0' COMMENT '请求耗时（毫秒）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='请求日志信息表';

-- ----------------------------
-- Records of t_logger
-- ----------------------------

-- ----------------------------
-- Table structure for `t_menu`
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu` (
  `id` int(11) NOT NULL,
  `title` varchar(100) NOT NULL,
  `url` varchar(100) DEFAULT NULL,
  `icon` varchar(100) DEFAULT NULL,
  `parent_id` int(11) NOT NULL,
  `sort` int(11) NOT NULL,
  `state` int(11) DEFAULT '0',
  `role` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_menu
-- ----------------------------
INSERT INTO `t_menu` VALUES ('1', '控制中心', null, 'icon-home', '0', '1', '0', '1');
INSERT INTO `t_menu` VALUES ('2', '设备管理', null, 'icon-diamond', '0', '2', '0', '1');
INSERT INTO `t_menu` VALUES ('3', '账户中心', null, 'icon-user', '0', '3', '0', '1');
INSERT INTO `t_menu` VALUES ('4', '设备概况', '/boleiot/home', 'icon-bar-chart', '1', '1', '0', '1');
INSERT INTO `t_menu` VALUES ('5', '设备列表', '/boleiot/terminal_list', 'icon-list', '2', '1', '0', '1');
INSERT INTO `t_menu` VALUES ('6', '创建设备', '/boleiot/add_terminal', 'icon-plus', '2', '2', '0', '1');
INSERT INTO `t_menu` VALUES ('7', '透传分组', null, 'icon-grid', '2', '3', '0', '1');
INSERT INTO `t_menu` VALUES ('8', '个人信息', null, 'icon-info', '3', '1', '0', '1');
INSERT INTO `t_menu` VALUES ('9', '修改密码', '/boleiot/modif_password', 'icon-key', '3', '2', '0', '1');
INSERT INTO `t_menu` VALUES ('10', '测试菜单', null, 'icon-magnet', '0', '4', '0', '2');
INSERT INTO `t_menu` VALUES ('11', '测试子菜单1', null, 'icon-magnet', '10', '1', '0', '2');
INSERT INTO `t_menu` VALUES ('12', '测试子菜单2', null, 'icon-magnet', '10', '2', '0', '2');

-- ----------------------------
-- Table structure for `t_permission`
-- ----------------------------
DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(128) DEFAULT NULL COMMENT 'url地址',
  `name` varchar(64) DEFAULT NULL COMMENT 'url描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_permission
-- ----------------------------
INSERT INTO `t_permission` VALUES ('1', 'device:add', '增加');
INSERT INTO `t_permission` VALUES ('2', 'device:update', '更新');
INSERT INTO `t_permission` VALUES ('3', 'device:delete', '删除');
INSERT INTO `t_permission` VALUES ('4', 'device:query', '查询');

-- ----------------------------
-- Table structure for `t_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL COMMENT '角色名称',
  `type` varchar(10) DEFAULT NULL COMMENT '角色类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('1', '普通用户', 'normal');
INSERT INTO `t_role` VALUES ('2', '管理员', 'admin');

-- ----------------------------
-- Table structure for `t_role_permission`
-- ----------------------------
DROP TABLE IF EXISTS `t_role_permission`;
CREATE TABLE `t_role_permission` (
  `r_id` int(11) NOT NULL COMMENT '角色ID',
  `p_id` int(11) NOT NULL COMMENT '权限ID',
  PRIMARY KEY (`r_id`,`p_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role_permission
-- ----------------------------
INSERT INTO `t_role_permission` VALUES ('1', '4');
INSERT INTO `t_role_permission` VALUES ('2', '1');
INSERT INTO `t_role_permission` VALUES ('2', '2');
INSERT INTO `t_role_permission` VALUES ('2', '3');
INSERT INTO `t_role_permission` VALUES ('2', '4');

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `phone` varchar(15) DEFAULT NULL,
  `qq` varchar(15) DEFAULT NULL,
  `company_name` varchar(30) DEFAULT NULL COMMENT '公司名称',
  `company_url` varchar(30) DEFAULT NULL COMMENT '公司网址',
  `company_address` varchar(30) DEFAULT NULL COMMENT '联系地址',
  `last_login_time` datetime DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `status` int(11) DEFAULT '1' COMMENT '''1:有效，0:禁止登录''',
  `salt` varchar(6) NOT NULL COMMENT '加密密码的盐',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'admin', '3544c283f876cffedcb6009be3313924', null, null, null, null, null, null, null, '1', '805fc6');
INSERT INTO `t_user` VALUES ('2', 'zhangsan', '3544c283f876cffedcb6009be3313924', null, null, null, null, null, null, null, '1', '805fc6');
INSERT INTO `t_user` VALUES ('3', 'zhouxiaojun', '1d582cfb5ebbf2515e4788d15aaca6ef', null, null, null, null, null, null, null, '1', 'd1ee09');

-- ----------------------------
-- Table structure for `t_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
  `u_id` int(11) NOT NULL COMMENT '用户ID',
  `r_id` int(11) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`u_id`,`r_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES ('1', '2');
INSERT INTO `t_user_role` VALUES ('2', '1');
INSERT INTO `t_user_role` VALUES ('3', '1');
