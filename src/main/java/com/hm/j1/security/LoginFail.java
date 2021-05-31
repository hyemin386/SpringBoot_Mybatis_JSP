package com.hm.j1.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

//로그인 실패했을 때 실행하는 객체
@Component
public class LoginFail implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		// TODO Auto-generated method stub
		System.out.println("Login Fail");
		System.out.println(exception.getClass());
		
		String errorClass = exception.getClass().toString().substring(exception.getClass().toString().lastIndexOf(".")+1);
		System.out.println(errorClass);
		
		String message="";
			
		switch (errorClass) {
		case "InternalAuthenticationServiceException" :
			message="Id가 존재하지 않음";
			break;
		case "BadCredentialsException" :
			message="비밀번호가 틀림";
			break;
		case "AuthenticationCredentialsNotFoundException" :
			message="인증이 안됨";
		case "LockedException" :
			message="계정이 잠겼습니다";
			break;
		case "DisabledException" :
			message="휴먼 계정입니다";
			break;
		case "AccountExpiredException" :	
			message="계정의 유효기간이 만료되었습니다";
			break;
		case "CredentialExpiredException" :
			message="휴먼 계정입니다";
			break;
		default:
			message="다시 시도하세요";
		}
		
		request.setAttribute("message", message);
		request.getRequestDispatcher("/member/memberLogin").forward(request, response);
	}
}
