package com.bookstore.test;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.dao.UsersDao;
import com.bookstore.entity.Users;


public class UsersDaoTes {
	
	// static variable to avoid duplication;
	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;
	private static UsersDao usersDao;
	
	// this method is for initialize the resources required by the test method;
	@BeforeClass
	public static void setUpClass() {
		// create Entity manger factory object 
	     entityManagerFactory  = Persistence.createEntityManagerFactory("BookStoreWebsite");
		// create Entity manger object for persistence;
	     entityManager = entityManagerFactory.createEntityManager();
		
		 usersDao = new UsersDao(entityManager);
	}

	@Test
	public void testCreateUsers() {
		Users users = new Users("sancho71@gmail.com" , "sancho77" , "Jadon Sancho");
		Users userDao = usersDao.Create(users);
		
		assertTrue(userDao != null &&  users.getUserId() > 0);
	}

	@Test
	public void testUpdateUsers() {
		Users user = new Users();
		user.setUserId(5);
		user.setEmail("sancho7@gmail.com");
		user.setPassword("sancho77");
		user.setFullName("Jadon Sancho");
		usersDao.Update(user);
		
		Integer expected = 5;
	
		assertEquals(expected, user.getUserId());
	}
	
	@Test
	public void  testGetUserfound() {
       Integer userId = 19;
     Users users = usersDao.get(userId);
       if(!users.equals(null)) {
    	   System.out.println(users.getEmail());
       }
       assertNotNull(users);
	}
	
	@Test
	public void testGetUserNotFound() {
		Integer userId = 99;
		Users users = usersDao.get(userId);
		
		assertNull(users);
	}
	
	@Test
	public void testDeleteUser() {
	    Integer userId = 19;
	    Users users = usersDao.delete(userId);
	    
	    assertNotNull(users);
	}
	
	// expected is for testing the null entity;
	@Test(expected = Exception.class)
	public void testDeleteUsersNotExist() {
		Integer userId = 55;
		usersDao.delete(userId);
	}
	
	@Test
	public void testFindAll() {
		List<Users> listUsers = usersDao.listAll();
		// Enhanced for statement;
		for(Users user: listUsers) {
			System.out.println(user.getEmail());
		}
		
		assertTrue(listUsers.size() > 0);
	}
	
	@Test
	public void testFindEmail() {
		String email = "sancho71@gmail.com";
		Users findEmail = usersDao.findByEmail(email);
		
		assertNotNull(findEmail);
	}
	

	@Test
	public void testCount() {
		long countUsers = usersDao.count();
		assertTrue(countUsers > 0);
	}
	
	@Test
	public void testCheckLogin() {
		String email = "cr7@gmail.com";
		String password = "cr7Ronaldo";
		
		Boolean loginResualt = usersDao.checkLogin(email, password);
		
		assertTrue(loginResualt);
	}
	
	@Test
	public void testCheckLoginFaild() {
		String email = "omoudden545@gmail.com";
		String password = "othman";
		 
		Boolean loginResualt = usersDao.checkLogin(email, password);
		
		assertFalse(loginResualt);
	}
	
	// this method have a purpose to free up the resources in the setUpClass();
	@AfterClass
	public static void tearDownClass() {
		entityManager.close();
		entityManagerFactory.close();
	}
	
	

}
