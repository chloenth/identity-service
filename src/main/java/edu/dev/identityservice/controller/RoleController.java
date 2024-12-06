package edu.dev.identityservice.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.dev.identityservice.dto.request.RoleRequest;
import edu.dev.identityservice.dto.response.ApiResponse;
import edu.dev.identityservice.dto.response.RoleResponse;
import edu.dev.identityservice.service.RoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleController {
	RoleService roleService;

	@PostMapping
	ApiResponse<RoleResponse> create(@RequestBody RoleRequest request) {
		return ApiResponse.<RoleResponse>builder().result(roleService.create(request)).build();
	}

	@GetMapping
	ApiResponse<List<RoleResponse>> getAll() {
		return ApiResponse.<List<RoleResponse>>builder().result(roleService.getAll()).build();
	}

	@DeleteMapping("/{role}")
	ApiResponse<Void> delete(@PathVariable String role) {
		roleService.delete(role);
		return ApiResponse.<Void>builder().message("Delete role successfully").build();
	}
}
