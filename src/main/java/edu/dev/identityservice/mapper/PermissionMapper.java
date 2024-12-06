package edu.dev.identityservice.mapper;

import org.mapstruct.Mapper;

import edu.dev.identityservice.dto.request.PermissionRequest;
import edu.dev.identityservice.dto.response.PermissionResponse;
import edu.dev.identityservice.entity.Permission;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
	
	Permission toPermission(PermissionRequest request);
	PermissionResponse toPermissionResponse(Permission permission);
}
