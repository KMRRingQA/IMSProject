package com.qa.ims.controller;

import java.math.BigDecimal;
import java.util.List;

import org.apache.log4j.Logger;

import com.qa.ims.persistence.domain.Item;
import com.qa.ims.services.CrudServices;
import com.qa.ims.utils.Utils;

/**
 * Takes in customer details for CRUD functionality
 *
 */
public class ItemController implements CrudController<Item> {

	public static final Logger LOGGER = Logger.getLogger(ItemController.class);

	private CrudServices<Item> itemService;

	public ItemController(CrudServices<Item> itemService) {
		this.itemService = itemService;
	}

	/**
	 * Creates a customer by taking in user input
	 */
	@Override
	public Item create() {
		LOGGER.info("Please enter item name");
		String name = getInput();

		LOGGER.info("Please enter the price of the item");
		BigDecimal price = BigDecimal.valueOf(Double.parseDouble(getInput()));

		LOGGER.info("Please enter the current stock size of the item");
		Long stock = Long.parseLong(getInput());

		Item item = itemService.create(new Item(name, price, stock));
		LOGGER.info("Item created");
		return item;
	}

	/**
	 * Deletes an existing customer by the id of the customer
	 */
	@Override
	public void delete() {
		LOGGER.info("Please enter the id of the customer you would like to delete");
		Long id = Long.valueOf(getInput());
		itemService.delete(id);
	}

	String getInput() {
		return Utils.getInput();
	}

	/**
	 * Reads all customers to the logger
	 */
	@Override
	public List<Item> readAll() {
		List<Item> items = itemService.readAll();
		for (Item item : items) {
			LOGGER.info(item.toString());
		}
		return items;
	}

	/**
	 * Updates an existing customer by taking in user input
	 */
	@Override
	public Item update() {
		LOGGER.info("Please enter the id of the customer you would like to update");
		Long id = Long.valueOf(getInput());

		LOGGER.info("Please enter item name");
		String name = getInput();

		LOGGER.info("Please enter the price of the item");
		BigDecimal price = BigDecimal.valueOf(Double.parseDouble(getInput()));

		LOGGER.info("Please enter the current stock size of the item");
		Long stock = Long.parseLong(getInput());

		Item item = itemService.create(new Item(id, name, price, stock));
		LOGGER.info("Customer Updated");
		return item;
	}

}
