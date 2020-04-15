package com.qa.ims.controller;

import java.util.List;

import org.apache.log4j.Logger;

import com.mysql.cj.x.protobuf.MysqlxCrud.Order;
import com.qa.ims.services.CrudServices;
import com.qa.ims.utils.Utils;

/**
 * Takes in customer details for CRUD functionality
 *
 */
public class OrderController implements CrudController<Order> {

	public static final Logger LOGGER = Logger.getLogger(OrderController.class);

	private CrudServices<Order> orderService;

	public OrderController(CrudServices<Order> customerService) {
		this.orderService = customerService;
	}

	/**
	 * Creates a customer by taking in user input
	 */
	@Override
	public Order create() {
		String firstName = null;
		String surname = null;
		do {
			LOGGER.info("Please enter a first name");
			firstName = getInput();
		} while (firstName.isEmpty());

		do {
			LOGGER.info("Please enter a surname");
			surname = getInput();
		} while (surname.isEmpty());

		Order order = orderService.create(new Order(firstName, surname));
		LOGGER.info("Order created");
		return order;
	}

	/**
	 * Deletes an existing customer by the id of the customer
	 */
	@Override
	public void delete() {
		Long id = null;
		do {
			try {
				LOGGER.info("Please enter the id of the customer you would like to delete");
				id = Long.valueOf(getInput());
			} catch (NumberFormatException nfe) {
				LOGGER.info("Please enter an integer (only).");
			}
		} while (id == null);
		orderService.delete(id);
	}

	String getInput() {
		return Utils.getInput();
	}

	/**
	 * Reads all customers to the logger
	 */
	@Override
	public List<Order> readAll() {
		List<Order> orders = orderService.readAll();
		for (Order order : orders) {
			LOGGER.info(order.toString());
		}
		return orders;
	}

	/**
	 * Updates an existing customer by taking in user input
	 */
	@Override
	public Order update() {
		Long id = null;
		String firstName = null;
		String surname = null;

		do {
			try {
				LOGGER.info("Please enter the id of the customer you would like to update");
				id = Long.valueOf(getInput());
			} catch (NumberFormatException nfe) {
				LOGGER.info("Please enter an integer (only).");
			}
		} while (id == null);

		do {
			LOGGER.info("Please enter a first name");
			firstName = getInput();
		} while (firstName == null);

		do {
			LOGGER.info("Please enter a surname");
			surname = getInput();
		} while (surname == null);

		Order order = orderService.update(new Order(id, firstName, surname));
		LOGGER.info("Order Updated");
		return order;
	}

}
