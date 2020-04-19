package com.qa.ims.persistence.dao;

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
import com.qa.ims.persistence.domain.Customer;

public class CustomerDaoMysql implements DaoCRUD<Customer> {

	public static final Logger LOGGER = Logger.getLogger(CustomerDaoMysql.class);

	private String jdbcConnectionUrl;
	private String username;
	private String password;

	public CustomerDaoMysql(String username, String password) {
		this.jdbcConnectionUrl = Ims.getJdbcConnectionUrl();
		this.username = username;
		this.password = password;
	}

	/**
	 * Creates a customer in the database
	 *
	 * @param customer - takes in a customer object. id will be ignored
	 */

	@Override
	public Customer create(Customer customer) {
		String create = "insert into customers(first_name, surname) values(? , ?)";
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				PreparedStatement pstatement = connection.prepareStatement(create)) {
			pstatement.setString(1, customer.getFirstName());
			pstatement.setString(2, customer.getSurname());
			pstatement.executeUpdate();
			LOGGER.info(readLatest());
			return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return customer;
	}

	Customer customerFromResultSet(ResultSet resultSet) throws SQLException {
		Long id = resultSet.getLong("id");
		String firstName = resultSet.getString("first_name");
		String surname = resultSet.getString("surname");
		return new Customer(id, firstName, surname);
	}

	/**
	 * Deletes a customer in the database
	 *
	 * @param id - id of the customer
	 */
	@Override
	public void delete(long id) {
		String delete = "delete from customers where id = ?";
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				PreparedStatement pstatement = connection.prepareStatement(delete)) {
			pstatement.setLong(1, id);
			pstatement.executeUpdate();
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
	}

	/**
	 * Reads all customers from the database
	 *
	 * @return A list of customers
	 */
	@Override
	public List<Customer> readAll() {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("select * from customers");) {
			ArrayList<Customer> customers = new ArrayList<>();
			while (resultSet.next()) {
				customers.add(customerFromResultSet(resultSet));
			}
			return customers;
		} catch (SQLException e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}

	public Customer readCustomer(Long id) {
		String readCustomer = "SELECT * FROM customers where id = ?";
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				PreparedStatement pstatement = connection.prepareStatement(readCustomer)) {
			pstatement.setLong(1, id);
			pstatement.executeQuery();
			try (ResultSet resultSet = pstatement.executeQuery();) {
				resultSet.next();
				return customerFromResultSet(resultSet);
			}
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	public Customer readLatest() {
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM customers ORDER BY id DESC LIMIT 1");) {
			resultSet.next();
			return customerFromResultSet(resultSet);
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
	public Customer update(Customer customer) {
		String update = "update customers set first_name = ? , surname = ?  where id =?";
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				PreparedStatement pstatement = connection.prepareStatement(update)) {
			pstatement.setString(1, customer.getFirstName());
			pstatement.setString(2, customer.getSurname());
			pstatement.setLong(3, customer.getId());
			pstatement.executeUpdate();
			return readCustomer(customer.getId());
		} catch (Exception e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public List<Customer> searchName(String name) {
		String searchName = "select * from customers where lower(first_name) = ? and lower(surname) = ?;";
		String firstName = name.split(" ")[0].toLowerCase();
		String surname = name.split(" ")[1].toLowerCase();
		try (Connection connection = DriverManager.getConnection(jdbcConnectionUrl, username, password);
				PreparedStatement pstatement = connection.prepareStatement(searchName)) {
			pstatement.setString(1, firstName);
			pstatement.setString(2, surname);
			try (ResultSet resultSet = pstatement.executeQuery()) {
				ArrayList<Customer> customers = new ArrayList<>();
				while (resultSet.next()) {
					customers.add(customerFromResultSet(resultSet));
				}
				return customers;
			}
		} catch (SQLException e) {
			LOGGER.debug(e.getStackTrace());
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}

}
