package com.qa.ims.services;

import java.math.BigDecimal;
import java.util.List;

import com.qa.ims.persistence.dao.DaoLine;
import com.qa.ims.persistence.domain.OrderLine;

public class OrderLineServices implements LineServices<OrderLine> {

	private DaoLine<OrderLine> orderLineDao;

	public OrderLineServices(DaoLine<OrderLine> orderLineDao) {
		this.orderLineDao = orderLineDao;
	}

	@Override
	public BigDecimal calculate(Long orderId) {
		return orderLineDao.calculate(orderId);
	}

	@Override
	public OrderLine changeItems(OrderLine orderLine) {
		return orderLineDao.changeItems(orderLine);
	}

	@Override
	public List<OrderLine> readOrder(Long orderId) {
		return orderLineDao.readOrder(orderId);
	}

}
