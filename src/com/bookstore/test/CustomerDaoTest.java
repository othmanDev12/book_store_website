package com.bookstore.test;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.dao.CustomerDao;
import com.bookstore.entity.Customer;

public class CustomerDaoTest {
	// STATIC VARIABLES;
	private static EntityManagerFactory entityMangerFactory;
	private static EntityManager entityManger;
	private static CustomerDao customerDao;

	// this function is for initial all resources that you need in your test;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		entityMangerFactory = Persistence.createEntityManagerFactory("BookStoreWebsite");
		entityManger = entityMangerFactory.createEntityManager();
		customerDao = new CustomerDao(entityManger);
	}

	// this function is fired up the resources in the setUpBeforeClass();
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		entityManger.close();
		entityMangerFactory.close();
	}

	@Test
	public void testCreateCustomer() {
		// create new instance of customer object;
		Customer newCustomer = new Customer();
		newCustomer.setEmail("trumpUsa@gmail.com");
		newCustomer.setFullName("Downald Trump");
		newCustomer.setAddress("591 Memorial Dr, Chicopee MA 1020");
		newCustomer.setCity("Los Angelos");
		newCustomer.setCountry("united states");
		newCustomer.setPhone("744.864.34209");
		newCustomer.setZipcode("90001");
		newCustomer.setPassword("localhosting");
		newCustomer.setRegisterDate(new Date());
		
		Customer customer = customerDao.Create(newCustomer);
		
		assertTrue(customer.getCustomerId() > 0 && customer != null);
	}
	
	@Test
	public void testUpdateCustomer() {
		Customer existCustomer = new Customer();
		existCustomer.setCustomerId(11);
		existCustomer.setEmail("david123@gmail.com");
		existCustomer.setFullName("David hosting");
		existCustomer.setAddress("250 Hartford Avenue, Bellingham MA 2019");
		existCustomer.setCity("New York");
		existCustomer.setCountry("united states");
		existCustomer.setPhone("787.797.8097");
		existCustomer.setZipcode("90073");
		existCustomer.setPassword("localhosting");
		existCustomer.setRegisterDate(new Date());
		
		Customer updateCustomer = customerDao.Update(existCustomer);
		Integer expected = 11;
		assertEquals(expected, updateCustomer.getCustomerId());
		
	}
	
	@Test
	public void testGetCustomer() {
		Integer getCustomerId = 13;
		Customer getCustomer = customerDao.get(getCustomerId);
		assertTrue(getCustomer != null);
	}
	
	@Test
	public void testDeleteCustomer() {
		Integer deleteCustomerId = 13;
		Customer deleteCustomer = customerDao.delete(deleteCustomerId);
		
		assertTrue(deleteCustomer != null);
		
	}
	@Test
	public void testListAll() {
		List<Customer> customers = customerDao.listAll();
		
		assertTrue(customers.size() > 0 && customers != null);
	}
	
	@Test
	public void testCount() {
		long count = customerDao.count();
		
		assertTrue(count > 0);
	}
	
	@Test
	public void testFindCustomerByEmail() {
		String email = "trumpUsa@gmail.com";
		Customer existCustomer = customerDao.findCustomerByEmail(email);
		
		assertTrue(existCustomer != null);
	}
	
	@Test
	public void testCheckLogin() {
		String email = "omoudden545@gmail.com";
		String password = "bleachnaruto1999";
		
		Customer loginBoolean = customerDao.checkLogin(email, password);
		assertNotNull(loginBoolean);
	}
	


}
