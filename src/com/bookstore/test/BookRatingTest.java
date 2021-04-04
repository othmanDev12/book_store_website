package com.bookstore.test;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.bookstore.entity.Book;
import com.bookstore.entity.Review;

public class BookRatingTest {
 


	@Test
	public void calculAvergeRatingTest() {
		Book book = new Book();
		Review review = new Review();
		review.setRating(5);
		Set<Review> reviews = new HashSet<Review>();
		reviews.add(review);
		
		float avergeRating = book.calculAvergeRating();
		float expected = 5.0f;
		assertEquals(expected, avergeRating , 5.0);
	}
	
	@Test
	public void calculAvergeRatingTest1() {
		Book book = new Book();
		
		float avregeRating = book.calculAvergeRating();
		float expected = 0.0f;
		
		assertEquals(expected, avregeRating , 0.0);
				
	}
	
	
	@Test
	public void calculAvergeRatingTest2() {
		Book book = new Book();
		Review review1 = new Review();
		review1.setRating(3);
		
		Review review2 = new Review();
		review2.setRating(4);
		
		Review review3 = new Review();
		review3.setRating(4);
		
		Set<Review> reviews = new HashSet<Review>();
		reviews.add(review1);
		reviews.add(review2);
		reviews.add(review3);
		
		int sum = 0;
		float averge = 0.0f;
		
		for(Review review: reviews) {
			sum = sum + review.getRating();
			
			System.out.println("sum is : " + sum);
		}
		
		averge = (float) sum / reviews.size();
		System.out.println(averge);
		
		
		book.setReviews(reviews);
		float avergeRating = book.calculAvergeRating();
		
		assertEquals(3.66, avergeRating, 3.66);
	}
	
	@Test
	public void starsRating() {
		Book book = new Book();
		float avergeRating = 5.0f;
		
		String starsOn = book.ratingString(avergeRating);
		
		assertEquals("on,on,on,on,on", starsOn);
			
	}
	
	@Test
	public void starsRating1() {
		Book book = new Book();
		float avergeRating = 0.0f;
		
		String starsOn = book.ratingString(avergeRating);
		
		assertEquals("off,off,off,off,off", starsOn);
	}
	
	@Test
	public void starsRating2() {
		Book book = new Book();
		float avergeRating = 3.5f;
		
		String starsOn = book.ratingString(avergeRating);
		
		assertEquals("on,on,on,half,off", starsOn);
	}
}

