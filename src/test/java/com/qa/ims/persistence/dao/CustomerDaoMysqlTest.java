package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.Ims;
import com.qa.ims.controller.CustomerController;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.services.CustomerServices;

@RunWith(MockitoJUnitRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CustomerDaoMysqlTest {

	/**
	 * The thing I want to fake functionality for
	 */
	@Mock
	private CustomerServices customerServices;

	/**
	 * Spy is used because i want to mock some methods inside the item I'm testing
	 * InjectMocks uses dependency injection to insert the mock into the customer
	 * controller
	 */
	@Spy
	@InjectMocks
	private CustomerController customerController;

	@Test
	public void aInit() {
		Ims ims = new Ims();
		ims.init("jdbc:mysql://34.67.113.137:3306/", "root", "root", "src/test/resources/sql-schema.sql");
	}

	@Test
	public void bCreateTest() {
		CustomerDaoMysql customerDaoMysql = new CustomerDaoMysql("root", "root");
		String firstName = "Korbinian";
		String surname = "Ring";
		Customer customer = new Customer(firstName, surname);
		assertEquals(customer, customerDaoMysql.create(customer));
	}

	@Test
	public void cReadAllTest() {
		CustomerDaoMysql customerDaoMysql = new CustomerDaoMysql("root", "root");
		List<Customer> customers = new ArrayList<>();
		customers.add(new Customer(1L, "Luke", "Conway"));
		customers.add(new Customer(2L, "Korbinian", "Ring"));
		assertEquals(customers, customerDaoMysql.readAll());
	}

	@Test
	public void dReadLatestTest() {
		CustomerDaoMysql customerDaoMysql = new CustomerDaoMysql("root", "root");
		Customer customer = new Customer(2L, "Korbinian", "Ring");
		assertEquals(customer, customerDaoMysql.readLatest());
	}

	@Test
	public void eReadCustomerTest() {
		CustomerDaoMysql customerDaoMysql = new CustomerDaoMysql("root", "root");
		Customer customer = new Customer(1L, "Luke", "Conway");
		assertEquals(customer, customerDaoMysql.readCustomer(1L));
	}

	/**
	 * 
	 */
	@Test
	public void fUpdateTest() {
		CustomerDaoMysql customerDaoMysql = new CustomerDaoMysql("root", "root");
		String id = "1";
		String firstName = "Rhys";
		String surname = "Thompson";
		Customer customer = new Customer(Long.parseLong(id), firstName, surname);
		assertEquals(customer, customerDaoMysql.update(customer));
	}

	/**
	 * Delete doesn't return anything, so we can just verify that it calls the
	 * delete method
	 */
	@Test
	public void gDeleteTest() {
		CustomerDaoMysql customerDaoMysql = new CustomerDaoMysql("root", "root");
		String id = "1";
		customerDaoMysql.delete(Long.parseLong(id));
		List<Customer> customers = new ArrayList<>();
		customers.add(new Customer(2L, "Korbinian", "Ring"));
		assertEquals(customers, customerDaoMysql.readAll());
	}

}
