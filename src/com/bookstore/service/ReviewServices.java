package com.bookstore.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookstore.dao.BookDao;
import com.bookstore.dao.ReviewDao;
import com.bookstore.entity.Book;
import com.bookstore.entity.Customer;
import com.bookstore.entity.Review;

public class ReviewServices {
	// INSTANCE VARIABLE;
	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	private ReviewDao reviewDao;
	private BookDao bookDao;
	
	
	//CONSTRUCTORS;
	public ReviewServices() {
		entityManagerFactory = Persistence.createEntityManagerFactory("BookStoreWebsite");
		entityManager = entityManagerFactory.createEntityManager();
		reviewDao = new ReviewDao(entityManager);
		bookDao = new BookDao(entityManager);
	}
	
	// business logic;
	public void listAllReviews(HttpServletRequest request , HttpServletResponse response , String message) throws ServletException, IOException {
		List<Review> listReviews  = reviewDao.listAll();
		
		// send a message to the client; 
		if(message != null) {
			request.setAttribute("message", message);
		}
		// send the review list to the client;
		request.setAttribute("listReviews", listReviews);
		// invoke the list_review.jsp file in the SERVLET;
		String listReviewPage = "review_list.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(listReviewPage);
		requestDispatcher.forward(request , response);
	}
	
	public void editReview(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		
		Review getReview = reviewDao.get(id);
		// send the review object to the client;
		request.setAttribute("review", getReview );
		
		// invoke review_form.jsp into the SERVLET;
		String formPage = "review_form.jsp";
		RequestDispatcher  requestDispatcher = request.getRequestDispatcher(formPage);
		requestDispatcher.forward(request, response);
	}
	
	public void updateReview(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		String comment = request.getParameter("comment");
		String headline = request.getParameter("headline");
		
		Review exsistReview = reviewDao.get(id);
		exsistReview.setReviewId(id);
		exsistReview.setReviewTime(new Date());
		exsistReview.setComment(comment);
		exsistReview.setHeadline(headline);
		
		reviewDao.Update(exsistReview);
		listAllReviews(request, response, "the review was updated");
	}
	
	public void deleteReview(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {
		Integer reviewId = Integer.parseInt(request.getParameter("id"));
		
		reviewDao.delete(reviewId);
		listAllReviews(request, response, "the review was deleted");
	}
	
	public void showReviewForm(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {
		Integer bookId = Integer.parseInt(request.getParameter("book_id"));
		Book book = bookDao.get(bookId);
		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("loggedcustomer");
		session.setAttribute("book", book);
		Review getReviewByBookAndCustomer = reviewDao.findReviewByCustomerAndBook(bookId, customer.getCustomerId());
		
		// check if already has A review with the same customer and book;
		String forReviewPage = "/frontend/review_form.jsp";
		if(getReviewByBookAndCustomer != null) {
			request.setAttribute("review", getReviewByBookAndCustomer);
			forReviewPage = "/frontend/review_info.jsp";
		}
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(forReviewPage);
		requestDispatcher.forward(request, response);
	}
	
	public void submitedReview(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {
		String idString = request.getParameter("id");
		Integer bookId = Integer.parseInt(idString);
		//Integer id = Integer.parseInt(request.getParameter("id"));
		Book book = new Book();
		book.setBookId(bookId);
		
		Customer customer = (Customer) request.getSession().getAttribute("loggedcustomer");
		String ratingString = request.getParameter("rating");
		Integer rating = Integer.parseInt(ratingString);
		String headline = request.getParameter("headline");
		String comment = request.getParameter("comment");
		
		Review review = new Review();
		//review.setReviewId(id);
		review.setBook(book);
		review.setHeadline(headline);
		review.setRating(rating); 
		review.setComment(comment);
		review.setCustomer(customer);
		review.setReviewTime(new Date());
		
		reviewDao.Create(review);
		
		String messagePage = "/frontend/review_done.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(messagePage);
		requestDispatcher.forward(request, response);
	}
	
}
