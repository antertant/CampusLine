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

 Date: 12/04/2021 11:42:17
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for chat_message
-- ----------------------------
DROP TABLE IF EXISTS `chat_message`;
CREATE TABLE `chat_message`  (
  `message_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '聊天内容id',
  `from_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '发送者',
  `to_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '接收者',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '聊天内容',
  `send_time` datetime(0) NOT NULL COMMENT '发送时间',
  `unread` int(11) NOT NULL DEFAULT 1 COMMENT '消息类型',
  PRIMARY KEY (`message_id`) USING BTREE,
  INDEX `from_user`(`from_user`) USING BTREE,
  INDEX `to_user`(`to_user`) USING BTREE,
  CONSTRAINT `chat_message_ibfk_1` FOREIGN KEY (`from_user`) REFERENCES `user_info` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `chat_message_ibfk_2` FOREIGN KEY (`to_user`) REFERENCES `user_info` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of chat_message
-- ----------------------------
INSERT INTO `chat_message` VALUES (17, 'gang', 'meng', 'string', '2021-04-09 06:41:11', 1);
INSERT INTO `chat_message` VALUES (18, 'meng', 'gang', 'sss', '2021-04-09 15:22:07', 1);
INSERT INTO `chat_message` VALUES (19, 'gang', 'Mao', 'dsss', '2021-04-09 15:22:23', 1);
INSERT INTO `chat_message` VALUES (20, 'Mao', 'ppp', 'hi', '2021-04-11 09:28:06', 0);
INSERT INTO `chat_message` VALUES (21, 'ppp', 'Mao', 'hi!', '2021-04-11 13:20:57', 1);

-- ----------------------------
-- Table structure for comment_like
-- ----------------------------
DROP TABLE IF EXISTS `comment_like`;
CREATE TABLE `comment_like`  (
  `comment_id` int(11) NOT NULL,
  `clike_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `clike_time` datetime(0) NULL DEFAULT NULL,
  `viewed` int(11) NULL DEFAULT 0,
  INDEX `like_user`(`clike_user`) USING BTREE,
  INDEX `comment_id`(`comment_id`) USING BTREE,
  CONSTRAINT `comment_like_ibfk_2` FOREIGN KEY (`clike_user`) REFERENCES `user_info` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `comment_like_ibfk_3` FOREIGN KEY (`comment_id`) REFERENCES `post_comment` (`comment_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of comment_like
-- ----------------------------
INSERT INTO `comment_like` VALUES (5, 'ppp', '2021-03-12 12:51:37', 0);
INSERT INTO `comment_like` VALUES (3, 'ppp', '2021-03-12 12:51:51', 0);
INSERT INTO `comment_like` VALUES (3, 'Mao', '2021-03-12 12:51:59', 0);
INSERT INTO `comment_like` VALUES (4, 'Mao', '2021-03-12 13:09:30', 0);
INSERT INTO `comment_like` VALUES (4, 'meng', '2021-03-12 13:09:37', 0);
INSERT INTO `comment_like` VALUES (5, 'Mao', '2021-04-12 15:39:21', 0);

-- ----------------------------
-- Table structure for comment_reply
-- ----------------------------
DROP TABLE IF EXISTS `comment_reply`;
CREATE TABLE `comment_reply`  (
  `reply_id` int(11) NOT NULL AUTO_INCREMENT,
  `comment_id` int(11) NOT NULL,
  `creply_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `creply_time` datetime(0) NULL DEFAULT NULL,
  `from_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `to_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `viewed` int(11) NULL DEFAULT 0,
  PRIMARY KEY (`reply_id`) USING BTREE,
  INDEX `from_user`(`from_user`) USING BTREE,
  INDEX `to_user`(`to_user`) USING BTREE,
  INDEX `comment_id`(`comment_id`) USING BTREE,
  CONSTRAINT `comment_reply_ibfk_2` FOREIGN KEY (`from_user`) REFERENCES `user_info` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `comment_reply_ibfk_3` FOREIGN KEY (`to_user`) REFERENCES `user_info` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `comment_reply_ibfk_4` FOREIGN KEY (`comment_id`) REFERENCES `post_comment` (`comment_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of comment_reply
-- ----------------------------
INSERT INTO `comment_reply` VALUES (1, 3, 'I agree', '2021-02-17 05:25:47', 'meng', 'Mao', 0);
INSERT INTO `comment_reply` VALUES (2, 4, 'no', '2021-02-17 05:29:57', 'meng', 'ppp', 0);
INSERT INTO `comment_reply` VALUES (4, 3, 'hahahaha', '2021-02-17 12:48:34', 'Mao', 'meng', 1);
INSERT INTO `comment_reply` VALUES (5, 8, 'reply test', '2021-02-18 16:27:17', 'Mao', 'Mao', 0);
INSERT INTO `comment_reply` VALUES (7, 8, 'string,,,', '2021-02-18 16:29:33', 'ppp', 'Mao', 0);
INSERT INTO `comment_reply` VALUES (16, 3, 'string', '2021-03-07 04:41:43', 'meng', 'meng', 1);
INSERT INTO `comment_reply` VALUES (17, 5, 'string', '2021-03-07 04:44:26', 'meng', 'meng', 1);
INSERT INTO `comment_reply` VALUES (18, 5, 'string', '2021-03-07 04:47:02', 'meng', 'meng', 1);
INSERT INTO `comment_reply` VALUES (19, 9, 'commentreply', '2021-04-12 12:50:00', 'Mao', 'ppp', 0);
INSERT INTO `comment_reply` VALUES (20, 9, 'commentreply', '2021-04-12 12:52:52', 'Mao', 'ppp', 0);
INSERT INTO `comment_reply` VALUES (21, 9, 'commentreply', '2021-04-12 15:39:01', 'Mao', 'ppp', 0);

-- ----------------------------
-- Table structure for email_verify
-- ----------------------------
DROP TABLE IF EXISTS `email_verify`;
CREATE TABLE `email_verify`  (
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `verifycode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of email_verify
-- ----------------------------
INSERT INTO `email_verify` VALUES ('1173229585@qq.com', '6462');
INSERT INTO `email_verify` VALUES ('z58fu@uwaterloo.ca', '5697');
INSERT INTO `email_verify` VALUES ('1234@qq.com', '1234');

-- ----------------------------
-- Table structure for module
-- ----------------------------
DROP TABLE IF EXISTS `module`;
CREATE TABLE `module`  (
  `module_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `module_intro` varchar(3000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'default module introduction',
  `max_adminnumber` int(11) NULL DEFAULT 5,
  PRIMARY KEY (`module_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of module
-- ----------------------------
INSERT INTO `module` VALUES ('django', 'default module introduction', 5);
INSERT INTO `module` VALUES ('java', 'Java Is the Language of Possibilities. Java is powering the innovation behind our digital world. Harness this potential with Java resources for student coders, hobbyists, developers, and IT leaders.', 5);
INSERT INTO `module` VALUES ('life', 'new life_intro', 5);
INSERT INTO `module` VALUES ('springboot', 'Spring Boot offers a fast way to build applications. It looks at your classpath and at the beans you have configured, makes reasonable assumptions about what you are missing, and adds those items. With Spring Boot, you can focus more on business features and less on infrastructure.', 5);
INSERT INTO `module` VALUES ('vue', 'Vue (pronounced /vjuː/, like view) is a progressive framework for building user interfaces. It is designed from the ground up to be incrementally adoptable, and can easily scale between a library and a framework depending on different use cases. It consists of an approachable core library that focuses on the view layer only, and an ecosystem of supporting libraries that helps you tackle complexity in large Single-Page Applications.', 5);

-- ----------------------------
-- Table structure for module_creation
-- ----------------------------
DROP TABLE IF EXISTS `module_creation`;
CREATE TABLE `module_creation`  (
  `module_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `request_count` int(11) NULL DEFAULT 1,
  PRIMARY KEY (`module_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of module_creation
-- ----------------------------
INSERT INTO `module_creation` VALUES ('h', 6);
INSERT INTO `module_creation` VALUES ('SVM', 1);

-- ----------------------------
-- Table structure for post
-- ----------------------------
DROP TABLE IF EXISTS `post`;
CREATE TABLE `post`  (
  `post_id` int(11) NOT NULL AUTO_INCREMENT,
  `if_top` int(255) NULL DEFAULT 0,
  `module_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `post_content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `post_author` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `post_createtime` datetime(0) NULL DEFAULT NULL,
  `post_likes` int(11) NULL DEFAULT 0 COMMENT 'number of like',
  `post_comments` int(11) NULL DEFAULT 0 COMMENT 'number of comment',
  `post_collections` int(11) NULL DEFAULT 0 COMMENT 'number of collection',
  PRIMARY KEY (`post_id`) USING BTREE,
  INDEX `author`(`post_author`) USING BTREE,
  INDEX `module_name`(`module_name`) USING BTREE,
  CONSTRAINT `post_ibfk_2` FOREIGN KEY (`post_author`) REFERENCES `user_info` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `post_ibfk_3` FOREIGN KEY (`module_name`) REFERENCES `module` (`module_name`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of post
-- ----------------------------
INSERT INTO `post` VALUES (2, 0, 'springboot', 'springboot is perfect', 'meng', '2021-02-06 03:28:00', 2, 5, 0);
INSERT INTO `post` VALUES (3, 0, 'vue', 'vue vuevue vue', 'ppp', '2021-02-06 03:28:24', 1, 0, 0);
INSERT INTO `post` VALUES (4, 0, NULL, 'Test', 'meng', '2021-02-06 08:31:57', 0, 0, 1);
INSERT INTO `post` VALUES (5, 1, 'java', 'System.out.println(\"hello java\")', 'Mao', '2021-02-18 03:30:57', 0, 0, 0);
INSERT INTO `post` VALUES (6, 1, 'java', '&lt;h1&gt;hello java&lt;/h1&gt;', 'meng', '2021-02-18 03:32:24', 0, 0, 0);
INSERT INTO `post` VALUES (7, 0, 'springboot', 'spspspspsp', 'Mao', '2021-02-18 05:31:19', 3, 0, 0);
INSERT INTO `post` VALUES (8, 0, 'java', 'life hahaha', 'meng', '2021-02-18 11:15:23', 2, 2, 0);
INSERT INTO `post` VALUES (9, 1, 'java', 'sohai', 'ppp', '2021-02-18 11:15:43', 3, 0, 0);
INSERT INTO `post` VALUES (10, 1, NULL, 'zzy', 'Mao', '2021-02-18 11:15:56', 0, 0, 0);
INSERT INTO `post` VALUES (11, 1, NULL, 'today is happy', 'meng', '2021-02-19 00:20:37', 1, 0, 0);
INSERT INTO `post` VALUES (12, 1, NULL, 'wuhu', 'ppp', '2021-02-19 00:20:53', 1, 0, 0);

-- ----------------------------
-- Table structure for post_collect
-- ----------------------------
DROP TABLE IF EXISTS `post_collect`;
CREATE TABLE `post_collect`  (
  `post_id` int(11) NOT NULL,
  `collect_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  INDEX `collect_user`(`collect_user`) USING BTREE,
  INDEX `post_id`(`post_id`) USING BTREE,
  CONSTRAINT `post_collect_ibfk_2` FOREIGN KEY (`collect_user`) REFERENCES `user_info` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `post_collect_ibfk_3` FOREIGN KEY (`post_id`) REFERENCES `post` (`post_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of post_collect
-- ----------------------------
INSERT INTO `post_collect` VALUES (4, 'Mao');

-- ----------------------------
-- Table structure for post_comment
-- ----------------------------
DROP TABLE IF EXISTS `post_comment`;
CREATE TABLE `post_comment`  (
  `comment_id` int(11) NOT NULL AUTO_INCREMENT,
  `post_id` int(11) NOT NULL,
  `comment_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `comment_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `comment_time` datetime(0) NULL DEFAULT NULL,
  `viewed` int(11) NULL DEFAULT 0,
  PRIMARY KEY (`comment_id`) USING BTREE,
  INDEX `comment_user`(`comment_user`) USING BTREE,
  INDEX `post_id`(`post_id`) USING BTREE,
  CONSTRAINT `post_comment_ibfk_2` FOREIGN KEY (`comment_user`) REFERENCES `user_info` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `post_comment_ibfk_3` FOREIGN KEY (`post_id`) REFERENCES `post` (`post_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of post_comment
-- ----------------------------
INSERT INTO `post_comment` VALUES (3, 2, 'Mao', 'good post', '2021-02-17 05:18:44', 1);
INSERT INTO `post_comment` VALUES (4, 2, 'ppp', 'what a bad post!', '2021-02-17 05:19:20', 1);
INSERT INTO `post_comment` VALUES (5, 2, 'meng', 'I like my post', '2021-02-17 10:36:07', 1);
INSERT INTO `post_comment` VALUES (8, 8, 'Mao', 'like like like', '2021-02-18 16:22:14', 1);
INSERT INTO `post_comment` VALUES (9, 8, 'ppp', 'like like like', '2021-02-18 16:24:12', 1);
INSERT INTO `post_comment` VALUES (14, 2, 'meng', 'comment...', '2021-04-12 15:39:21', 0);

-- ----------------------------
-- Table structure for post_like
-- ----------------------------
DROP TABLE IF EXISTS `post_like`;
CREATE TABLE `post_like`  (
  `post_id` int(11) NOT NULL,
  `like_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'user that likes this post',
  `like_time` datetime(0) NULL DEFAULT NULL,
  `viewed` int(11) NULL DEFAULT 0,
  PRIMARY KEY (`post_id`, `like_user`) USING BTREE,
  INDEX `like_user`(`like_user`) USING BTREE,
  CONSTRAINT `post_like_ibfk_2` FOREIGN KEY (`like_user`) REFERENCES `user_info` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `post_like_ibfk_3` FOREIGN KEY (`post_id`) REFERENCES `post` (`post_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of post_like
-- ----------------------------
INSERT INTO `post_like` VALUES (2, 'Mao', '2021-02-16 22:56:25', 1);
INSERT INTO `post_like` VALUES (2, 'meng', '2021-02-16 22:56:41', 1);
INSERT INTO `post_like` VALUES (3, 'meng', '2021-02-18 05:02:39', 0);
INSERT INTO `post_like` VALUES (7, 'Mao', '2021-02-18 05:33:07', 0);
INSERT INTO `post_like` VALUES (7, 'meng', '2021-02-18 05:32:58', 0);
INSERT INTO `post_like` VALUES (7, 'ppp', '2021-02-18 05:32:45', 0);
INSERT INTO `post_like` VALUES (8, 'Mao', '2021-02-18 11:16:17', 1);
INSERT INTO `post_like` VALUES (8, 'meng', '2021-02-18 11:16:34', 1);
INSERT INTO `post_like` VALUES (9, 'Mao', '2021-02-18 16:18:39', 0);
INSERT INTO `post_like` VALUES (9, 'meng', '2021-02-18 16:19:14', 0);
INSERT INTO `post_like` VALUES (9, 'ppp', '2021-02-18 16:18:55', 0);
INSERT INTO `post_like` VALUES (11, 'Mao', '2021-02-19 05:21:26', 1);
INSERT INTO `post_like` VALUES (12, 'Mao', '2021-04-11 09:22:12', 0);

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
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user_follower
-- ----------------------------
INSERT INTO `user_follower` VALUES ('Mao', 'meng');
INSERT INTO `user_follower` VALUES ('ppp', 'Mao');

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`  (
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `module_admin` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`username`) USING BTREE,
  INDEX `module_admin`(`module_admin`) USING BTREE,
  CONSTRAINT `user_info_ibfk_2` FOREIGN KEY (`module_admin`) REFERENCES `module` (`module_name`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('gang', '123', '1@qq.com', 'springboot');
INSERT INTO `user_info` VALUES ('h349wu', '123456789', 'h349wu@uwaterloo.ca', 'vue');
INSERT INTO `user_info` VALUES ('Mao', '1234', '', 'java');
INSERT INTO `user_info` VALUES ('meng', '123', '', 'life');
INSERT INTO `user_info` VALUES ('ppp', '123', ' ', 'vue');
INSERT INTO `user_info` VALUES ('systemadmin', '123', ' ', NULL);
INSERT INTO `user_info` VALUES ('user1', '123', ' ', 'vue');
INSERT INTO `user_info` VALUES ('user3', '123', ' ', NULL);
INSERT INTO `user_info` VALUES ('zgf', '123', '1173229585@qq.com', 'vue');
INSERT INTO `user_info` VALUES ('zgfu', '123', 'z58fu@uwaterloo.ca', 'vue');

SET FOREIGN_KEY_CHECKS = 1;
