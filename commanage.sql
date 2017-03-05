create database commanage
default character set utf8
default collate utf8_general_ci;

use commanage;

create table `employees` (
	`id` int not null auto_increment,
	`name` varchar(50) not null,
    `function` varchar(20),
    `phone` varchar(15),
    `email` varchar(40),
    primary key(id)
) default charset = utf8;

create table `products` (
	`id` int not null auto_increment,
	`name` varchar(20) not null,
    `purchasePrice` double not null,
    `salePrice` double not null,
    primary key(id)
) default charset = utf8;

create table `purchases` (
	`id` int not null auto_increment,
    `productId` int,
    `quantity` int,
	primary key(id)
) default charset = utf8;

create table `sales` (
	`id` int not null auto_increment,
    `productId` int,
    `quantity` int,
	primary key(id)
) default charset = utf8;

create table `stock` (
	`productId` int,
    `quantity` int,
    primary key(`productId`)
) default charset = utf8;