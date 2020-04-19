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

import com.qa.ims.persistence.domain.Item;

public class ItemDaoMysql implements DaoCRUD<Item> {

	public static final Logger LOGGER = Logger.getLogger(ItemDaoMysql.class);

	private String jdbcConnectionUrl;
	private String username;
	private String password;

	public ItemDaoMysql(String username, String password) {
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
	public Item create(Item item) {
		String create = "insert into items(name, price, stock) values(?,?,?)";
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				PreparedStatement pstatement = connection.prepareStatement(create)) {
			pstatement.setString(1, item.getName());
			pstatement.setBigDecimal(2, item.getPrice());
			pstatement.setLong(3, item.getStock());
			pstatement.executeUpdate();
			LOGGER.info(readLatest());
			return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return item;
	}

	/**
	 * Deletes a customer in the database
	 *
	 * @param id - id of the customer
	 */

	@Override
	public void delete(long id) {
		String delete = "delete from items where id = ?";
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				PreparedStatement pstatement = connection.prepareStatement(delete)) {
			pstatement.setLong(1, id);
			pstatement.executeUpdate();
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
	}

	Item itemFromResultSet(ResultSet resultSet) throws SQLException {
		Long id = resultSet.getLong("id");
		String name = resultSet.getString("name");
		BigDecimal price = resultSet.getBigDecimal("price");
		Long stock = resultSet.getLong("stock");
		return new Item(id, name, price, stock);
	}

	/**
	 * Reads all customers from the database
	 *
	 * @return A list of customers
	 */
	@Override
	public List<Item> readAll() {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("select * from items");) {
			ArrayList<Item> items = new ArrayList<>();
			while (resultSet.next()) {
				items.add(itemFromResultSet(resultSet));
			}
			return items;
		} catch (SQLException e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}

	public Item readItem(Long id) {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM items where id = " + id);) {
			resultSet.next();
			return itemFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	public Item readLatest() {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM items ORDER BY id DESC LIMIT 1");) {
			resultSet.next();
			return itemFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Updates a customer in the database
	 *
	 * @param item - takes in a customer object, the id field will be used to update
	 *             that customer in the database
	 * @return
	 */

	@Override
	public Item update(Item item) {
		String update = "update items set name = ? , price = ? , stock = ? where id = ? ";
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				PreparedStatement pstatement = connection.prepareStatement(update)) {
			pstatement.setString(1, item.getName());
			pstatement.setBigDecimal(2, item.getPrice());
			pstatement.setLong(3, item.getStock());
			pstatement.setLong(4, item.getId());
			pstatement.executeUpdate();
			return readItem(item.getId());
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public List<Item> searchName(String name) {
		String searchName = "select * from items where lower(name) = ? ;";
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				PreparedStatement pstatement = connection.prepareStatement(searchName)) {
			pstatement.setString(1, name);
			try (ResultSet resultSet = pstatement.executeQuery()) {
				ArrayList<Item> items = new ArrayList<>();
				while (resultSet.next()) {
					items.add(itemFromResultSet(resultSet));
				}
				return items;
			}
		} catch (SQLException e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}

}
