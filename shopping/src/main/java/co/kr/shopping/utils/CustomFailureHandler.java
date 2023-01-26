package co.kr.shopping.utils;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		String errorMsg;
		if(exception instanceof BadCredentialsException) {
			errorMsg = "아이디 또는 비밀번호가 맞지 않습니다. 확인해주시기 바랍니다";
		}else if(exception instanceof InternalAuthenticationServiceException) {
			errorMsg = "내부적인 문제로 인해 요청을 처리할 수 없습니다. 관리자에게 문의 바랍니다";
		}else if(exception instanceof UsernameNotFoundException) {
			errorMsg = "계정이 존재하지 않습니다. 회원가입을 시도한 후에 다시 해주시기 바랍니다";
		}else if(exception instanceof AuthenticationCredentialsNotFoundException) {
			errorMsg = "인증 요청이 거부되었습니다. 관리자에게 문의 바랍니다";
		} else {
			errorMsg = "알 수 없는 이유로 로그인 실패하였습니다 관리자에게 문의 바랍니다";
		}
		setDefaultFailureUrl("/login");
		
		super.onAuthenticationFailure(request, response, exception);
	}
}
