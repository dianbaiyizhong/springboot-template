DROP TABLE IF EXISTS `t_device_info`;
CREATE TABLE `t_device_info`  (
                                  `device_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
                                  `device_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                                  `device_ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
                                  `gmt_create` datetime(6) NULL DEFAULT NULL,
                                  PRIMARY KEY (`device_id`) USING BTREE
)