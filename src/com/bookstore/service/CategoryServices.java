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


import com.bookstore.dao.CategoryDao;
import com.bookstore.entity.Category;



public class CategoryServices {
  
	// INSTANCE VARIABLE;
	private EntityManager entityManager;
	private EntityManagerFactory entityManagerFactory;
	private CategoryDao categoryDao;
	
	// CONSTRUCTORS;
	public CategoryServices() {
		entityManagerFactory = Persistence.createEntityManagerFactory("BookStoreWebsite");
		entityManager = entityManagerFactory.createEntityManager();
		categoryDao =  new CategoryDao(entityManager);
	}
	
	// get list of all Categories;
	public void getListCategories(HttpServletRequest request , HttpServletResponse response , String message) throws ServletException, IOException {
		List<Category> listCategory = categoryDao.listAll();
		// for displaying the data in the JSP file we need to call setArttribute();
		request.setAttribute("listCategory", listCategory);
		// for displaying message in the JSP;
		if(message != null) {
			request.setAttribute("message", message);
		}
		String listCategoryPage = "category_list.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(listCategoryPage);
		requestDispatcher.forward(request, response);
	}
	
	// create categories;
	public void craeteCategory(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {
		// fetch the data from forms in JSP file using getParameter();
		String name = request.getParameter("name");
		
		// invoke the findByName();
		Category categoryByName = categoryDao.findByName(name);
		// if is still exist (found the duplication);
		if(categoryByName != null) {
			
			String message = "could not create this category " + name + " because is alredy exist";
			request.setAttribute("message", message);
			String message2Page = "message.jsp";
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(message2Page);
			requestDispatcher.forward(request, response);
		}
		else {
			Category newCategory = new Category(name);
			categoryDao.Create(newCategory);
			getListCategories(request, response, "the category was created");
		}
	}
	
	public void getCraeteCategory(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {
		String craeteCategory = "category_create.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(craeteCategory);
		requestDispatcher.forward(request, response);
	}
	
	public void editCategory(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		Category category = categoryDao.get(id);
		
		request.setAttribute("id", id);
		request.setAttribute("name", name);
		request.setAttribute("category", category);
		if(category != null) {
		    String editPage = "category_create.jsp";
		    RequestDispatcher requestDispatcher = request.getRequestDispatcher(editPage);
		    requestDispatcher.forward(request, response);
		}	
	}
	
	public void updateCategory(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		
		Category category = categoryDao.get(id);
		Category categoryByName = categoryDao.findByName(name);
		
		request.setAttribute("id", id);
		request.setAttribute("name", name);
		
		// if the name of the category alredyExist and have the same id;
		if(categoryByName != null && category.getCategoryId() !=  categoryByName.getCategoryId()) {
			
			String message = "could not update the category " + name + " because is already exist";
			request.setAttribute("message" , message);
			String messagePage = "message.jsp";
		
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(messagePage);
			requestDispatcher.forward(request, response);
		}
		else {
			Category category2 = new Category(id , name);
			categoryDao.Update(category2);
		}
	}
	
	
	public void deleteCategory(HttpServletRequest request , HttpServletResponse response) {
		Integer id = Integer.parseInt(request.getParameter("id"));
		categoryDao.delete(id);
	}

}
