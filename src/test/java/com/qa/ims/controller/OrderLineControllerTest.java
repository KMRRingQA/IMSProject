package com.qa.ims.controller;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.persistence.domain.OrderLine;
import com.qa.ims.services.OrderLineServices;

@RunWith(MockitoJUnitRunner.class)
public class OrderLineControllerTest {

	@Mock
	private OrderLineServices orderLineServices;

	@Spy
	@InjectMocks
	private OrderLineController orderLineController;

	@Before
	public void init() {

	}

	@Test
	public void changeItemsTestAdd() {
		String action = "add";
		String orderId = "1";
		String itemId = "1";
		String quantity = "1";

		Mockito.doReturn(action, orderId, itemId, "0.1", quantity).when(orderLineController).getInput();
		Long orderIdConverted = Long.valueOf(orderId);
		Long itemIdConverted = Long.valueOf(itemId);
		Long quantityConverted = Long.valueOf(quantity);
		OrderLine orderLine = new OrderLine(orderIdConverted, itemIdConverted, quantityConverted);
		OrderLine savedOrderLine = new OrderLine(1L, 1L, 1L);
		Mockito.when(orderLineServices.changeItems(orderLine)).thenReturn(savedOrderLine);
		assertEquals(savedOrderLine, orderLineController.changeItems());
	}

	@Test
	public void changeItemsTestDelete() {
		String action = "remove";
		String orderId = "1";
		String itemId = "1";

		Mockito.doReturn("hello", action, "0.1", orderId, "0.1", itemId).when(orderLineController).getInput();
		Long orderIdConverted = Long.valueOf(orderId);
		Long itemIdConverted = Long.valueOf(itemId);
		OrderLine orderLine = new OrderLine(orderIdConverted, itemIdConverted, null);
		OrderLine savedOrderLine = new OrderLine(1L, 1L, null);
		Mockito.when(orderLineServices.changeItems(orderLine)).thenReturn(savedOrderLine);
		assertEquals(savedOrderLine, orderLineController.changeItems());
	}

	@Test
	public void readItemsInOrderTest() {
		Mockito.doReturn("0.1", "1").when(orderLineController).getInput();
		List<OrderLine> orderLines = new ArrayList<>();
		orderLines.add(new OrderLine(1L, 2L, 1L));
		orderLines.add(new OrderLine(1L, 1L, 3L));
		Mockito.when(orderLineServices.readOrder2(1L)).thenReturn(orderLines);
		assertEquals(orderLines, orderLineController.readItemsInOrder());
	}

	@Test
	public void betterOrderReaderTest() {
		Mockito.doReturn("0.1", "1").when(orderLineController).getInput();
		String order = "<format of order 1>";
		Mockito.when(orderLineServices.readOrder(1L)).thenReturn(order);
		assertEquals(order, orderLineController.betterOrderReader());
	}

	@Test
	public void calculateOrderPriceTest() {
		String id = "1";
		Mockito.doReturn("0.1", id).when(orderLineController).getInput();

		Mockito.when(orderLineServices.calculate(Long.valueOf(id))).thenReturn(BigDecimal.valueOf(47.96));
		assertEquals(BigDecimal.valueOf(47.96), orderLineController.calculateOrderPrice());
	}

}
