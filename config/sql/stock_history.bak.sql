/*
Navicat MySQL Data Transfer

Source Server         : 192.168.99.100
Source Server Version : 50637
Source Host           : 192.168.99.100:3306
Source Database       : stock

Target Server Type    : MYSQL
Target Server Version : 50637
File Encoding         : 65001

Date: 2017-11-03 16:19:11
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for stock_history
-- ----------------------------
DROP TABLE IF EXISTS `stock_history`;
CREATE TABLE `stock_history` (
  `id` bigint(20) NOT NULL COMMENT '主鍵',
  `stock_id` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '股票id',
  `date` date NOT NULL COMMENT '日期',
  `opening` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '開盤',
  `highest` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '最高',
  `lowest` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '最低',
  `closing` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '收盤',
  `ups_downs` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '漲跌',
  `rise_rate` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '漲%',
  `vol` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '成交量',
  `amount` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '成交金額',
  `per` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '本益比',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '最后更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
