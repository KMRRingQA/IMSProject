package com.qa.ims;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
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

	@BeforeClass
	public static void aInit() {
		Ims ims = new Ims();
		ims.init("jdbc:mysql://34.67.113.137:3306/", "root", "root", "src/test/resources/sql-schema.sql");
	}

	@Test
	public void bImsSystem() {
		Mockito.doReturn("root", "root", "customer", "read", "item", "read", "order", "read", "customer", "return",
				"stop").when(ims).getInput();
		assertEquals("Program finished succesfully", ims.imsSystem());
	}

}
