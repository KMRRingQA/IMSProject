package com.qa.ims.services;

import java.math.BigDecimal;
import java.util.List;

import com.qa.ims.Ims;
import com.qa.ims.persistence.dao.OrderLineDaoMysql;
import com.qa.ims.persistence.domain.OrderLine;

public class OrderLineServices {

	private OrderLineDaoMysql orderLineDao = new OrderLineDaoMysql(Ims.getUsername(), Ims.getPassword());

	public OrderLineServices() {
		// TODO Auto-generated constructor stub
	}

	public OrderLineServices(OrderLineDaoMysql orderLineDaoMysql) {
		this.orderLineDao = orderLineDaoMysql;
	}

	public BigDecimal calculate(Long order_id) {
		return orderLineDao.calculate(order_id);
	}

	public OrderLine changeItems(OrderLine orderLine) {
		return orderLineDao.changeItems(orderLine);
	}

	public List<OrderLine> readOrder(Long order_id) {
		return orderLineDao.readOrder(order_id);
	}

}
