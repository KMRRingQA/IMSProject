package com.qa.ims.services;

import java.util.List;

import com.mysql.cj.x.protobuf.MysqlxCrud.Order;
import com.qa.ims.persistence.dao.Dao;

public class OrderServices implements CrudServices<Order> {

	private Dao<Order> orderDao;

	public OrderServices(Dao<Order> customerDao) {
		this.orderDao = customerDao;
	}

	@Override
	public Order create(Order customer) {
		return orderDao.create(customer);
	}

	@Override
	public void delete(Long id) {
		orderDao.delete(id);
	}

	@Override
	public List<Order> readAll() {
		return orderDao.readAll();
	}

	@Override
	public Order update(Order customer) {
		return orderDao.update(customer);
	}

}
