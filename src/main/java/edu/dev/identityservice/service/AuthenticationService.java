package edu.dev.identityservice.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import edu.dev.identityservice.dto.request.AuthenticationRequest;
import edu.dev.identityservice.exception.AppException;
import edu.dev.identityservice.exception.ErrorCode;
import edu.dev.identityservice.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService {
	UserRepository userRepository;

	public boolean authenticate(AuthenticationRequest request) {
		var user = userRepository.findByUsername(request.getUsername())
				.orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

		return passwordEncoder.matches(request.getPassword(), user.getPassword());
	}
}
