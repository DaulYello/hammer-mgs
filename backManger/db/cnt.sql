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
/*created by huangshuang on 2018-10-26*/
ALTER TABLE `temp`.`fm_cnt_pool` CHANGE `create_date` `create_date` TIMESTAMP DEFAULT '0000-00-00 00:00:00' NULL COMMENT '生成时间', CHANGE `update_date` `update_date`
TIMESTAMP DEFAULT '0000-00-00 00:00:00' NULL COMMENT '回收时间';
/*created by huangshuang on 2018-10-26*/
ALTER TABLE `fm_assets_poundage`
  CHANGE `release_rate` `release_rate` INT (11) DEFAULT 0 NULL COMMENT '释放比例',
  CHANGE `create_date` `create_date` TIMESTAMP DEFAULT '0000-00-00 00:00:00' NULL COMMENT '数据记录时间',
  CHANGE `update_date` `update_date` TIMESTAMP DEFAULT '0000-00-00 00:00:00' NULL COMMENT '更新时间';
/*created by huangshuang on 2018-10-30*/
ALTER TABLE `temp`.`fm_assets_poundage` ADD COLUMN `uid` INT(11) NULL COMMENT '释放剩余的CNT沉淀的用户id' AFTER `aid`;