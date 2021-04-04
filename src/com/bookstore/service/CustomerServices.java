package com.bookstore.service;

import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

import org.junit.experimental.categories.Categories;

import com.bookstore.dao.BookDao;
import com.bookstore.dao.CategoryDao;
import com.bookstore.dao.CustomerDao;
import com.bookstore.entity.Book;
import com.bookstore.entity.Category;
import com.bookstore.entity.Customer;


public class CustomerServices {
	// instance variables;
	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	private CustomerDao customerDao;
	private CategoryDao categoryDao;
    private BookDao bookDao;
	// CONSTRUCTOR;
	public CustomerServices() {
		entityManagerFactory = Persistence.createEntityManagerFactory("BookStoreWebsite");
		entityManager = entityManagerFactory.createEntityManager();
		customerDao = new CustomerDao(entityManager);
		categoryDao = new CategoryDao(entityManager);
		bookDao = new BookDao(entityManager);
	}

	// Business logic for all operators;
	public void listAll(HttpServletRequest request, HttpServletResponse response, String message)
			throws ServletException, IOException {
		List<Customer> customers = customerDao.listAll();
		// send a request to display the data in the client side;
		request.setAttribute("customers", customers);
		String listCustomerPage = "customer_list.jsp";

		// send a request to the display a success message;
		if (message != null) {
			request.setAttribute("message", message);
		}
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(listCustomerPage);
		requestDispatcher.forward(request, response);
	}
	
	@SuppressWarnings({ "unused", "unlikely-arg-type" })
	private void customerComponenets(Customer customer , HttpServletRequest request , HttpServletResponse response) {
		String email = request.getParameter("email");
		String fullname = request.getParameter("fullname");
		String password = request.getParameter("password");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String city = request.getParameter("city");
		String zipcode = request.getParameter("zipcode");
		String country = request.getParameter("country");
		if(customer != null && !customer.equals("")) {
			customer.setEmail(email);
		}
		customer.setFullName(fullname);
		if(customer != null && !customer.equals("")) {
			customer.setPassword(password);
		}
		customer.setPhone(phone);
		customer.setAddress(address);
		customer.setCity(city);
		customer.setZipcode(zipcode);
		customer.setCountry(country);
		customer.setRegisterDate(new Date());
		

	}

	public void createCustomer(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {
		// fetch the data entered by the user in the input form;
		String email = request.getParameter("email");
		Customer existCustomer = customerDao.findCustomerByEmail(email);
		
		 // we display a message error if we persist the same email in the database
		if(existCustomer != null) {
			String message = "could not create a new customer with the email " + email + "   because is already exist";
			request.setAttribute("message", message);
			
			String messagePage = "message.jsp";
			RequestDispatcher requestDispatcher =request.getRequestDispatcher(messagePage);
			requestDispatcher.forward(request, response);
		}
		else {
			// create new Instance of customer object; 
			Customer customer = new Customer();
			customerComponenets(customer, request, response);
			// persist the customer instance;
			customerDao.Create(customer);
			listAll(request, response, "the customer was created");
		}
		
	}
		
		public void registerCustomer(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {
			// fetch the data entered by the user in the input form;
			String email = request.getParameter("email");
			Customer existCustomer = customerDao.findCustomerByEmail(email);
			
			 // we display a message error if we persist the same email in the database
			String message = "";
			if(existCustomer != null) {
			    message = "could not register customer with the email " + email + "   because is already exist";
				request.setAttribute("message", message);

			}
			else {
				// create new Instance of customer object;
				Customer newCustomer = new Customer();
				customerComponenets(newCustomer, request, response);
				// persist the customer instance;
				customerDao.Create(newCustomer);
				message = "the customer was registred successfully! thank you.";
				request.setAttribute("message", message);
				
			}
			
			String messagePage = "/frontend/message.jsp";
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(messagePage);
			requestDispatcher.forward(request, response);
	}

	public void getCreateCustomer(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String createCustomerPage = "customer_create.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(createCustomerPage);
		requestDispatcher.forward(request, response);
	}
	
	public void editCustomer(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {
		Integer customerId = Integer.parseInt(request.getParameter("id"));
		Customer getCustomer = customerDao.get(customerId);
		
		request.setAttribute("customer", getCustomer);
		
		String editCase = "customer_create.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(editCase);
		requestDispatcher.forward(request, response);
	}
	
	public void updateCustomer(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException
	{
		// fetch the data entered by the user in the input form;
		Integer id = Integer.parseInt(request.getParameter("id"));
		String email = request.getParameter("email");;
		
		Customer getCustomer = customerDao.get(id);
		Customer getCustomerByEmail = customerDao.findCustomerByEmail(email);
		
		if(getCustomerByEmail != null && getCustomer.getCustomerId() != getCustomerByEmail.getCustomerId()) {
			String message = "Could not update this email " + email + "because id already exist";
			request.setAttribute("message", message);
			
			String messagePage = "message.jsp";
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(messagePage);
			requestDispatcher.forward(request, response);
		}
		
		else {
			// create new Instance of customer object;
			Customer updateCustomer = new Customer();

			customerComponenets(updateCustomer, request, response);
			customerDao.Update(updateCustomer);
			listAll(request, response, "the customer was updated");
			
		}	
	}
	
	
	public void deleteCustomer(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {
		Integer customerId = Integer.parseInt(request.getParameter("id"));
		
		customerDao.delete(customerId);
		listAll(request, response, "the customer was deleted");
	}
	
	public void showLogin(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {
		List<Category> categories = categoryDao.listAll();
		request.setAttribute("categories", categories);
		String loginPage = "/frontend/login.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(loginPage);
		requestDispatcher.forward(request, response);
	}
	
	public void doLogin(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
	
		Customer loggedcustomer = customerDao.checkLogin(email, password);
		List<Category> categories = categoryDao.listAll();
		request.getSession().setAttribute("categories", categories);
		
		if(loggedcustomer == null) {
			
			String message = "message failed! please try again";
			request.setAttribute("message", message);
			showLogin(request, response);
		}
		else {
			HttpSession session = request.getSession();
			session.setAttribute("loggedcustomer", loggedcustomer);
			
			Object redirectUrlObject = session.getAttribute("redirectUrl");
			if(redirectUrlObject != null) {
				String redirectUrl = (String) redirectUrlObject;
				session.removeAttribute("redirectUrl");
				response.sendRedirect(redirectUrl);
			}
			else {
				showProfile(request, response);
			}
			
		}
	}
	
	public void doLogout(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {
		HttpSession httpSession = request.getSession();
		httpSession.removeAttribute("loggedcustomer");
		List<Book> newBooks = bookDao.listNewBook();
		request.setAttribute("newBooks", newBooks);
		List<Category> categories = categoryDao.listAll();
		request.setAttribute("categories", categories);
		
		String indexPage = "/frontend/index.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(indexPage);
		requestDispatcher.forward(request, response);
	}
	
	public void showProfile(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {
		List<Category> categories = categoryDao.listAll();
		request.setAttribute("categories", categories);
		String customerProfile = "/frontend/customer_profile.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(customerProfile);
		requestDispatcher.forward(request, response);
	}
	
	public void editProfile(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {
		List<Category> categories = categoryDao.listAll();
		request.setAttribute("categories", categories);
		String customerProfile = "/frontend/profile_edit.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(customerProfile);
		requestDispatcher.forward(request, response);
	}
	
	public void updateProfile(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {
		Customer customer = (Customer) request.getSession().getAttribute("loggedcustomer");
		customerComponenets(customer, request, response);
		customerDao.Update(customer);
		showProfile(request, response);
	}
	
	
}
