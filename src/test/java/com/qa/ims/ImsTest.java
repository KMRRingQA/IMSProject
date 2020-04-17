package com.qa.ims;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.utils.Utils;

@RunWith(MockitoJUnitRunner.class)
public class ImsTest {

	/**
	 * The thing I want to fake functionality for
	 */
	@Mock
	private Utils utils;

	/**
	 * Spy is used because i want to mock some methods inside the orderLine I'm
	 * testing InjectMocks uses dependency injection to insert the mock into the
	 * orderLine controller
	 */
	@Spy
	@InjectMocks
	private Ims ims;

//	@Test
//	public void aInit() {
//		Ims ims = new Ims();
//		ims.init("jdbc:mysql://34.67.113.137:3306/", "root", "root", "src/test/resources/sql-schema.sql");
//	}

//	String custId = "1";
//	String date = "2020-4-1";
//
//	Mockito.doReturn(custId, "no", "2020-4-40", "30.2-20.3-29.3", date).when(orderController).getInput();
//	Long custIdConverted = Long.valueOf(custId);
//	Order order = new Order(custIdConverted, date, BigDecimal.valueOf(0));
//	Order savedOrder = new Order(1L, "2020-4-1", BigDecimal.valueOf(0));
//	Mockito.when(orderServices.create(order)).thenReturn(savedOrder);
//	assertEquals(savedOrder, orderController.create());

	@Test
	public void bImsSystem() {
//		ByteArrayInputStream in = new ByteArrayInputStream("root".getBytes());
//		System.setIn(in);
		Mockito.doReturn("root", "root", "stop").when(ims).getInput();
		ims.imsSystem();
	}

}
