create database if not exists ims;
drop table if exists ims.customers;
create table ims.customers(id int primary key auto_increment, first_name varchar(40), surname varchar(40));
