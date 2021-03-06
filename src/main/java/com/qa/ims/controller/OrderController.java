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

	private String notAnInteger = "Please enter an integer (only).";
	private String enterDate = "Please enter a Date in the format YYYY-MM-DD. You may leave this blank to default to the current date.\n";

	public static final Logger LOGGER = Logger.getLogger(OrderController.class);

	private CrudServices<Order> orderService;

	public OrderController(CrudServices<Order> orderService) {
		this.orderService = orderService;
	}

	private String date() {
		String date = null;
		boolean format;
		boolean exception;
		LOGGER.info(enterDate);
		do {
			exception = false;
			format = true;
			try {
				date = getInput();
				if (!date.isEmpty() && (date.split("-").length != 3 || Integer.valueOf(date.split("-")[0]) > 3000
						|| Integer.valueOf(date.split("-")[0]) < 2010 || Integer.valueOf(date.split("-")[1]) > 12
						|| Integer.valueOf(date.split("-")[2]) > 31)) {
					format = false;
					LOGGER.info(enterDate);
				}
			} catch (ArrayIndexOutOfBoundsException | NumberFormatException aioobeNfe) {
				LOGGER.info(enterDate);
				exception = true;
			}
		} while ((!format && date != null) || exception);
		return date;
	}

	@Override
	public Order create() {
		Long custId = null;
		BigDecimal totalPrice = BigDecimal.valueOf(0);

		do {
			try {
				LOGGER.info("Please enter the customer ID associated with the Order.");
				custId = Long.valueOf(getInput());
			} catch (NumberFormatException nfe) {
				LOGGER.info(notAnInteger);
			}
		} while (custId == null || custId < 0);

		String date = date();

		Order order = orderService.create(new Order(custId, date, totalPrice));
		LOGGER.info("Order created");
		return order;
	}

	@Override
	public void delete() {
		Long id = null;
		do {
			try {
				LOGGER.info("Please enter the id of the order you would like to delete");
				id = Long.valueOf(getInput());
			} catch (NumberFormatException nfe) {
				LOGGER.info(notAnInteger);
			}
		} while (id == null);
		orderService.delete(id);
	}

	String getInput() {
		return Utils.getInput();
	}

	@Override
	public List<Order> readAll() {
		List<Order> orders = orderService.readAll();
		for (Order order : orders) {
			LOGGER.info(order.toString());
		}
		return orders;
	}

	@Override
	public Order update() {
		Long orderId = null;
		Long custId = null;
		BigDecimal totalPrice = BigDecimal.valueOf(0);

		do {
			try {
				LOGGER.info("Please enter the Order ID associated with the Order.");
				orderId = Long.valueOf(getInput());
			} catch (NumberFormatException nfe) {
				LOGGER.info(notAnInteger);
			}
		} while (orderId == null || orderId < 0);

		do {
			try {
				LOGGER.info("Please enter the (new) Customer ID associated with the Order.");
				custId = Long.valueOf(getInput());
			} catch (NumberFormatException nfe) {
				LOGGER.info("notAnInteger");
			}
		} while (custId == null || custId < 0);

		String date = date();

		do {
			try {
				LOGGER.info("Please enter the total cost of the order (�)");
				totalPrice = BigDecimal.valueOf(Double.parseDouble(getInput()));
			} catch (NumberFormatException nfe) {
				LOGGER.info("Please enter a decimal number (only).");
			}
		} while (totalPrice == null || totalPrice.intValue() < 0);

		Order order = orderService.update(new Order(orderId, custId, date, totalPrice));
		LOGGER.info("Order Updated");
		return order;
	}

	@Override
	public List<Order> searchName() {
		String name = null;
		do {
			LOGGER.info("Please enter the first and last name of the customer you would like to find");
			name = getInput();
		} while (name.isEmpty() || (name.split(" ").length != 2));

		List<Order> orders = orderService.searchName(name);

		for (Order order : orders) {
			LOGGER.info(order.toString());
		}
		return orders;
	}

}
