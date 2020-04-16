package com.qa.ims.controller;

import static org.junit.Assert.assertEquals;

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

	/**
	 * The thing I want to fake functionlity for
	 */
	@Mock
	private OrderLineServices orderLineServices;

	/**
	 * Spy is used because i want to mock some methods inside the item I'm testing
	 * InjectMocks uses dependency injection to insert the mock into the orderLine
	 * controller
	 */
	@Spy
	@InjectMocks
	private OrderLineController orderLineController;

	@Test
	public void changeItemsTestAdd() {
		String input1 = "add";
		String orderId = "1";
		String itemId = "1";
		String quantity = "5";

		Mockito.doReturn(input1, orderId, itemId, quantity).when(orderLineController).getInput();

		Long orderIdConverted = Long.parseLong(orderId);
		Long itemIdConverted = Long.parseLong(itemId);
		Long quantityConverted = Long.parseLong(quantity);

		OrderLine orderLine = new OrderLine(orderIdConverted, itemIdConverted, quantityConverted);
		OrderLine savedOrderLine = new OrderLine(1l, 1l, 5l);

		Mockito.when(orderLineServices.changeItems(orderLine)).thenReturn(savedOrderLine);
		assertEquals(savedOrderLine, orderLineController.changeItems());
	}

//	String custId = "1";
//	String date = "2020-4-1";
//
//	Mockito.doReturn(custId, date).when(orderController).getInput();
//	Long custIdConverted = Long.valueOf(custId);

//	Order order = new Order(custIdConverted, date, BigDecimal.valueOf(0));
//	Order savedOrder = new Order(1L, "2020-4-1", BigDecimal.valueOf(0));
//	Mockito.when(orderServices.create(order)).thenReturn(savedOrder);
//	assertEquals(savedOrder, orderController.create());

	@Test
	public void changeItemsTestRemove() {
		String input1 = "remove";
		String orderId = "1";
		String itemId = "1";

		Mockito.doReturn(input1, orderId, itemId).when(orderLineController).getInput();

		Long orderIdConverted = Long.parseLong(orderId);
		Long itemIdConverted = Long.parseLong(itemId);

		OrderLine orderLine = new OrderLine(orderIdConverted, itemIdConverted, null);
		OrderLine savedOrderLine = new OrderLine(1l, 1l, null);

		Mockito.when(orderLineServices.changeItems(orderLine)).thenReturn(savedOrderLine);
		assertEquals(savedOrderLine, orderLineController.changeItems());
	}

//	@Test
//	public void readItemsInOrderTest() {
//		String firstName = "Chris";
//		String surname = "Perrins";
//		Mockito.doReturn(firstName, surname).when(orderLineController).getInput();
//		OrderLine orderLine = new OrderLine(firstName, surname);
//		OrderLine savedOrderLine = new OrderLine(1L, "Chris", "Perrins");
//		Mockito.when(orderLineServices.create(orderLine)).thenReturn(savedOrderLine);
//		assertEquals(savedOrderLine, orderLineController.create());
//	}
//
//	/**
//	 * 
//	 */
//	@Test
//	public void calculateOrderPriceTest() {
//		String id = "1";
//		String firstName = "Rhys";
//		String surname = "Thompson";
//		Mockito.doReturn(id, firstName, surname).when(orderLineController).getInput();
//		OrderLine orderLine = new OrderLine(1L, firstName, surname);
//		Mockito.when(orderLineServices.update(orderLine)).thenReturn(orderLine);
//		assertEquals(orderLine, orderLineController.update());
//	}

}
