drop database if exists myuser;
create database myuser;
use myuser;
create table user(
id int primary key auto_increment,
name varchar(20) not null,
password varchar(30) not null,
age int ,
sex char(2) default '男'
);