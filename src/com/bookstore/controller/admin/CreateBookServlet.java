package com.bookstore.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.service.BookServices;


@WebServlet("/admin/create_book")
//post request with file is done with MULTI PART request;
@MultipartConfig(
		fileSizeThreshold = 1024 * 10, // 10 kilobyte;
		maxFileSize = 1024 * 300, // 300 kilobyte;
		maxRequestSize = 1024 * 1024 // 1 Megabyte
)
public class CreateBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CreateBookServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BookServices bookServices = new BookServices();
		bookServices.getCreateBook(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BookServices bookServices = new BookServices();
		bookServices.createBook(request, response);
	}

}
