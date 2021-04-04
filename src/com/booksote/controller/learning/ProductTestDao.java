package com.booksote.controller.learning;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Book;
import com.bookstore.entity.Users;


public class ProductTestDao {
	private static EntityManagerFactory entityMangerFactory;
	private static EntityManager entityManger;
	private static ProductDao productDao;
	
	// this method is for initialize the resources that we need to build this test Class
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		entityMangerFactory = Persistence.createEntityManagerFactory("BookStoreWebsite");
		entityManger = entityMangerFactory.createEntityManager();
		productDao = new ProductDao(entityManger);
	}

	// this method is for fire up the resources in the setUpBeforeClass();
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		entityManger.close();
		entityMangerFactory.close();
	}

	@Test
	public void createTest() {
		Users newUser = new Users();
		newUser.setEmail("hash99@gmail.com");
		newUser.setFullName("hash map");
		newUser.setPassword("hash123456");
		
		Users user = productDao.Create(newUser);
		
		assertTrue(user != null && user.getUserId() > 0);
	}
	
	@Test
	public void updateTest() {
		Users exsistUser = new Users();
		exsistUser.setUserId(1);
		exsistUser.setEmail("hash99@gmail.com");
		exsistUser.setPassword("hash1345");
		exsistUser.setFullName("hash table");
		
		Users updateUser = productDao.Update(exsistUser);
		
		Integer expected = 1;
		assertEquals(expected, updateUser.getUserId());
	}
	
	@Test
	public void getTest() {
		Integer userId = 1;
		Users getUser = productDao.get(userId);
		
		assertTrue(getUser != null);
	}
	
	@Test 
	public void deleteTest() {
		Integer userId = 1;
		Users deleteUsers = productDao.delete(userId);
		
		assertTrue(deleteUsers != null);
	}
	
	@Test
	public void listAllTest() {
		List<Users> listUsers = productDao.listAll();
		
		assertTrue(listUsers != null && listUsers.size() > 0);
	}
	
	@Test
	public void countTest() {
		long count = productDao.count();
		
		assertTrue(count > 0);
	}
	
	@Test
	public void findByEmailTest() {
		String email = "hash99@gmail.com";
		Users getEmail = productDao.findUsersByEmail(email);
		
		assertTrue(getEmail != null);
	}
	
	@Test
	public void searchBooksTest() {
		String searchKeyword = "Java";
		List<Users> listBook = productDao.SearchBooks(searchKeyword);
		
		assertTrue(listBook != null && listBook.size() > 0);
	}

}
