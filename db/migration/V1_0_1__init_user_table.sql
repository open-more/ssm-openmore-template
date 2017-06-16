CREATE TABLE `user` (
  `id`                    int(11) NOT NULL AUTO_INCREMENT                     COMMENT '用户id,自增长',
  `name`                  char(32) DEFAULT NULL                               COMMENT '用户名',
  `password`              char(64) DEFAULT NULL                               COMMENT '密码',
 CONSTRAINT PRIMARY KEY (`id`)
) COMMENT '用户表';

INSERT INTO `user` VALUES (1, 'testuser1', 'password1');
INSERT INTO `user` VALUES (2, 'testuser2', 'password2');
INSERT INTO `user` VALUES (3, 'testuser3', 'password3');
INSERT INTO `user` VALUES (4, 'testuser4', 'password4');
INSERT INTO `user` VALUES (5, 'testuser5', 'password5');


