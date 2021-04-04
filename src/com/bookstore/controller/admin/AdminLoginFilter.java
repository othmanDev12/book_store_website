package com.bookstore.controller.admin;


import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@WebFilter("/admin/*")
public class AdminLoginFilter implements Filter { 

    public AdminLoginFilter() {
    }
    
	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession(false);
		// if the user is already login in;
		Boolean isLoggedIn = session != null && session.getAttribute("email") != null;
		
		String loginURI = httpRequest.getContextPath() + "/admin/login";
		// to allow the user to forward the destination;
		Boolean loginRequest = httpRequest.getRequestURI().equals(loginURI);
		Boolean isLoginIn = httpRequest.getRequestURI().endsWith("login.jsp");
		
		if(isLoggedIn && (loginRequest || isLoginIn)) {
			// the ADMIN is already logged in and he's trying to login again
            // then forwards to the admin's home page
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/admin/admin.jsp");
			requestDispatcher.forward(httpRequest, response);
		}
		else if(isLoggedIn || loginRequest) {
			System.out.println("is login");
			chain.doFilter(request, response);
		}
		else {
			System.out.println("not log in");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/admin/login.jsp");
			requestDispatcher.forward(httpRequest, response);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
