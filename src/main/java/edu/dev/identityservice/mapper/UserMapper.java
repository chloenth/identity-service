package edu.dev.identityservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import edu.dev.identityservice.dto.request.UserCreationRequest;
import edu.dev.identityservice.dto.request.UserUpdateRequest;
import edu.dev.identityservice.dto.response.UserResponse;
import edu.dev.identityservice.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
	@Mapping(target = "id", ignore = true)
	User toUser(UserCreationRequest request);
	
	UserResponse toUserResponse(User user);
	
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "username", ignore = true)
	void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
