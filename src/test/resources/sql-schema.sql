create database ims;
create table ims.customers(id int primary key auto_increment not null, first_name varchar(40), surname varchar(40));
create table ims.items(id int primary key auto_increment not null, name varchar(40) not null unique, price decimal(7,2) not null, stock int not null);
CREATE TABLE ims.orders(order_id int primary key auto_increment not null, cust_id int NOT NULL,date timestamp DEFAULT CURRENT_TIMESTAMP,total_price decimal(7,2) NOT NULL, FOREIGN KEY (cust_id) REFERENCES customers (id) on delete cascade);
CREATE TABLE ims.orderLine(order_id int not null, item_id int not null,quantity int default 1,FOREIGN KEY (order_id) REFERENCES orders (order_id) on delete cascade,FOREIGN KEY (item_id) REFERENCES items (id) on delete cascade);
