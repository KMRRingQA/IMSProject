create database if not exists ims;
create table if not exists ims.customers(id int primary key auto_increment not null, first_name varchar(40), surname varchar(40));
create table if not exists ims.items(id int primary key auto_increment not null, name varchar(40) not null unique, price decimal(7,2) not null, stock int not null);
CREATE TABLE if not exists ims.orders(order_id int primary key auto_increment not null, cust_id int NOT NULL,date timestamp DEFAULT CURRENT_TIMESTAMP,total_price decimal(7,2) NOT NULL, FOREIGN KEY (cust_id) REFERENCES customers (id));
