/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50619
Source Host           : localhost:3306
Source Database       : student

Target Server Type    : MYSQL
Target Server Version : 50619
File Encoding         : 65001

Date: 2019-03-16 23:20:54
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `admin`
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', 'admin', 'admin', 'admin');
INSERT INTO `admin` VALUES ('2', '刘江', 'liu', '123');

-- ----------------------------
-- Table structure for `student`
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `sno` varchar(20) NOT NULL,
  `department` varchar(20) NOT NULL,
  `hometown` varchar(20) NOT NULL,
  `mark` varchar(20) NOT NULL,
  `email` varchar(20) NOT NULL,
  `tel` varchar(20) NOT NULL,
  `sex` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('45', 'liu', '001', '计算机', '四川', '85', '123456789@123.com', '123456789', '男');
INSERT INTO `student` VALUES ('48', '张三', '002', '计科', '四川', '89', '123456789@qq.com', '123456789', '男');
INSERT INTO `student` VALUES ('49', '李四', '003', '财经', '湖北', '95', '123456789@qq.com', '12345678', '女');
INSERT INTO `student` VALUES ('50', '王五', '004', '土木', '北京', '86', '12226263601', '12311011322', '男');
