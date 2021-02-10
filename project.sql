/*
 Navicat Premium Data Transfer

 Source Server         : mydatabase
 Source Server Type    : MySQL
 Source Server Version : 80011
 Source Host           : localhost:3306
 Source Schema         : project

 Target Server Type    : MySQL
 Target Server Version : 80011
 File Encoding         : 65001

 Date: 10/02/2021 10:52:56
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for comment_like
-- ----------------------------
DROP TABLE IF EXISTS `comment_like`;
CREATE TABLE `comment_like`  (
  `comment_id` int(11) NOT NULL,
  `clike_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `clike_time` datetime(0) NULL DEFAULT NULL,
  INDEX `comment_id`(`comment_id`) USING BTREE,
  INDEX `like_user`(`clike_user`) USING BTREE,
  CONSTRAINT `comment_like_ibfk_1` FOREIGN KEY (`comment_id`) REFERENCES `post_comment` (`comment_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `comment_like_ibfk_2` FOREIGN KEY (`clike_user`) REFERENCES `user_info` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comment_like
-- ----------------------------

-- ----------------------------
-- Table structure for comment_reply
-- ----------------------------
DROP TABLE IF EXISTS `comment_reply`;
CREATE TABLE `comment_reply`  (
  `reply_id` int(11) NOT NULL,
  `comment_id` int(11) NOT NULL,
  `creply_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `creply_time` datetime(0) NULL DEFAULT NULL,
  `from_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `to_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`reply_id`) USING BTREE,
  INDEX `comment_id`(`comment_id`) USING BTREE,
  INDEX `from_user`(`from_user`) USING BTREE,
  INDEX `to_user`(`to_user`) USING BTREE,
  CONSTRAINT `comment_reply_ibfk_1` FOREIGN KEY (`comment_id`) REFERENCES `post_comment` (`comment_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `comment_reply_ibfk_2` FOREIGN KEY (`from_user`) REFERENCES `user_info` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `comment_reply_ibfk_3` FOREIGN KEY (`to_user`) REFERENCES `user_info` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comment_reply
-- ----------------------------

-- ----------------------------
-- Table structure for module
-- ----------------------------
DROP TABLE IF EXISTS `module`;
CREATE TABLE `module`  (
  `module_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `module_intro` varchar(3000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `max_adminnumber` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`module_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of module
-- ----------------------------
INSERT INTO `module` VALUES ('life', 'This is a module for students to share their daily lives with others', 5);
INSERT INTO `module` VALUES ('springboot', 'Spring Boot offers a fast way to build applications. It looks at your classpath and at the beans you have configured, makes reasonable assumptions about what you are missing, and adds those items. With Spring Boot, you can focus more on business features and less on infrastructure.', 5);
INSERT INTO `module` VALUES ('vue', 'Vue (pronounced /vjuː/, like view) is a progressive framework for building user interfaces. It is designed from the ground up to be incrementally adoptable, and can easily scale between a library and a framework depending on different use cases. It consists of an approachable core library that focuses on the view layer only, and an ecosystem of supporting libraries that helps you tackle complexity in large Single-Page Applications.', 5);

-- ----------------------------
-- Table structure for module_management
-- ----------------------------
DROP TABLE IF EXISTS `module_management`;
CREATE TABLE `module_management`  (
  `permission_id` int(11) NOT NULL AUTO_INCREMENT,
  `module_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`permission_id`) USING BTREE,
  INDEX `module_name`(`module_name`) USING BTREE,
  CONSTRAINT `module_management_ibfk_1` FOREIGN KEY (`module_name`) REFERENCES `module` (`module_name`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of module_management
-- ----------------------------
INSERT INTO `module_management` VALUES (1, 'life');
INSERT INTO `module_management` VALUES (4, 'life');
INSERT INTO `module_management` VALUES (2, 'springboot');
INSERT INTO `module_management` VALUES (3, 'vue');

-- ----------------------------
-- Table structure for module_point
-- ----------------------------
DROP TABLE IF EXISTS `module_point`;
CREATE TABLE `module_point`  (
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `module_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `point` int(255) NULL DEFAULT NULL,
  PRIMARY KEY (`username`, `module_name`) USING BTREE,
  INDEX `module`(`module_name`) USING BTREE,
  CONSTRAINT `module_point_ibfk_1` FOREIGN KEY (`module_name`) REFERENCES `module` (`module_name`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `module_point_ibfk_2` FOREIGN KEY (`username`) REFERENCES `user_info` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of module_point
-- ----------------------------
INSERT INTO `module_point` VALUES ('Mao', 'life', 8);
INSERT INTO `module_point` VALUES ('Mao', 'springboot', 0);
INSERT INTO `module_point` VALUES ('meng', 'springboot', 0);

-- ----------------------------
-- Table structure for post
-- ----------------------------
DROP TABLE IF EXISTS `post`;
CREATE TABLE `post`  (
  `post_id` int(11) NOT NULL,
  `module_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `post_content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `post_author` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `post_createtime` datetime(0) NULL DEFAULT NULL,
  `post_likes` int(11) NULL DEFAULT NULL COMMENT 'number of like',
  `post_comments` int(11) NULL DEFAULT NULL COMMENT 'number of comment',
  `post_collections` int(11) NULL DEFAULT NULL COMMENT 'number of collection',
  PRIMARY KEY (`post_id`) USING BTREE,
  INDEX `author`(`post_author`) USING BTREE,
  INDEX `module_name`(`module_name`) USING BTREE,
  CONSTRAINT `post_ibfk_2` FOREIGN KEY (`post_author`) REFERENCES `user_info` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `post_ibfk_3` FOREIGN KEY (`module_name`) REFERENCES `module` (`module_name`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of post
-- ----------------------------
INSERT INTO `post` VALUES (1, NULL, 'Hello, my name is mao.', 'Mao', '2021-02-06 03:27:17', 1, 0, 2);
INSERT INTO `post` VALUES (2, 'springboot', 'springboot is perfect', 'meng', '2021-02-06 03:28:00', 0, 0, 0);
INSERT INTO `post` VALUES (3, 'vue', 'vue vuevue vue', 'ppp', '2021-02-06 03:28:24', 0, 0, 0);
INSERT INTO `post` VALUES (4, NULL, 'Test', 'meng', '2021-02-06 08:31:57', 0, 0, 0);

-- ----------------------------
-- Table structure for post_collect
-- ----------------------------
DROP TABLE IF EXISTS `post_collect`;
CREATE TABLE `post_collect`  (
  `post_id` int(11) NOT NULL,
  `collect_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  INDEX `post_id`(`post_id`) USING BTREE,
  INDEX `collect_user`(`collect_user`) USING BTREE,
  CONSTRAINT `post_collect_ibfk_1` FOREIGN KEY (`post_id`) REFERENCES `post` (`post_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `post_collect_ibfk_2` FOREIGN KEY (`collect_user`) REFERENCES `user_info` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of post_collect
-- ----------------------------
INSERT INTO `post_collect` VALUES (1, 'Mao');
INSERT INTO `post_collect` VALUES (1, 'meng');

-- ----------------------------
-- Table structure for post_comment
-- ----------------------------
DROP TABLE IF EXISTS `post_comment`;
CREATE TABLE `post_comment`  (
  `comment_id` int(11) NOT NULL,
  `post_id` int(11) NOT NULL,
  `comment_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `comment_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `comment_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`comment_id`) USING BTREE,
  INDEX `post_id`(`post_id`) USING BTREE,
  INDEX `comment_user`(`comment_user`) USING BTREE,
  CONSTRAINT `post_comment_ibfk_1` FOREIGN KEY (`post_id`) REFERENCES `post` (`post_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `post_comment_ibfk_2` FOREIGN KEY (`comment_user`) REFERENCES `user_info` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of post_comment
-- ----------------------------

-- ----------------------------
-- Table structure for post_like
-- ----------------------------
DROP TABLE IF EXISTS `post_like`;
CREATE TABLE `post_like`  (
  `post_id` int(11) NOT NULL,
  `like_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'user that likes this post',
  `like_time` datetime(0) NULL DEFAULT NULL,
  INDEX `post_id`(`post_id`) USING BTREE,
  INDEX `like_user`(`like_user`) USING BTREE,
  CONSTRAINT `post_like_ibfk_1` FOREIGN KEY (`post_id`) REFERENCES `post` (`post_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `post_like_ibfk_2` FOREIGN KEY (`like_user`) REFERENCES `user_info` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of post_like
-- ----------------------------
INSERT INTO `post_like` VALUES (1, 'meng', '2021-02-08 12:40:38');

-- ----------------------------
-- Table structure for user_follow
-- ----------------------------
DROP TABLE IF EXISTS `user_follow`;
CREATE TABLE `user_follow`  (
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `follow` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  INDEX `username`(`username`) USING BTREE,
  INDEX `following`(`follow`) USING BTREE,
  CONSTRAINT `user_follow_ibfk_1` FOREIGN KEY (`username`) REFERENCES `user_info` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_follow_ibfk_2` FOREIGN KEY (`follow`) REFERENCES `user_info` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_follow
-- ----------------------------
INSERT INTO `user_follow` VALUES ('Mao', 'meng');
INSERT INTO `user_follow` VALUES ('Mao', 'ppp');

-- ----------------------------
-- Table structure for user_follower
-- ----------------------------
DROP TABLE IF EXISTS `user_follower`;
CREATE TABLE `user_follower`  (
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `follower` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  INDEX `username`(`username`) USING BTREE,
  INDEX `follower`(`follower`) USING BTREE,
  CONSTRAINT `user_follower_ibfk_1` FOREIGN KEY (`username`) REFERENCES `user_info` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_follower_ibfk_2` FOREIGN KEY (`follower`) REFERENCES `user_info` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_follower
-- ----------------------------
INSERT INTO `user_follower` VALUES ('Mao', 'meng');
INSERT INTO `user_follower` VALUES ('Mao', 'ppp');

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`  (
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `permission_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`username`) USING BTREE,
  INDEX `permission_id`(`permission_id`) USING BTREE,
  CONSTRAINT `user_info_ibfk_1` FOREIGN KEY (`permission_id`) REFERENCES `module_management` (`permission_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('Mao', '123', '', 4);
INSERT INTO `user_info` VALUES ('meng', '123', '', 2);
INSERT INTO `user_info` VALUES ('ppp', '123', ' ', 3);

SET FOREIGN_KEY_CHECKS = 1;
