package com.bookstore.controller.admin;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.service.UsersServices;



@WebServlet("/admin/create_user")
public class CreateUsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    UsersServices usersServices = new UsersServices();
	    usersServices.createUser(request, response);
	}
	
	protected void doGet(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {
		UsersServices usersServices = new UsersServices();
		usersServices.getCreateUser(request, response);
	}

}
