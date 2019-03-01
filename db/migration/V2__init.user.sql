-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `user_id` varchar(255) NOT NULL,
  `user_name` varchar(255) default NULL,
  `birthdate` date default NULL,
  `sex` char(255) default NULL,
  `create_user` varchar(255) default NULL,
  `create_time` datetime default NULL,
  `update_user` varchar(255) default NULL,
  `update_time` datetime default NULL,
  PRIMARY KEY  (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('uid0003', 'winjean', '2018-10-18', '1', 'c_user', '2018-10-19 08:12:22', 'u_user', '2018-10-20 18:12:22');
INSERT INTO `t_user` VALUES ('uid0004', 'winjean', '2018-10-18', '1', 'c_user', '2018-10-19 08:12:22', 'u_user', '2018-10-20 18:12:22');
INSERT INTO `t_user` VALUES ('uid0005', 'winjean', '2018-10-18', '1', 'c_user', '2018-10-19 08:12:22', 'u_user', '2018-10-20 18:12:22');
INSERT INTO `t_user` VALUES ('uid0006', 'winjean', '2018-10-18', '1', 'c_user', '2018-10-19 08:12:22', 'u_user', '2018-10-20 18:12:22');
INSERT INTO `t_user` VALUES ('uid0007', 'winjean', '2018-10-18', '1', 'c_user', '2018-10-19 08:12:22', 'u_user', '2018-10-20 18:12:22');


commit;