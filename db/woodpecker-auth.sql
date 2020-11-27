-- 新增用户
CREATE USER 'user_center'@'localhost' IDENTIFIED BY 'user_center';
CREATE USER 'user_center'@'%' IDENTIFIED BY 'user_center';
-- 新增数据库
CREATE DATABASE  user_center DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- 授权
GRANT SELECT,INSERT,UPDATE,DELETE ON user_center.* TO 'user_center'@'localhost';
GRANT SELECT,INSERT,UPDATE,DELETE ON user_center.* TO 'user_center'@'%';
-- 生效
FLUSH PRIVILEGES;

-- 新建表
drop table if exists oauth_client_details;
create table oauth_client_details (
      client_id varchar(256) primary key,
      resource_ids varchar(256) default null,
      client_secret varchar(256) default null,
      scope varchar(256) default null,
      authorized_grant_types varchar(256) default null,
      web_server_redirect_uri varchar(256) default null,
      authorities varchar(256) default null,
      access_token_validity int(11) default null,
      refresh_token_validity int(11) default null,
      additional_information varchar(4096) default null,
      auto_approve varchar(256) default null
) comment='终端信息表';
