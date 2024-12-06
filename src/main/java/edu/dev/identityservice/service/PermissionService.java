package edu.dev.identityservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import edu.dev.identityservice.dto.request.PermissionRequest;
import edu.dev.identityservice.dto.response.PermissionResponse;
import edu.dev.identityservice.entity.Permission;
import edu.dev.identityservice.mapper.PermissionMapper;
import edu.dev.identityservice.repository.PermissionRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionService {
	PermissionRepository permissionRepository;
	PermissionMapper permissionMapper;

	public PermissionResponse create(PermissionRequest request) {
		log.info("in method create Permission");
		Permission permission = permissionMapper.toPermission(request);
		permission = permissionRepository.save(permission);
		return permissionMapper.toPermissionResponse(permission);
	}

	public List<PermissionResponse> getAll() {
		var permissions = permissionRepository.findAll();
		return permissions.stream().map(permissionMapper::toPermissionResponse).toList();
	}
	
	public void delete(String permission) {
		permissionRepository.deleteById(permission);
	}
}
