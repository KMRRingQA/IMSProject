package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

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
import com.qa.ims.controller.OrderLineController;
import com.qa.ims.persistence.domain.OrderLine;
import com.qa.ims.services.OrderLineServices;

@RunWith(MockitoJUnitRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OrderLineDaoMysqlTest {

	/**
	 * The thing I want to fake functionality for
	 */
	@Mock
	private OrderLineServices orderLineServices;

	/**
	 * Spy is used because i want to mock some methods inside the orderLine I'm
	 * testing InjectMocks uses dependency injection to insert the mock into the
	 * orderLine controller
	 */
	@Spy
	@InjectMocks
	private OrderLineController orderLineController;

	@BeforeClass
	public static void aInit() {
		Ims ims = new Ims();
		ims.init("jdbc:mysql://34.67.113.137:3306/", "root", "root", "src/test/resources/sql-schema.sql");
	}

	@Test
	public void bCreateTest() {
		OrderLineDaoMysql orderLineDaoMysql = new OrderLineDaoMysql("root", "root");
		Long orderId = 1L;
		Long itemId = 1L;
		Long quantity = 10L;
		OrderLine orderLine = new OrderLine(orderId, itemId, quantity);
		assertEquals(orderLine, orderLineDaoMysql.changeItems(orderLine));
	}

	String price = "price";

	@Test
	public void cReadOrder() {
		OrderLineDaoMysql orderLineDaoMysql = new OrderLineDaoMysql("root", "root");
		List<OrderLine> orderLines = new ArrayList<>();
		orderLines.add(new OrderLine(1L, 1L, 20L));
		orderLines.add(new OrderLine(1L, 1L, 10L));
		assertEquals("Order ID [1]\n" + "placed: 2020-04-16 00:00:00\n" + "by customer: Luke Conway\n"
				+ "total price: £74.70\n" + "contents:\n" + "£49.80:	 20 x Skyrim\n" + "£24.90:	 10 x Skyrim",
				orderLineDaoMysql.readOrder(1L));
	}

	@Test
	public void dReadOrder2() {
		OrderLineDaoMysql orderLineDaoMysql = new OrderLineDaoMysql("root", "root");
		List<OrderLine> orderLines = new ArrayList<>();
		orderLines.add(new OrderLine(1L, 1L, 20L));
		orderLines.add(new OrderLine(1L, 1L, 10L));
		assertEquals(orderLines, orderLineDaoMysql.readOrder2(1L));
	}

	@Test
	public void eReadLatestTest() {
		OrderLineDaoMysql orderLineDaoMysql = new OrderLineDaoMysql("root", "root");
		OrderLine orderLine = new OrderLine(1L, 1L, 10L);
		assertEquals(orderLine, orderLineDaoMysql.readLatest());
	}

	@Test
	public void gDeleteTest() {
		OrderLineDaoMysql orderLineDaoMysql = new OrderLineDaoMysql("root", "root");
		Long orderId = 1L;
		Long itemId = 1L;
		Long quantity = null;
		orderLineDaoMysql.changeItems(new OrderLine(orderId, itemId, quantity));
		assertEquals(new ArrayList<>(), orderLineDaoMysql.readOrder2(1L));
	}
}