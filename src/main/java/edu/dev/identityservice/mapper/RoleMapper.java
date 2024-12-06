package edu.dev.identityservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import edu.dev.identityservice.dto.request.RoleRequest;
import edu.dev.identityservice.dto.response.RoleResponse;
import edu.dev.identityservice.entity.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper {
	
	@Mapping(target="permissions", ignore = true)
	Role toRole(RoleRequest request);
	
	RoleResponse toRoleResponse(Role role);
}
