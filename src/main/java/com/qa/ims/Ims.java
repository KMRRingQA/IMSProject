package com.qa.ims;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.qa.ims.controller.Action;
import com.qa.ims.controller.CrudController;
import com.qa.ims.controller.CustomerController;
import com.qa.ims.controller.ItemController;
import com.qa.ims.controller.OrderController;
import com.qa.ims.persistence.dao.CustomerDaoMysql;
import com.qa.ims.persistence.dao.ItemDaoMysql;
import com.qa.ims.persistence.dao.OrderDaoMysql;
import com.qa.ims.persistence.domain.Domain;
import com.qa.ims.services.CustomerServices;
import com.qa.ims.services.ItemServices;
import com.qa.ims.services.OrderServices;
import com.qa.ims.utils.Utils;

public class Ims {

	public static final Logger LOGGER = Logger.getLogger(Ims.class);

	public void doAction(CrudController<?> crudController, Action action) {
		switch (action) {
		case CREATE:
			crudController.create();
			break;
		case READ:
			crudController.readAll();
			break;
		case UPDATE:
			crudController.update();
			break;
		case DELETE:
			crudController.delete();
			break;
		case RETURN:
			break;
		default:
			break;
		}
	}

	public void imsSystem() {
		LOGGER.info("Enter username");
		String username = Utils.getInput();
		LOGGER.info("Enter password");
		String password = Utils.getInput();

		init(username, password);

		while (true) {
			LOGGER.info("Which entity would you like to use?");
			Domain.printDomains();

			Domain domain = Domain.getDomain();
			LOGGER.info("What would you like to do with " + domain.name().toLowerCase() + ":");

			if (domain.name().toLowerCase().equals("order")) {
				Action.printOrderActions();
			} else {
				Action.printActions();
			}
			Action action = Action.getAction();

			switch (domain) {
			case CUSTOMER:
				CustomerController customerController = new CustomerController(
						new CustomerServices(new CustomerDaoMysql(username, password)));
				doAction(customerController, action);
				break;
			case ITEM:
				ItemController itemController = new ItemController(
						new ItemServices(new ItemDaoMysql(username, password)));
				doAction(itemController, action);
				break;
			case ORDER:
				String name = action.name().toLowerCase();
				if (name.equals("create") || name.equals("read") || name.equals("update") || name.equals("delete")) {
					OrderController orderController = new OrderController(
							new OrderServices(new OrderDaoMysql(username, password)));
					doAction(orderController, action);
				} else if (name.equals("additem") || name.equals("removeitem") || name.equals("calculateorderprice")
						|| name.equals("readitems")) {
					System.out.println("this is a special action" + action.name());
					OrderListController orderListController = new OrderListController(
							new OrderListServices(new OrderListDaoMysql(username, password)));
					doAction(orderListController, action);
				}
				break;
			case STOP:
				break;
			default:
				break;
			}
			if (!action.name().equalsIgnoreCase("return")) {
				LOGGER.info("Task completed succesfully.\n");
			}
		}
	}

	/**
	 * To initialise the database schema. DatabaseConnectionUrl will default to
	 * localhost.
	 *
	 * @param username
	 * @param password
	 */
	public void init(String username, String password) {
		init("jdbc:mysql://34.67.113.137:3306/", username, password, "src/main/resources/sql-schema.sql");
	}

	/**
	 * To initialise the database with the schema needed to run the application
	 */
	public void init(String jdbcConnectionUrl, String username, String password, String fileLocation) {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				BufferedReader br = new BufferedReader(new FileReader(fileLocation));) {
			String string;
			while ((string = br.readLine()) != null) {
				try (Statement statement = connection.createStatement();) {
					statement.executeUpdate(string);
				}
			}
		} catch (SQLException | IOException e) {
			for (StackTraceElement ele : e.getStackTrace()) {
				LOGGER.debug(ele);
			}
			LOGGER.error(e.getMessage());
		}
	}

	public String readFile(String fileLocation) {
		StringBuilder stringBuilder = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new FileReader(fileLocation));) {
			String string;
			while ((string = br.readLine()) != null) {
				stringBuilder.append(string);
				stringBuilder.append("\r\n");
			}
		} catch (IOException e) {
			for (StackTraceElement ele : e.getStackTrace()) {
				LOGGER.debug(ele);
			}
			LOGGER.error(e.getMessage());
		}
		return stringBuilder.toString();
	}

}
