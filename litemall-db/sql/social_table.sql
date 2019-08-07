-- litemall_footprint逻辑重复
DROP TABLE IF EXISTS `litemall_visit`;
CREATE TABLE `litemall_visit` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL DEFAULT '0' COMMENT '用户表的用户ID',
  `visited_id` int(11) NOT NULL DEFAULT '0' COMMENT '被访问用户表的用户ID',
  `visit_time` datetime DEFAULT NULL COMMENT '创建时间',
  `visit_count` varchar(255) DEFAULT '1' COMMENT '访问同一个人的次数',
  `type` tinyint(3) NOT NULL DEFAULT '0' COMMENT '关注类型，如果type=0，则是用户；如果type=1，则是鱼塘',
  `add_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `visited_id` (`visited_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='访客逻辑表';

DROP TABLE IF EXISTS `litemall_focuse`;
CREATE TABLE `litemall_focuse` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL DEFAULT '0' COMMENT '用户表的用户ID',
  `focused_id` int(11) NOT NULL DEFAULT '0' COMMENT '被关注ID',
  `type` tinyint(3) NOT NULL DEFAULT '0' COMMENT '关注类型，如果type=0，则是用户；如果type=1，则是鱼塘',
  `add_time` datetime DEFAULT NULL COMMENT '关注时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `focused_id` (`focused_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='关注逻辑表';

DROP TABLE IF EXISTS `litemall_impress`;
CREATE TABLE `litemall_impress` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(100) NOT NULL COMMENT '印象',
  `add_time` datetime DEFAULT NULL COMMENT '创建时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='印象常量表';

DROP TABLE IF EXISTS `litemall_impress_user`;
CREATE TABLE `litemall_impress_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL DEFAULT '0' COMMENT '用户表的用户ID',
  `impressed_id` int(11) NOT NULL DEFAULT '0' COMMENT '被添加印象的对象ID',
  `type` tinyint(3) NOT NULL DEFAULT '0' COMMENT '关注类型，如果type=0，则是用户；如果type=1，则是鱼塘',
  `impress_id` int(11) NOT NULL DEFAULT '0' COMMENT '印象ID',
  `add_time` datetime DEFAULT NULL COMMENT '印象时间',
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
  `age` varchar(20) DEFAULT NULL,
  `collectionCount` int(11) NOT NULL,
  `credit` int(11) NOT NULL,
  `current_people` int(11) NOT NULL,
  `description` varchar(100) NOT NULL COMMENT '描述',
  `pic_urls` varchar(1023) NOT NULL COMMENT '背景图',
  `km` float NOT NULL,
  `gender` tinyint(3) NOT NULL DEFAULT '0' COMMENT '性别：0 未知， 1男， 1 女',
  `status` int(11) NOT NULL,
  `talkId` bigint(20) NOT NULL,
  `way` varchar(1023) DEFAULT NULL,
  `wayTime` varchar(1023) DEFAULT NULL,
  `add_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='群组表';

INSERT INTO `litemall_group` VALUES (
'1',
'1',
'20',
'0',
'0',
'1',
'合肥万达交友群',
'["http://yanxuan.nosdn.127.net/4eb09e08ac9de543d2291d27a6be0b54.jpg", "http://yanxuan.nosdn.127.net/0c9eb81c7594dbe42802ff1ebbece51a.jpg", "http://yanxuan.nosdn.127.net/8cfc7b6bfd28687ab3399da08e5ba61b.jpg", "http://yanxuan.nosdn.127.net/b98cfd7f197b62abd1679321eae253a6.jpg"]',
'1',
'1',
'0',
'0',
'0',
'1',
'2019-06-13 22:49:32',
'2019-06-13 22:49:32',
'0'
);

INSERT INTO `litemall_group` VALUES (
'2',
'1',
'20',
'0',
'0',
'1',
'上海东方门主群',
'["http://yanxuan.nosdn.127.net/4eb09e08ac9de543d2291d27a6be0b54.jpg", "http://yanxuan.nosdn.127.net/0c9eb81c7594dbe42802ff1ebbece51a.jpg", "http://yanxuan.nosdn.127.net/8cfc7b6bfd28687ab3399da08e5ba61b.jpg", "http://yanxuan.nosdn.127.net/b98cfd7f197b62abd1679321eae253a6.jpg"]',
'1',
'1',
'0',
'0',
'0',
'1',
'2019-06-13 22:49:32',
'2019-06-13 22:49:32',
'0'
);

INSERT INTO `litemall_group` VALUES (
'3',
'1',
'20',
'0',
'0',
'1',
'北京鸟巢群',
'["http://yanxuan.nosdn.127.net/4eb09e08ac9de543d2291d27a6be0b54.jpg", "http://yanxuan.nosdn.127.net/0c9eb81c7594dbe42802ff1ebbece51a.jpg", "http://yanxuan.nosdn.127.net/8cfc7b6bfd28687ab3399da08e5ba61b.jpg", "http://yanxuan.nosdn.127.net/b98cfd7f197b62abd1679321eae253a6.jpg"]',
'1',
'1',
'0',
'0',
'0',
'1',
'2019-06-13 22:49:32',
'2019-06-13 22:49:32',
'0'
);

DROP TABLE IF EXISTS `litemall_group_user`;
CREATE TABLE `litemall_group_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL DEFAULT '0' COMMENT '用户表的用户ID',
  `group_id` int(11) NOT NULL DEFAULT '0' COMMENT '群组ID',
  `add_time` datetime DEFAULT NULL COMMENT '创建时间',
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
  `content` varchar(1023) NOT NULL COMMENT '举报内容',
  `pic_urls` varchar(1023) NOT NULL COMMENT '背景图',
  `add_time` datetime DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `report_id` (`report_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='举报表';

DROP TABLE IF EXISTS `litemall_announce`;
CREATE TABLE `litemall_announce` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` tinyint(3) NOT NULL DEFAULT '0' COMMENT '类型',
  `title` varchar(255) NOT NULL COMMENT '公告标题',
  `content` varchar(1023) NOT NULL COMMENT '公告内容',
  `status` smallint(6) DEFAULT '0' COMMENT '状态，如果是0则是正常可用；如果是1则是过期; 如果是2则是下架。',
  `add_time` datetime DEFAULT NULL COMMENT '创建时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='公告表';

DROP TABLE IF EXISTS `litemall_vote`;
CREATE TABLE `litemall_vote` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL DEFAULT '0' COMMENT '用户表的用户ID',
  `voted_id` int(11) NOT NULL DEFAULT '0' COMMENT '被投票的对象ID',
  `activity_id` int(11) NOT NULL DEFAULT '0' COMMENT '活动ID',
  `type` tinyint(3) NOT NULL DEFAULT '0' COMMENT '类型',
  `add_time` datetime DEFAULT NULL COMMENT '创建时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `voted_id` (`voted_id`),
  KEY `activity_id` (`activity_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='投票表';

DROP TABLE IF EXISTS `litemall_activity`;
CREATE TABLE `litemall_activity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL DEFAULT '0' COMMENT '用户表的用户ID',
  `title` varchar(255) DEFAULT NULL,
  `description` varchar(1023) NOT NULL COMMENT '描述',
  `rule` int(11) DEFAULT NULL COMMENT '规则',
  `reword` int(11) DEFAULT NULL COMMENT '奖励',
  `fee` int(11) DEFAULT NULL COMMENT '报名费',
  `limited` int(11) NOT NULL COMMENT '人数限制',
  `collectionCount` int(11) NOT NULL,
  `credit` int(11) NOT NULL,
  `level` int(11) NOT NULL COMMENT '级别',
  `current_people` int(11) NOT NULL,
  `pic_urls` varchar(1023) NOT NULL COMMENT '背景图',
  `km` float NOT NULL,
  `status` int(11) NOT NULL,
  `talkId` bigint(20) NOT NULL,
  `activity_time` datetime DEFAULT NULL COMMENT '活动时间',
  `add_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='活动表';

INSERT INTO `litemall_activity` VALUES (
'1',
'1',
'7+1鱼王比赛',
'7条10斤大青鱼+1条20斤鱼王草鱼，钓得者奖励500元。',
'1',
'100',
'20',
'10',
'0',
'0',
'1',
'0',
'["http://yanxuan.nosdn.127.net/4eb09e08ac9de543d2291d27a6be0b54.jpg", "http://yanxuan.nosdn.127.net/0c9eb81c7594dbe42802ff1ebbece51a.jpg", "http://yanxuan.nosdn.127.net/8cfc7b6bfd28687ab3399da08e5ba61b.jpg", "http://yanxuan.nosdn.127.net/b98cfd7f197b62abd1679321eae253a6.jpg"]',
'1',
'0',
'1',
'2019-06-13 22:49:32',
'2019-06-13 22:49:32',
'2019-06-13 22:49:32',
'0'
);

INSERT INTO `litemall_activity` VALUES (
'2',
'1',
'儿童节亲子垂钓',
'儿童节带着家人和宝宝，来这里体验不一样的垂钓和户外亲子乐趣吧',
'1',
'100',
'20',
'10',
'0',
'0',
'1',
'0',
'["http://yanxuan.nosdn.127.net/4eb09e08ac9de543d2291d27a6be0b54.jpg", "http://yanxuan.nosdn.127.net/0c9eb81c7594dbe42802ff1ebbece51a.jpg", "http://yanxuan.nosdn.127.net/8cfc7b6bfd28687ab3399da08e5ba61b.jpg", "http://yanxuan.nosdn.127.net/b98cfd7f197b62abd1679321eae253a6.jpg"]',
'1',
'0',
'1',
'2019-06-13 22:49:32',
'2019-06-13 22:49:32',
'2019-06-13 22:49:32',
'0'
);

INSERT INTO `litemall_activity` VALUES (
'3',
'1',
'中秋大鱼海棠',
'钓得海棠标记的大鱼者，免费带走，不谢~',
'1',
'100',
'20',
'10',
'0',
'0',
'1',
'0',
'["http://yanxuan.nosdn.127.net/4eb09e08ac9de543d2291d27a6be0b54.jpg", "http://yanxuan.nosdn.127.net/0c9eb81c7594dbe42802ff1ebbece51a.jpg", "http://yanxuan.nosdn.127.net/8cfc7b6bfd28687ab3399da08e5ba61b.jpg", "http://yanxuan.nosdn.127.net/b98cfd7f197b62abd1679321eae253a6.jpg"]',
'1',
'0',
'1',
'2019-06-13 22:49:32',
'2019-06-13 22:49:32',
'2019-06-13 22:49:32',
'0'
);

DROP TABLE IF EXISTS `litemall_activity_user`;
CREATE TABLE `litemall_activity_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL DEFAULT '0' COMMENT '用户表的用户ID',
  `activity_id` int(11) NOT NULL DEFAULT '0' COMMENT '活动ID',
  `add_time` datetime DEFAULT NULL COMMENT '创建时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `activity_id` (`activity_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='活动逻辑表';

DROP TABLE IF EXISTS `litemall_fish_ponds`;
CREATE TABLE `litemall_fish_ponds` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL DEFAULT '0' COMMENT '用户表的用户ID',
  `title` varchar(255) DEFAULT NULL,
  `description` varchar(1023) NOT NULL COMMENT '描述',
  `rule` int(11) DEFAULT NULL COMMENT '规则',
  `reword` int(11) DEFAULT NULL COMMENT '奖励',
  `fee` int(11) DEFAULT NULL COMMENT '报名费',
  `limited` int(11) NOT NULL COMMENT '人数限制',
  `collectionCount` int(11) NOT NULL,
  `credit` int(11) NOT NULL,
  `level` int(11) NOT NULL COMMENT '级别',
  `current_people` int(11) NOT NULL,
  `pic_urls` varchar(1023) NOT NULL COMMENT '背景图',
  `km` float NOT NULL,
  `status` int(11) NOT NULL,
  `talkId` bigint(20) NOT NULL,
  `activity_time` datetime DEFAULT NULL COMMENT '活动时间',
  `add_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='鱼塘表';

INSERT INTO `litemall_fish_ponds` VALUES (
'1',
'1',
'老王渔场',
'最好的周末垂钓休闲场所，欢迎新老客户光临！',
'1',
'100',
'20',
'10',
'0',
'0',
'1',
'0',
'["http://yanxuan.nosdn.127.net/4eb09e08ac9de543d2291d27a6be0b54.jpg", "http://yanxuan.nosdn.127.net/0c9eb81c7594dbe42802ff1ebbece51a.jpg", "http://yanxuan.nosdn.127.net/8cfc7b6bfd28687ab3399da08e5ba61b.jpg", "http://yanxuan.nosdn.127.net/b98cfd7f197b62abd1679321eae253a6.jpg"]',
'1',
'0',
'1',
'2019-06-13 22:49:32',
'2019-06-13 22:49:32',
'2019-06-13 22:49:32',
'0'
);
INSERT INTO `litemall_fish_ponds` VALUES (
'2',
'1',
'老张渔场',
'垂钓得鱼王者，奖励1000元，高手快来！',
'1',
'100',
'20',
'10',
'0',
'0',
'2',
'0',
'["http://yanxuan.nosdn.127.net/4eb09e08ac9de543d2291d27a6be0b54.jpg", "http://yanxuan.nosdn.127.net/0c9eb81c7594dbe42802ff1ebbece51a.jpg", "http://yanxuan.nosdn.127.net/8cfc7b6bfd28687ab3399da08e5ba61b.jpg", "http://yanxuan.nosdn.127.net/b98cfd7f197b62abd1679321eae253a6.jpg"]',
'1',
'0',
'1',
'2019-06-13 22:49:32',
'2019-06-13 22:49:32',
'2019-06-13 22:49:32',
'0'
);
INSERT INTO `litemall_fish_ponds` VALUES (
'3',
'1',
'老李渔场',
'累计奖金5000元，人气超高，欢迎前来挑战！',
'1',
'100',
'20',
'10',
'0',
'0',
'3',
'0',
'["http://yanxuan.nosdn.127.net/4eb09e08ac9de543d2291d27a6be0b54.jpg", "http://yanxuan.nosdn.127.net/0c9eb81c7594dbe42802ff1ebbece51a.jpg", "http://yanxuan.nosdn.127.net/8cfc7b6bfd28687ab3399da08e5ba61b.jpg", "http://yanxuan.nosdn.127.net/b98cfd7f197b62abd1679321eae253a6.jpg"]',
'1',
'0',
'1',
'2019-06-13 22:49:32',
'2019-06-13 22:49:32',
'2019-06-13 22:49:32',
'0'
);

DROP TABLE IF EXISTS `litemall_fish_ponds_user`;
CREATE TABLE `litemall_fish_ponds_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL DEFAULT '0' COMMENT '用户表的用户ID',
  `fish_ponds_id` int(11) NOT NULL DEFAULT '0' COMMENT '活动ID',
  `add_time` datetime DEFAULT NULL COMMENT '创建时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `fish_ponds_id` (`fish_ponds_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='鱼塘逻辑表';

DROP TABLE IF EXISTS `litemall_question`;
CREATE TABLE `litemall_question` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL DEFAULT '0' COMMENT '用户表的用户ID',
  `title` varchar(255) DEFAULT NULL,
  `description` varchar(1023) NOT NULL COMMENT '描述',
  `answer_count` int(11) DEFAULT NULL COMMENT '规则',
  `reword` int(11) DEFAULT NULL COMMENT '奖励',
  `pic_urls` varchar(1023) NOT NULL COMMENT '背景图',
  `status` int(11) NOT NULL,
  `add_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='提问表';

INSERT INTO `litemall_question` VALUES (
'1',
'1',
'哪儿有鱼钓',
'附近找了好久了，都没有发现可以野钓的地方，请问好心人，哪儿可以野钓啊？',
'0',
'0',
'["http://yanxuan.nosdn.127.net/4eb09e08ac9de543d2291d27a6be0b54.jpg", "http://yanxuan.nosdn.127.net/0c9eb81c7594dbe42802ff1ebbece51a.jpg", "http://yanxuan.nosdn.127.net/8cfc7b6bfd28687ab3399da08e5ba61b.jpg", "http://yanxuan.nosdn.127.net/b98cfd7f197b62abd1679321eae253a6.jpg"]',
'0',
'2019-06-13 22:49:32',
'2019-06-13 22:49:32',
'0'
);

INSERT INTO `litemall_question` VALUES (
'2',
'1',
'哪儿有好吃的',
'我是一个吃货，喜欢看吃货驾到，可是方圆百里都已经吃遍了，有没有更多推荐的好吃的？',
'0',
'0',
'["http://yanxuan.nosdn.127.net/4eb09e08ac9de543d2291d27a6be0b54.jpg", "http://yanxuan.nosdn.127.net/0c9eb81c7594dbe42802ff1ebbece51a.jpg", "http://yanxuan.nosdn.127.net/8cfc7b6bfd28687ab3399da08e5ba61b.jpg", "http://yanxuan.nosdn.127.net/b98cfd7f197b62abd1679321eae253a6.jpg"]',
'0',
'2019-06-13 22:49:32',
'2019-06-13 22:49:32',
'0'
);

INSERT INTO `litemall_question` VALUES (
'3',
'1',
'哪儿有好玩的',
'我喜欢泡吧、美女、香车，有没有相同兴趣的，加个好友一起hi梵天~快到碗里来~',
'0',
'0',
'["http://yanxuan.nosdn.127.net/4eb09e08ac9de543d2291d27a6be0b54.jpg", "http://yanxuan.nosdn.127.net/0c9eb81c7594dbe42802ff1ebbece51a.jpg", "http://yanxuan.nosdn.127.net/8cfc7b6bfd28687ab3399da08e5ba61b.jpg", "http://yanxuan.nosdn.127.net/b98cfd7f197b62abd1679321eae253a6.jpg"]',
'0',
'2019-06-13 22:49:32',
'2019-06-13 22:49:32',
'0'
);

DROP TABLE IF EXISTS `litemall_question_answer`;
CREATE TABLE `litemall_question_answer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL DEFAULT '0' COMMENT '用户表的用户ID',
  `question_id` int(11) NOT NULL DEFAULT '0' COMMENT '活动ID',
  `answer` varchar(1023) NOT NULL COMMENT '回答',
  `pic_urls` varchar(1023) NOT NULL COMMENT '背景图',
  `add_time` datetime DEFAULT NULL COMMENT '创建时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `question_id` (`question_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='回答逻辑表';

DROP TABLE IF EXISTS `litemall_question_reply_answer`;
CREATE TABLE `litemall_question_reply_answer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL DEFAULT '0' COMMENT '用户表的用户ID',
  `answer_id` int(11) NOT NULL DEFAULT '0' COMMENT '活动ID',
  `reply_content` varchar(1023) NOT NULL COMMENT '回复',
  `pic_urls` varchar(1023) NOT NULL COMMENT '背景图',
  `useful` tinyint(1) DEFAULT '0' COMMENT '是否对于我有用',
  `add_time` datetime DEFAULT NULL COMMENT '创建时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `answer_id` (`answer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='回答回复逻辑表';

DROP TABLE IF EXISTS `litemall_circle`;
CREATE TABLE `litemall_circle` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL DEFAULT '0' COMMENT '用户表的用户ID',
  `type` tinyint(3) NOT NULL DEFAULT '0' COMMENT '类型',
  `content` varchar(1023) NOT NULL COMMENT '描述',
  `pic_urls` varchar(1023) NOT NULL COMMENT '背景图',
  `status` int(11) NOT NULL,
  `add_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='动态/朋友圈表';

INSERT INTO `litemall_circle` VALUES (
'1',
'1',
'1',
'今天好开心啊，工资涨了1000啦，晚上去吃大餐~',
'["http://yanxuan.nosdn.127.net/4eb09e08ac9de543d2291d27a6be0b54.jpg", "http://yanxuan.nosdn.127.net/0c9eb81c7594dbe42802ff1ebbece51a.jpg", "http://yanxuan.nosdn.127.net/8cfc7b6bfd28687ab3399da08e5ba61b.jpg", "http://yanxuan.nosdn.127.net/b98cfd7f197b62abd1679321eae253a6.jpg"]',
'0',
'2019-06-13 22:49:32',
'2019-06-13 22:49:32',
'0'
);

INSERT INTO `litemall_circle` VALUES (
'2',
'1',
'1',
'今天被交警罚了200，正不爽啊，有没有在枫林路被坑的呀？',
'["http://yanxuan.nosdn.127.net/4eb09e08ac9de543d2291d27a6be0b54.jpg", "http://yanxuan.nosdn.127.net/0c9eb81c7594dbe42802ff1ebbece51a.jpg", "http://yanxuan.nosdn.127.net/8cfc7b6bfd28687ab3399da08e5ba61b.jpg", "http://yanxuan.nosdn.127.net/b98cfd7f197b62abd1679321eae253a6.jpg"]',
'0',
'2019-06-13 22:49:32',
'2019-06-13 22:49:32',
'0'
);

INSERT INTO `litemall_circle` VALUES (
'3',
'1',
'1',
'听说今年股市大涨，该不该下手，懂的人吱声呗？',
'["http://yanxuan.nosdn.127.net/4eb09e08ac9de543d2291d27a6be0b54.jpg", "http://yanxuan.nosdn.127.net/0c9eb81c7594dbe42802ff1ebbece51a.jpg", "http://yanxuan.nosdn.127.net/8cfc7b6bfd28687ab3399da08e5ba61b.jpg", "http://yanxuan.nosdn.127.net/b98cfd7f197b62abd1679321eae253a6.jpg"]',
'0',
'2019-06-13 22:49:32',
'2019-06-13 22:49:32',
'0'
);
-- litemall_comment重复
DROP TABLE IF EXISTS `litemall_circle_replay`;
CREATE TABLE `litemall_circle_replay` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL DEFAULT '0' COMMENT '用户表的用户ID',
  `circle_id` int(11) NOT NULL DEFAULT '0' COMMENT '活动ID',
  `replay_id` int(11) NOT NULL DEFAULT '0' COMMENT '回复的此评论ID和circle_id有且只有一个',
  `replay` varchar(1023) NOT NULL COMMENT '回答内容',
  `pic_urls` varchar(1023) NOT NULL COMMENT '背景图',
  `add_time` datetime DEFAULT NULL COMMENT '创建时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `circle_id` (`circle_id`),
  KEY `replay_id` (`replay_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='朋友圈回复表';

DROP TABLE IF EXISTS `litemall_circle_zan`;
CREATE TABLE `litemall_circle_zan` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL DEFAULT '0' COMMENT '用户表的用户ID',
  `circle_id` int(11) NOT NULL DEFAULT '0' COMMENT '活动ID',
  `add_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `circle_id` (`circle_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='朋友圈赞表';

DROP TABLE IF EXISTS `litemall_scatter`;
CREATE TABLE `litemall_scatter` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL DEFAULT '0' COMMENT '用户表的用户ID',
  `to_user_id` int(11) NOT NULL DEFAULT '0' COMMENT '用户ID',
  `content` varchar(1023) NOT NULL COMMENT '回答内容',
  `add_time` datetime DEFAULT NULL COMMENT '创建时间',
  `readed` tinyint(1) DEFAULT '0' COMMENT '是否被查看',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `to_user_id` (`to_user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='撒网捞鱼/模仿漂流瓶';

DROP TABLE IF EXISTS `litemall_channel`;
CREATE TABLE `litemall_channel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL COMMENT '回答内容',
  `add_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='渠道';

DROP TABLE IF EXISTS `litemall_channel_phone`;
CREATE TABLE `litemall_channel_phone` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ip` varchar(100) NOT NULL COMMENT '回答内容',
  `imei` varchar(100) NOT NULL COMMENT '回答内容',
  `channel_id` varchar(100) NOT NULL COMMENT '回答内容',
  `app_version` varchar(100) NOT NULL COMMENT '回答内容',
  `app_id` varchar(100) NOT NULL COMMENT '回答内容',
  `city` varchar(100) NOT NULL COMMENT '回答内容',
  `add_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `channel_id` (`channel_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='渠道逻辑关系';

DROP TABLE IF EXISTS `litemall_ad_cost_time`;
CREATE TABLE `litemall_ad_cost_time` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cost_time` int(11) NOT NULL COMMENT '回答内容',
  `fee` int(11) NOT NULL COMMENT '回答内容',
  `add_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='广告时间常量';

DROP TABLE IF EXISTS `litemall_ad_market`;
CREATE TABLE `litemall_ad_market` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cost_time` int(11) NOT NULL COMMENT '回答内容',
  `fee` int(11) NOT NULL COMMENT '回答内容',
  `type` tinyint(3) NOT NULL DEFAULT '0' COMMENT '类型',
  `status` smallint(6) DEFAULT '0' COMMENT '状态，如果是0则是正常可用；如果是1则是过期; 如果是2则是下架。',
  `add_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='广告市场';

DROP TABLE IF EXISTS `litemall_ad_rent`;
CREATE TABLE `litemall_ad_rent` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `target_id` int(11) NOT NULL COMMENT '类型',
  `cost_time_id` int(11) NOT NULL COMMENT '回答内容',
  `type` tinyint(3) NOT NULL DEFAULT '0' COMMENT '类型',
  `content` varchar(100) NOT NULL COMMENT '回答内容',
  `pic_urls` varchar(1023) NOT NULL COMMENT '背景图',
  `link_url` varchar(1023) NOT NULL COMMENT '背景图',
  `position` tinyint(3) NOT NULL COMMENT '回答内容',
  `status` smallint(6) DEFAULT '0' COMMENT '状态，如果是0则是正常可用；如果是1则是过期; 如果是2则是下架。',
  `add_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `target_id` (`target_id`),
  KEY `cost_time_id` (`cost_time_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='广告出租';

DROP TABLE IF EXISTS `litemall_type_obj`;
CREATE TABLE `litemall_type_obj` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL COMMENT '回答内容',
  `is_show_client` tinyint(1) NOT NULL DEFAULT '0' COMMENT '客户端是否显示',
  `add_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='类型常量';
INSERT INTO `litemall_type_obj` VALUES (
'1',
'商品',
'0',
'2019-06-13 22:49:32',
'2019-06-13 22:49:32'
);
INSERT INTO `litemall_type_obj` VALUES (
'2',
'专题',
'0',
'2019-06-13 22:49:32',
'2019-06-13 22:49:32'
);
INSERT INTO `litemall_type_obj` VALUES (
'3',
'用户',
'0',
'2019-06-13 22:49:32',
'2019-06-13 22:49:32'
);
INSERT INTO `litemall_type_obj` VALUES (
'4',
'动态',
'1',
'2019-06-13 22:49:32',
'2019-06-13 22:49:32'
);
INSERT INTO `litemall_type_obj` VALUES (
'5',
'鱼塘',
'1',
'2019-06-13 22:49:32',
'2019-06-13 22:49:32'
);
INSERT INTO `litemall_type_obj` VALUES (
'6',
'问答',
'1',
'2019-06-13 22:49:32',
'2019-06-13 22:49:32'
);
INSERT INTO `litemall_type_obj` VALUES (
'7',
'群组',
'1',
'2019-06-13 22:49:32',
'2019-06-13 22:49:32'
);
INSERT INTO `litemall_type_obj` VALUES (
'8',
'活动',
'1',
'2019-06-13 22:49:32',
'2019-06-13 22:49:32'
);
INSERT INTO `litemall_type_obj` VALUES (
'9',
'品牌',
'0',
'2019-06-13 22:49:32',
'2019-06-13 22:49:32'
);
INSERT INTO `litemall_type_obj` VALUES (
'10',
'订单',
'0',
'2019-06-13 22:49:32',
'2019-06-13 22:49:32'
);
INSERT INTO `litemall_type_obj` VALUES (
'11',
'外链',
'1',
'2019-06-13 22:49:32',
'2019-06-13 22:49:32'
);
-- ////circle/scatter/ad_rent/擦亮-刷新