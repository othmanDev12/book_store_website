package com.bookstore.service;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bookstore.dao.UsersDao;
import com.bookstore.entity.Users;

public class UsersServices {
	private UsersDao usersDao;
	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;

	// CONSTRUCTOR();
	public UsersServices() {
		entityManagerFactory = Persistence.createEntityManagerFactory("BookStoreWebsite");
		entityManager = entityManagerFactory.createEntityManager();
		usersDao = new UsersDao(entityManager);

	}

	// all the Business logic in users service;
	public void listUser(HttpServletRequest request, HttpServletResponse response, String message)
			throws ServletException, IOException {
		// invoke or deploy the JSP file into SERVLET we use request dispatcher
		String listUserPage = "user_list.jsp";

		List<Users> usersList = usersDao.listAll();
		request.setAttribute("usersList", usersList);
		if (message != null) {
			request.setAttribute("message", message);
		}

		RequestDispatcher requestDispatcher = request.getRequestDispatcher(listUserPage);
		requestDispatcher.forward(request, response);
	}

	public void createUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// to fetch the HTTP request from the client we need to use getParameter() with
		// the name of parameter;
		String email = request.getParameter("email");
		String fullname = request.getParameter("fullname");
		String password = request.getParameter("password");

		Users emailUsers = usersDao.findByEmail(email);

		if (emailUsers != null) {
			// to display the message in the JSP file with JSTL;
			String message = "could not create the user with the email " + email + " because is already exsist";
			request.setAttribute("message", message);
			
			String messagePage = "message.jsp";
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(messagePage);
			requestDispatcher.forward(request, response);
		}

		else {
			// create new User;
			Users newUser = new Users(email, password, fullname);
			usersDao.Create(newUser);
			listUser(request, response, "the user was created");
		}
	}

	public void getCreateUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String createUserPage = "user_create.jsp";

		RequestDispatcher requestDispatcher = request.getRequestDispatcher(createUserPage);
		requestDispatcher.forward(request, response);
	}

	public void editUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// get the ID of each user;
		Integer userId = Integer.parseInt(request.getParameter("id"));
		// invoke the get method in the userDao class;
		Users user = usersDao.get(userId);

		// fetch or display the data into the JSP file;
		request.setAttribute("user", user);

		String editPage = "user_create.jsp";
		// deploy or invoke a JSP file in the SERVLET;
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(editPage);
		requestDispatcher.forward(request, response);
	}
	
	public void updateUser(HttpServletRequest request , HttpServletResponse response) throws ServletException , IOException {
		// retrieve data from the forms in the JSP file;
	    Integer userId = Integer.parseInt(request.getParameter("userId"));
		String email = request.getParameter("email");
		String fullname = request.getParameter("fullname");
		String password = request.getParameter("password");
		
		Users user = usersDao.get(userId);
		
		Users getByEmail = usersDao.findByEmail(email);
		
		if(getByEmail != null && getByEmail.getUserId() != user.getUserId()) {
			String message = "could not update user wih the email " + email + " because is already exist";
			request.setAttribute("message", message);
			String messagePage = "message.jsp";
			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(messagePage);
			requestDispatcher.forward(request, response);
		}
		
		else {
			Users newUser = new Users();
			newUser.setEmail(email);
			newUser.setFullName(fullname);
			newUser.setPassword(password);
			newUser.setUserId(userId);
			usersDao.Update(newUser);
			listUser(request, response, "the user was updated");
			
		}
		
	}
	

	public void deleteUser(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {
		Integer userId = Integer.parseInt(request.getParameter("id"));
		usersDao.delete(userId);
	}
	
	
	public void login(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {
		// fetch the data entered in JSP file;
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		Boolean loginResualt = usersDao.checkLogin(email, password);
		
		// if the login was authenticated then return true
		if(loginResualt) {
			request.getSession().setAttribute("email", email);
			
			String adminPage = "admin.jsp";
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(adminPage);
		    requestDispatcher.forward(request, response);
		}
		else {
			String messagePage = "login.jsp";
			String message = "login faild";
			if(message != null) {
				request.setAttribute("message", message);
			}
			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(messagePage);
			requestDispatcher.forward(request, response);
			
		}
		
		
	}
	
	public void getLogin(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {
		String loginPage = "login.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(loginPage);
	    requestDispatcher.forward(request, response);
	}
	
	public void logout(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException{
		// remove the session request;
		HttpSession session = request.getSession();
		session.removeAttribute("email");
		String loginPage = "login.jsp";
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(loginPage);
		requestDispatcher.forward(request, response);
		
	}

	
	
}