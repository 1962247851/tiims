# Host: 47.100.91.123  (Version 5.7.26-log)
# Date: 2020-07-20 10:02:03
# Generator: MySQL-Front 6.1  (Build 1.26)


#
# Structure for table "sys_permission"
#

DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` bigint(20) NOT NULL,
  `code` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_2vm98en2ouht0v15fvef2whp4` (`code`),
  UNIQUE KEY `UK_awgpnmh0eukj0uq98pcp26gdv` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

#
# Data for table "sys_permission"
#


#
# Structure for table "sys_permission_seq"
#

DROP TABLE IF EXISTS `sys_permission_seq`;
CREATE TABLE `sys_permission_seq` (
  `sequence_name` varchar(255) NOT NULL,
  `sequence_next_hi_value` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`sequence_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

#
# Data for table "sys_permission_seq"
#

INSERT INTO `sys_permission_seq` VALUES ('sys_permission_id',0);

#
# Structure for table "sys_request_path"
#

DROP TABLE IF EXISTS `sys_request_path`;
CREATE TABLE `sys_request_path` (
  `id` bigint(20) NOT NULL,
  `description` varchar(255) NOT NULL,
  `url` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_d6unig0l3wce7jx4sr1bvt3qh` (`url`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

#
# Data for table "sys_request_path"
#


#
# Structure for table "sys_request_path_permission_relation"
#

DROP TABLE IF EXISTS `sys_request_path_permission_relation`;
CREATE TABLE `sys_request_path_permission_relation` (
  `id` bigint(20) NOT NULL,
  `permission_id` bigint(20) DEFAULT NULL,
  `request_path_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

#
# Data for table "sys_request_path_permission_relation"
#


#
# Structure for table "sys_request_path_permission_relation_seq"
#

DROP TABLE IF EXISTS `sys_request_path_permission_relation_seq`;
CREATE TABLE `sys_request_path_permission_relation_seq` (
  `sequence_name` varchar(255) NOT NULL,
  `sequence_next_hi_value` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`sequence_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

#
# Data for table "sys_request_path_permission_relation_seq"
#

INSERT INTO `sys_request_path_permission_relation_seq` VALUES ('sys_request_path_permission_relation_id',0);

#
# Structure for table "sys_request_path_seq"
#

DROP TABLE IF EXISTS `sys_request_path_seq`;
CREATE TABLE `sys_request_path_seq` (
  `sequence_name` varchar(255) NOT NULL,
  `sequence_next_hi_value` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`sequence_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

#
# Data for table "sys_request_path_seq"
#

INSERT INTO `sys_request_path_seq` VALUES ('sys_request_path_id',0);

#
# Structure for table "sys_role"
#

DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL,
  `code` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_plpigyqwsqfn7mn66npgf9ftp` (`code`),
  UNIQUE KEY `UK_bqy406dtsr7j7d6fawi1ckyn1` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

#
# Data for table "sys_role"
#


#
# Structure for table "sys_role_permission_relation"
#

DROP TABLE IF EXISTS `sys_role_permission_relation`;
CREATE TABLE `sys_role_permission_relation` (
  `id` bigint(20) NOT NULL,
  `permission_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

#
# Data for table "sys_role_permission_relation"
#


#
# Structure for table "sys_role_permission_relation_seq"
#

DROP TABLE IF EXISTS `sys_role_permission_relation_seq`;
CREATE TABLE `sys_role_permission_relation_seq` (
  `sequence_name` varchar(255) NOT NULL,
  `sequence_next_hi_value` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`sequence_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

#
# Data for table "sys_role_permission_relation_seq"
#

INSERT INTO `sys_role_permission_relation_seq` VALUES ('sys_role_permission_relation_id',0);

#
# Structure for table "sys_role_seq"
#

DROP TABLE IF EXISTS `sys_role_seq`;
CREATE TABLE `sys_role_seq` (
  `sequence_name` varchar(255) NOT NULL,
  `sequence_next_hi_value` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`sequence_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

#
# Data for table "sys_role_seq"
#

INSERT INTO `sys_role_seq` VALUES ('sys_role_id',0);

#
# Structure for table "sys_user"
#

DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL,
  `birthday` datetime(6) NOT NULL,
  `create_time` datetime(6) NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  `enabled` bit(1) NOT NULL DEFAULT b'1',
  `last_login_time` datetime(6) DEFAULT NULL,
  `not_expired` bit(1) NOT NULL DEFAULT b'1',
  `not_locked` bit(1) NOT NULL DEFAULT b'1',
  `password` varchar(64) NOT NULL,
  `password_not_expired` bit(1) NOT NULL DEFAULT b'1',
  `phone` varchar(11) DEFAULT NULL,
  `self_introduction` varchar(255) DEFAULT NULL,
  `sex` bit(1) NOT NULL DEFAULT b'1',
  `update_time` datetime(6) DEFAULT NULL,
  `user_name` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_cb0fsvip6qow952a07et2k9xv` (`user_name`),
  UNIQUE KEY `UK_ahtq5ew3v0kt1n7hf1sgp7p8l` (`email`),
  UNIQUE KEY `UK_pulp17fvich5aby4m0kc820h6` (`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

#
# Data for table "sys_user"
#

INSERT INTO `sys_user` VALUES (1,X'323030302D31312D30322030303A30303A30302E303030303030',X'323032302D30352D32322030393A32353A32352E343531303030',NULL,b'1',X'323032302D30372D31392032313A34323A34362E383936303030',b'1',b'1','$2a$10$C98cG1QQBlw8pzhSLq5PaeRddxjIH1ZUaZxCJecGxIj3fyKqQzki.',b'1',NULL,NULL,b'1',X'323032302D30372D31392032313A34323A34362E383936303030','苗锦洲'),(2,X'323032302D30352D32322030303A30303A30302E303030303030',X'323032302D30352D32322030393A34323A30342E363331303030',NULL,b'1',X'323032302D30362D32392032333A33373A31322E383736303030',b'1',b'1','$2a$10$C98cG1QQBlw8pzhSLq5PaeRddxjIH1ZUaZxCJecGxIj3fyKqQzki.',b'1',NULL,NULL,b'1',X'323032302D30362D32392032333A33373A31322E383736303030','高仁浩'),(3,X'323030302D31312D30322030303A30303A30302E303030303030',X'323032302D30352D32322030393A32353A32352E343531303030',NULL,b'1',X'323032302D30372D31392032323A32333A31312E313537303030',b'1',b'1','$2a$10$C98cG1QQBlw8pzhSLq5PaeRddxjIH1ZUaZxCJecGxIj3fyKqQzki.',b'1',NULL,NULL,b'1',X'323032302D30372D31392032323A32333A31312E313537303030','黄敬文'),(4,X'323032302D30352D32322030303A30303A30302E303030303030',X'323032302D30352D32322030393A34323A30342E363331303030',NULL,b'1',X'323032302D30372D31382031343A34353A34322E383633303030',b'1',b'1','$2a$10$C98cG1QQBlw8pzhSLq5PaeRddxjIH1ZUaZxCJecGxIj3fyKqQzki.',b'1',NULL,NULL,b'1',X'323032302D30372D31382031343A34353A34322E383633303030','吴德鑫');

#
# Structure for table "sys_user_role_relation"
#

DROP TABLE IF EXISTS `sys_user_role_relation`;
CREATE TABLE `sys_user_role_relation` (
  `id` bigint(20) NOT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

#
# Data for table "sys_user_role_relation"
#


#
# Structure for table "sys_user_role_relation_seq"
#

DROP TABLE IF EXISTS `sys_user_role_relation_seq`;
CREATE TABLE `sys_user_role_relation_seq` (
  `sequence_name` varchar(255) NOT NULL,
  `sequence_next_hi_value` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`sequence_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

#
# Data for table "sys_user_role_relation_seq"
#

INSERT INTO `sys_user_role_relation_seq` VALUES ('sys_user_role_relation_id',0);

#
# Structure for table "sys_user_seq"
#

DROP TABLE IF EXISTS `sys_user_seq`;
CREATE TABLE `sys_user_seq` (
  `sequence_name` varchar(255) NOT NULL,
  `sequence_next_hi_value` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`sequence_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

#
# Data for table "sys_user_seq"
#

INSERT INTO `sys_user_seq` VALUES ('sys_user_id',0);

#
# Structure for table "ti_class"
#

DROP TABLE IF EXISTS `ti_class`;
CREATE TABLE `ti_class` (
  `id` bigint(20) NOT NULL,
  `cost` float DEFAULT NULL,
  `lessons` int(11) DEFAULT NULL,
  `nature` varchar(50) DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

#
# Data for table "ti_class"
#

INSERT INTO `ti_class` VALUES (1,1600,5,'八升九',45),(2,1200,2,'七升八',20),(7,1600,5,'八升九',45);

#
# Structure for table "ti_class_seq"
#

DROP TABLE IF EXISTS `ti_class_seq`;
CREATE TABLE `ti_class_seq` (
  `sequence_name` varchar(255) NOT NULL,
  `sequence_next_hi_value` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`sequence_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

#
# Data for table "ti_class_seq"
#

INSERT INTO `ti_class_seq` VALUES ('ti_class_id',7);

#
# Structure for table "ti_cs_relation"
#

DROP TABLE IF EXISTS `ti_cs_relation`;
CREATE TABLE `ti_cs_relation` (
  `id` bigint(20) NOT NULL,
  `class_id` bigint(20) DEFAULT NULL,
  `student_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

#
# Data for table "ti_cs_relation"
#

INSERT INTO `ti_cs_relation` VALUES (2,1,3),(3,1,1);

#
# Structure for table "ti_cs_relation_seq"
#

DROP TABLE IF EXISTS `ti_cs_relation_seq`;
CREATE TABLE `ti_cs_relation_seq` (
  `sequence_name` varchar(255) NOT NULL,
  `sequence_next_hi_value` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`sequence_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

#
# Data for table "ti_cs_relation_seq"
#

INSERT INTO `ti_cs_relation_seq` VALUES ('ti_cs_relation_id',2);

#
# Structure for table "ti_ct_relation"
#

DROP TABLE IF EXISTS `ti_ct_relation`;
CREATE TABLE `ti_ct_relation` (
  `id` bigint(20) NOT NULL,
  `class_id` bigint(20) DEFAULT NULL,
  `teacher_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

#
# Data for table "ti_ct_relation"
#

INSERT INTO `ti_ct_relation` VALUES (1,1,4),(2,1,5),(4,1,7);

#
# Structure for table "ti_ct_relation_seq"
#

DROP TABLE IF EXISTS `ti_ct_relation_seq`;
CREATE TABLE `ti_ct_relation_seq` (
  `sequence_name` varchar(255) NOT NULL,
  `sequence_next_hi_value` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`sequence_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

#
# Data for table "ti_ct_relation_seq"
#

INSERT INTO `ti_ct_relation_seq` VALUES ('ti_ct_relation_id',4);

#
# Structure for table "ti_student"
#

DROP TABLE IF EXISTS `ti_student`;
CREATE TABLE `ti_student` (
  `id` bigint(20) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `phone` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

#
# Data for table "ti_student"
#

INSERT INTO `ti_student` VALUES (1,'学生1','1223444888'),(2,'学生2','12345678911'),(3,'学生3','15364897204'),(4,'学生4','16482349756'),(5,'学生5','15264259876');

#
# Structure for table "ti_student_seq"
#

DROP TABLE IF EXISTS `ti_student_seq`;
CREATE TABLE `ti_student_seq` (
  `sequence_name` varchar(255) NOT NULL,
  `sequence_next_hi_value` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`sequence_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

#
# Data for table "ti_student_seq"
#

INSERT INTO `ti_student_seq` VALUES ('ti_student_id',6);

#
# Structure for table "ti_teacher"
#

DROP TABLE IF EXISTS `ti_teacher`;
CREATE TABLE `ti_teacher` (
  `id` bigint(20) NOT NULL,
  `degree` varchar(2) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(50) DEFAULT NULL,
  `salary` float DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

#
# Data for table "ti_teacher"
#

INSERT INTO `ti_teacher` VALUES (4,'0','老师4','66662',6500),(5,'1','助教1','12345678910',112),(6,'2','老师1','221122',5500),(7,'2','老师2','113355',5000),(8,'2','老师3','156426',4500);

#
# Structure for table "ti_teacher_seq"
#

DROP TABLE IF EXISTS `ti_teacher_seq`;
CREATE TABLE `ti_teacher_seq` (
  `sequence_name` varchar(255) NOT NULL,
  `sequence_next_hi_value` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`sequence_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

#
# Data for table "ti_teacher_seq"
#

INSERT INTO `ti_teacher_seq` VALUES ('ti_teacher_id',9);

#
# Structure for table "ti_work"
#

DROP TABLE IF EXISTS `ti_work`;
CREATE TABLE `ti_work` (
  `id` bigint(20) NOT NULL,
  `class_id` bigint(20) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  `teacher_id` bigint(20) DEFAULT NULL,
  `update_timestamp` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

#
# Data for table "ti_work"
#

INSERT INTO `ti_work` VALUES (1,1,'2020-07-16',30,5,X'323032302D30372D31382031393A31373A30312E313737303030'),(2,1,'2020-07-17',45,5,X'323032302D30372D31392032323A31353A31352E353233303030'),(3,1,'2020-07-18',25,5,X'323032302D30372D31382031393A32343A33392E333231303030');

#
# Structure for table "ti_work_seq"
#

DROP TABLE IF EXISTS `ti_work_seq`;
CREATE TABLE `ti_work_seq` (
  `sequence_name` varchar(255) NOT NULL,
  `sequence_next_hi_value` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`sequence_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

#
# Data for table "ti_work_seq"
#

INSERT INTO `ti_work_seq` VALUES ('ti_work_id',3);
