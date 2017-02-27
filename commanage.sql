create database commanage
default character set utf8
default collate utf8_general_ci;

use commanage;

create table `employees` (
	id int not null auto_increment,
	`name` varchar(50) not null,
    `phone` varchar(15),
    `email` varchar(20),
    primary key(id)
) default charset = utf8;