create database if not exists oj_online;

use oj_online

drop table if exists problems;

create table problems(
    id int primary key auto_increment,
    title varchar(50),
    level varchar(20),
    description varchar(4096),
    templateCode varchar(4096),
    testCode varchar(4096)
);

drop table if exists user;

create table user(
    id int primary key auto_increment,
    username varchar(20) not null,
    password varchar(20) not null
);

