/*
Navicat MySQL Data Transfer

Source Server         : etc01
Source Server Version : 50530
Source Host           : etc01:3306
Source Database       : dataTest

Target Server Type    : MYSQL
Target Server Version : 50530
File Encoding         : 65001

Date: 2018-01-19 14:36:15
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `student`
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` int(11) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `age` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('1', '小张', '23');
INSERT INTO `student` VALUES ('2', '小李', '22');
INSERT INTO `student` VALUES ('3', '小王', '25');
INSERT INTO `student` VALUES ('4', '小明', '30');
INSERT INTO `student` VALUES ('5', '小张', '23');
INSERT INTO `student` VALUES ('6', '小李', '22');
INSERT INTO `student` VALUES ('7', '小王', '25');
INSERT INTO `student` VALUES ('8', '小明', '30');
INSERT INTO `student` VALUES ('9', '小张', '23');
INSERT INTO `student` VALUES ('10', '小李', '22');
INSERT INTO `student` VALUES ('11', '小王', '25');
INSERT INTO `student` VALUES ('12', '小明', '30');
INSERT INTO `student` VALUES ('13', '小张', '23');
INSERT INTO `student` VALUES ('14', '小李', '22');
INSERT INTO `student` VALUES ('15', '小王', '25');
INSERT INTO `student` VALUES ('16', '小明', '30');
INSERT INTO `student` VALUES ('17', '小张', '23');
INSERT INTO `student` VALUES ('18', '小李', '22');
INSERT INTO `student` VALUES ('19', '小王', '25');
INSERT INTO `student` VALUES ('20', '小明', '30');
INSERT INTO `student` VALUES ('21', '小张', '23');
INSERT INTO `student` VALUES ('22', '小李', '22');
INSERT INTO `student` VALUES ('23', '小王', '25');
INSERT INTO `student` VALUES ('24', '小明', '30');
INSERT INTO `student` VALUES ('25', '小张', '23');
INSERT INTO `student` VALUES ('26', '小李', '22');
INSERT INTO `student` VALUES ('27', '小王', '25');
INSERT INTO `student` VALUES ('28', '小明', '30');
INSERT INTO `student` VALUES ('29', '小张', '23');
INSERT INTO `student` VALUES ('30', '小李', '22');
INSERT INTO `student` VALUES ('31', '小王', '25');
INSERT INTO `student` VALUES ('32', '小明', '30');
INSERT INTO `student` VALUES ('33', '小张', '23');
INSERT INTO `student` VALUES ('34', '小李', '22');
INSERT INTO `student` VALUES ('35', '小王', '25');
INSERT INTO `student` VALUES ('36', '小明', '30');
