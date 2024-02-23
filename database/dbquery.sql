drop database if exists hotel;

create database hotel;
use hotel;
create table empployee (
    id int primary key auto_increment,
    name varchar(30),
    gender boolean,
    birthdate date,
    salary real


);