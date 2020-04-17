package com.qa.ims.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.persistence.dao.DaoOrderLine;
import com.qa.ims.persistence.domain.OrderLine;

@RunWith(MockitoJUnitRunner.class)
public class OrderLineServicesTest {

	@Mock
	private DaoOrderLine<OrderLine> orderLineDao;

	@InjectMocks
	private OrderLineServices orderLineServices;

	@Test
	public void orderLineServicesCalculate() {
		orderLineServices.calculate(1l);
		Mockito.verify(orderLineDao, Mockito.times(1)).calculate(1l);
	}

	@Test
	public void orderLineServicesChangeItems() {
		OrderLine orderLine = new OrderLine(1l, 1l, 1l);
		orderLineServices.changeItems(orderLine);
		Mockito.verify(orderLineDao, Mockito.times(1)).changeItems(orderLine);
	}

	@Test
	public void orderLineServicesReadOrder() {
		orderLineServices.readOrder(1l);
		Mockito.verify(orderLineDao, Mockito.times(1)).readOrder(1l);
	}
}
