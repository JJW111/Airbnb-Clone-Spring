package com.clone.airbnb.security;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class AdminAuthenticationFailureHandler implements AuthenticationFailureHandler {

  	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
  		request.setAttribute("username", request.getParameter("username"));
  		request.setAttribute("error", exception.getMessage());
  		request.getRequestDispatcher("/admin_session_fail").forward(request, response);
	}

}
