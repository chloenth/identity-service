package edu.dev.identityservice.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.dev.identityservice.dto.request.PermissionRequest;
import edu.dev.identityservice.dto.response.ApiResponse;
import edu.dev.identityservice.dto.response.PermissionResponse;
import edu.dev.identityservice.service.PermissionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/permissions")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class PermissionController {
	PermissionService permissionService;

	@PostMapping
	ApiResponse<PermissionResponse> create(@RequestBody PermissionRequest request) {
		return ApiResponse.<PermissionResponse>builder().result(permissionService.create(request)).build();
	}

	@GetMapping
	ApiResponse<List<PermissionResponse>> getAll() {
		return ApiResponse.<List<PermissionResponse>>builder().result(permissionService.getAll()).build();
	}

	@DeleteMapping("/{permission}")
	ApiResponse<Void> delete(@PathVariable String permission) {
		permissionService.delete(permission);
		return ApiResponse.<Void>builder().message("Delete permission successfully").build();
	}
}
