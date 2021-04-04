package com.bookstore.controller.frontend;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.service.BookServices;

@WebServlet("/view_book")
public class ViewBookListByCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ViewBookListByCategory() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BookServices bookServices = new BookServices();
		bookServices.listBookByCategory(request, response);
	}

}
