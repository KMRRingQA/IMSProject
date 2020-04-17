package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.Ims;
import com.qa.ims.controller.OrderController;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.services.OrderServices;

@RunWith(MockitoJUnitRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OrderDaoMysqlTest {

	/**
	 * The thing I want to fake functionality for
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
	public void aInit() {
		Ims ims = new Ims();
		ims.init("jdbc:mysql://34.67.113.137:3306/", "root", "root", "src/test/resources/sql-schema.sql");
	}

	@Test
	public void bCreateTest() {
		OrderDaoMysql orderDaoMysql = new OrderDaoMysql("root", "root");
		Long custId = 1L;
		String date = "2020-4-18";
		BigDecimal totalPrice = BigDecimal.valueOf(0.0);
		Order order = new Order(custId, date, totalPrice);
		assertEquals(order, orderDaoMysql.create(order));
	}

	@Test
	public void cReadAllTest() {
		OrderDaoMysql orderDaoMysql = new OrderDaoMysql("root", "root");
		List<Order> orders = new ArrayList<>();
		orders.add(new Order(1L, 1L, "2020-04-16 00:00:00",
				BigDecimal.valueOf(0.00).setScale(2, BigDecimal.ROUND_HALF_UP)));
		orders.add(new Order(2L, 1L, "2020-04-18 00:00:00",
				BigDecimal.valueOf(0.00).setScale(2, BigDecimal.ROUND_HALF_UP)));

		assertEquals(orders, orderDaoMysql.readAll());
	}

	@Test
	public void dReadLatestTest() {
		OrderDaoMysql orderDaoMysql = new OrderDaoMysql("root", "root");
		Order order = new Order(2L, 1L, "2020-04-18 00:00:00",
				BigDecimal.valueOf(0.00).setScale(2, BigDecimal.ROUND_HALF_UP));
		assertEquals(order, orderDaoMysql.readLatest());
	}

	@Test
	public void eReadOrderTest() {
		OrderDaoMysql orderDaoMysql = new OrderDaoMysql("root", "root");
		Order order = new Order(1L, 1L, "2020-04-16 00:00:00",
				BigDecimal.valueOf(0.00).setScale(2, BigDecimal.ROUND_HALF_UP));
		assertEquals(order, orderDaoMysql.readOrder(1L));
	}

	/**
	 * 
	 */
	@Test
	public void fUpdateTest() {
		OrderDaoMysql orderDaoMysql = new OrderDaoMysql("root", "root");
		String orderId = "1";
		String custId = "1";
		String date = "2020-04-17";
		String totalPrice = "1.01";
		Order order = new Order(Long.parseLong(orderId), Long.parseLong(custId), date + " 00:00:00",
				BigDecimal.valueOf(Double.parseDouble(totalPrice)).setScale(2, BigDecimal.ROUND_HALF_UP));
		assertEquals(order, orderDaoMysql.update(order));
	}

	/**
	 * Delete doesn't return anything, but we can readall afterwards to make sure
	 * that it has been deleted.
	 */
	@Test
	public void gDeleteTest() {
		OrderDaoMysql orderDaoMysql = new OrderDaoMysql("root", "root");
		String id = "1";
		orderDaoMysql.delete(Long.parseLong(id));
		List<Order> orders = new ArrayList<>();
		orders.add(new Order(2L, 1L, "2020-04-18 00:00:00",
				BigDecimal.valueOf(0.00).setScale(2, BigDecimal.ROUND_HALF_UP)));
		assertEquals(orders, orderDaoMysql.readAll());
	}

	@Test
	public void hCreateWithoutDateTest() {
		OrderDaoMysql orderDaoMysql = new OrderDaoMysql("root", "root");
		Long custId = 1L;
		String date = "";
		BigDecimal totalPrice = BigDecimal.valueOf(0.0);
		Order order = new Order(custId, date, totalPrice);
		assertEquals(order, orderDaoMysql.create(order));
	}

}
