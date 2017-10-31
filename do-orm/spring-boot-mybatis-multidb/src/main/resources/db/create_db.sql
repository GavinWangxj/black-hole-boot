CREATE DATABASE `mybatis-jta1` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
CREATE DATABASE `mybatis-jta2` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

USE `mybatis-jta1`;
create table Main
(
  id int auto_increment
    primary key,
  name varchar(50) not null,
  count int not null
)ENGINE = INNODB DEFAULT CHARSET = utf8;


USE `mybatis-jta2`;
create table Secondary
(
  id int auto_increment
    primary key,
  name varchar(50) not null,
  count int not null
)ENGINE = INNODB DEFAULT CHARSET = utf8;
