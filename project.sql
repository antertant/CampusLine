-- chat_message: table
CREATE TABLE `chat_message` (
  `message_id` int NOT NULL AUTO_INCREMENT COMMENT '聊天内容id',
  `from_user` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL COMMENT '发送者',
  `to_user` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8_general_ci NOT NULL COMMENT '接收者',
  `content` longtext NOT NULL COMMENT '聊天内容',
  `send_time` datetime NOT NULL COMMENT '发送时间',
  `unread` int NOT NULL DEFAULT '1' COMMENT '消息类型',
  PRIMARY KEY (`message_id`) USING BTREE,
  KEY `from_user` (`from_user`) USING BTREE,
  KEY `to_user` (`to_user`) USING BTREE,
  CONSTRAINT `chat_message_ibfk_1` FOREIGN KEY (`from_user`) REFERENCES `user_info` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `chat_message_ibfk_2` FOREIGN KEY (`to_user`) REFERENCES `user_info` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT;

-- No native definition for element: from_user (index)

-- No native definition for element: to_user (index)

-- comment_like: table
CREATE TABLE `comment_like` (
  `comment_id` int NOT NULL COMMENT '评论id',
  `clike_user` varchar(255) DEFAULT NULL COMMENT '点赞用户',
  `clike_time` datetime DEFAULT NULL COMMENT '点赞时间',
  `viewed` int DEFAULT '0' COMMENT '点赞消息是否查看',
  KEY `like_user` (`clike_user`) USING BTREE,
  KEY `comment_id` (`comment_id`) USING BTREE,
  CONSTRAINT `comment_like_ibfk_2` FOREIGN KEY (`clike_user`) REFERENCES `user_info` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `comment_like_ibfk_3` FOREIGN KEY (`comment_id`) REFERENCES `post_comment` (`comment_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT;

-- No native definition for element: comment_id (index)

-- No native definition for element: like_user (index)

-- comment_reply: table
CREATE TABLE `comment_reply` (
  `reply_id` int NOT NULL AUTO_INCREMENT COMMENT '回复id',
  `comment_id` int NOT NULL COMMENT '评论id',
  `creply_content` longtext COMMENT '回复内容',
  `creply_time` datetime DEFAULT NULL COMMENT '回复时间',
  `from_user` varchar(255) DEFAULT NULL COMMENT '回复者',
  `to_user` varchar(255) DEFAULT NULL COMMENT '回复谁',
  `viewed` int DEFAULT '0' COMMENT '消息是否查看',
  PRIMARY KEY (`reply_id`) USING BTREE,
  KEY `from_user` (`from_user`) USING BTREE,
  KEY `to_user` (`to_user`) USING BTREE,
  KEY `comment_id` (`comment_id`) USING BTREE,
  CONSTRAINT `comment_reply_ibfk_2` FOREIGN KEY (`from_user`) REFERENCES `user_info` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `comment_reply_ibfk_3` FOREIGN KEY (`to_user`) REFERENCES `user_info` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `comment_reply_ibfk_4` FOREIGN KEY (`comment_id`) REFERENCES `post_comment` (`comment_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT;

-- No native definition for element: comment_id (index)

-- No native definition for element: from_user (index)

-- No native definition for element: to_user (index)

-- email_verify: table
CREATE TABLE `email_verify` (
  `email` varchar(255) DEFAULT NULL COMMENT '验证邮箱',
  `verifycode` varchar(255) DEFAULT NULL COMMENT '验证码'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT;

-- module: table
CREATE TABLE `module` (
  `module_name` varchar(255) NOT NULL COMMENT '板块名',
  `module_intro` varchar(3000) DEFAULT 'default module introduction' COMMENT '板块介绍',
  `max_adminnumber` int DEFAULT '5' COMMENT '板块最大管理员数量',
  PRIMARY KEY (`module_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT;

-- module_creation: table
CREATE TABLE `module_creation` (
  `module_name` varchar(255) NOT NULL COMMENT '申请新建板块名',
  `request_count` int DEFAULT '1' COMMENT '申请人数',
  PRIMARY KEY (`module_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT;

-- post: table
CREATE TABLE `post` (
  `post_id` int NOT NULL AUTO_INCREMENT COMMENT '贴子id',
  `if_top` int DEFAULT '0' COMMENT '是否置顶',
  `module_name` varchar(255) DEFAULT NULL COMMENT '板块名',
  `post_content` longtext COMMENT '帖子内容',
  `post_author` varchar(255) DEFAULT NULL COMMENT '帖子作者',
  `post_createtime` datetime DEFAULT NULL COMMENT '帖子发布时间',
  `post_likes` int DEFAULT '0' COMMENT '点赞人数',
  `post_comments` int DEFAULT '0' COMMENT '帖子评论数',
  `post_collections` int DEFAULT '0' COMMENT '帖子收藏人数',
  PRIMARY KEY (`post_id`) USING BTREE,
  KEY `author` (`post_author`) USING BTREE,
  KEY `module_name` (`module_name`) USING BTREE,
  CONSTRAINT `post_ibfk_2` FOREIGN KEY (`post_author`) REFERENCES `user_info` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `post_ibfk_3` FOREIGN KEY (`module_name`) REFERENCES `module` (`module_name`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT;

-- No native definition for element: module_name (index)

-- No native definition for element: author (index)

-- post_collect: table
CREATE TABLE `post_collect` (
  `post_id` int NOT NULL COMMENT '收藏帖子id',
  `collect_user` varchar(255) DEFAULT NULL COMMENT '收藏者',
  KEY `collect_user` (`collect_user`) USING BTREE,
  KEY `post_id` (`post_id`) USING BTREE,
  CONSTRAINT `post_collect_ibfk_2` FOREIGN KEY (`collect_user`) REFERENCES `user_info` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `post_collect_ibfk_3` FOREIGN KEY (`post_id`) REFERENCES `post` (`post_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT;

-- No native definition for element: post_id (index)

-- No native definition for element: collect_user (index)

-- post_comment: table
CREATE TABLE `post_comment` (
  `comment_id` int NOT NULL AUTO_INCREMENT COMMENT '评论id',
  `post_id` int NOT NULL COMMENT '帖子id',
  `comment_user` varchar(255) DEFAULT NULL COMMENT '评论用户',
  `comment_content` longtext COMMENT '评论内容',
  `comment_time` datetime DEFAULT NULL COMMENT '评论时间',
  `viewed` int DEFAULT '0' COMMENT '消息是否查看',
  PRIMARY KEY (`comment_id`) USING BTREE,
  KEY `comment_user` (`comment_user`) USING BTREE,
  KEY `post_id` (`post_id`) USING BTREE,
  CONSTRAINT `post_comment_ibfk_2` FOREIGN KEY (`comment_user`) REFERENCES `user_info` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `post_comment_ibfk_3` FOREIGN KEY (`post_id`) REFERENCES `post` (`post_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT;

-- No native definition for element: post_id (index)

-- No native definition for element: comment_user (index)

-- post_like: table
CREATE TABLE `post_like` (
  `post_id` int NOT NULL COMMENT '帖子id',
  `like_user` varchar(255) NOT NULL COMMENT '帖子点赞用户',
  `like_time` datetime DEFAULT NULL COMMENT '点赞时间',
  `viewed` int DEFAULT '0' COMMENT '消息是否查看',
  PRIMARY KEY (`post_id`,`like_user`) USING BTREE,
  KEY `like_user` (`like_user`) USING BTREE,
  CONSTRAINT `post_like_ibfk_2` FOREIGN KEY (`like_user`) REFERENCES `user_info` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `post_like_ibfk_3` FOREIGN KEY (`post_id`) REFERENCES `post` (`post_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT;

-- No native definition for element: like_user (index)

-- user_follower: table
CREATE TABLE `user_follower` (
  `username` varchar(255) DEFAULT NULL COMMENT '被关注用户',
  `follower` varchar(255) DEFAULT NULL COMMENT '关注粉丝',
  KEY `username` (`username`) USING BTREE,
  KEY `follower` (`follower`) USING BTREE,
  CONSTRAINT `user_follower_ibfk_1` FOREIGN KEY (`username`) REFERENCES `user_info` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_follower_ibfk_2` FOREIGN KEY (`follower`) REFERENCES `user_info` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT;

-- No native definition for element: username (index)

-- No native definition for element: follower (index)

-- user_info: table
CREATE TABLE `user_info` (
  `username` varchar(255) NOT NULL COMMENT '用户名',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `email` varchar(255) NOT NULL COMMENT '邮箱',
  `module_admin` varchar(255) DEFAULT NULL COMMENT '板块管理员',
  PRIMARY KEY (`username`) USING BTREE,
  KEY `module_admin` (`module_admin`) USING BTREE,
  CONSTRAINT `user_info_ibfk_2` FOREIGN KEY (`module_admin`) REFERENCES `module` (`module_name`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT;

-- No native definition for element: module_admin (index)


