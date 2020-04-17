package com.qa.ims;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ImsTest {

	@Spy
	@InjectMocks
	private Ims ims;

	@Test
	public void aInit() {
		Ims ims = new Ims();
		ims.init("jdbc:mysql://34.67.113.137:3306/", "root", "root", "src/test/resources/sql-schema.sql");
	}

	@Test
	public void bImsSystem() {
		Mockito.doReturn("root", "root", "customer", "return", "item", "return", "order", "return", "stop").when(ims)
				.getInput();
		ims.imsSystem();
	}

}
