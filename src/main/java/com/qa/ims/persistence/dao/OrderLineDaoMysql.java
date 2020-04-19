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

import com.qa.ims.Ims;
import com.qa.ims.persistence.domain.OrderLine;

public class OrderLineDaoMysql implements DaoLine<OrderLine> {

	public static final Logger LOGGER = Logger.getLogger(OrderLineDaoMysql.class);

	private String jdbcConnectionUrl;
	private String username;
	private String password;

	public OrderLineDaoMysql(String username, String password) {
		this.jdbcConnectionUrl = Ims.getJdbcConnectionUrl();
		this.username = username;
		this.password = password;
	}

	public OrderLine changeItems(OrderLine orderLine) {
		String delete = "delete from orderLine where order_id = ? and item_id = ?";
		String create = "insert into orderLine(order_id, item_id, quantity) values( ? , ? , ?)";
		if (orderLine.getQuantity() == null) {
			try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
					PreparedStatement pstatement = connection.prepareStatement(delete)) {
				pstatement.setLong(1, orderLine.getOrderId());
				pstatement.setLong(2, orderLine.getItemId());
				pstatement.executeUpdate();
			} catch (Exception e) {
				LOGGER.debug(e.getStackTrace());
				LOGGER.error(e.getMessage());
			}
		} else {
			try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
					PreparedStatement pstatement = connection.prepareStatement(create)) {
				pstatement.setLong(1, orderLine.getOrderId());
				pstatement.setLong(2, orderLine.getItemId());
				pstatement.setLong(3, orderLine.getQuantity());
				pstatement.executeUpdate();
			} catch (Exception e) {
				LOGGER.debug(e.getStackTrace());
				LOGGER.error(e.getMessage());
			}
			LOGGER.info(readLatest());
		}
		calculate(orderLine.getOrderId());
		return orderLine;
	}

	OrderLine orderLineFromResultSet(ResultSet resultSet) throws SQLException {
		Long orderId = resultSet.getLong("order_id");
		Long itemId = resultSet.getLong("item_id");
		Long quantity = resultSet.getLong("quantity");
		return new OrderLine(orderId, itemId, quantity);
	}

	public OrderLine readLatest() {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement
						.executeQuery("SELECT * FROM orderLine ORDER BY orderline_id DESC LIMIT 1");) {
			resultSet.next();
			return orderLineFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	public List<OrderLine> readOrder2(Long orderId) {
		String readOrder = "select * from orderLine where order_id = ?";
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				PreparedStatement pstatement = connection.prepareStatement(readOrder)) {
			pstatement.setLong(1, orderId);
			try (ResultSet resultSet = pstatement.executeQuery()) {
				ArrayList<OrderLine> orderLines = new ArrayList<>();
				while (resultSet.next()) {
					orderLines.add(orderLineFromResultSet(resultSet));
				}
				return orderLines;
			}
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}

	public BigDecimal calculate(Long orderId) {
		BigDecimal sum = BigDecimal.valueOf(0.00);
		String calculate = "select quantity,items.price from orderLine join items on orderLine.item_id=items.id where order_id = ? ";
		String updatePrice = "update orders set total_price = ? where order_id = ?";
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				PreparedStatement pStatementCalculate = connection.prepareStatement(calculate);
				PreparedStatement pStatementUpdate = connection.prepareStatement(updatePrice)) {
			pStatementCalculate.setLong(1, orderId);
			try (ResultSet resultSet = pStatementCalculate.executeQuery()) {
				while (resultSet.next()) {
					BigDecimal tempQuantity = BigDecimal.valueOf(resultSet.getLong("quantity"));
					BigDecimal product = tempQuantity.multiply(resultSet.getBigDecimal("price"));
					sum = sum.add(product);
				}
			}
			pStatementUpdate.setBigDecimal(1, sum);
			pStatementUpdate.setLong(2, orderId);
			pStatementUpdate.executeUpdate();
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return sum;
	}

	@Override
	public String readOrder(Long orderId) {
		String readOrder = "select * from orderLine join items on orderLine.item_id=items.id "
				+ "join orders on orderLine.order_id=orders.order_id "
				+ "join customers on orders.cust_id=customers.id where orderLine.order_id = ?";
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				PreparedStatement pstatement = connection.prepareStatement(readOrder)) {
			pstatement.setLong(1, orderId);
			try (ResultSet resultSet = pstatement.executeQuery()) {
				resultSet.next();
				BigDecimal tempQuantity = BigDecimal.valueOf(resultSet.getLong("quantity"));
				BigDecimal product = tempQuantity.multiply(resultSet.getBigDecimal("price"));

				StringBuilder bld = new StringBuilder("Order ID [" + resultSet.getLong("orderLine.order_id") + "]"
						+ "\nplaced: " + resultSet.getString("orders.date") + "\nby customer: "
						+ resultSet.getString("customers.first_name") + " " + resultSet.getString("customers.surname")
						+ "\ntotal price: £" + resultSet.getBigDecimal("orders.total_price") + "\ncontents:" + "\n£"
						+ product + ":\t " + resultSet.getLong("quantity") + " x " + resultSet.getString("items.name"));
				while (resultSet.next()) {
					tempQuantity = BigDecimal.valueOf(resultSet.getLong("quantity"));
					product = tempQuantity.multiply(resultSet.getBigDecimal("price"));
					bld.append("\n£" + product + ":\t " + resultSet.getLong("quantity") + " x "
							+ resultSet.getString("items.name"));
				}
				return bld.toString();
			}
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return null;
	}

}
