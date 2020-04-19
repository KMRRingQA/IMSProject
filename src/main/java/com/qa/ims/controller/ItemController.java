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
	private String integerInput = "Please enter an integer (only).";

	private String getName() {
		String name;
		do {
			LOGGER.info("Please enter item name");
			name = getInput();
		} while (name.isEmpty());
		return name;
	}

	private BigDecimal getPrice() {
		BigDecimal price = null;
		do {
			try {
				LOGGER.info("Please enter the price of the item (€)");
				price = BigDecimal.valueOf(Double.parseDouble(getInput()));
			} catch (NumberFormatException nfe) {
				LOGGER.info("Please enter a decimal number (only).");
			}
		} while (price == null || price.intValue() < 0);
		return price;
	}

	private Long getStock() {
		Long stock = null;
		do {
			try {
				LOGGER.info("Please enter the current stock size of the item");
				stock = Long.parseLong(getInput());
			} catch (NumberFormatException nfe) {
				LOGGER.info(integerInput);
			}
		} while (stock == null || stock < 0);
		return stock;
	}

	@Override
	public Item create() {

		String name = getName();
		BigDecimal price = getPrice();
		Long stock = getStock();

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
		Long id = null;

		do {
			try {
				LOGGER.info("Please enter the id of the item you would like to update");
				id = Long.valueOf(getInput());
			} catch (NumberFormatException nfe) {
				LOGGER.info(integerInput);
			}
		} while (id == null);

		String name = getName();
		BigDecimal price = getPrice();
		Long stock = getStock();

		Item item = itemService.update(new Item(id, name, price, stock));
		LOGGER.info("Item Updated");
		return item;
	}

	@Override
	public List<Item> searchName() {
		String name = null;
		do {
			try {
				LOGGER.info("Please enter the title of the Game you would like to find");
				name = getInput();
			} catch (Exception e) {
				LOGGER.info("Please try again.");
			}
		} while (name.isEmpty());

		List<Item> items = itemService.searchName(name);

		for (Item item : items) {
			LOGGER.info(item.toString());
		}
		return items;
	}

}
