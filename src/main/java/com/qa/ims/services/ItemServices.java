package com.qa.ims.services;

import java.util.List;

import com.qa.ims.persistence.dao.DaoCRUD;
import com.qa.ims.persistence.domain.Item;

public class ItemServices implements CrudServices<Item> {

	private DaoCRUD<Item> itemDao;

	public ItemServices(DaoCRUD<Item> itemDao) {
		this.itemDao = itemDao;
	}

	@Override
	public Item create(Item item) {
		return itemDao.create(item);
	}

	@Override
	public void delete(Long id) {
		itemDao.delete(id);
	}

	@Override
	public List<Item> readAll() {
		return itemDao.readAll();
	}

	@Override
	public Item update(Item item) {
		return itemDao.update(item);
	}

}
