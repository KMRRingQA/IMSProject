package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.Ims;
import com.qa.ims.controller.ItemController;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.services.ItemServices;

@RunWith(MockitoJUnitRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ItemDaoMysqlTest {

	/**
	 * The thing I want to fake functionality for
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

	@BeforeClass
	public void aInit() {
		Ims ims = new Ims();
		ims.init("jdbc:mysql://34.67.113.137:3306/", "root", "root", "src/test/resources/sql-schema.sql");
	}

	@Test
	public void bCreateTest() {
		ItemDaoMysql itemDaoMysql = new ItemDaoMysql("root", "root");
		String name = "Minecraft";
		BigDecimal price = BigDecimal.valueOf(14.99);
		Long stock = 1000L;
		Item item = new Item(name, price, stock);
		assertEquals(item, itemDaoMysql.create(item));
	}

	@Test
	public void cReadAllTest() {
		ItemDaoMysql itemDaoMysql = new ItemDaoMysql("root", "root");
		List<Item> items = new ArrayList<>();
		items.add(new Item(1L, "Skyrim", BigDecimal.valueOf(2.49), 100L));
		items.add(new Item(2L, "Minecraft", BigDecimal.valueOf(14.99), 1000L));
		assertEquals(items, itemDaoMysql.readAll());
	}

	@Test
	public void dReadLatestTest() {
		ItemDaoMysql itemDaoMysql = new ItemDaoMysql("root", "root");
		Item item = new Item(2L, "Minecraft", BigDecimal.valueOf(14.99), 1000L);
		assertEquals(item, itemDaoMysql.readLatest());
	}

	@Test
	public void eReadItemTest() {
		ItemDaoMysql itemDaoMysql = new ItemDaoMysql("root", "root");
		Item item = new Item(1L, "Skyrim", BigDecimal.valueOf(2.49), 100L);
		assertEquals(item, itemDaoMysql.readItem(1L));
	}

	/**
	 * 
	 */
	@Test
	public void fUpdateTest() {
		ItemDaoMysql itemDaoMysql = new ItemDaoMysql("root", "root");
		String id = "1";
		String name = "League of Legends";
		String price = "199.99";
		String stock = "10000";
		Item item = new Item(Long.parseLong(id), name, BigDecimal.valueOf(Double.parseDouble(price)),
				Long.parseLong(stock));
		assertEquals(item, itemDaoMysql.update(item));
	}

	/**
	 * Delete doesn't return anything, so we can just verify that it calls the
	 * delete method
	 */
	@Test
	public void gDeleteTest() {
		ItemDaoMysql itemDaoMysql = new ItemDaoMysql("root", "root");
		String id = "1";
		itemDaoMysql.delete(Long.parseLong(id));
		List<Item> items = new ArrayList<>();
		items.add(new Item(2L, "Minecraft", BigDecimal.valueOf(14.99), 1000L));
		assertEquals(items, itemDaoMysql.readAll());
	}

}
