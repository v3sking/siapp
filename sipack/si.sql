-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.6.11 - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win32
-- HeidiSQL 版本:                  9.2.0.4947
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出  表 jeesns.t_sec_api 结构
CREATE TABLE IF NOT EXISTS `t_sec_api` (
  `api_id` int(50) NOT NULL AUTO_INCREMENT COMMENT 'apiId',
  `api_name` varchar(50) DEFAULT NULL COMMENT 'api名称',
  `api_type` varchar(25) DEFAULT NULL COMMENT 'API类型',
  `api_path` varchar(100) DEFAULT NULL COMMENT 'API路径',
  `status` int(2) DEFAULT NULL COMMENT '状态：0正常，-1失效',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`api_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色api关系表';

-- 正在导出表  jeesns.t_sec_api 的数据：~0 rows (大约)
DELETE FROM `t_sec_api`;
/*!40000 ALTER TABLE `t_sec_api` DISABLE KEYS */;
INSERT INTO `t_sec_api` (`api_id`, `api_name`, `api_type`, `api_path`, `status`, `create_time`) VALUES
	(1, '查询用户', '1', '/secServer/user/get', 0, '2017-11-26 12:06:01'),
	(2, '查询用户列表', '1', '/secServer/user/list', 0, '2017-11-26 18:36:42'),
	(3, '查询附近的人', '2', '/siPay/weibo/friendFeed', 0, '2017-11-26 19:05:53');
/*!40000 ALTER TABLE `t_sec_api` ENABLE KEYS */;


-- 导出  表 jeesns.t_sec_api_menu_rel 结构
CREATE TABLE IF NOT EXISTS `t_sec_api_menu_rel` (
  `rel_id` int(50) NOT NULL AUTO_INCREMENT COMMENT '关系id',
  `api_id` int(50) DEFAULT NULL COMMENT 'APIid',
  `menu_id` int(50) DEFAULT NULL COMMENT '菜单id',
  `status` int(2) DEFAULT NULL COMMENT '状态：0正常，-1失效',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`rel_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='api菜单关系表';

-- 正在导出表  jeesns.t_sec_api_menu_rel 的数据：~0 rows (大约)
DELETE FROM `t_sec_api_menu_rel`;
/*!40000 ALTER TABLE `t_sec_api_menu_rel` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_sec_api_menu_rel` ENABLE KEYS */;


-- 导出  表 jeesns.t_sec_menu 结构
CREATE TABLE IF NOT EXISTS `t_sec_menu` (
  `menu_id` int(50) NOT NULL AUTO_INCREMENT COMMENT '菜单id',
  `menu_name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `parent_id` int(50) DEFAULT NULL COMMENT '父菜单id',
  `menu_path` varchar(400) DEFAULT NULL COMMENT '菜单路径',
  `sort_id` int(50) DEFAULT NULL COMMENT '排序号',
  `status` int(2) DEFAULT NULL COMMENT '状态：0正常，-1失效',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单表';

-- 正在导出表  jeesns.t_sec_menu 的数据：~0 rows (大约)
DELETE FROM `t_sec_menu`;
/*!40000 ALTER TABLE `t_sec_menu` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_sec_menu` ENABLE KEYS */;


-- 导出  表 jeesns.t_sec_role 结构
CREATE TABLE IF NOT EXISTS `t_sec_role` (
  `role_id` int(50) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `role_name` varchar(50) DEFAULT NULL COMMENT '角色名称',
  `role_type` varchar(25) DEFAULT NULL COMMENT '角色类型',
  `status` int(2) DEFAULT NULL COMMENT '状态：0正常，-1失效',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- 正在导出表  jeesns.t_sec_role 的数据：~0 rows (大约)
DELETE FROM `t_sec_role`;
/*!40000 ALTER TABLE `t_sec_role` DISABLE KEYS */;
INSERT INTO `t_sec_role` (`role_id`, `role_name`, `role_type`, `status`, `create_time`) VALUES
	(1, '管理员', '1', 0, '2017-11-26 11:24:08'),
	(2, '学生', '2', 0, '2017-11-26 11:24:18');
/*!40000 ALTER TABLE `t_sec_role` ENABLE KEYS */;


-- 导出  表 jeesns.t_sec_role_api_rel 结构
CREATE TABLE IF NOT EXISTS `t_sec_role_api_rel` (
  `rel_id` int(50) NOT NULL AUTO_INCREMENT COMMENT '关系id',
  `role_id` int(50) DEFAULT NULL COMMENT '角色id',
  `api_id` int(50) DEFAULT NULL COMMENT 'APIid',
  `status` int(2) DEFAULT NULL COMMENT '状态：0正常，-1失效',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`rel_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色api关系表';

-- 正在导出表  jeesns.t_sec_role_api_rel 的数据：~0 rows (大约)
DELETE FROM `t_sec_role_api_rel`;
/*!40000 ALTER TABLE `t_sec_role_api_rel` DISABLE KEYS */;
INSERT INTO `t_sec_role_api_rel` (`rel_id`, `role_id`, `api_id`, `status`, `create_time`) VALUES
	(1, 1, 1, 0, '2017-11-26 12:23:10'),
	(2, 1, 2, 0, '2017-11-26 18:36:57'),
	(3, 1, 3, 0, '2017-11-26 19:05:16');
/*!40000 ALTER TABLE `t_sec_role_api_rel` ENABLE KEYS */;


-- 导出  表 jeesns.t_sec_user 结构
CREATE TABLE IF NOT EXISTS `t_sec_user` (
  `id` int(50) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `nick_name` varchar(50) DEFAULT NULL COMMENT '昵称',
  `password` varchar(32) DEFAULT '' COMMENT '密码',
  `sex` varchar(2) DEFAULT NULL COMMENT '性别',
  `avatar` varchar(4000) DEFAULT NULL COMMENT '头像',
  `vip_type` varchar(15) DEFAULT '' COMMENT '会员类型 0普通，1月会员，2年会员',
  `money` int(11) DEFAULT '0' COMMENT '学币',
  `interest` varchar(255) DEFAULT '' COMMENT '兴趣爱好',
  `add_province` varchar(20) DEFAULT '' COMMENT '省份',
  `add_city` varchar(20) DEFAULT '' COMMENT '城市',
  `add_area` varchar(20) DEFAULT '' COMMENT '地区',
  `add_school` varchar(50) DEFAULT '' COMMENT '学校',
  `email` varchar(50) DEFAULT '' COMMENT '邮箱',
  `phone` varchar(11) DEFAULT '' COMMENT '手机号码',
  `qq` varchar(15) DEFAULT '' COMMENT 'QQ',
  `wechat` varchar(20) DEFAULT '' COMMENT '微信',
  `score` int(11) DEFAULT '0' COMMENT '积分',
  `status` int(2) DEFAULT '0' COMMENT '状态：0正常，1审核中，2审核驳回，-1注销,-2黑名单',
  `reg_ip` varchar(15) DEFAULT '' COMMENT '注册IP',
  `create_time` datetime DEFAULT NULL COMMENT '注册时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `nick_name` (`nick_name`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- 正在导出表  jeesns.t_sec_user 的数据：~2 rows (大约)
DELETE FROM `t_sec_user`;
/*!40000 ALTER TABLE `t_sec_user` DISABLE KEYS */;
INSERT INTO `t_sec_user` (`id`, `nick_name`, `password`, `sex`, `avatar`, `vip_type`, `money`, `interest`, `add_province`, `add_city`, `add_area`, `add_school`, `email`, `phone`, `qq`, `wechat`, `score`, `status`, `reg_ip`, `create_time`, `update_time`) VALUES
	(1, NULL, '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1234555555', NULL, NULL, NULL, NULL, 0, NULL, NULL, NULL),
	(3, NULL, '', NULL, NULL, '', 0, '', '', '', '', '', '123', '', '', '', 0, 0, '', NULL, NULL),
	(4, '3', '3', '1', '1', '0', 0, '', '', '', '', '', '', '', '', '', 0, 0, '', NULL, NULL);
/*!40000 ALTER TABLE `t_sec_user` ENABLE KEYS */;


-- 导出  表 jeesns.t_sec_user_role_rel 结构
CREATE TABLE IF NOT EXISTS `t_sec_user_role_rel` (
  `rel_id` int(50) NOT NULL AUTO_INCREMENT COMMENT '关系id',
  `user_id` int(50) DEFAULT NULL COMMENT '用户id',
  `role_id` int(50) DEFAULT NULL COMMENT '角色id',
  `status` int(2) DEFAULT NULL COMMENT '状态：0正常，-1失效',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`rel_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='用户角色关系表';

-- 正在导出表  jeesns.t_sec_user_role_rel 的数据：~0 rows (大约)
DELETE FROM `t_sec_user_role_rel`;
/*!40000 ALTER TABLE `t_sec_user_role_rel` DISABLE KEYS */;
INSERT INTO `t_sec_user_role_rel` (`rel_id`, `user_id`, `role_id`, `status`, `create_time`) VALUES
	(1, 1, 1, 0, '2017-11-26 11:22:47'),
	(2, 1, 2, 0, '2017-11-26 11:23:46');
/*!40000 ALTER TABLE `t_sec_user_role_rel` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
