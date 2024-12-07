package edu.dev.identityservice.dto.request;

import java.time.LocalDate;
import java.util.List;

import edu.dev.identityservice.validator.DobConstraint;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateRequest {
	String password;
	String firstName;
	String lastName;
	
	@DobConstraint(min = 18, message = "INVALID_DOB")
	LocalDate dob;
	
	List<String> roles;
}
