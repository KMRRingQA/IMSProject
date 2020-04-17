package com.qa.ims.controller;

import java.math.BigDecimal;
import java.util.List;

import org.apache.log4j.Logger;

import com.qa.ims.persistence.domain.OrderLine;
import com.qa.ims.services.OrderLineServices;
import com.qa.ims.utils.Utils;

/**
 * Takes in customer details for CRUD functionality
 *
 */
public class OrderLineController {

	public static final Logger LOGGER = Logger.getLogger(OrderLineController.class);

	private OrderLineServices orderLineService;

	public OrderLineController(OrderLineServices orderLineServices) {
		this.orderLineService = new OrderLineServices();
	}

	String getInput() {
		return Utils.getInput();
	}

	public OrderLine changeItems() {
		Long orderId = null;
		Long itemId = null;
		Long quantity = null;
		String action = null;

		LOGGER.info("Would you like to add or remove an item from your order?");
		LOGGER.info("ADD: To add an item to an order");
		LOGGER.info("REMOVE: To remove an item from an order");
		do {
			action = getInput().toLowerCase();
		} while (!action.equals("remove") && !action.equals("add"));

		do {
			try {
				LOGGER.info("Please enter the id of the order you would like add/remove an item to/from");
				orderId = Long.valueOf(getInput());
			} catch (NumberFormatException nfe) {
				LOGGER.info("Please enter an integer (only).");
			}
		} while (orderId == null || orderId < 0);

		do {
			try {
				LOGGER.info("Please enter the id of the item you would like to add/remove");
				itemId = Long.valueOf(getInput());
			} catch (NumberFormatException nfe) {
				LOGGER.info("Please enter an integer (only).");
			}
		} while (itemId == null || itemId < 0);

		if (action.equals("add")) {
			quantity = 1l;
			do {
				try {
					LOGGER.info(
							"Please enter the quantity of given items you would like to add to the order. Leaving this blank will default to 1.");
					String tempString = getInput();
					if (!tempString.isEmpty()) {
						quantity = Long.valueOf(tempString);
					}
				} catch (NumberFormatException | NullPointerException nfeNpe) {
					LOGGER.info("Please enter an integer (only).");
					quantity = 1l;
				}
			} while (quantity == null || quantity < 1l);
		}

		LOGGER.info("Item added to order");
		return orderLineService.changeItems(new OrderLine(orderId, itemId, quantity));
	}

	public List<OrderLine> readItemsInOrder() {
		Long orderId = null;
		do {
			try {
				LOGGER.info("Please enter the id of the order you would like read");
				orderId = Long.valueOf(getInput());
			} catch (NumberFormatException nfe) {
				LOGGER.info("Please enter an integer (only).");
			}
		} while (orderId == null || orderId < 0);
		List<OrderLine> orderLines = orderLineService.readOrder(orderId);
		for (OrderLine orderLine : orderLines) {
			LOGGER.info(orderLine.toString());
		}
		return orderLines;
	}

	public BigDecimal calculateOrderPrice() {
		Long orderId = null;
		do {
			try {
				LOGGER.info("Please enter the id of the order you would like calculate the total cost for");
				orderId = Long.valueOf(getInput());
			} catch (NumberFormatException nfe) {
				LOGGER.info("Please enter an integer (only).");
			}
		} while (orderId == null || orderId < 0);
		BigDecimal totalCost = orderLineService.calculate(orderId);
		LOGGER.info("Order with id " + orderId + " has a total cost of: �" + totalCost);
		return totalCost;
	}
}
