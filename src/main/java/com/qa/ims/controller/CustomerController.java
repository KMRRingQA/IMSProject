package com.qa.ims.controller;

import java.util.List;

import org.apache.log4j.Logger;

import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.services.CrudServices;
import com.qa.ims.utils.Utils;

/**
 * Takes in customer details for CRUD functionality
 *
 */
public class CustomerController implements CrudController<Customer> {

	public static final Logger LOGGER = Logger.getLogger(CustomerController.class);

	private CrudServices<Customer> customerService;

	public CustomerController(CrudServices<Customer> customerService) {
		this.customerService = customerService;
	}

	/**
	 * Creates a customer by taking in user input
	 */
	@Override
	public Customer create() {
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

		Customer customer = customerService.create(new Customer(firstName, surname));
		LOGGER.info("Customer created");
		return customer;
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
		customerService.delete(id);
	}

	String getInput() {
		return Utils.getInput();
	}

	/**
	 * Reads all customers to the logger
	 */
	@Override
	public List<Customer> readAll() {
		List<Customer> customers = customerService.readAll();
		for (Customer customer : customers) {
			LOGGER.info(customer.toString());
		}
		return customers;
	}

	/**
	 * Updates an existing customer by taking in user input
	 */
	@Override
	public Customer update() {
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

		Customer customer = customerService.update(new Customer(id, firstName, surname));
		LOGGER.info("Customer Updated");
		return customer;
	}

}
