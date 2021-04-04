package com.bookstore.controller.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.service.ReviewServices;

@WebServlet("/admin/delete_review")
public class DeleteReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DeleteReviewServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ReviewServices reviewServices = new ReviewServices();
		reviewServices.deleteReview(request, response);
	}

}
