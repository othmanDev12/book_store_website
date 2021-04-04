package com.bookstore.service;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


import com.bookstore.dao.BookDao;
import com.bookstore.dao.CategoryDao;
import com.bookstore.entity.Book;
import com.bookstore.entity.Category;



public class BookServices {

	private EntityManager entityManager;
	private EntityManagerFactory entityManagerFactory;
	private BookDao bookDao;
	private CategoryDao categoryDao;
	
	public BookServices() {
		entityManagerFactory = Persistence.createEntityManagerFactory("BookStoreWebsite");
		entityManager = entityManagerFactory.createEntityManager();
		bookDao = new BookDao(entityManager);
		categoryDao = new CategoryDao(entityManager);
	}
	
	
	// business logic;
	public void listAll(HttpServletRequest request , HttpServletResponse response , String message) throws ServletException, IOException {
		List<Book> bookList = bookDao.listAll();
		request.setAttribute("bookList", bookList);
		
		if(message != null) {
			request.setAttribute("message", message);
		}
		
		// invoke JSP file in the SERVLET;
		String bookListPage = "book_list.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(bookListPage);
		requestDispatcher.forward(request, response);
	}
	
	public void createBook(HttpServletRequest request , HttpServletResponse response) throws IOException, ServletException  {
		// get data that user entered  from inputs in JSP file;
		Integer categoryId = Integer.parseInt(request.getParameter("category"));
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String description = request.getParameter("description");
		String isbn = request.getParameter("isbn");
		Float price = Float.parseFloat(request.getParameter("price"));
		// convert date into string;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date date = null;
		try {
			 date = simpleDateFormat.parse(request.getParameter("publish"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Error("you must type this format MM/dd/yyyy");
		}
		
		Date lastDate = new Date();
		
		// create Part object;
		Part part = request.getPart("image");
		
		// check if the title was duplicated;
		Book getBookByTitle = bookDao.findByTitle(title);
		if(getBookByTitle != null) {
			String message = "could not create a book with this title " + title + " because is already exist";
			request.setAttribute("message", message);
			
		    String createBookPage = "message.jsp";
		    RequestDispatcher requestDispatcher = request.getRequestDispatcher(createBookPage);
		    requestDispatcher.forward(request, response);
			
		}else {
			// create new book object;
			Book book = new Book();
			Category category = categoryDao.get(categoryId);
			book.setCategory(category);
			book.setTitle(title);
			book.setAuthor(author);
			book.setDescription(description);
			book.setIsbn(isbn);
			book.setPublishDate(date);
			book.setPrice(price);
			book.setLastUpdate(lastDate);
			
			
			// check if the part was not null and have some data;
			if(part != null && part.getSize() > 0) {
				// determine the size of the part;
				long size = part.getSize();
				byte[] imagebyte = new byte[(int) size];
				
				// read the byte data type;
				InputStream inputStream = part.getInputStream();
				inputStream.read(imagebyte);
				inputStream.close();
				
				book.setImage(imagebyte);
				
			}

		    bookDao.Create(book);
		    listAll(request, response, "the book was created");
		}
		

 	}
	
	public void getCreateBook(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {
		List<Category> categoryList = categoryDao.listAll();
		request.setAttribute("categoryList", categoryList);
	    String createBookPage = "book_create.jsp";
	    RequestDispatcher requestDispatcher = request.getRequestDispatcher(createBookPage);
	    requestDispatcher.forward(request, response);
	}
	
	
	public void editBook(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {
		Integer bookId = Integer.parseInt(request.getParameter("id"));
		
		Book book = bookDao.get(bookId);
		request.setAttribute("book", book);
		
		List<Category> categories = categoryDao.listAll();
		request.setAttribute("categories", categories);
		
		String editPage = "book_create.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(editPage);
		requestDispatcher.forward(request, response);
	}
	
	public void updateBook(HttpServletRequest request , HttpServletResponse response) throws IOException, ServletException {
		// fetch data from the client (browser);
		Integer bookId = Integer.parseInt(request.getParameter("id"));
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String description = request.getParameter("description");
		String isbn = request.getParameter("isbn");
		Float price = Float.parseFloat(request.getParameter("price"));
		Integer categoryId = Integer.parseInt(request.getParameter("category"));
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyy");
		Date date = null;
		try {
		     date = simpleDateFormat.parse(request.getParameter("publish"));
		} catch (ParseException e) {
			e.printStackTrace();
			throw new Error("you must type this format MM/dd/yyyy");
		}
		
		// create Part object;
		Part part = request.getPart("image");
		
		Book fetchBookByTitle = bookDao.findByTitle(title);
		Book getBook = bookDao.get(bookId);
		
		if(fetchBookByTitle != null && fetchBookByTitle.getBookId() != getBook.getBookId()) {
			String errorMessage = "could not update this book with title " + title + " because is already exist";
			request.setAttribute("message", errorMessage);
			
			String messagePage = "message.jsp";
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(messagePage);
			requestDispatcher.forward(request, response);
		}
		else {
			Book updateBook = new Book();
			// get the category object by ID;
			Category category = categoryDao.get(categoryId);
			updateBook.setCategory(category);
			updateBook.setTitle(title);
			updateBook.setAuthor(author);
			updateBook.setDescription(description);
			updateBook.setPublishDate(date);
			Date lastDate = new Date();
			updateBook.setLastUpdate(lastDate);
			updateBook.setPublishDate(date);
			updateBook.setIsbn(isbn);
			updateBook.setPrice(price);
			updateBook.setBookId(bookId);
			
			// check if the part is not and has some data;
			if(part != null && part.getSize() > 0) {
				// determine the size of the part;
				long size = part.getSize();
				byte[] imageByte = new byte[(int) size];
				// read the part;
				InputStream inputStream = part.getInputStream();
				inputStream.read(imageByte);
				inputStream.close();
				
				updateBook.setImage(imageByte);
				
			}
			
			// update the boob object by invoking the book DAO class;
			 bookDao.Update(updateBook);
			 listAll(request, response, "the book was updated");
		}
		
		
	}
	
	
	public void deleteBook(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {
		Integer integer = Integer.parseInt(request.getParameter("id"));
		bookDao.delete(integer);
		listAll(request, response, "the book was deleted");
	}
	
	public void listBookByCategory(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {
		Integer categoryId = 	Integer.parseInt(request.getParameter("id"));
		
		List<Book> listBooks = bookDao.listByCategory(categoryId);
		// send a request to display list of books;
		request.setAttribute("listBooks", listBooks);
		// Retrieve category by Id;
		Category category = categoryDao.get(categoryId);
		request.setAttribute("category", category);
		
		String listBookPage = "/frontend/list_book_by_category.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(listBookPage);
		requestDispatcher.forward(request, response);
	}
	
	public void getBook(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		Book book = bookDao.get(id);
		request.setAttribute("book", book);
		
		List<Category> categories = categoryDao.listAll();
		request.setAttribute("categories", categories);
		String detaitBookPage = "/frontend/detail_book.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(detaitBookPage);
		requestDispatcher.forward(request, response);
		
	}
	
	public void searchBook(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {
	   String keyword = request.getParameter("keyword");
	   request.setAttribute("keyword", keyword);
	   List<Book> resualt = null;
	   
	   
	
	   if(keyword.equals("")) {
		   resualt = bookDao.listAll();
	   }
	   else {
		   resualt = bookDao.searchBooks(keyword);
	   }
	   
	   request.setAttribute("resualt", resualt);
	   
	   String searchPage = "/frontend/search_result.jsp";
	   RequestDispatcher requestDispatcher = request.getRequestDispatcher(searchPage);
	   requestDispatcher.forward(request, response);
	}

}
