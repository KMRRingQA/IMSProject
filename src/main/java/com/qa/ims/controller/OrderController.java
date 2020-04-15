package com.qa.ims.controller;

import java.math.BigDecimal;
import java.util.List;

import org.apache.log4j.Logger;

import com.qa.ims.persistence.domain.Order;
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
		Long cust_id = null;
		String date = null;
		BigDecimal totalPrice = BigDecimal.valueOf(0);

		do {
			try {
				LOGGER.info("Please enter the customer ID associated with the Order.");
				cust_id = Long.valueOf(getInput());
			} catch (NumberFormatException nfe) {
				LOGGER.info("Please enter an integer (only).");
			}
		} while (cust_id == null || cust_id < 0);

		boolean format;
		boolean exception;
		LOGGER.info(
				"Please enter a Date in the format YYYY-MM-DD. You may leave this blank to default to the current date.");
		do {
			exception = false;
			format = true;
			try {
				date = getInput();
				if (!date.isEmpty() && (date.split("-").length != 3 || Integer.valueOf(date.split("-")[0]) > 3000
						|| Integer.valueOf(date.split("-")[1]) > 12 || Integer.valueOf(date.split("-")[2]) > 31)) {
					format = false;
					System.out.println(
							"Please follow the input format, or leave it blank to default to the current date.");
				}
			} catch (ArrayIndexOutOfBoundsException aioobe) {
				LOGGER.info("Please follow the input format, or leave it blank to default to the current date.");
				exception = true;
			} catch (NumberFormatException nfe) {
				LOGGER.info("Please follow the input format, or leave it blank to default to the current date.");
				exception = true;
			} catch (Exception e) {
				LOGGER.info("Please follow the input format, or leave it blank to default to the current date.");
				exception = true;
			}
		} while ((format == false && date != null) || exception == true);

		Order order = orderService.create(new Order(cust_id, date, totalPrice));
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
				LOGGER.info("Please enter the id of the order you would like to delete");
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
		Long order_id = null;
		Long cust_id = null;
		String date = null;
		BigDecimal totalPrice = BigDecimal.valueOf(0);

		do {
			try {
				LOGGER.info("Please enter the Order ID associated with the Order.");
				order_id = Long.valueOf(getInput());
			} catch (NumberFormatException nfe) {
				LOGGER.info("Please enter a decimal number (only).");
			}
		} while (order_id == null || order_id < 0);

		do {
			try {
				LOGGER.info("Please enter the (new) Customer ID associated with the Order.");
				cust_id = Long.valueOf(getInput());
			} catch (NumberFormatException nfe) {
				LOGGER.info("Please enter a decimal number (only).");
			}
		} while (cust_id == null || cust_id < 0);

		boolean format;
		boolean exception;
		LOGGER.info(
				"Please enter a Date in the format YYYY-MM-DD. You may leave this blank to default to the current date.");
		do {
			exception = false;
			format = true;
			try {
				date = getInput();
				if (!date.isEmpty() && (date.split("-").length != 3 || Integer.valueOf(date.split("-")[0]) > 3000
						|| Integer.valueOf(date.split("-")[1]) > 12 || Integer.valueOf(date.split("-")[2]) > 31)) {
					format = false;
					LOGGER.info("Please follow the input format, or leave it blank to default to the current date.");
				}
			} catch (ArrayIndexOutOfBoundsException aioobe) {
				LOGGER.info("Please follow the input format, or leave it blank to default to the current date.");
				exception = true;
			} catch (NumberFormatException nfe) {
				LOGGER.info("Please follow the input format, or leave it blank to default to the current date.");
				exception = true;
			} catch (Exception e) {
				LOGGER.info("Please follow the input format, or leave it blank to default to the current date.");
				exception = true;
			}
		} while ((format == false && date != null) || exception == true);

		Order order = orderService.update(new Order(order_id, cust_id, date, totalPrice));
		LOGGER.info("Order Updated");
		return order;
	}

}
