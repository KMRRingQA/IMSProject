package com.qa.ims.services;

import java.math.BigDecimal;
import java.util.List;

import com.qa.ims.persistence.dao.DaoOrderLine;
import com.qa.ims.persistence.domain.OrderLine;

public class OrderLineServices implements OLServices<OrderLine> {

	private DaoOrderLine<OrderLine> orderLineDao;

	public OrderLineServices(DaoOrderLine<OrderLine> orderLineDao) {
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
