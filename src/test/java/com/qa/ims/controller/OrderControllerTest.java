package com.qa.ims.controller;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.persistence.domain.Order;
import com.qa.ims.services.OrderServices;

@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest {

	/**
	 * The thing I want to fake functionlity for
	 */
	@Mock
	private OrderServices orderServices;

	/**
	 * Spy is used because i want to mock some methods inside the order I'm testing
	 * InjectMocks uses dependency injection to insert the mock into the order
	 * controller
	 */
	@Spy
	@InjectMocks
	private OrderController orderController;

	@Test
	public void readAllTest() {
		OrderController orderController = new OrderController(orderServices);
		List<Order> orders = new ArrayList<>();
		orders.add(new Order(1L, 1L, "2020-4-1", BigDecimal.valueOf(14.99)));
		orders.add(new Order(2L, 1L, "2021-4-19", BigDecimal.valueOf(2.49)));
		orders.add(new Order(3L, 1L, "2020-4-18", BigDecimal.valueOf(299.49)));
		Mockito.when(orderServices.readAll()).thenReturn(orders);
		assertEquals(orders, orderController.readAll());
	}

	@Test
	public void createTest() {
		String custId = "1";
		String date = "2020-4-1";

		Mockito.doReturn(custId, date).when(orderController).getInput();
		Long custIdConverted = Long.valueOf(custId);
		Order order = new Order(custIdConverted, date, BigDecimal.valueOf(0));
		Order savedOrder = new Order(1L, "2020-4-1", BigDecimal.valueOf(0));
		Mockito.when(orderServices.create(order)).thenReturn(savedOrder);
		assertEquals(savedOrder, orderController.create());
	}

	/**
	 * 
	 */
	@Test
	public void updateTest() {
		String orderId = "2";
		String custId = "2";
		String date = "2020-4-1";
		String totalPrice = "1.49";

		Mockito.doReturn(orderId, custId, date, totalPrice).when(orderController).getInput();
		Long orderIdConverted = Long.parseLong(orderId);
		Long custIdConverted = Long.parseLong(custId);
		BigDecimal totalPriceConverted = BigDecimal.valueOf(Double.parseDouble(totalPrice));
		Order order = new Order(orderIdConverted, custIdConverted, date, totalPriceConverted);
		Mockito.when(orderServices.update(order)).thenReturn(order);
		assertEquals(orderController.update(), order);
	}

	/**
	 * Delete doesn't return anything, so we can just verify that it calls the
	 * delete method
	 */
	@Test
	public void deleteTest() {
		String id = "1";
		Mockito.doReturn(id).when(orderController).getInput();
		orderController.delete();
		Mockito.verify(orderServices, Mockito.times(1)).delete(1L);
	}

}
