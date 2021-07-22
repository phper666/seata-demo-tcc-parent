DROP TABLE IF EXISTS `account`;
CREATE TABLE `account`
(
    `id`            bigInt NOT NULL AUTO_INCREMENT,
    `user_id`       varchar(255) DEFAULT NULL,
    `freeze_amount` double(14, 2
) unsigned DEFAULT '0.00',
   `amount` double(14,2) unsigned DEFAULT '0.00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

INSERT INTO `account`
VALUES ('1', '1', '0.00', '4000.00');

DROP TABLE IF EXISTS undo_log;
CREATE TABLE `undo_log`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT,
    `branch_id`     bigint(20) NOT NULL,
    `xid`           varchar(100) NOT NULL,
    `context`       varchar(128) NOT NULL,
    `rollback_info` longblob     NOT NULL,
    `log_status`    int(11) NOT NULL,
    `log_created`   datetime     NOT NULL,
    `log_modified`  datetime     NOT NULL,
    `ext`           varchar(100) DEFAULT NULL,
    `deleted`       int(11) default '0',
    PRIMARY KEY (`id`),
    UNIQUE KEY `ux_undo_log` (`xid`,`branch_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;