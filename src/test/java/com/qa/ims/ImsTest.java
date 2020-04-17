package com.qa.ims;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.persistence.domain.Item;

@RunWith(MockitoJUnitRunner.class)
public class ImsTest {

	@Mock
	private Order customerController;

	@InjectMocks
	private Ims ims;

	@Test
	public void itemServicesCreate() {
		Item item = new Item("Minecraft", BigDecimal.valueOf(14.99), 100l);
		itemServices.create(item);
		Mockito.verify(itemDao, Mockito.times(1)).create(item);
	}

	@Test
	public void itemServicesRead() {
		itemServices.readAll();
		Mockito.verify(itemDao, Mockito.times(1)).readAll();
	}

	@Test
	public void itemServicesUpdate() {
		Item item = new Item("Minecraft", BigDecimal.valueOf(14.99), 100l);
		itemServices.update(item);
		Mockito.verify(itemDao, Mockito.times(1)).update(item);
	}

	@Test
	public void itemServicesDelete() {
		itemServices.delete(1L);
		Mockito.verify(itemDao, Mockito.times(1)).delete(1L);
	}
}
