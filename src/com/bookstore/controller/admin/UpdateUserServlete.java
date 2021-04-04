package com.bookstore.controller.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.service.UsersServices;

@WebServlet("/admin/update_user")
public class UpdateUserServlete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private UsersServices usersServices;

    public UpdateUserServlete() {
        super();
        // instantiate the userServices class;
        usersServices = new UsersServices();
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	     usersServices.updateUser(request, response);
	}

}
