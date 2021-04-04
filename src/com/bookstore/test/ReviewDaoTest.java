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

import com.bookstore.dao.ReviewDao;
import com.bookstore.entity.Book;
import com.bookstore.entity.Customer;
import com.bookstore.entity.Review;


public class ReviewDaoTest {
	// static variable;
	private static EntityManager entityManger;
	private static EntityManagerFactory entityMangerFactory;
	private static ReviewDao reviewDao;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		entityMangerFactory = Persistence.createEntityManagerFactory("BookStoreWebsite");
		entityManger = entityMangerFactory.createEntityManager();
		reviewDao = new ReviewDao(entityManger);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		entityManger.close();
		entityMangerFactory.close();
	}

	@Test
	public void createTest() {
		//create new Instance Review Object;
		Review newReview = new Review();
		
		Book book = new Book();
		book.setBookId(33);
		Customer customer = new Customer();
		customer.setCustomerId(17);
		
		newReview.setBook(book);
		newReview.setCustomer(customer);
		newReview.setHeadline("good book");
		newReview.setRating(5);
		newReview.setComment("This book describe the basic of java with algoritms and data structure");
		newReview.setReviewTime(new Date());
		
		Review createReview = reviewDao.Create(newReview);
		assertTrue(createReview != null && createReview.getReviewId() > 0);
	}
	
	@Test
	public void updateTest() {
		Integer reviewId = 15;
		Review getReview = reviewDao.get(reviewId);
		
		Book book = new Book();
		book.setBookId(33);
		Customer customer = new Customer();
		customer.setCustomerId(13);
		
		getReview.setReviewId(reviewId);
		getReview.setBook(book);
		getReview.setCustomer(customer);
		getReview.setHeadline("the is a very good book");
		getReview.setRating(5);
		getReview.setComment("EveryOne must read this book");
		getReview.setReviewTime(new Date());
		
		Review updateReview = reviewDao.Update(getReview);
		
		assertEquals(getReview.getHeadline(), updateReview.getHeadline());
	}
	
	@Test 
	public void getTest() {
		Integer reviewId = 15;
		Review getReview = reviewDao.get(reviewId);
		
		assertTrue(getReview != null);
		
	}
	
	@Test
	public void deleteTest() {
		Integer reviewId = 15;
		Review deleteReview = reviewDao.delete(reviewId);
		
		assertTrue(deleteReview != null);
	}
	
	@Test 
	public void listAllTest() {
		List<Review> reviews = reviewDao.listAll();
		
		for(Review review: reviews) {
			System.out.println("review Id: " + review.getReviewId());
			System.out.println("book title:" + review.getBook().getTitle());
			System.out.println("Customer full name: " + review.getCustomer().getFullName());
			System.out.println("headline: " + review.getHeadline());
			System.out.println("rating is: " + review.getRating());
			System.out.println("comment is: " + review.getComment());
			System.out.println("review time is: " + review.getReviewTime());
		}
		
		assertTrue(reviews != null && reviews.size() > 0);
	}
	
	@Test
	public void countTest() {
		long count = reviewDao.count(); 
		assertTrue(count > 0);
	}
	
	@Test
	public void testFindReviewByCustomerAndBook() {
		Integer customerId = 11;
		Integer bookId = 35;
		
		Review review = reviewDao.findReviewByCustomerAndBook(bookId, customerId);
		assertTrue(review != null);
	}
}
