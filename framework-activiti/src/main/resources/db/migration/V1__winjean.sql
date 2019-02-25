
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_annotationinfo
-- ----------------------------
DROP TABLE IF EXISTS `t_annotationinfo`;
CREATE TABLE `t_annotationinfo` (
  `annotation_id` varchar(255) NOT NULL,
  `annotation_name` varchar(255) default NULL,
  `annotation_category` varchar(255) default NULL COMMENT '注解分类：1 类注解； 2 方法注解；3 接口注解',
  `category_id` varchar(255) default NULL COMMENT '分类编号',
  PRIMARY KEY  (`annotation_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_annotationinfo
-- ----------------------------

-- ----------------------------
-- Table structure for t_classinfo
-- ----------------------------
DROP TABLE IF EXISTS `t_classinfo`;
CREATE TABLE `t_classinfo` (
  `class_id` varchar(255) NOT NULL,
  `class_name` varchar(255) default NULL,
  `swagger_api` varchar(255) default NULL,
  `requestMapping` varchar(255) default NULL,
  PRIMARY KEY  (`class_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_classinfo
-- ----------------------------
INSERT INTO `t_classinfo` VALUES ('0001', 'com.cnaidun.controller.Test', 'haha', 'aaa');
INSERT INTO `t_classinfo` VALUES ('0002', 'com.cnaidun.service.Test', '', '');

-- ----------------------------
-- Table structure for t_methodinfo
-- ----------------------------
DROP TABLE IF EXISTS `t_methodinfo`;
CREATE TABLE `t_methodinfo` (
  `method_id` varchar(255) NOT NULL,
  `class_id` varchar(255) default NULL,
  `method_name` varchar(255) default NULL,
  `swagger_apiOperation` varchar(255) default NULL,
  `postMapping` varchar(255) default NULL,
  `method_body` varchar(255) default NULL,
  PRIMARY KEY  (`method_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_methodinfo
-- ----------------------------
INSERT INTO `t_methodinfo` VALUES ('0001', '0001', 'method1', 'method1 function', 'aaa', '{\r\n System.out.println($1);\r\n return \"Hello\";\r\n}');
INSERT INTO `t_methodinfo` VALUES ('0002', '0001', 'method2', 'method2 function', 'bbb', '{\r\n System.out.println($1);\r\n return \"Hello\";\r\n}');

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
