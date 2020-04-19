package com.qa.ims.services;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.persistence.dao.DaoCRUD;
import com.qa.ims.persistence.domain.Item;

@RunWith(MockitoJUnitRunner.class)
public class ItemServicesTest {

	@Mock
	private DaoCRUD<Item> itemDao;

	@InjectMocks
	private ItemServices itemServices;

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
