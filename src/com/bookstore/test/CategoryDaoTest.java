package com.bookstore.test;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.dao.CategoryDao;
import com.bookstore.entity.Category;

public class CategoryDaoTest {
	
	private static EntityManager entityManger;
	private static EntityManagerFactory entityMangerFactory;
	private static CategoryDao categoryDao;

	// the method defined all resource that you need in the test classes;
	@BeforeClass
	public static void setUpClass() throws Exception {
		entityMangerFactory = Persistence.createEntityManagerFactory("BookStoreWebsite");
		entityManger = entityMangerFactory.createEntityManager();
		categoryDao = new CategoryDao(entityManger);
	}
	
	@Test
	public void testCraeteCategory() {
		Category category = new Category();
		category.setName("Programming");
		
		Category category2 = categoryDao.Create(category);
		
		assertTrue(category2 != null && category.getCategoryId() > 0);
	}
	
	@Test
	public void testUpdateCategory() {
	    Category category = new Category();
	    category.setCategoryId(18);
	    category.setName("Python");
	    categoryDao.Update(category);
	 
	    Integer expected = 18;
	    assertEquals(expected, category.getCategoryId());
	}
	
	@Test
	public void testGetCategory() {
		Integer categoryId = 11;
		Category category = categoryDao.get(categoryId);
		
		assertNotNull(category);
	}
	
	@Test
	public void testDeleteCategory() {
		Integer categoryId = 11;
		Category category = categoryDao.delete(categoryId);
		
		assertNotNull(category);
	}
	
	@Test
	public void testListAll() {
		List<Category> categories = categoryDao.listAll();
		
		assertTrue(categories.size() > 0);
	}
	
	@Test
	public void testCount() {
		long count = categoryDao.count();
		
		assertTrue(count >= 0);
	}
	
	@Test
	public void testFindByName()  {
		String name = "Programming";
		Category categoryName = categoryDao.findByName(name);
		
		assertNotNull(categoryName);
	}
	
	// the method is fired up the setUpClass();
	@AfterClass
	public static void tearDownClass() throws Exception {
		entityManger.close();
		entityMangerFactory.close();
	}

}
