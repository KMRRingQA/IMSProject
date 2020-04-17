//package com.qa.ims.controller;
//
//import static org.junit.Assert.assertEquals;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.Spy;
//import org.mockito.junit.MockitoJUnitRunner;
//
//import com.qa.ims.persistence.domain.OrderLine;
//import com.qa.ims.services.OrderLineServices;
//
//@RunWith(MockitoJUnitRunner.class)
//public class OrderLineControllerTest {
//
//	@Mock
//	private OrderLineServices orderLineServices;
//
//	@Spy
//	@InjectMocks
//	private OrderLineController orderLineController;
//
//	@Test
//	public void changeItemsTest() {
//		String action = "add";
//		String orderId = "1";
//		String itemId = "1";
//		String quantity = "3";
//
//		Mockito.doReturn(action, orderId, itemId, quantity).when(orderLineController).getInput();
//		Long orderIdConverted = Long.valueOf(orderId);
//		Long itemIdConverted = Long.valueOf(itemId);
//		Long quantityConverted = Long.valueOf(quantity);
//		OrderLine orderLine = new OrderLine(orderIdConverted, itemIdConverted, quantityConverted);
//		OrderLine savedOrderLine = new OrderLine(1L, 1L, 3L);
//		Mockito.when(orderLineServices.changeItems(orderLine)).thenReturn(savedOrderLine);
//		assertEquals(savedOrderLine, orderLineController.changeItems());
//	}
//
////	@Test
////	public void readItemsInOrderTest() {
////		String firstName = "Chris";
////		String surname = "Perrins";
////		Mockito.doReturn(firstName, surname).when(orderLineController).getInput();
////		OrderLine orderLine = new OrderLine(firstName, surname);
////		OrderLine savedOrderLine = new OrderLine(1L, "Chris", "Perrins");
////		Mockito.when(orderLineServices.create(orderLine)).thenReturn(savedOrderLine);
////		assertEquals(savedOrderLine, orderLineController.create());
////	}
////
////	/**
////	 * 
////	 */
////	@Test
////	public void calculateOrderPriceTest() {
////		String id = "1";
////		String firstName = "Rhys";
////		String surname = "Thompson";
////		Mockito.doReturn(id, firstName, surname).when(orderLineController).getInput();
////		OrderLine orderLine = new OrderLine(1L, firstName, surname);
////		Mockito.when(orderLineServices.update(orderLine)).thenReturn(orderLine);
////		assertEquals(orderLine, orderLineController.update());
////	}
//
//}
