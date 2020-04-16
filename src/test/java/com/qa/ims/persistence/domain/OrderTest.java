package com.qa.ims.persistence.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

public class OrderTest {

	private Order order;
	private Order other;

	@Before
	public void setUp() {
		order = new Order(1L, 1L, "2000-4-1", BigDecimal.valueOf(29.98));
		other = new Order(1L, 1L, "2000-4-1", BigDecimal.valueOf(29.98));
	}

	@Test
	public void settersTest() {
		assertNotNull(order.getOrderId());
		assertNotNull(order.getCustId());
		assertNotNull(order.getDate());
		assertNotNull(order.getTotalPrice());

		order.setOrderId(null);
		assertNull(order.getOrderId());
		order.setCustId(null);
		assertNull(order.getCustId());
		order.setDate(null);
		assertNull(order.getDate());
		order.setTotalPrice(null);
		assertNull(order.getTotalPrice());
	}

	@Test
	public void equalsWithNull() {
		assertFalse(order.equals(null));
	}

	@Test
	public void equalsWithDifferentObject() {
		assertFalse(order.equals(new Object()));
	}

	@Test
	public void createOrderWithId() {
		assertEquals(1L, order.getOrderId(), 0);
		assertEquals(1L, order.getCustId(), 0);
		assertEquals("2000-4-1", order.getDate());
		assertEquals(BigDecimal.valueOf(29.98), order.getTotalPrice());
	}

	@Test
	public void checkEquality() {
		assertTrue(order.equals(order));
	}

	@Test
	public void checkEqualityBetweenDifferentObjects() {
		assertTrue(order.equals(other));
	}

	@Test
	public void nullOrderId() {
		order.setOrderId(null);
		assertFalse(order.equals(other));
	}

	@Test
	public void otherOrderIdDifferent() {
		other.setOrderId(2l);
		assertFalse(order.equals(other));
	}

	@Test
	public void nullOrderIdOnBoth() {
		order.setOrderId(null);
		other.setOrderId(null);
		assertTrue(order.equals(other));
	}

	@Test
	public void nullCustId() {
		order.setCustId(null);
		assertFalse(order.equals(other));
	}

	@Test
	public void nullCustIdOnBoth() {
		order.setCustId(null);
		other.setCustId(null);
		assertTrue(order.equals(other));
	}

	@Test
	public void otherCustIdDifferent() {
		other.setCustId(2L);
		assertFalse(order.equals(other));
	}

	@Test
	public void nullDate() {
		order.setDate(null);
		assertFalse(order.equals(other));
	}

	@Test
	public void nullDateOnBoth() {
		order.setDate(null);
		other.setDate(null);
		assertTrue(order.equals(other));
	}

	@Test
	public void otherDateDifferent() {
		other.setDate("thompson");
		assertFalse(order.equals(other));
	}

	@Test
	public void nullTotalPrice() {
		order.setTotalPrice(null);
		assertFalse(order.equals(other));
	}

	@Test
	public void nullTotalPriceOnBoth() {
		order.setTotalPrice(null);
		other.setTotalPrice(null);
		assertTrue(order.equals(other));
	}

	@Test
	public void otherTotalPriceDifferent() {
		other.setTotalPrice(BigDecimal.valueOf(51.01));
		assertFalse(order.equals(other));
	}

	@Test
	public void constructorWithoutId() {
		Order order = new Order(3L, "2020-6-2", BigDecimal.valueOf(49.94));
		assertNull(order.getOrderId());
		assertNotNull(order.getCustId());
		assertNotNull(order.getDate());
		assertNotNull(order.getTotalPrice());
	}

	@Test
	public void hashCodeTest() {
		assertEquals(order.hashCode(), other.hashCode());
	}

	@Test
	public void hashCodeTestWithNull() {
		Order order = new Order(null, null, null);
		Order other = new Order(null, null, null);
		assertEquals(order.hashCode(), other.hashCode());
	}

	@Test
	public void toStringTest() {
		String toString = "order id:1 cust id:1 date:2000-4-1 total Price:29.98";
		assertEquals(toString, order.toString());
	}
}
