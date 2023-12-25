ALTER TABLE `chart` ADD COLUMN `schema_name` varchar(128) NULL COMMENT 'schemaName';
## 密码为chat2db的加密字符串
UPDATE `dbhub_user`
SET password = '$2a$10$XRjuCZ1dRpY16axTHWnjV.ye1gXVCqN2m8TMCMy0izAsmxGzd9lR6',
    email    = 'chat2db@chat2db.com'
WHERE id = 2
  and password = 'chat2db';