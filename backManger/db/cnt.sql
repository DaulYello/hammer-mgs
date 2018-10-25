/*created by huangshuang on 2018-10-25*/
CREATE TABLE `fm_assets_poundage` (
  `id` INT (11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `aid` INT (11) COMMENT '活动id',
  `total_assets` DOUBLE (18, 2) COMMENT '活动溢价后的总资产',
  `poundage` DOUBLE (18, 2) COMMENT '手续费',
  `release_rate` DOUBLE (18, 2) COMMENT '释放比例',
  `create_date` DATE COMMENT '数据记录时间',
  `update_date` DATE COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE = INNODB CHARSET = utf8 COLLATE = utf8_general_ci;