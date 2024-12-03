package edu.dev.identityservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.dev.identityservice.dto.request.UserCreationRequest;
import edu.dev.identityservice.dto.request.UserUpdateRequest;
import edu.dev.identityservice.entity.User;
import edu.dev.identityservice.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	public User createUser(UserCreationRequest request) {
		if (userRepository.existsByUsername(request.getUsername())) {
			throw new RuntimeException("User already existed");
		}

		User user = new User();

		user.setUsername(request.getUsername());
		user.setPassword(request.getPassword());
		user.setFirstName(request.getFirstName());
		user.setLastName(request.getLastName());
		user.setDob(request.getDob());

		return userRepository.save(user);
	}

	public User updateUser(String userId, UserUpdateRequest request) {
		User user = getUser(userId);

		user.setPassword(request.getPassword());
		user.setFirstName(request.getFirstName());
		user.setLastName(request.getLastName());
		user.setDob(request.getDob());

		return userRepository.save(user);
	}

	public void deleteUser(String userId) {
		userRepository.deleteById(userId);
	}

	public List<User> getUsers() {
		return userRepository.findAll();
	}

	public User getUser(String id) {
		return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
	}

}
