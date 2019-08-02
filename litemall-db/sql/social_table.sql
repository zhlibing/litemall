DROP TABLE IF EXISTS `litemall_visit`;
CREATE TABLE `litemall_visit` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL DEFAULT '0' COMMENT '用户表的用户ID',
  `visit_id` int(11) NOT NULL DEFAULT '0' COMMENT '被访问用户表的用户ID',
  `visit_time` datetime DEFAULT NULL COMMENT '创建时间',
  `visit_count` varchar(255) DEFAULT '1' COMMENT '访问同一个人的次数',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `visit_id` (`visit_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='访客逻辑表';

DROP TABLE IF EXISTS `litemall_focuse`;
CREATE TABLE `litemall_focuse` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL DEFAULT '0' COMMENT '用户表的用户ID',
  `focuse_id` int(11) NOT NULL DEFAULT '0' COMMENT '被关注ID',
  `type` tinyint(3) NOT NULL DEFAULT '0' COMMENT '关注类型，如果type=0，则是用户；如果type=1，则是鱼塘',
  `focuse_time` datetime DEFAULT NULL COMMENT '关注时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `focuse_id` (`focuse_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='关注逻辑表';

DROP TABLE IF EXISTS `litemall_impress`;
CREATE TABLE `litemall_impress` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(100) NOT NULL COMMENT '印象',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='印象表';

DROP TABLE IF EXISTS `litemall_impress_user`;
CREATE TABLE `litemall_impress_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL DEFAULT '0' COMMENT '用户表的用户ID',
  `impressed_id` int(11) NOT NULL DEFAULT '0' COMMENT '被添加印象的对象ID',
  `type` tinyint(3) NOT NULL DEFAULT '0' COMMENT '关注类型，如果type=0，则是用户；如果type=1，则是鱼塘',
  `impress_id` int(11) NOT NULL DEFAULT '0' COMMENT '印象ID',
  `impress_time` datetime DEFAULT NULL COMMENT '印象时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `impressed_id` (`impressed_id`),
  KEY `impress_id` (`impress_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='印象逻辑表';

DROP TABLE IF EXISTS `litemall_group`;
CREATE TABLE `litemall_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL DEFAULT '0' COMMENT '用户表的用户ID',
  `age` varchar(255) DEFAULT NULL,
  `collectionCount` int(11) NOT NULL,
  `credit` int(11) NOT NULL,
  `current_people` int(11) NOT NULL,
  `description` varchar(100) NOT NULL COMMENT '描述',
  `pic_url` varchar(100) NOT NULL COMMENT '背景图',
  `km` float NOT NULL,
  `sexType` varchar(255) DEFAULT NULL,
  `status` int(11) NOT NULL,
  `talkId` bigint(20) NOT NULL,
  `way` varchar(255) DEFAULT NULL,
  `wayTime` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='群组表';

DROP TABLE IF EXISTS `litemall_user_group`;
CREATE TABLE `litemall_user_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL DEFAULT '0' COMMENT '用户表的用户ID',
  `group_id` int(11) NOT NULL DEFAULT '0' COMMENT '群组ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `group_id` (`group_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='群组逻辑表';

DROP TABLE IF EXISTS `litemall_report`;
CREATE TABLE `litemall_report` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL DEFAULT '0' COMMENT '用户表的用户ID',
  `report_id` int(11) NOT NULL DEFAULT '0' COMMENT '被举报的对象ID',
  `type` tinyint(3) NOT NULL DEFAULT '0' COMMENT '类型',
  `content` varchar(1000) NOT NULL COMMENT '举报内容',
  `pic_url` varchar(255) NOT NULL COMMENT '背景图',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `report_id` (`report_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='举报表';

DROP TABLE IF EXISTS `litemall_announce`;
CREATE TABLE `litemall_announce` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` tinyint(3) NOT NULL DEFAULT '0' COMMENT '类型',
  `title` varchar(100) NOT NULL COMMENT '公告标题',
  `content` varchar(1000) NOT NULL COMMENT '公告内容',
  `status` smallint(6) DEFAULT '0' COMMENT '状态，如果是0则是正常可用；如果是1则是过期; 如果是2则是下架。',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='公告表';

DROP TABLE IF EXISTS `litemall_vote`;
CREATE TABLE `litemall_vote` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL DEFAULT '0' COMMENT '用户表的用户ID',
  `vote_user_id` int(11) NOT NULL DEFAULT '0' COMMENT '被投票的用户ID',
  `activity_id` int(11) NOT NULL DEFAULT '0' COMMENT '活动ID',
  `type` tinyint(3) NOT NULL DEFAULT '0' COMMENT '类型',
  `vote_time` datetime DEFAULT NULL COMMENT '创建时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `vote_user_id` (`vote_user_id`),
  KEY `activity_id` (`activity_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='投票表';

DROP TABLE IF EXISTS `litemall_activity`;
CREATE TABLE `litemall_activity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL DEFAULT '0' COMMENT '用户表的用户ID',
  `title` varchar(100) DEFAULT NULL,
  `description` varchar(255) NOT NULL COMMENT '描述',
  `rule` int(11) DEFAULT NULL COMMENT '规则',
  `reword` int(11) DEFAULT NULL COMMENT '奖励',
  `fee` int(11) DEFAULT NULL COMMENT '报名费',
  `limited` int(11) NOT NULL COMMENT '人数限制',
  `collectionCount` int(11) NOT NULL,
  `credit` int(11) NOT NULL,
  `level` int(11) NOT NULL COMMENT '级别',
  `current_people` int(11) NOT NULL,
  `pic_url` varchar(255) NOT NULL COMMENT '背景图',
  `km` float NOT NULL,
  `status` int(11) NOT NULL,
  `talkId` bigint(20) NOT NULL,
  `activity_time` datetime DEFAULT NULL COMMENT '活动时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='活动表';

DROP TABLE IF EXISTS `litemall_user_activity`;
CREATE TABLE `litemall_user_activity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL DEFAULT '0' COMMENT '用户表的用户ID',
  `activity_id` int(11) NOT NULL DEFAULT '0' COMMENT '活动ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `activity_id` (`activity_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='活动逻辑表';

DROP TABLE IF EXISTS `litemall_fish_ponds`;
CREATE TABLE `litemall_fish_ponds` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL DEFAULT '0' COMMENT '用户表的用户ID',
  `title` varchar(100) DEFAULT NULL,
  `description` varchar(255) NOT NULL COMMENT '描述',
  `rule` int(11) DEFAULT NULL COMMENT '规则',
  `reword` int(11) DEFAULT NULL COMMENT '奖励',
  `fee` int(11) DEFAULT NULL COMMENT '报名费',
  `limited` int(11) NOT NULL COMMENT '人数限制',
  `collectionCount` int(11) NOT NULL,
  `credit` int(11) NOT NULL,
  `level` int(11) NOT NULL COMMENT '级别',
  `current_people` int(11) NOT NULL,
  `pic_url` varchar(255) NOT NULL COMMENT '背景图',
  `km` float NOT NULL,
  `status` int(11) NOT NULL,
  `talkId` bigint(20) NOT NULL,
  `activity_time` datetime DEFAULT NULL COMMENT '活动时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='鱼塘表';

DROP TABLE IF EXISTS `litemall_user_fish_ponds`;
CREATE TABLE `litemall_user_fish_ponds` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL DEFAULT '0' COMMENT '用户表的用户ID',
  `fish_ponds_id` int(11) NOT NULL DEFAULT '0' COMMENT '活动ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `fish_ponds_id` (`fish_ponds_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='鱼塘逻辑表';

DROP TABLE IF EXISTS `litemall_question`;
CREATE TABLE `litemall_question` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL DEFAULT '0' COMMENT '用户表的用户ID',
  `title` varchar(100) DEFAULT NULL,
  `description` varchar(255) NOT NULL COMMENT '描述',
  `answer_count` int(11) DEFAULT NULL COMMENT '规则',
  `reword` int(11) DEFAULT NULL COMMENT '奖励',
  `pic_url` varchar(255) NOT NULL COMMENT '背景图',
  `status` int(11) NOT NULL,
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='提问表';

DROP TABLE IF EXISTS `litemall_question_answer`;
CREATE TABLE `litemall_question_answer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL DEFAULT '0' COMMENT '用户表的用户ID',
  `question_id` int(11) NOT NULL DEFAULT '0' COMMENT '活动ID',
  `answer` varchar(255) NOT NULL COMMENT '回答',
  `pic_url` varchar(255) NOT NULL COMMENT '背景图',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `question_id` (`question_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='回答逻辑表';

DROP TABLE IF EXISTS `litemall_user_reply_answer`;
CREATE TABLE `litemall_user_reply_answer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL DEFAULT '0' COMMENT '用户表的用户ID',
  `answer_id` int(11) NOT NULL DEFAULT '0' COMMENT '活动ID',
  `reply_content` varchar(255) NOT NULL COMMENT '回复',
  `pic_url` varchar(255) NOT NULL COMMENT '背景图',
  `useful` tinyint(1) DEFAULT '0' COMMENT '是否对于我有用',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `answer_id` (`answer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='回答回复逻辑表';



-- ////