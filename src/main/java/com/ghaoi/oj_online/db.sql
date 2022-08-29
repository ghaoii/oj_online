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