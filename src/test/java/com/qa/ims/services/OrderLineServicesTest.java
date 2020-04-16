package com.qa.ims.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.persistence.dao.Dao;
import com.qa.ims.persistence.domain.OrderLine;

@RunWith(MockitoJUnitRunner.class)
public class OrderLineServicesTest {

	@Mock
	private Dao<OrderLine> orderLineDao;

	@InjectMocks
	private OrderLineServices orderLineServices;

	@Test
	public void orderLineServicesCreate() {
		OrderLine orderLine = new OrderLine(1l);
		orderLineServices.calculate(orderLine);
		Mockito.verify(orderLineDao, Mockito.times(1)).calculate(orderLine);
	}

	@Test
	public void orderLineServicesRead() {
		orderLineServices.readAll();
		Mockito.verify(orderLineDao, Mockito.times(1)).readAll();
	}

	@Test
	public void orderLineServicesUpdate() {
		OrderLine orderLine = new OrderLine("chris", "perrins");
		orderLineServices.update(orderLine);
		Mockito.verify(orderLineDao, Mockito.times(1)).update(orderLine);
	}

	@Test
	public void orderLineServicesDelete() {
		orderLineServices.delete(1L);
		;
		Mockito.verify(orderLineDao, Mockito.times(1)).delete(1L);
	}
}
