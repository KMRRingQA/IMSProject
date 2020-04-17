package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

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

	@Test
	public void aInit() {
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

	@Test
	public void cReadOrder() {
		OrderLineDaoMysql orderLineDaoMysql = new OrderLineDaoMysql("root", "root");
		List<OrderLine> orderLines = new ArrayList<>();
		orderLines.add(new OrderLine(1L, 1L, 20L));
		orderLines.add(new OrderLine(1L, 1L, 10L));
		assertEquals(orderLines, orderLineDaoMysql.readOrder(1L));
	}

	@Test
	public void dReadLatestTest() {
		OrderLineDaoMysql orderLineDaoMysql = new OrderLineDaoMysql("root", "root");
		OrderLine orderLine = new OrderLine(1L, 1L, 10L);
		assertEquals(orderLine, orderLineDaoMysql.readLatest());
	}
}
//	@Test
//	public void eReadOrderLineTest() {
//	public void eCalculate() {
//		OrderLineDaoMysql orderLineDaoMysql = new OrderLineDaoMysql("root", "root");
//		OrderLine orderLine = new OrderLine(1L, 1L, "2020-04-16 00:00:00",
//				BigDecimal.valueOf(0.00).setScale(2, BigDecimal.ROUND_HALF_UP));
//		assertEquals(orderLine, orderLineDaoMysql.readOrderLine(1L));
//	}
//
//	/**
//	 * 
//	 */
//	@Test
//	public void fUpdateTest() {
//		OrderLineDaoMysql orderLineDaoMysql = new OrderLineDaoMysql("root", "root");
//		String orderLineId = "1";
//		String custId = "1";
//		String date = "2020-04-17";
//		String totalPrice = "1.01";
//		OrderLine orderLine = new OrderLine(Long.parseLong(orderLineId), Long.parseLong(custId), date + " 00:00:00",
//				BigDecimal.valueOf(Double.parseDouble(totalPrice)).setScale(2, BigDecimal.ROUND_HALF_UP));
//		assertEquals(orderLine, orderLineDaoMysql.update(orderLine));
//	}
//
////
//	/**
//	 * Delete doesn't return anything, but we can readall afterwards to make sure
//	 * that it has been deleted.
//	 */
//	@Test
//	public void gDeleteTest() {
//		OrderLineDaoMysql orderLineDaoMysql = new OrderLineDaoMysql("root", "root");
//		String id = "1";
//		orderLineDaoMysql.delete(Long.parseLong(id));
//		List<OrderLine> orderLines = new ArrayList<>();
//		orderLines.add(new OrderLine(2L, 1L, "2020-04-18 00:00:00",
//				BigDecimal.valueOf(0.00).setScale(2, BigDecimal.ROUND_HALF_UP)));
//		assertEquals(orderLines, orderLineDaoMysql.readAll());
//	}
