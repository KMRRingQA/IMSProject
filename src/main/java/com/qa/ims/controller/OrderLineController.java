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
		Long order_id = null;
		Long item_id = null;
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
				order_id = Long.valueOf(getInput());
			} catch (NumberFormatException nfe) {
				LOGGER.info("Please enter an integer (only).");
			}
		} while (order_id == null || order_id < 0);

		do {
			try {
				LOGGER.info("Please enter the id of the item you would like to add/remove");
				item_id = Long.valueOf(getInput());
			} catch (NumberFormatException nfe) {
				LOGGER.info("Please enter an integer (only).");
			}
		} while (item_id == null || item_id < 0);

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
				} catch (NumberFormatException | NullPointerException nfe_npe) {
					LOGGER.info("Please enter an integer (only).");
					quantity = 1l;
				}
			} while (quantity == null || quantity < 1l);
		}
		OrderLine orderLine = orderLineService.changeItems(new OrderLine(order_id, item_id, quantity));
		return orderLine;
	}

	public List<OrderLine> readItemsInOrder() {
		Long order_id = null;
		do {
			try {
				LOGGER.info("Please enter the id of the order you would like read");
				order_id = Long.valueOf(getInput());
			} catch (NumberFormatException nfe) {
				LOGGER.info("Please enter an integer (only).");
			}
		} while (order_id == null || order_id < 0);
		List<OrderLine> orderLines = orderLineService.readOrder(order_id);
		for (OrderLine orderLine : orderLines) {
			LOGGER.info(orderLine.toString());
		}
		return orderLines;

	}

	public BigDecimal calculateOrderPrice() {
		Long order_id = null;
		do {
			try {
				LOGGER.info("Please enter the id of the order you would like calculate the total cost for");
				order_id = Long.valueOf(getInput());
			} catch (NumberFormatException nfe) {
				LOGGER.info("Please enter an integer (only).");
			}
		} while (order_id == null || order_id < 0);
		BigDecimal totalCost = orderLineService.calculate(order_id);
		LOGGER.info("Order with id " + order_id + " has a total cost of: £" + totalCost);
		return totalCost;
	}
}
