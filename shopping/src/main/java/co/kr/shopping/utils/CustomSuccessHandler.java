package co.kr.shopping.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

@Component
public class CustomSuccessHandler implements AuthenticationSuccessHandler {
	
    private final RequestCache requestCache = new HttpSessionRequestCache();
    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		clearSession(request);
		SavedRequest savedRequest = requestCache.getRequest(request, response);
		String fullprevPage = (String)request.getSession().getAttribute("prevPage");
		if (fullprevPage != null) {
            request.getSession().removeAttribute("prevPage");
        }

		List<String> roleNames = new ArrayList<>();
		authentication.getAuthorities().forEach(authority -> {
			roleNames.add(authority.getAuthority());
		});
		String url = "";
		String prevPage = fullprevPage.substring(22);
		if(savedRequest != null) {
			url = savedRequest.getRedirectUrl();
		}else {
			if(prevPage != null && !prevPage.equals("")) {
				if(prevPage.contains("/member/memberReg")) {
					url = "/";
				}else {
					url = prevPage;
				}
			}else {
				if(roleNames.contains("ROLE_USER")) {
					url = "/";
				}else if(roleNames.contains("ROLE_ADMIN")) {
					url = "admin/adminPage";
				}
				else {
					url = "retire/retirePage";
				}
			}
		}
		redirectStrategy.sendRedirect(request, response, url);
	}

	private void clearSession(HttpServletRequest request) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
		if(session != null) {
			session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
		}
	}		
}
