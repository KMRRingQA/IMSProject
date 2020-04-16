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

import com.qa.ims.persistence.domain.Item;
import com.qa.ims.services.ItemServices;

@RunWith(MockitoJUnitRunner.class)
public class ItemControllerTest {

	/**
	 * The thing I want to fake functionlity for
	 */
	@Mock
	private ItemServices itemServices;

	/**
	 * Spy is used because i want to mock some methods inside the item I'm testing
	 * InjectMocks uses dependency injection to insert the mock into the item
	 * controller
	 */
	@Spy
	@InjectMocks
	private ItemController itemController;

	@Test
	public void readAllTest() {
		ItemController itemController = new ItemController(itemServices);
		List<Item> items = new ArrayList<>();
		items.add(new Item(1L, "Minecraft", BigDecimal.valueOf(14.99), 100L));
		items.add(new Item(2L, "Skyrim", BigDecimal.valueOf(2.49), 1000L));
		items.add(new Item(3L, "League of Legends", BigDecimal.valueOf(299.49), 18L));
		Mockito.when(itemServices.readAll()).thenReturn(items);
		assertEquals(items, itemController.readAll());
	}

	@Test
	public void createTest() {
		String name = "Minecraft";
		String price = "14.99";
		String stock = "100";

		Mockito.doReturn(name, price, stock).when(itemController).getInput();
		BigDecimal priceConverted = BigDecimal.valueOf(Double.parseDouble(price));
		Long stockConverted = Long.parseLong(stock);
		Item item = new Item(name, priceConverted, stockConverted);
		Item savedItem = new Item(1L, "Minecraft", BigDecimal.valueOf(14.99), 100L);
		Mockito.when(itemServices.create(item)).thenReturn(savedItem);
		assertEquals(savedItem, itemController.create());
	}

	/**
	 * 
	 */
	@Test
	public void updateTest() {
		String id = "2";
		String name = "Skyrim";
		String price = "2.49";
		String stock = "1000";

		Mockito.doReturn(id, name, price, stock).when(itemController).getInput();
		BigDecimal priceConverted = BigDecimal.valueOf(Double.parseDouble(price));
		Long stockConverted = Long.parseLong(stock);
		Item item = new Item(2L, name, priceConverted, stockConverted);
		Mockito.when(itemServices.update(item)).thenReturn(item);
		assertEquals(itemController.update(), item);
	}

	/**
	 * Delete doesn't return anything, so we can just verify that it calls the
	 * delete method
	 */
	@Test
	public void deleteTest() {
		String id = "1";
		Mockito.doReturn(id).when(itemController).getInput();
		itemController.delete();
		Mockito.verify(itemServices, Mockito.times(1)).delete(1L);
	}

}
