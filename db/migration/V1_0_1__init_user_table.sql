SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;
-- ----------------------------
--  Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `auth_key` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `password_hash` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `password_reset_token` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `status` smallint(6) unsigned NOT NULL DEFAULT '10',
  `created_at` int(11) unsigned NOT NULL,
  `updated_at` int(11) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `password_reset_token` (`password_reset_token`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=COMPACT  COMMENT '用户表';

SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO `user` VALUES (1, 'testuser1', 'auth_key1', 'password_hash1', 'password_reset_token1', 'michael@openmore.org', 10, 0, 0);
INSERT INTO `user` VALUES (2, 'testuser2', 'auth_key2', 'password_hash2', 'password_reset_token2', 'aa@openmore.org', 10, 0, 0);
INSERT INTO `user` VALUES (3, 'testuser3', 'auth_key3', 'password_hash3', 'password_reset_token3', 'bb@openmore.org', 10, 0, 0);
INSERT INTO `user` VALUES (4, 'testuser4', 'auth_key4', 'password_hash4', 'password_reset_token4', 'cc@openmore.org', 10, 0, 0);
INSERT INTO `user` VALUES (5, 'testuser5', 'auth_key5', 'password_hash5', 'password_reset_token5', 'dd@openmore.org', 10, 0, 0);
INSERT INTO `user` VALUES (6, 'testuser6', 'auth_key6', 'password_hash6', 'password_reset_token6', 'ee@openmore.org', 10, 0, 0);


