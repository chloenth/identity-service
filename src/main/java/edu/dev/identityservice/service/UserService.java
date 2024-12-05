package edu.dev.identityservice.service;

import java.util.HashSet;
import java.util.List;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import edu.dev.identityservice.dto.request.UserCreationRequest;
import edu.dev.identityservice.dto.request.UserUpdateRequest;
import edu.dev.identityservice.dto.response.UserResponse;
import edu.dev.identityservice.entity.User;
import edu.dev.identityservice.enums.Role;
import edu.dev.identityservice.exception.AppException;
import edu.dev.identityservice.exception.ErrorCode;
import edu.dev.identityservice.mapper.UserMapper;
import edu.dev.identityservice.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {

	UserRepository userRepository;
	UserMapper userMapper;
	PasswordEncoder passwordEncoder;

	public User createUser(UserCreationRequest request) {
		if (userRepository.existsByUsername(request.getUsername())) {
			throw new AppException(ErrorCode.USER_EXISTED);
		}

		User user = userMapper.toUser(request);
		user.setPassword(passwordEncoder.encode(request.getPassword()));

		HashSet<String> roles = new HashSet<>();
		roles.add(Role.USER.name());

		user.setRoles(roles);

		return userRepository.save(user);
	}

	public UserResponse getMyInfo() {
		var context = SecurityContextHolder.getContext();
		String name = context.getAuthentication().getName();

		User user = userRepository.findByUsername(name).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
		return userMapper.toUserResponse(user);
	}

	public UserResponse updateUser(String userId, UserUpdateRequest request) {
		User user = userRepository.findById(userId).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

		userMapper.updateUser(user, request);
		User updatedUser = userRepository.save(user);

		return userMapper.toUserResponse(updatedUser);
	}

	public void deleteUser(String userId) {
		userRepository.deleteById(userId);
	}

	@PreAuthorize("hasRole('ADMIN')")
	public List<UserResponse> getUsers() {
		log.info("in method get Users");
		return userRepository.findAll().stream().map(user -> userMapper.toUserResponse(user)).toList();
	}

	@PostAuthorize("returnObject.username == authentication.name")
	public UserResponse getUser(String id) {
		log.info("in method get User");
		User user = userRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
		return userMapper.toUserResponse(user);
	}

}
