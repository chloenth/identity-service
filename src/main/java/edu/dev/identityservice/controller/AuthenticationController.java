package edu.dev.identityservice.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.dev.identityservice.dto.request.AuthenticationRequest;
import edu.dev.identityservice.dto.response.ApiResponse;
import edu.dev.identityservice.dto.response.AuthenticationResponse;
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
	
	@PostMapping("/log-in")
	ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
		boolean result = authenticationServer.authenticate(request);
		return ApiResponse.<AuthenticationResponse>builder()
				.result(AuthenticationResponse.builder().authenticated(result).build()).build();
	}
}
