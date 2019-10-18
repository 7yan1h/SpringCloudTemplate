CREATE TABLE `oauth2_client_details`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `client_id` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '客户端id',
  `resource_ids` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '资源id，使用,分割',
  `client_secret` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '客户端密码',
  `scope` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '作用域，使用,分割',
  `authorized_grant_types` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '授权类型，使用,分割',
  `web_server_redirect_uri` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '授权后，重定向url，一般是客户端登录url',
  `authorities` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '客户端权限，使用,分割',
  `access_token_validity` int(11) NULL DEFAULT NULL COMMENT 'access token有效期',
  `refresh_token_validity` int(11) NULL DEFAULT NULL COMMENT 'refresh token有效期',
  `additional_information` varchar(4096) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '额外信息',
  `autoapprove` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '自动同意授权的scope，使用,分割',
  `create_id` bigint(20) UNSIGNED NOT NULL COMMENT '创建人id',
  `create_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '创建人姓名',
  `create_dt` datetime(0) NOT NULL COMMENT '创建时间',
  `update_id` bigint(20) UNSIGNED NULL DEFAULT NULL COMMENT '修改人id',
  `update_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '修改人姓名',
  `update_dt` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `client_id`(`client_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of oauth2_client_details
-- ----------------------------
INSERT INTO `oauth2_client_details` VALUES (1, 'hx-client', 'id1,id2', '$2a$10$SWnBBm.eWhc3wKxycu7j5uD9PwErS3fg4PpuOd2yqK8GL.4/3r7gu', 'app,web', 'authorization_code,refresh_token', 'http://localhost:8080/login', 'CLIENT_ADD,CLIENT_DELETE', 1800, 604800, NULL, '', 1, 'robot', '2019-10-13 23:51:14', NULL, NULL, NULL);
