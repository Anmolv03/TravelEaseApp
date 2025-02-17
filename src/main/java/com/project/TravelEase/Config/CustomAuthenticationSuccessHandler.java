package com.project.TravelEase.Config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.project.TravelEase.model.Role;
import com.project.TravelEase.model.User;
import com.project.TravelEase.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	
	@Autowired
	private  UserService userService;
	


	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
	
		String userName=authentication.getName();
		HttpSession session = request.getSession();
		User user=userService.getUserByEmail(userName);
		session.setAttribute("user", user);
		if(user.getRole()==Role.ADMIN) {
			response.sendRedirect("/users/adminDashboard");
		}
		else {
			response.sendRedirect("/users/home");
		}
		
	}

}
