package com.qa.ims.persistence.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.qa.ims.persistence.domain.Order;

public class OrderDaoMysql implements DaoCRUD<Order> {

	public static final Logger LOGGER = Logger.getLogger(OrderDaoMysql.class);

	private String jdbcConnectionUrl;
	private String username;
	private String password;

	public OrderDaoMysql(String username, String password) {
		this.jdbcConnectionUrl = "jdbc:mysql://34.67.113.137:3306/ims";
		this.username = username;
		this.password = password;
	}

	/**
	 * Creates a customer in the database
	 *
	 * @param customer - takes in a customer object. id will be ignored
	 */
	@Override
	public Order create(Order order) {
		String createWithoutDate = "insert into orders(cust_id, total_price) values(? , ?)";
		String createWithDate = "insert into orders(cust_id,date, total_price) values(? , ?, ?)";

		if (order.getDate().isEmpty()) {
			try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
					PreparedStatement pstatement = connection.prepareStatement(createWithoutDate)) {
				pstatement.setLong(1, order.getCustId());
				pstatement.setBigDecimal(2, order.getTotalPrice());
				pstatement.executeUpdate();
			} catch (Exception e) {
				LOGGER.debug(e.getStackTrace());
				LOGGER.error(e.getMessage());
			}
		} else {
			try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
					PreparedStatement pstatement = connection.prepareStatement(createWithDate)) {
				pstatement.setLong(1, order.getCustId());
				pstatement.setString(2, order.getDate());
				pstatement.setBigDecimal(3, order.getTotalPrice());
				pstatement.executeUpdate();
			} catch (Exception e) {
				LOGGER.debug(e.getStackTrace());
				LOGGER.error(e.getMessage());
			}
		}
		LOGGER.info(readLatest());

		return order;
	}

	/**
	 * Deletes a customer in the database
	 *
	 * @param id - id of the customer
	 */

	@Override
	public void delete(long id) {
		String delete = "delete from orders where order_id = ?";
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				PreparedStatement pstatement = connection.prepareStatement(delete)) {
			pstatement.setLong(1, id);
			pstatement.executeUpdate();
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
	}

	Order orderFromResultSet(ResultSet resultSet) throws SQLException {
		Long orderId = resultSet.getLong("order_id");
		Long custId = resultSet.getLong("cust_id");
		String date = resultSet.getString("date");
		BigDecimal totalPrice = resultSet.getBigDecimal("total_price");
		return new Order(orderId, custId, date, totalPrice);
	}

	/**
	 * Reads all customers from the database
	 *
	 * @return A list of customers
	 */
	@Override
	public List<Order> readAll() {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("select * from orders");) {
			ArrayList<Order> orders = new ArrayList<>();
			while (resultSet.next()) {
				orders.add(orderFromResultSet(resultSet));
			}
			return orders;
		} catch (SQLException e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}

	public Order readLatest() {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM orders ORDER BY order_id DESC LIMIT 1");) {
			resultSet.next();
			return orderFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	public Order readOrder(Long orderId) {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM orders where order_id = " + orderId);) {
			resultSet.next();
			return orderFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Updates a customer in the database
	 *
	 * @param customer - takes in a customer object, the id field will be used to
	 *                 update that customer in the database
	 * @return
	 */

	@Override
	public Order update(Order order) {
		String update = "update orders set cust_id = ? , date = ? , total_price = ? where order_id = ?;";
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				PreparedStatement pstatement = connection.prepareStatement(update)) {
			pstatement.setLong(1, order.getCustId());
			pstatement.setString(2, order.getDate());
			pstatement.setBigDecimal(3, order.getTotalPrice());
			pstatement.setLong(4, order.getOrderId());
			pstatement.executeUpdate();
			return readOrder(order.getOrderId());
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public List<Order> searchName(String name) {
		String searchName = "select * from orders join customers on orders.cust_id=customers.id where lower(customers.first_name) = ? and lower(customers.surname) = ?;";
		String firstName = name.split(" ")[0].toLowerCase();
		String surname = name.split(" ")[1].toLowerCase();
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				PreparedStatement pstatement = connection.prepareStatement(searchName)) {
			pstatement.setString(1, firstName);
			pstatement.setString(2, surname);
			try (ResultSet resultSet = pstatement.executeQuery()) {
				ArrayList<Order> orders = new ArrayList<>();
				while (resultSet.next()) {
					orders.add(orderFromResultSet(resultSet));
				}
				return orders;
			}
		} catch (SQLException e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}

}
