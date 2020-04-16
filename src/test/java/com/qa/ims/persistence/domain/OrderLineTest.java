package com.qa.ims.persistence.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class OrderLineTest {

	private OrderLine orderLine;
	private OrderLine other;

	@Before
	public void setUp() {
		orderLine = new OrderLine(1L, 1L, 20L);
		other = new OrderLine(1L, 1L, 20L);
		;
	}

	@Test
	public void settersTest() {
		assertNotNull(orderLine.getItemId());
		assertNotNull(orderLine.getItemId());
		assertNotNull(orderLine.getQuantity());

		orderLine.setItemId(null);
		assertNull(orderLine.getItemId());
		orderLine.setOrderId(null);
		assertNull(orderLine.getOrderId());
		orderLine.setQuantity(null);
		assertNull(orderLine.getQuantity());

	}

	@Test
	public void equalsWithNull() {
		assertFalse(orderLine.equals(null));
	}

	@Test
	public void equalsWithDifferentObject() {
		assertFalse(orderLine.equals(new Object()));
	}

	@Test
	public void createOrderLineWithId() {
		assertEquals(1L, orderLine.getItemId(), 0);
		assertEquals(1L, orderLine.getOrderId(), 0);
		assertEquals(20L, orderLine.getQuantity(), 0);
	}

	@Test
	public void checkEquality() {
		assertTrue(orderLine.equals(orderLine));
	}

	@Test
	public void checkEqualityBetweenDifferentObjects() {
		assertTrue(orderLine.equals(other));
	}

	@Test
	public void orderLineNameNullButOtherNameNotNull() {
		orderLine.setItemId(null);
		assertFalse(orderLine.equals(other));
	}

	@Test
	public void orderLineNamesNotEqual() {
		other.setItemId(2L);
		assertFalse(orderLine.equals(other));
	}

	@Test
	public void checkEqualityBetweenDifferentObjectsNullName() {
		orderLine.setItemId(null);
		other.setItemId(null);
		assertTrue(orderLine.equals(other));
	}

	@Test
	public void nullId() {
		orderLine.setOrderId(null);
		assertFalse(orderLine.equals(other));
	}

	@Test
	public void nullIdOnBoth() {
		orderLine.setOrderId(null);
		other.setOrderId(null);
		assertTrue(orderLine.equals(other));
	}

	@Test
	public void otherIdDifferent() {
		other.setOrderId(2L);
		assertFalse(orderLine.equals(other));
	}

	@Test
	public void nullSurname() {
		orderLine.setQuantity(null);
		assertFalse(orderLine.equals(other));
	}

	@Test
	public void nullSurnameOnBoth() {
		orderLine.setQuantity(null);
		other.setQuantity(null);
		assertTrue(orderLine.equals(other));
	}

	@Test
	public void otherSurnameDifferent() {
		other.setQuantity(423L);
		assertFalse(orderLine.equals(other));
	}

	@Test
	public void hashCodeTest() {
		assertEquals(orderLine.hashCode(), other.hashCode());
	}

	@Test
	public void hashCodeTestWithNull() {
		OrderLine orderLine = new OrderLine(null, null, null);
		OrderLine other = new OrderLine(null, null, null);
		assertEquals(orderLine.hashCode(), other.hashCode());
	}

	@Test
	public void toStringTest() {
		String toString = "order id:1 item id:1 quantity:20";
		assertEquals(toString, orderLine.toString());
	}
}
