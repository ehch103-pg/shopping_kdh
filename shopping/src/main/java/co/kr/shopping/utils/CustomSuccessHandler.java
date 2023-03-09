package co.kr.shopping.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class CustomSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// TODO Auto-generated method stub
		List<String> roleNames = new ArrayList<>();
		authentication.getAuthorities().forEach(authority -> {
			roleNames.add(authority.getAuthority());
		});
		
		if(roleNames.contains("ROLE_USER")) {
			response.sendRedirect("/");
			return;
		}else if(roleNames.contains("ROLE_ADMIN")) {
			response.sendRedirect("admin/adminPage");
			return;
		}
		else {
			response.sendRedirect("retire/retirePage");
			return;
		}
	}		
}
