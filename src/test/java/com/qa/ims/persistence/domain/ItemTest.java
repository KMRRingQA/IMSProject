package com.qa.ims.persistence.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

public class ItemTest {

	private Item item;
	private Item other;

	@Before
	public void setUp() {
		item = new Item(1L, "Minecraft", BigDecimal.valueOf(29.98), 200L);
		other = new Item(1L, "Minecraft", BigDecimal.valueOf(29.98), 200L);
	}

	@Test
	public void settersTest() {
		assertNotNull(item.getId());
		assertNotNull(item.getName());
		assertNotNull(item.getPrice());
		assertNotNull(item.getStock());

		item.setId(null);
		assertNull(item.getId());
		item.setName(null);
		assertNull(item.getName());
		item.setPrice(null);
		assertNull(item.getPrice());
		item.setStock(null);
		assertNull(item.getStock());
	}

	@Test
	public void equalsWithNull() {
		assertFalse(item.equals(null));
	}

	@Test
	public void equalsWithDifferentObject() {
		assertFalse(item.equals(new Object()));
	}

	@Test
	public void createItemWithId() {
		assertEquals(1L, item.getId(), 0);
		assertEquals("Minecraft", item.getName());
		assertEquals(BigDecimal.valueOf(29.98), item.getPrice());
		assertEquals(200L, item.getStock(), 0);
	}

	@Test
	public void checkEquality() {
		assertTrue(item.equals(item));
	}

	@Test
	public void checkEqualityBetweenDifferentObjects() {
		assertTrue(item.equals(other));
	}

	@Test
	public void nullItemId() {
		item.setId(null);
		assertFalse(item.equals(other));
	}

	@Test
	public void otherItemIdDifferent() {
		other.setId(2l);
		assertFalse(item.equals(other));
	}

	@Test
	public void nullItemIdOnBoth() {
		item.setId(null);
		other.setId(null);
		assertTrue(item.equals(other));
	}

	@Test
	public void nullName() {
		item.setName(null);
		assertFalse(item.equals(other));
	}

	@Test
	public void nullNameOnBoth() {
		item.setName(null);
		other.setName(null);
		assertTrue(item.equals(other));
	}

	@Test
	public void otherNameDifferent() {
		other.setName("Skyrim");
		assertFalse(item.equals(other));
	}

	@Test
	public void nullPrice() {
		item.setPrice(null);
		assertFalse(item.equals(other));
	}

	@Test
	public void nullPriceOnBoth() {
		item.setPrice(null);
		other.setPrice(null);
		assertTrue(item.equals(other));
	}

	@Test
	public void otherPriceDifferent() {
		other.setPrice(BigDecimal.valueOf(89.98));
		assertFalse(item.equals(other));
	}

	@Test
	public void nullStock() {
		item.setStock(null);
		assertFalse(item.equals(other));
	}

	@Test
	public void nullStockOnBoth() {
		item.setStock(null);
		other.setStock(null);
		assertTrue(item.equals(other));
	}

	@Test
	public void otherStockDifferent() {
		other.setStock(38L);
		assertFalse(item.equals(other));
	}

	@Test
	public void constructorWithoutId() {
		Item item = new Item("Minecraft", BigDecimal.valueOf(29.98), 200L);
		assertNull(item.getId());
		assertNotNull(item.getName());
		assertNotNull(item.getPrice());
		assertNotNull(item.getStock());
	}

	@Test
	public void hashCodeTest() {
		assertEquals(item.hashCode(), other.hashCode());
	}

	@Test
	public void hashCodeTestWithNull() {
		Item item = new Item(null, null, null);
		Item other = new Item(null, null, null);
		assertEquals(item.hashCode(), other.hashCode());
	}

	@Test
	public void toStringTest() {
		String toString = "id:1 name:Minecraft price:29.98 stock:200";
		assertEquals(toString, item.toString());
	}
}
