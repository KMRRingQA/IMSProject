package com.qa.ims.persistence.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.qa.ims.persistence.domain.OrderLine;

public class OrderLineDaoMysql {

	public static final Logger LOGGER = Logger.getLogger(OrderLineDaoMysql.class);

	private String jdbcConnectionUrl;
	private String username;
	private String password;

	public OrderLineDaoMysql(String username, String password) {
		this.jdbcConnectionUrl = "jdbc:mysql://34.67.113.137:3306/ims";
		this.username = username;
		this.password = password;
	}

	public OrderLineDaoMysql(String jdbcConnectionUrl, String username, String password) {
		this.jdbcConnectionUrl = jdbcConnectionUrl;
		this.username = username;
		this.password = password;
	}

	public OrderLine changeItems(OrderLine orderLine) {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();) {

			if (orderLine.getQuantity() == null) {
				statement.executeUpdate("delete from orderLine where order_id = " + orderLine.getOrder_id()
						+ " and item_id = " + orderLine.getItem_id());
			} else {
				statement.executeUpdate(
						"insert into orderLine(order_id, item_id, quantity) values('" + orderLine.getOrder_id() + "','"
								+ orderLine.getItem_id() + "','" + orderLine.getQuantity() + "')");
				LOGGER.info(readLatest());
			}
			calculate(orderLine.getOrder_id());
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	OrderLine orderListFromResultSet(ResultSet resultSet) throws SQLException {
		Long order_id = resultSet.getLong("order_id");
		Long item_id = resultSet.getLong("item_id");
		Long quantity = resultSet.getLong("quantity");
		return new OrderLine(order_id, item_id, quantity);
	}

	public OrderLine readLatest() {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement
						.executeQuery("SELECT * FROM orderLine ORDER BY order_id DESC LIMIT 1");) {
			resultSet.next();
			return orderListFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	public List<OrderLine> readOrder(Long order_id) {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM orderLine where order_id = " + order_id);) {
			System.out.println(order_id);
			ArrayList<OrderLine> orderLines = new ArrayList<>();
			while (resultSet.next()) {
				orderLines.add(orderListFromResultSet(resultSet));
			}
			return orderLines;
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}

	public BigDecimal calculate(Long order_id) {
		BigDecimal sum = BigDecimal.valueOf(0.00);

		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(
						"select quantity,items.price from orderLine join items on orderLine.item_id=items.id where order_id = "
								+ order_id + ";");) {
			while (resultSet.next()) {
				BigDecimal tempQuantity = BigDecimal.valueOf(resultSet.getLong("quantity"));
				BigDecimal product = tempQuantity.multiply(resultSet.getBigDecimal("price"));
				sum = sum.add(product);
			}
			statement.executeUpdate("update orders set total_price = '" + sum + "' where order_id =" + order_id);
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return sum;
	}

}