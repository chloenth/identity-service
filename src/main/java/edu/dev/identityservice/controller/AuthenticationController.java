package edu.dev.identityservice.controller;

import java.text.ParseException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nimbusds.jose.JOSEException;

import edu.dev.identityservice.dto.request.AuthenticationRequest;
import edu.dev.identityservice.dto.request.IntrospectRequest;
import edu.dev.identityservice.dto.response.ApiResponse;
import edu.dev.identityservice.dto.response.AuthenticationResponse;
import edu.dev.identityservice.dto.response.IntrospectResponse;
import edu.dev.identityservice.service.AuthenticationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {

	AuthenticationService authenticationServer;

	@PostMapping("/token")
	ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
		var result = authenticationServer.authenticate(request);
		return ApiResponse.<AuthenticationResponse>builder().result(result).build();
	}
	
	@PostMapping("/introspect")
	ApiResponse<IntrospectResponse> authenticate(@RequestBody IntrospectRequest request)
			throws JOSEException, ParseException {
		var result = authenticationServer.introspect(request);
		return ApiResponse.<IntrospectResponse>builder().result(result).build();
	}
}
