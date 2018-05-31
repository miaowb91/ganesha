#建表和初始化数据语句

CREATE TABLE `ganesha_asset` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `asset_code` varchar(255) NOT NULL DEFAULT '' COMMENT '资产ID',
  `asset_name` varchar(50) NOT NULL DEFAULT '' COMMENT '资产名称',
  `asset_unit` varchar(50) NOT NULL DEFAULT '' COMMENT '资产单位',
  `asset_unit_code` tinyint(4) NOT NULL DEFAULT '0' COMMENT '资产单位代码',
  `asset_amount` int(11) NOT NULL DEFAULT '0' COMMENT '资产总额',
  `asset_type` tinyint(4) DEFAULT '' COMMENT '资产类型',
  `expires` int(11) DEFAULT '-1' COMMENT '资产过期时间（秒），-1：永不过期',
  `description` varchar(255) NOT NULL DEFAULT '' COMMENT '资产描述',
  `m_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `c_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_asset_code` (`asset_code`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='资产表';

CREATE TABLE `ganesha_account_asset` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `account` varchar(255) NOT NULL DEFAULT '' COMMENT '账户',
  `asset_code` varchar(255) NOT NULL DEFAULT '' COMMENT '资产ID',
  `asset_name` varchar(50) NOT NULL DEFAULT '' COMMENT '资产名称',
  `asset_unit` varchar(50) NOT NULL DEFAULT '' COMMENT '资产单位',
  `asset_unit_code` tinyint(4) NOT NULL DEFAULT '0' COMMENT '资产单位代码',
  `asset_amount` int(11) NOT NULL DEFAULT '0' COMMENT '资产总额',
  `asset_type` tinyint(4) DEFAULT '' COMMENT '资产类型',
  `expires` int(11) DEFAULT '-1' COMMENT '资产过期时间（秒），-1：永不过期',
  `m_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `c_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_asset_code` (`asset_code`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='账户资产表';

CREATE TABLE `ganesha_asset_trans`(
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `trans_no` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '转账流水',
  `from_account` varchar(255) NOT NULL DEFAULT '' COMMENT '转出账户',
  `to_account` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '转入账户',
  `asset_code` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '资产ID',
  `trans_amount` INT(11) NOT NULL DEFAULT 0 COMMENT '转出额',
  `status` TINYINT(4) NOT NULL DEFAULT 0 COMMENT '0：冻结，1：成功，2：失败',
  `m_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `c_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_trans_no`(`trans_no`),
  KEY `index_from_account` (`from_account`),
  KEY `index_to_account` (`to_account`)
) ENGINE = InnoDB AUTO_INCREMENT=1 DEFAULT CHAR SET =utf8mb4 COMMENT = '资产转账记录表';
