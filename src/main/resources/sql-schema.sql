create database if not exists ims;
create table if not exists ims.customers(id int primary key auto_increment, first_name varchar(40), surname varchar(40));
create table if not exists ims.items(id int primary key auto_increment not null, name varchar(40) not null unique, price decimal(7,2) not null, stock int not null);