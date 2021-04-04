package com.booksote.controller.learning.backend;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.metamodel.SetAttribute;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sound.midi.VoiceStatus;

import org.jvnet.fastinfoset.VocabularyApplicationData;

import com.booksote.controller.learning.ProductDao;
import com.bookstore.entity.Book;
import com.bookstore.entity.Users;

public class ProductServices {
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	private EntityManager entityManager;
	private EntityManagerFactory entityManagerFactory;
	private ProductDao productDao;

	public ProductServices() {
		entityManagerFactory = Persistence.createEntityManagerFactory("BookStoreWebsite");
		entityManager = entityManagerFactory.createEntityManager();
		productDao = new ProductDao(entityManager);
	}
	
	// business logic;
	public void listAll(String message) throws ServletException, IOException {
		List<Users> listUsers = productDao.listAll();
		// sending a request to the browser.
		request.setAttribute("listusers", listUsers);
		if(message != null) {
			request.setAttribute("message", message);
		}
		// invoke any JSP page into SERVLET;
		String userListPage = "user_list.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(userListPage);
		requestDispatcher.forward(request, response);
	}
	
	public void createUser() throws ServletException, IOException {
		// get values Entered by the user in the input field using getParameter();
		String fullname = request.getParameter("fullname");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		Users getUserByEmail = productDao.findUsersByEmail(email);
		
		if(getUserByEmail != null) {
			String errorMessage = "we already have this email in database";
			request.setAttribute("message", errorMessage);
			
			String messagePage = "message.jsp";
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(messagePage);
			requestDispatcher.forward(request, response);
		}
		// create a new instance of users class;
		else {
			Users user = new Users();
			user.setPassword(password);
			user.setFullName(fullname);
			user.setEmail(email);
			productDao.Create(user);
			listAll("the user was created");
		}

		
	}
	
	public void getCreateUser() throws ServletException, IOException {
		String userForm = "user_create.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(userForm);
		requestDispatcher.forward(request, response);
	}
	
	public void editUser() throws ServletException, IOException {
	   Integer usersId = Integer.parseInt(request.getParameter("id"));
	   Users getUserByEmail =  productDao.get(usersId);
	   request.setAttribute("user", getUserByEmail);
	   getCreateUser();
	}
	
	public void updateUser() throws ServletException, IOException {
		Integer userId = Integer.parseInt(request.getParameter("id"));
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String fullname = request.getParameter("fullname");
		
		Users getUserById = productDao.get(userId);
		Users getUserByEmail = productDao.findUsersByEmail(email);
		
		if(getUserByEmail != null && getUserByEmail.getUserId() != getUserById.getUserId()) {
			String errorMessage = "this user is already exsist in database";
			request.setAttribute("message", errorMessage);
			
			String messagePage = "message.jsp";
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(messagePage);
			requestDispatcher.forward(request, response);
		}
		else {
			Users exsistUsers = new Users();
			exsistUsers.setEmail(email);
			exsistUsers.setUserId(userId);
			exsistUsers.setFullName(fullname);
			exsistUsers.setPassword(password);
			productDao.Update(exsistUsers);
			listAll("the user was updated");
		}
		
	}
	
	public void deleteUser() throws ServletException, IOException {
		Integer userId = Integer.parseInt(request.getParameter("id"));
		productDao.delete(userId);
		listAll("the user was deleted");
	}
	
	public void searchBook() {
		String keyword = request.getParameter("keyword");
		request.setAttribute("keyword", keyword);
		
		List<Users> resualt = null;
		
		if(keyword.equals("")) {
			resualt = productDao.listAll();
		}
		else {
			resualt = productDao.SearchBooks(keyword);
		}
		
		request.setAttribute("books", resualt);
			
	}

}
