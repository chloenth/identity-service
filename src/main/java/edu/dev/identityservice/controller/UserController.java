package edu.dev.identityservice.controller;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.dev.identityservice.dto.request.UserCreationRequest;
import edu.dev.identityservice.dto.request.UserUpdateRequest;
import edu.dev.identityservice.dto.response.ApiResponse;
import edu.dev.identityservice.dto.response.UserResponse;
import edu.dev.identityservice.entity.User;
import edu.dev.identityservice.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/users")
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {

	UserService userService;

	@PostMapping
	ApiResponse<User> createUser(@Valid @RequestBody UserCreationRequest request) {
		ApiResponse<User> apiResponse = new ApiResponse<>();
		apiResponse.setResult(userService.createUser(request));

		return apiResponse;
	}

	@GetMapping
	ApiResponse<List<UserResponse>> getUsers() {
		// log to check who is logging (username & scope)
		var authentication = SecurityContextHolder.getContext().getAuthentication();
		log.info("Username: {}", authentication.getName());
		authentication.getAuthorities().forEach(grantedAuthority -> log.info(grantedAuthority.getAuthority()));

		return ApiResponse.<List<UserResponse>>builder().result(userService.getUsers()).build();
	}

	@GetMapping("/myInfo")
	ApiResponse<UserResponse> getMyInfo() {
		return ApiResponse.<UserResponse>builder().result(userService.getMyInfo()).build();
	}

	@PutMapping("/{userId}")
	UserResponse updateUser(@PathVariable String userId, @RequestBody UserUpdateRequest request) {
		return userService.updateUser(userId, request);
	}

	@DeleteMapping("/{userId}")
	String deleteUser(@PathVariable String userId) {
		userService.deleteUser(userId);
		return "User has been deleted";
	}
}
