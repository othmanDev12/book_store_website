package com.bookstore.controller.frontend;


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



@WebFilter("/*")
public class CustomerLoginFilter implements Filter {
    public CustomerLoginFilter() {
    }
    
    private static final String[] LoginRequiredUrls = {"/view_profile" , "/edit_profile" , "/update_profile" , "/write_review"};

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		// create session;
		HttpSession session = httpRequest.getSession(false);
		
        String path = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());
        // if the URL has /admin/ do filter;
        if (path.startsWith("/admin/")) {
            chain.doFilter(request, response);
            return;
        }
		
        String requiresUrlString = httpRequest.getRequestURI().toString();
		
		Boolean isLogged = session != null && session.getAttribute("loggedcustomer") != null;
		
		if(!isLogged && requiredUrls(requiresUrlString)) {
			// adding a query string in the request URL;
			String queryString = httpRequest.getQueryString();
			String redirectUrl = requiresUrlString;
			if(queryString != null) {
				redirectUrl = redirectUrl.concat("?").concat(queryString);
			}
			
			session.setAttribute("redirectUrl", redirectUrl);
			String loginPage = "/frontend/login.jsp";
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(loginPage);
			requestDispatcher.forward(httpRequest, response);
		}
		
		else {
			chain.doFilter(request, response);
		}
	
		
	}
	
	public boolean requiredUrls(String Urls) {
		for(String authenticationUrl: LoginRequiredUrls) {
			if(Urls.contains(authenticationUrl)) {
				return true;
			}
		}
		return false;
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
