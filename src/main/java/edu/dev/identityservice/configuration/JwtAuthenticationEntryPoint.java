package edu.dev.identityservice.configuration;

import java.io.IOException;

import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.dev.identityservice.dto.response.ApiResponse;
import edu.dev.identityservice.exception.ErrorCode;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		ErrorCode errorCode = ErrorCode.UNAUTHENTICATED;
		
		System.out.println("JwtAuthenticationEntryPoint invoked");
		
		response.setStatus(errorCode.getStatusCode().value());
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);

		ApiResponse<?> apiResponse = ApiResponse.builder().code(errorCode.getCode()).message(errorCode.getMessage())
				.build();

		ObjectMapper objectMapper = new ObjectMapper();

		response.getWriter().write(objectMapper.writeValueAsString(apiResponse));
		response.flushBuffer();
	}

}