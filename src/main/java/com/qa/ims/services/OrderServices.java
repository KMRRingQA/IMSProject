package com.qa.ims.services;

import java.util.List;

import com.qa.ims.persistence.dao.DaoCRUD;
import com.qa.ims.persistence.domain.Order;

public class OrderServices implements CrudServices<Order> {

	private DaoCRUD<Order> orderDao;

	public OrderServices(DaoCRUD<Order> orderDao) {
		this.orderDao = orderDao;
	}

	@Override
	public Order create(Order order) {
		return orderDao.create(order);
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
	public Order update(Order order) {
		return orderDao.update(order);
	}

	@Override
	public List<Order> searchName(String name) {
		return orderDao.searchName(name);
	}
}
