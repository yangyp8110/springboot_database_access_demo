     /** create user table */
     DROP TABLE IF EXISTS `user`;
     CREATE TABLE `user` (
     `id` bigint unsign NOT NULL AUTO_INCREMENT COMMENT '主键',
     `user_name` varchar(20) DEFAULT NULL COMMENT '姓名',
     `age` int unsign DEFAULT NULL COMMENT '年龄',
     `inserttime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '插入时间',
     `updatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
     PRIMARY KEY (`id`)
     ) ENGINE=InnoDB DEFAULT CHARSET = utf8mb4 COMMENT = '用户表';

    /** create user_details table */
     DROP TABLE IF EXISTS `user_details`;
     CREATE TABLE `user_details` (
     `id` bigint unsign NOT NULL AUTO_INCREMENT COMMENT '主键',
     `u_id` bigint unsign DEFAULT NULL COMMENT '用户id',
     `nick_name` varchar(255) DEFAULT NULL COMMENT '用户昵称',
     `user_desc` varchar(255) DEFAULT NULL COMMENT '用户描述',
     `inserttime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '新增时间',
     `updatetime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
     PRIMARY KEY (`id`),
     FULLTEXT KEY `UserDetail_FullIndex` (`user_desc`)
     ) ENGINE=InnoDB DEFAULT CHARSET = utf8mb4 COMMENT = '用户详细表';