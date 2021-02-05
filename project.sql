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

 Date: 05/02/2021 09:32:23
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
  `module_intro` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `max_adminnumber` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`module_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of module
-- ----------------------------

-- ----------------------------
-- Table structure for module_management
-- ----------------------------
DROP TABLE IF EXISTS `module_management`;
CREATE TABLE `module_management`  (
  `permission_id` int(11) NOT NULL,
  `module_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`permission_id`) USING BTREE,
  INDEX `module_name`(`module_name`) USING BTREE,
  CONSTRAINT `module_management_ibfk_1` FOREIGN KEY (`module_name`) REFERENCES `module` (`module_name`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of module_management
-- ----------------------------

-- ----------------------------
-- Table structure for module_point
-- ----------------------------
DROP TABLE IF EXISTS `module_point`;
CREATE TABLE `module_point`  (
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `module_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `point` int(255) NULL DEFAULT NULL,
  PRIMARY KEY (`username`) USING BTREE,
  INDEX `module`(`module_name`) USING BTREE,
  CONSTRAINT `module_point_ibfk_1` FOREIGN KEY (`module_name`) REFERENCES `module` (`module_name`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `module_point_ibfk_2` FOREIGN KEY (`username`) REFERENCES `user_info` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of module_point
-- ----------------------------

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
INSERT INTO `post` VALUES (1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `post` VALUES (2, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `post` VALUES (3, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

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
INSERT INTO `post_collect` VALUES (2, 'Mao');

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
  INDEX `user_info_ibfk_1`(`permission_id`) USING BTREE,
  CONSTRAINT `user_info_ibfk_1` FOREIGN KEY (`permission_id`) REFERENCES `module_management` (`permission_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('Mao', '123', '', NULL);
INSERT INTO `user_info` VALUES ('meng', '123', '', NULL);
INSERT INTO `user_info` VALUES ('ppp', '123', ' ', NULL);

SET FOREIGN_KEY_CHECKS = 1;
